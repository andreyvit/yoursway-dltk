/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.core.codeassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.ScriptSelectionEngine;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.codeassist.select.SelectionNodeFound;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.extensions.ISelectionExtension;
import org.eclipse.dltk.tcl.internal.core.TclExtensionManager;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclResolver.IResolveElementParent;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnAST;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnNode;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnVariable;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclField;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclProc;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclSelectionEngine extends ScriptSelectionEngine {
	public static boolean DEBUG = DLTKCore.DEBUG_SELECTION;

	protected int actualSelectionStart;

	protected int actualSelectionEnd;

	protected List selectionElements = new ArrayList();

	protected TclSelectionParser parser = new TclSelectionParser();

	protected org.eclipse.dltk.core.ISourceModule sourceModule;

	protected IDLTKLanguageToolkit toolkit;
	
	protected ISelectionExtension[] extensions;

	public TclSelectionEngine() {
		this.toolkit = TclLanguageToolkit.getDefault();
		this.extensions = TclExtensionManager.getDefault().getSelectionExtensions();
	}

	public IModelElement[] select(ISourceModule sourceUnit,
			int selectionSourceStart, int selectionSourceEnd) {
		sourceModule = (org.eclipse.dltk.core.ISourceModule) sourceUnit
				.getModelElement();
		String content = sourceUnit.getSourceContents();

		if (DEBUG) {
			System.out.print("SELECTION IN "); //$NON-NLS-1$
			System.out.print(sourceUnit.getFileName());
			System.out.print(" FROM "); //$NON-NLS-1$
			System.out.print(selectionSourceStart);
			System.out.print(" TO "); //$NON-NLS-1$
			System.out.println(selectionSourceEnd);
			System.out.println("SELECTION - Source :"); //$NON-NLS-1$
			System.out.println(content);
		}

		if (!checkSelection(content, selectionSourceStart, selectionSourceEnd)) {
			return new IModelElement[0];
		}

		if (DEBUG) {
			System.out.print("SELECTION - Checked : \""); //$NON-NLS-1$
			System.out.print(content.substring(actualSelectionStart,
					actualSelectionEnd));
			System.out.println('"');
		}

		try {
			ModuleDeclaration parsedUnit = (ModuleDeclaration) this.parser
					.parse(sourceUnit);
			if (parsedUnit != null) {
				try {
					this.lookupEnvironment.buildTypeScope(parsedUnit, null);
					if ((this.unitScope = parsedUnit.scope) != null) {
						parseBlockStatements(parsedUnit,
								this.actualSelectionStart);
						if (DEBUG) {
							System.out.println("COMPLETION - AST :"); //$NON-NLS-1$
							System.out.println(parsedUnit.toString());
						}
						// parsedUnit.resolve();
					}
				} catch (SelectionNodeFound e) {
					// completionNodeFound = true;
					if (e.getNode() != null) {
						if (DEBUG) {
							System.out.print("COMPLETION - Completion node : "); //$NON-NLS-1$
							System.out.println(e.getNode().toString());
							if (this.parser.getAssistNodeParent() != null) {
								System.out.print("COMPLETION - Parent Node : "); //$NON-NLS-1$
								System.out.println(this.parser
										.getAssistNodeParent());
							}
						}
						// if null then we found a problem in the completion
						// node
						select(e.getNode(), this.parser.getAssistNodeParent());
					}
				}
			}
		} catch (IndexOutOfBoundsException e) { // work-around internal failure
			// - 1GEMF6D
			if (DEBUG) {
				System.out.println("Exception caught by SelectionEngine:"); //$NON-NLS-1$
				e.printStackTrace(System.out);
			}
		}

		return (IModelElement[]) selectionElements
				.toArray(new IModelElement[selectionElements.size()]);
	}

	protected void select(ASTNode astNode, ASTNode astNodeParent) {
		if (astNode instanceof SelectionOnKeywordOrFunction) {
			SelectionOnKeywordOrFunction key = (SelectionOnKeywordOrFunction) astNode;
			// findKeywords(key.getToken(), key.getPossibleKeywords(),
			// key.canCompleteEmptyToken());
			/*
			 * TODO: Add search for functions. Variables start with $ so it will
			 * not be here... To all functions are possible. Functions with
			 * ::will not be here.
			 * 
			 */
			String name = key.getName();
			if (name != null) {
				findLocalFunctions(name, astNodeParent);
				if (this.selectionElements.size() > 0) {
					return;
				}
				findMethodFromMixin(name, astNodeParent);
				// findMethodFromSearch(name);
				if (this.selectionElements.size() > 0) {
					return;
				}
				String fqnName = null;
				if (astNodeParent instanceof TypeDeclaration) {
					TypeDeclaration t = (TypeDeclaration) astNodeParent;
					fqnName = t.getEnclosingTypeName() + "::" + t.getName()
							+ "::" + name;
				} else if (astNodeParent instanceof MethodDeclaration) {
					MethodDeclaration t = (MethodDeclaration) astNodeParent;
					fqnName = t.getDeclaringTypeName() + "::" + name;
				}
				if (fqnName != null) {
					if (!fqnName.startsWith("::"))
						fqnName = "::" + fqnName;
					findMethodFromMixin(name, astNodeParent);
					// findMethodFromSearch(fqnName);
				}
			}
			for (int i = 0; i < this.extensions.length; i++) {
				this.extensions[i].selectionOnKeywordOrFunction(key, this);
			}
		} else if (astNode instanceof SelectionOnVariable) {
			SelectionOnVariable completion = (SelectionOnVariable) astNode;
			findVariables(completion.getName(), astNodeParent, astNode
					.sourceStart());
		} else if (astNode instanceof SelectionOnAST) {
			ASTNode node = ((SelectionOnAST) astNode).getNode();
			for (int i = 0; i < this.extensions.length; i++) {
				this.extensions[i].selectionOnAST(node, this);
			}
			addElementFromASTNode(node);
		} else if (astNode instanceof SelectionOnNode) {
			ASTNode node = ((SelectionOnNode) astNode).getNode();
			int position = ((SelectionOnNode) astNode).getPosition();
			for (int i = 0; i < this.extensions.length; i++) {
				this.extensions[i].selectionOnNode(node,position,this);
			}
		}
	}

	protected void findMethodFromMixin(String name, ASTNode parent) {
		if (name.startsWith("::")) {
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
		}
		String oName = name;
		if (name.indexOf("::") != -1) {
			String[] split = name.split("::");
			oName = split[split.length - 1];
		}
		findMethodMixin(tclNameToKey(name), oName);
	}

	public boolean checkMethodFrom(TypeDeclaration declaringType,
			SimpleReference callName, IModelElement parent) {
		List methodList = declaringType.getMethodList();
		for (Iterator iterator = methodList.iterator(); iterator.hasNext();) {
			MethodDeclaration method = (MethodDeclaration) iterator.next();
			if (method.getName().equals(callName.toString())) {

				IModelElement methodElement = TclResolver.findChildrenByName(
						method.getName(), (IParent) parent);
				this.selectionElements.add(methodElement);
				return true;
			}
		}
		return false;
	}

	public void fillSuperClassesTo(TypeDeclaration declaringType,
			List supersToHandle) {
		if (declaringType == null) {
			return;
		}
		if (declaringType.getSuperClasses() == null) {
			return;
		}
		List superClasses = declaringType.getSuperClasses().getChilds();
		for (int i = 0; i < superClasses.size(); i++) {
			String superClassName = TclParseUtil
					.getNameFromNode((ASTNode) superClasses.get(i));
			if (superClassName != null) {
				supersToHandle.add(superClassName);
			}
		}
	}

	protected void findVariables(String name, ASTNode parent, int beforePosition) {
		String originalName = name;
		if (parent instanceof MethodDeclaration) {
			MethodDeclaration method = (MethodDeclaration) parent;
			List statements = method.getArguments();
			if (statements != null) {
				for (int i = 0; i < statements.size(); ++i) {
					Argument a = (Argument) statements.get(i);
					if (a != null) {
						checkVariable(name, a.getName(), method);
					}
				}
			}
			// Process variable setters.
			statements = method.getStatements();
			checkVariableStatements(name, beforePosition, statements, "");
		} else if (parent instanceof ModuleDeclaration) {
			ModuleDeclaration module = (ModuleDeclaration) parent;
			checkVariableStatements(name, beforePosition, module
					.getStatements(), "");
		} else if (parent instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) parent;
			checkVariableStatements(name, beforePosition, type.getStatements(),
					"");
		} else {
			List levels = TclParseUtil.findLevelsTo(this.parser.getModule(),
					parent);
			ASTNode realParent = findRealParent(levels);
			if (realParent != null) {
				findVariables(name, realParent, beforePosition);
			}
		}
		if (this.selectionElements.size() > 0) {
			return;
		}
		// Find global variables
		if (name.startsWith("$")) {
			name = name.substring(1);
			if (!name.startsWith("::")) {
				name = "::" + name;
			}
			String typeName = name.substring(0, name.lastIndexOf("::"));
			String varName = name.substring(name.lastIndexOf("::") + 2);
			typeName = typeName.replaceAll("::", "\\$");
			try {
				IModelElement type = null;
				if (typeName.length() > 0) {
					type = (IModelElement) TclResolver.findTypeFrom(
							sourceModule.getChildren(), "", typeName, '$');
				} else {
					type = this.sourceModule;
				}
				if (type != null && type instanceof IParent) {
					IModelElement field = TclResolver.findChildrenByName(
							varName, (IParent) type);
					if (field != null) {
						this.selectionElements.add(field);
					}
				}
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
			// findFieldFromSearch(name);
			findFieldFromMixin(parent, name);
		}
		if (this.selectionElements.size() > 0) {
			return;
		}
		// Search from mixins if not found local.
		findFieldFromMixin(parent, originalName);
	}

	protected void findFieldFromMixin(ASTNode parent, String name) {

		// findFieldMixin(IMixinRequestor.MIXIN_NAME_SEPARATOR +
		// tclNameToKey(name), name);
		if (name.startsWith("$")) {
			name = name.substring(1);
		}
		if (name.startsWith("{") || name.endsWith("}")) {
			name = name.substring(1, name.length() - 1);
		}
		if (parent instanceof ModuleDeclaration || name.startsWith("::")) {
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
			String oName = name;
			if (name.indexOf("::") != -1) {
				String[] split = name.split("::");
				oName = split[split.length - 1];
			}
			findFieldMixin(tclNameToKey(name), oName);
		} else {
			List levels = TclParseUtil.findLevelsTo(this.parser.getModule(),
					parent);
			String keyFromLevels = getKeyFromLevels(levels);
			findFieldMixin(keyFromLevels + IMixinRequestor.MIXIN_NAME_SEPARATOR
					+ name, name);
		}
	}

	public String tclNameToKey(String name) {
		return TclParseUtil.tclNameTo(name,
				IMixinRequestor.MIXIN_NAME_SEPARATOR);
	}

	protected void findFieldMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern);
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[i] != null && allObjects[i] instanceof TclField) {
					TclField field = (TclField) allObjects[i];
					if (name.equals(field.getName())) {
						this.selectionElements.add(field.getModelElement());
						return;
					}
				}
			}
		}
	}

	public void findMethodMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern);
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null && allObjects[j] instanceof TclProc) {
					TclProc field = (TclProc) allObjects[j];
					if (name.equals(field.getName())) {
						this.selectionElements.add(field.getModelElement());
						return;
					}
				}
			}
		}
	}

	protected String getKeyFromLevels(List nodes) {
		return TclParseUtil.getElementFQN(nodes,
				IMixinRequestor.MIXIN_NAME_SEPARATOR, this.parser.getModule());
	}

	protected ASTNode findRealParent(List levels) {
		for (int i = levels.size() - 1; i >= 0; --i) {
			ASTNode n = (ASTNode) levels.get(i);
			if (n instanceof MethodDeclaration || n instanceof TypeDeclaration
					|| n instanceof ModuleDeclaration) {
				return n;
			}
		}
		return null;
	}

	protected void checkVariableStatements(String name, int beforePosition,
			List statements, String prefix) {
		if (statements != null) {
			for (int i = 0; i < statements.size(); ++i) {
				ASTNode node = (ASTNode) statements.get(i);
				if (node instanceof FieldDeclaration) {
					FieldDeclaration decl = (FieldDeclaration) node;
					checkVariable(name, prefix + decl.getName(), node);
				}
				if (node instanceof TclStatement
						&& node.sourceEnd() < beforePosition) {
					TclStatement s = (TclStatement) node;
					String[] variable = TclParseUtils.returnVariable(s);
					if (variable != null) {
						for (int u = 0; u < variable.length; ++u) {
							checkVariable(name, prefix + variable[u], node);
						}
					}
				}
				if (node instanceof TypeDeclaration) {
					TypeDeclaration type = (TypeDeclaration) node;
					String nn = type.getName();
					if (nn.startsWith("::")) {
						nn = nn.substring(2);
					}
					// TODO: Possible bug with adding to global namespace from
					// inner namespace.
					checkVariableStatements(name, beforePosition, type
							.getStatements(), prefix + nn + "::");
					// checkVariableStatements(name, beforePosition, type
					// .getStatements(), "");
				}
				// This is in case not of type declaration
				else if (node.sourceStart() <= beforePosition) {
					List statements2 = TclResolver.findExtractBlocks(node);
					if (statements2.size() != 0) {
						checkVariableStatements(name, beforePosition,
								statements2, prefix);
					}
				}
			}
		}
	}

	protected void processExecuteBlock(String name, Expression bl,
			int beforePosition) {
		TclExecuteExpression block = (TclExecuteExpression) bl;
		List code = block.parseExpression(block.sourceStart() + 1);
		checkVariableStatements(name, beforePosition, code, "");
	}

	private void checkVariable(String name, String variable, ASTNode node) {
		String str;
		if (variable.indexOf('(') != -1) {
			variable = variable.substring(0, variable.indexOf('('));
		}
		if (name.startsWith("${")) {
			str = "$" + '{' + variable + '}';
		} else {
			str = "$" + variable;
		}
		// strip array indexes
		if (name.indexOf('(') != -1) {
			name = name.substring(0, name.indexOf('('));
		}

		if (name.equals(str)) {
			addElementFromASTNode(node);
		}
	}

	protected void search(String patternString, int searchFor, int limitTo,
			IDLTKSearchScope scope, SearchRequestor resultCollector)
			throws CoreException {
		search(patternString, searchFor, limitTo, EXACT_RULE, scope,
				resultCollector);
	}

	protected void search(String patternString, int searchFor, int limitTo,
			int matchRule, IDLTKSearchScope scope, SearchRequestor requestor)
			throws CoreException {
		if (patternString.indexOf('*') != -1
				|| patternString.indexOf('?') != -1) {
			matchRule |= SearchPattern.R_PATTERN_MATCH;
		}
		// SearchPattern pattern = SearchPattern.createPattern(patternString,
		// searchFor, limitTo, matchRule);
		// new SearchEngine().search(pattern,
		// new SearchParticipant[] { SearchEngine
		// .getDefaultSearchParticipant() }, scope, requestor,
		// null);
	}

	protected void findLocalFunctions(String name, ASTNode parent) {
		List levels = TclParseUtil
				.findLevelsTo(this.parser.getModule(), parent);
		int len = levels.size();
		ASTNode realParent = findRealParent(levels);
		// At first search for namespace function
		if (realParent instanceof MethodDeclaration && !name.startsWith("::")) {
			MethodDeclaration mParent = (MethodDeclaration) realParent;
			String pName = mParent.getName();
			if (pName.indexOf("::") != -1) {
				pName = pName.substring(0, pName.lastIndexOf("::") + 2);
				processFindLocalFunctions(pName + name, levels, len);
			}
		}
		processFindLocalFunctions(name, levels, len);
	}

	protected void processFindLocalFunctions(String name, List levels, int len) {
		List visited = new ArrayList();
		for (int j = 0; j < len; ++j) {
			ASTNode astNodeParent = (ASTNode) levels.get(len - 1 - j);
			boolean topLevel = false;
			if (name != null && name.length() > 0 && name.charAt(0) == ':') {
				topLevel = true;
			}
			if (astNodeParent instanceof TypeDeclaration && !topLevel) {
				// Add all method here.
				TypeDeclaration decl = (TypeDeclaration) astNodeParent;
				List statements = decl.getStatements();
				if (statements != null) {
					processMethods(name, statements, "", visited);

					if (!name.startsWith("::")) {
						processMethods(decl.getName() + "::" + name,
								statements, "", visited);
					}
					if (selectionElements.size() > 0) {
						return;
					}
				}
			} else if (astNodeParent instanceof ModuleDeclaration) {
				ModuleDeclaration decl = (ModuleDeclaration) astNodeParent;
				List statements = decl.getStatements();
				processMethods(name, statements, "", visited);
				if (statements != null) {
					if (selectionElements.size() > 0) {
						return;
					}
				}
			}
		}
	}

	protected void processMethods(String name, final List statements,
			final String namePrefix, final List visited) {
		if (selectionElements.size() > 0) {
			return;
		}
		for (int i = 0; i < statements.size(); ++i) {
			ASTNode nde = (ASTNode) statements.get(i);
			if (nde instanceof MethodDeclaration) {
				String mName = ((MethodDeclaration) nde).getName();
				if (!mName.startsWith("::")) {
					mName = namePrefix + mName;
				}
				if (mName.startsWith("::::")) {
					mName = mName.substring(2);
				}
				if (name.startsWith("::")) {
					name = name.substring(2);
				}
				if (mName.startsWith("::")) {
					mName = mName.substring(2);
				}
				if (name.equals(mName)) {
					addElementFromASTNode(nde);
					if (selectionElements.size() > 0) {
						return;
					}
				}
			} else if (nde instanceof TypeDeclaration && !visited.contains(nde)) {
				List tStatements = ((TypeDeclaration) nde).getStatements();
				visited.add(nde);
				String ndeName = ((TypeDeclaration) nde).getName();
				if (ndeName.startsWith("::")) {
					processMethods(name, tStatements, ndeName + "::", visited);
				} else {
					processMethods(name, tStatements, namePrefix + ndeName
							+ "::", visited);
				}

			} else {
				// We need to visit blocked expressions to one level.
				final String fname = name;
				ASTVisitor visitor = new ASTVisitor() {
					public boolean visit(Expression s) throws Exception {
						if (s instanceof Block) {
							List tStatements = ((Block) s).getStatements();
							visited.add(s);
							processMethods(fname, tStatements, namePrefix,
									visited);
						}
						return false;
					}

					public boolean visit(MethodDeclaration s) throws Exception {
						return false;
					}

					public boolean visit(TypeDeclaration s) throws Exception {
						return false;
					}
				};
				try {
					nde.traverse(visitor);
				} catch (Exception e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}

			visited.add(nde);
		}
	}

	public void addElementFromASTNode(ASTNode nde) {
		ModuleDeclaration module = parser.getModule();
		List statements = module.getStatements();
		new TclResolver(sourceModule, parser.module, parentResolver)
				.searchAddElementsTo(statements, nde, sourceModule,
						this.selectionElements);
	}

	public IModelElement findElementFromNode(ASTNode nde) {
		ModuleDeclaration module = parser.getModule();
		List statements = module.getStatements();
		List elements = new ArrayList();
		new TclResolver(sourceModule, parser.module, parentResolver)
				.searchAddElementsTo(statements, nde, sourceModule, elements);
		if (elements.size() == 1) {
			return (IModelElement) elements.get(0);
		}
		return null;
	}

	IResolveElementParent parentResolver = new IResolveElementParent() {
		public IModelElement findElementParent(ASTNode node, String name,
				IParent parent) {
			return TclSelectionEngine.this
					.findElementParent(node, name, parent);
		}
	};

	protected IModelElement findElementParent(ASTNode node, String name,
			IParent parent) {
		return null;
	}

	protected boolean checkSelection(String source, int selectionSourceStart,
			int selectionSourceEnd) {

		boolean cheat = false;
		if (selectionSourceEnd < selectionSourceStart) {
			selectionSourceEnd = selectionSourceStart;
			cheat = true;
		}

		int start = TclParseUtils.startLineOrNoSymbol(selectionSourceStart,
				source);
		int end = TclParseUtils.endLineOrNoSymbol(selectionSourceEnd, source);
		if (end <= start) {
			if (cheat)
				return checkSelection(source, selectionSourceEnd - 1,
						selectionSourceEnd - 1);
			return false;
		}
		if (start > source.length() || end > source.length()) {
			if (cheat)
				return checkSelection(source, selectionSourceEnd - 1,
						selectionSourceEnd - 1);
			return false;
		}

		boolean isVariable = false;
		if (source.charAt(start) == '$') {
			isVariable = true;
		} else {
			if (start > 0) { // check if it is variable
				if (source.charAt(start - 1) == '{') {
					if (start - 1 > 0 && source.charAt(start - 2) == '$') {
						start -= 2;
						isVariable = true;
						while (end < source.length()
								&& source.charAt(end) != '}')
							end++;
						end++;
					}
				}
			}
		}
		if (isVariable && end < source.length() && source.charAt(end) == '(') {// it
			// is
			// array
			while (end < source.length() && source.charAt(end) != ')')
				end++;
			end++;
		}
		String sub = source.substring(start, end);
		// If contain tabs or spaces, then from start.
		if (!isVariable
				&& (sub.indexOf(' ') != -1 || sub.indexOf('\t') != -1 || sub
						.indexOf('\n') != -1)) {
			if (cheat)
				return checkSelection(source, selectionSourceEnd - 1,
						selectionSourceEnd - 1);
			return false;
		}
		this.actualSelectionStart = start;
		this.actualSelectionEnd = end;
		return true;
	}

	public IAssistParser getParser() {
		return parser;
	}

	protected IParent getSourceModule() {
		return this.sourceModule;
	}

	public int getActualSelectionStart() {
		return this.actualSelectionStart;
	}

	public void addSelectionElement(IModelElement element) {
		this.selectionElements.add(element);	
	}

	public int getSelectionElementsSize() {
		return this.selectionElements.size();
	}
}
