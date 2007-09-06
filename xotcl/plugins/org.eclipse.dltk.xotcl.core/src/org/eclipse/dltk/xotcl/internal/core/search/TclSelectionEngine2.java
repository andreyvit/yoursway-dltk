/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.xotcl.internal.core.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.declarations.Argument;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.ScriptSelectionEngine;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.internal.codeassist.select.SelectionNodeFound;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.ast.expressions.TclBlockExpression;
import org.eclipse.dltk.tcl.ast.expressions.TclExecuteExpression;
import org.eclipse.dltk.tcl.core.TclLanguageToolkit;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnAST;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnNode;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnVariable;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.TclField;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.TclProc;

public class TclSelectionEngine2 extends ScriptSelectionEngine {
	public static boolean DEBUG = DLTKCore.DEBUG_SELECTION;

	private int actualSelectionStart;

	private int actualSelectionEnd;

	protected List selectionElements = new ArrayList();

	private TclSelectionParser2 parser = new TclSelectionParser2();

	private org.eclipse.dltk.core.ISourceModule sourceModule;

	private IDLTKLanguageToolkit toolkit;

	public TclSelectionEngine2() {
		this.toolkit = TclLanguageToolkit.getDefault();
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
//				findMethodFromSearch(name);
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
//					findMethodFromSearch(fqnName);
				}
			}
		} else if (astNode instanceof SelectionOnVariable) {
			SelectionOnVariable completion = (SelectionOnVariable) astNode;
			findVariables(completion.getName(), astNodeParent, astNode
					.sourceStart());
		} else if (astNode instanceof SelectionOnAST) {
			ASTNode node = ((SelectionOnAST) astNode).getNode();
			if (node instanceof XOTclMethodDeclaration) {
				processSelectXOTclMethodDeclaration(
						(XOTclMethodDeclaration) node,
						this.actualSelectionStart);
				if (this.selectionElements.size() > 0) {
					return;
				}
			}
			addElementFromASTNode(node);
		} else if (astNode instanceof SelectionOnNode) {
			ASTNode node = ((SelectionOnNode) astNode).getNode();
			int position = ((SelectionOnNode) astNode).getPosition();
			if (node instanceof XOTclMethodCallStatement) {
				processSelectXOTclMethod((XOTclMethodCallStatement) node,
						position);
			} else if (node instanceof XOTclProcCallStatement) {
				processSelectXOTclMethod((XOTclProcCallStatement) node,
						position);
			} else if (node instanceof XOTclMethodDeclaration) {
				processSelectXOTclMethodDeclaration(
						(XOTclMethodDeclaration) node,
						this.actualSelectionStart);
			}
		}
	}

	private void findMethodFromMixin(String name, ASTNode parent) {
		// TODO Auto-generated method stub
		if( name.startsWith("::")) {
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
			String oName = name;
			if( name.indexOf("::") != -1 ) {
				String[] split = name.split("::");
				oName = split[split.length - 1];
			}
			findMethodMixin(tclNameToKey(name), oName);
		}
	}

	private void processSelectXOTclMethod(XOTclProcCallStatement node,
			int position) {
		SimpleReference typeName = node.getInstNameRef();
		List levels = TclParseUtil.findLevelsTo(this.parser.getModule(), node);
		ASTNode nParent = (ASTNode) levels.get(levels.size() - 2);
		TypeDeclaration type = TclParseUtil.findXOTclTypeDeclarationFrom(
				this.parser.getModule(), nParent, typeName.getName());
		if (type instanceof TypeDeclaration) {
			IModelElement parent = findElementFromNode(type);
			if (parent != null && parent instanceof IParent) {
				if (node.getCallName().sourceStart() <= position
						&& position <= node.getCallName().sourceEnd()) {

					IModelElement methodElement = findChildrenByName(node
							.getCallName().getName(), (IParent) parent);
					this.selectionElements.add(methodElement);
					return;
				} else if (typeName.sourceStart() <= position
						&& position <= typeName.sourceEnd()) {
					this.selectionElements.add(parent);
				}
			}
		}
	}

	private void processSelectXOTclMethodDeclaration(
			XOTclMethodDeclaration node, int position) {
		ASTNode type = node.getDeclaringXOTclType();
		if (type instanceof TypeDeclaration) {
			SimpleReference ref = node.getTypeNameRef();
			IModelElement parent = findElementFromNode(type);
			if (parent != null && parent instanceof IParent) {
				if (node.getNameStart() <= position
						&& position <= node.getNameEnd()) {

					IModelElement methodElement = findChildrenByName(node
							.getName(), (IParent) parent);
					this.selectionElements.add(methodElement);
					return;
				} else if (ref.sourceStart() <= position
						&& position <= ref.sourceEnd()) {
					this.selectionElements.add(parent);
				}
			}
		}
	}

	private void processSelectXOTclMethod(XOTclMethodCallStatement call,
			int position) {
		XOTclInstanceVariable instanceVariable = call.getInstanceVariable();
		if (instanceVariable != null) {
			TypeDeclaration declaringType = instanceVariable.getDeclaringType();
			SimpleReference callName = call.getCallName();
			SimpleReference instName = call.getInstNameRef();
			// Check for method
			if (callName.sourceStart() <= position
					&& position <= callName.sourceEnd()) {
				if (declaringType != null) {
					IModelElement parent = findElementFromNode(declaringType);
					if (parent != null) {
						List methodList = declaringType.getMethodList();
						for (Iterator iterator = methodList.iterator(); iterator
								.hasNext();) {
							MethodDeclaration method = (MethodDeclaration) iterator
									.next();
							if (method.getName().equals(callName.toString())) {

								IModelElement methodElement = findChildrenByName(
										method.getName(), (IParent) parent);
								this.selectionElements.add(methodElement);
								return;
							}
						}
					}
				}
			} else if (instName.sourceStart() <= position
					&& position <= instName.sourceEnd()) {
				addElementFromASTNode(instanceVariable);
			}
		}
	}

	private void findVariables(String name, ASTNode parent, int beforePosition) {
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
					type = (IModelElement) findTypeFrom(sourceModule
							.getChildren(), "", typeName, '$');
				} else {
					type = this.sourceModule;
				}
				if (type != null && type instanceof IParent) {
					IModelElement field = this.findChildrenByName(varName,
							(IParent) type);
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
		}
		if( this.selectionElements.size() > 0 ) {
			return;
		}
		// Search from mixins if not found local.
		findFieldFromMixin(parent, originalName);
	}

	private void findFieldFromMixin(ASTNode parent, String name) {

//		findFieldMixin(IMixinRequestor.MIXIN_NAME_SEPARATOR + tclNameToKey(name), name);
		if( name.startsWith("$")) {
			name = name.substring(1);
		}
		if( name.startsWith("{") || name.endsWith( "}" )) {
			name = name.substring(1, name.length() - 1 );
		}
		if( parent instanceof ModuleDeclaration || name.startsWith("::")) {
			if (name.startsWith("::")) {
				name = name.substring(2);
			}
			String oName = name;
			if( name.indexOf("::") != -1 ) {
				String[] split = name.split("::");
				oName = split[split.length - 1];
			}
			findFieldMixin(tclNameToKey(name), oName);
		}
		else {
			List levels = TclParseUtil.findLevelsTo(this.parser.getModule(), parent);
			String keyFromLevels = getKeyFromLevels(levels);
			findFieldMixin(keyFromLevels + IMixinRequestor.MIXIN_NAME_SEPARATOR + name, name);
		}
	}
	private String tclNameToKey(String name) {
		return TclParseUtil.tclNameTo(name,
				IMixinRequestor.MIXIN_NAME_SEPARATOR);
	}

	private void findFieldMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find( pattern + "*"
				);
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[i] != null
						&& allObjects[i] instanceof TclField) {
					TclField field = (TclField) allObjects[i];
					if (field.getName().equals(name)) {
						this.selectionElements.add(field.getModelElement());
						return;
					}
				}
			}
		}
	}
	private void findMethodMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find( pattern + "*"
				);
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[i] != null
						&& allObjects[i] instanceof TclProc) {
					TclProc field = (TclProc) allObjects[i];
					if (field.getName().equals(name)) {
						this.selectionElements.add(field.getModelElement());
						return;
					}
				}
			}
		}
	}
	private String getKeyFromLevels(List nodes) {
		return TclParseUtil.getElementFQN(nodes,
				IMixinRequestor.MIXIN_NAME_SEPARATOR, this.parser.getModule());
	}

	private void findFieldFromSearch(String varName) {
		if (this.selectionElements.size() > 0) {
			return;
		}
		if (varName.startsWith("$")) {
			varName = varName.substring(1);
		}
		final String name = varName;
		SearchRequestor requestor = new SearchRequestor() {
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				Object element = match.getElement();
				if (element instanceof IType) {
					IType type = (IType) element;
					// We need to skip not namespaces.
					if ((type.getFlags() & Modifiers.AccNameSpace) == 0) {
						return;
					}
					// String mn = TclParseUtils.processTypeName(type, name);
					// if (mn.equals(name)
					// && !TclSelectionEngine.this.selectionElements
					// .contains(type)) {
					// TclSelectionEngine.this.selectionElements.add(type);
					// }
					IField[] tfields = type.getFields();
					for (int i = 0; i < tfields.length; ++i) {
						processField(name, tfields[i]);
					}
				} else if (element instanceof IField) {
					IField field = (IField) element;
					processField(name, field);
				}
			}

			private void processField(final String name, IField field) {
				String mn = TclParseUtils.processFieldName(field, name);
				if (mn.equals(name)
						&& !TclSelectionEngine2.this.selectionElements
								.contains(field)) {
					TclSelectionEngine2.this.selectionElements.add(field);
				}
			}
		};
		IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		if (name != null && name.length() >= 3 && name.charAt(0) == ':') {
			try {
				search(name, IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				search(name, IDLTKSearchConstants.FIELD,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				if (name.startsWith("::")) {
					String name_wo = name.substring(2);
					search(name_wo, IDLTKSearchConstants.TYPE,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					search(name_wo, IDLTKSearchConstants.FIELD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				}
				String to = new String(name);
				String[] tokens = to.split("::");
				final String tok = tokens[1];
				try {
					search(to, IDLTKSearchConstants.FIELD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					search(to, IDLTKSearchConstants.FIELD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					// search( "::" + tok + "*", IDLTKSearchConstants.TYPE,
					// IDLTKSearchConstants.DECLARATIONS, scope, requestor );
					search(tok + "*", IDLTKSearchConstants.TYPE,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					if (to.startsWith("::")) {
						String to_wo = to.substring(2);
						search(new String(to_wo) + "*",
								IDLTKSearchConstants.FIELD,
								IDLTKSearchConstants.DECLARATIONS, scope,
								requestor);
					}
					search(new String(tok) + "*", IDLTKSearchConstants.METHOD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else if (name != null && name.length() >= 1 && name.charAt(0) != ':') {
			try {
				search(name, IDLTKSearchConstants.FIELD,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				search(name, IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				String[] tokens = name.split("::");
				String tok = tokens[0];
				search(tok + "*", IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	private ASTNode findRealParent(List levels) {
		for (int i = levels.size() - 1; i >= 0; --i) {
			ASTNode n = (ASTNode) levels.get(i);
			if (n instanceof MethodDeclaration || n instanceof TypeDeclaration
					|| n instanceof ModuleDeclaration) {
				return n;
			}
		}
		return null;
	}

	private void checkVariableStatements(String name, int beforePosition,
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
				if (node.sourceStart() <= beforePosition) {
					if (node instanceof TclStatement) {
						TclStatement s = (TclStatement) node;
						Expression commandId = s.getAt(0);
						if (commandId != null
								&& commandId instanceof SimpleReference) {
							String qname = ((SimpleReference) commandId)
									.getName();
							if (qname.equals("if")) {
								TclParseUtils.processIf(s.getExpressions(),
										name, beforePosition,
										processBlockAction);
							} else if (qname.equals("while")) {
								processWhile(name, s, beforePosition,
										processBlockAction);
							} else if (qname.equals("for")) {
								processFor(name, s, beforePosition,
										processBlockAction);
							} else if (qname.equals("catch")) {
								processCatch(name, s, beforePosition,
										processBlockAction);
							}
						}
					} else {
						List statements2 = findExtractBlocks(node);
						if (statements2.size() != 0) {
							checkVariableStatements(name, beforePosition,
									statements2, prefix);
						}
					}
				}
			}
		}
	}

	private List findExtractBlocks(ASTNode node) {
		final List statements2 = new ArrayList();
		ASTVisitor visitor = new ASTVisitor() {
			public boolean visit(Expression s) throws Exception {
				if (s instanceof Block) {
					statements2.addAll(((Block) s).getStatements());
				}
				return super.visit(s);
			}
		};
		try {
			node.traverse(visitor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statements2;
	}

	private void processBlock(String name, Expression bl, int beforePosition) {
		TclBlockExpression block = (TclBlockExpression) bl;
		List/* < Statement > */code = null;

		// Block b = new Block();
		code = block.parseBlock(block.sourceStart() + 1);
		// b.acceptStatements(code);

		// TclASTBuilder.build(b, code, TclASTBuilder.TYPE_UNKNOWN);
		checkVariableStatements(name, beforePosition,
				code/* b.getStatements() */, "");

	}

	private void processExecuteBlock(String name, Expression bl,
			int beforePosition) {
		TclExecuteExpression block = (TclExecuteExpression) bl;
		List code = block.parseExpression(block.sourceStart() + 1);
		checkVariableStatements(name, beforePosition, code, "");

	}

	private TclParseUtils.IProcessStatementAction processBlockAction = new TclParseUtils.IProcessStatementAction() {
		public void doAction(String name, Expression bl, int beforePosition) {
			if (bl instanceof TclBlockExpression)
				processBlock(name, bl, beforePosition);
			if (bl instanceof TclExecuteExpression)
				processExecuteBlock(name, bl, beforePosition);
		}
	};

	private void processFor(String name, TclStatement statement,
			int beforePosition, TclParseUtils.IProcessStatementAction action) {
		// TODO: Add variable corrections here.
		List exprs = statement.getExpressions();
		int len = exprs.size();
		if (1 < len) { // Process initializers
			Expression bl = (Expression) exprs.get(1);
			if (bl instanceof TclBlockExpression) {
				action.doAction(name, bl, beforePosition);
			}
		}
		int bi = 4; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				action.doAction(name, bl, beforePosition);
			}
		}
	}

	private void processWhile(String name, TclStatement statement,
			int beforePosition, TclParseUtils.IProcessStatementAction action) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		int bi = 2; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression) {
				action.doAction(name, bl, beforePosition);
			}
		}
	}

	private void processCatch(String name, TclStatement statement,
			int beforePosition, TclParseUtils.IProcessStatementAction action) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		int bi = 1; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression
					|| bl instanceof TclExecuteExpression) {
				action.doAction(name, bl, beforePosition);
			}
		}
	}

	private void processAfter(String name, TclStatement statement,
			int beforePosition, TclParseUtils.IProcessStatementAction action) {
		List exprs = statement.getExpressions();
		int len = exprs.size();
		int bi = 2; // Skip expression
		if (bi < len) {
			Expression bl = (Expression) exprs.get(bi);
			if (bl instanceof TclBlockExpression
					|| bl instanceof TclExecuteExpression) {
				action.doAction(name, bl, beforePosition);
			}
		}
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

	private void findMethodFromSearch(final String name) {
		SearchRequestor requestor = new SearchRequestor() {
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				Object element = match.getElement();
				if (element instanceof IType) {
					IType type = (IType) element;
					if ((type.getFlags() & Modifiers.AccNameSpace) == 0) {
						return;
					}
					String mn = TclParseUtils.processTypeName(type, name);
					if (mn.equals(name)
							&& !TclSelectionEngine2.this.selectionElements
									.contains(type)) {
						TclSelectionEngine2.this.selectionElements.add(type);
					}
					IMethod[] tmethods = type.getMethods();
					for (int i = 0; i < tmethods.length; ++i) {
						processMethod(name, tmethods[i]);
					}
				} else if (element instanceof IMethod) {
					IMethod method = (IMethod) element;
					processMethod(name, method);
				}
			}

			private void processMethod(final String name, IMethod method) {
				String mn = TclParseUtils.processMethodName(method, name);
				if (mn.equals(name)
						&& !TclSelectionEngine2.this.selectionElements
								.contains(method)) {
					TclSelectionEngine2.this.selectionElements.add(method);
				}
			}
		};
		IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		if (name != null && name.length() >= 3 && name.charAt(0) == ':') {
			try {
				search(name, IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				search(name, IDLTKSearchConstants.METHOD,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				if (name.startsWith("::")) {
					String name_wo = name.substring(2);
					search(name_wo, IDLTKSearchConstants.TYPE,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					search(name_wo, IDLTKSearchConstants.METHOD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				}
				String to = new String(name);
				String[] tokens = to.split("::");
				final String tok = tokens[1];
				try {
					search(to, IDLTKSearchConstants.METHOD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					// search(to, IDLTKSearchConstants.METHOD,
					// IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					// search( "::" + tok + "*", IDLTKSearchConstants.TYPE,
					// IDLTKSearchConstants.DECLARATIONS, scope, requestor );
					search(tok + "*", IDLTKSearchConstants.TYPE,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
					if (to.startsWith("::")) {
						String to_wo = to.substring(2);
						search(new String(to_wo) + "*",
								IDLTKSearchConstants.METHOD,
								IDLTKSearchConstants.DECLARATIONS, scope,
								requestor);
					}
					search(new String(tok) + "*", IDLTKSearchConstants.METHOD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else if (name != null && name.length() >= 1 && name.charAt(0) != ':') {
			try {
				search(name, IDLTKSearchConstants.METHOD,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				search(name, IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				String[] tokens = name.split("::");
				String tok = tokens[0];
				search(tok + "*", IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	protected void search(String patternString, int searchFor, int limitTo,
			IDLTKSearchScope scope, SearchRequestor resultCollector)
			throws CoreException {
		// search(patternString, searchFor, limitTo, EXACT_RULE, scope,
		// resultCollector);
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

	private void findLocalFunctions(String name, ASTNode parent) {
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

	private void processFindLocalFunctions(String name, List levels, int len) {
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

	private void processMethods(String name, List statements,
			String namePrefix, List visited) {
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

			}
			visited.add(nde);
		}
	}

	protected void addElementFromASTNode(ASTNode nde) {
		ModuleDeclaration module = parser.getModule();
		List statements = module.getStatements();
		searchAddElementsTo(statements, nde, sourceModule,
				this.selectionElements);
	}

	protected IModelElement findElementFromNode(ASTNode nde) {
		ModuleDeclaration module = parser.getModule();
		List statements = module.getStatements();
		List elements = new ArrayList();
		searchAddElementsTo(statements, nde, sourceModule, elements);
		if (elements.size() == 1) {
			return (IModelElement) elements.get(0);
		}
		return null;
	}

	protected IParent findTypeFrom(IModelElement[] childs, String name,
			String parentName, char delimiter) {
		try {
			for (int i = 0; i < childs.length; ++i) {
				if (childs[i] instanceof IType) {
					if ((((IType) childs[i]).getFlags() & Modifiers.AccNameSpace) == 0) {
						continue;
					}
					IType type = (IType) childs[i];
					String qname = name + delimiter + type.getElementName();
					if (qname.equals(parentName)) {
						return type;
					}
					IParent val = findTypeFrom(type.getChildren(), qname,
							parentName, delimiter);
					if (val != null) {
						return val;
					}
				}
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected IModelElement findElementParent(ASTNode node, String name,
			IParent parent) {
		if (node instanceof XOTclMethodDeclaration) {
			XOTclMethodDeclaration xotclMethod = (XOTclMethodDeclaration) node;
			ASTNode declaringXOTclType = xotclMethod.getDeclaringXOTclType();
			if (declaringXOTclType instanceof TypeDeclaration) {
				TypeDeclaration t = (TypeDeclaration) declaringXOTclType;
				IModelElement pa = findElementFromNode(t);
				if (pa != null || pa instanceof IParent) {
					return findChildrenByName(name, (IParent) pa);
				}
			}
		}
		return null;
	}

	protected void searchAddElementsTo(List statements, final ASTNode node,
			IParent element, List selectionElements) {
		if (statements == null || element == null) {
			return;
		}
		Iterator i = statements.iterator();
		while (i.hasNext()) {
			ASTNode nde = (ASTNode) i.next();
			if (nde.equals(node)) {
				if (node instanceof MethodDeclaration) {
					String oName = ((MethodDeclaration) node).getName();
					if (oName.indexOf("::") != -1) {
						String pName = oName.substring(0, oName
								.lastIndexOf("::"));
						pName = pName.replaceAll("::", "\\$");

						if (pName.startsWith("$")) {
							if (pName.equals("$")) {
								element = this.sourceModule;
							} else {
								try {
									element = findTypeFrom(this.sourceModule
											.getChildren(), "", pName, '$');
								} catch (ModelException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} else {
							pName = "$" + pName;
							try {
								element = findTypeFrom(element.getChildren(),
										"", pName, '$');
								if (element == null) {
									return;
								}
							} catch (ModelException e) {
								e.printStackTrace();
								return;
							}
						}
					}
				}
				String nodeName = getNodeChildName(node);
				if (nodeName != null) {
					IModelElement e = null;
					if (nodeName.startsWith("::")) {
						nodeName = nodeName.substring(2);
						e = findChildrenByName(nodeName,
								(IParent) this.sourceModule);
					} else {
						e = findChildrenByName(nodeName, (IParent) element);
					}
					if (e == null) {
						e = findElementParent(node, nodeName, (IParent) element);

					}
					if (e != null) {
						List toRemove = new ArrayList();
						for (int k = 0; k < selectionElements.size(); ++k) {
							IModelElement ke = (IModelElement) selectionElements
									.get(k);
							String keName = ke.getElementName();
							if (keName.equals(nodeName)) {
								toRemove.add(ke);
							}
						}
						for (int k = 0; k < toRemove.size(); ++k) {
							selectionElements.remove(toRemove.get(k));
						}
						selectionElements.add(e);
					}
				}
				return;
			}
			if (nde.sourceStart() <= node.sourceStart()
					&& node.sourceEnd() <= nde.sourceEnd()) {
				if (element instanceof IParent) {
					if (nde instanceof TypeDeclaration) {
						TypeDeclaration type = (TypeDeclaration) nde;
						String typeName = getNodeChildName(type);
						IModelElement e = findChildrenByName(typeName,
								(IParent) element);
						if (e == null && type.getName().startsWith("::")) {
							try {
								e = (IModelElement) findTypeFrom(sourceModule
										.getChildren(), "", type.getName()
										.replaceAll("::", "\\$"), '$');
							} catch (ModelException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (e instanceof IParent) {
							// was: if (e != null || e instanceof IParent)
							List stats = ((TypeDeclaration) nde)
									.getStatements();
							searchAddElementsTo(stats, node, (IParent) e,
									selectionElements);
						}
					} else if (nde instanceof MethodDeclaration) {
						searchInMethod(node, element, nde);
					} /*
						 * else if (nde instanceof TclStatement) { TclStatement
						 * s = (TclStatement) nde; Expression commandId =
						 * s.getAt(0); final IParent e = element; if (commandId !=
						 * null && commandId instanceof SimpleReference) {
						 * String qname = ((SimpleReference) commandId)
						 * .getName(); } }
						 */
					else {
						final IParent e = element;
						List statements2 = findExtractBlocks(nde);
						if (statements2.size() > 0) {
							searchAddElementsTo(statements2, node, e,
									selectionElements);
						}
					}
				}
				return;
			}
		}
	}

	protected void searchInMethod(final ASTNode node, IParent element,
			ASTNode nde) {
		MethodDeclaration method = (MethodDeclaration) nde;
		if (method instanceof XOTclMethodDeclaration) {
			XOTclMethodDeclaration xotclMethod = (XOTclMethodDeclaration) method;
			ASTNode declaringXOTclType = xotclMethod.getDeclaringXOTclType();
			if (declaringXOTclType instanceof TypeDeclaration) {
				TypeDeclaration t = (TypeDeclaration) declaringXOTclType;
				IModelElement pa = findElementFromNode(t);
				if (pa != null || pa instanceof IParent) {
					element = (IParent) pa;
				}
			}
		}
		String methodName = method.getName();
		if (methodName.indexOf("::") != -1) {
			String pName = methodName
					.substring(0, methodName.lastIndexOf("::"));
			pName = pName.replaceAll("::", "\\$");
			if (pName.equals("$")) {
				element = this.sourceModule;
			} else {
				try {
					element = findTypeFrom(sourceModule.getChildren(), "",
							pName, '$');
					if (element == null) {
						return;
					}
				} catch (ModelException e) {
					e.printStackTrace();
					return;
				}
			}
			methodName = getNodeChildName(nde);
		}
		IModelElement e = findChildrenByName(methodName, (IParent) element);
		if (e != null && e instanceof IParent) {
			List stats = ((MethodDeclaration) nde).getStatements();
			searchAddElementsTo(stats, node, (IParent) e,
					this.selectionElements);
		}
	}

	protected String getNodeChildName(ASTNode node) {
		if (node instanceof MethodDeclaration) {
			MethodDeclaration method = (MethodDeclaration) node;
			String name = method.getName();
			if (name.indexOf("::") != -1) {
				return name.substring(name.lastIndexOf("::") + 2);
			}
			return name;
		} else if (node instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) node;
			String name = type.getName();
			/*
			 * if (name.startsWith("::")) { return name.substring(2); }
			 */
			return name;
		} else if (node instanceof TclStatement) {
			String[] var = TclParseUtils.returnVariable((TclStatement) node);
			if (var != null) {
				return var[0];
			}
		} else if (node instanceof FieldDeclaration) {
			return ((FieldDeclaration) node).getName();
		}
		return null;
	}

	protected IModelElement findChildrenByName(String childName, IParent element) {
		try {
			String nextName = null;
			int pos;
			if ((pos = childName.indexOf("::")) != -1) {
				nextName = childName.substring(pos + 2);
				childName = childName.split("::")[0];
			}
			IModelElement[] children = element.getChildren();
			if (children != null) {
				for (int i = 0; i < children.length; ++i) {
					String name = children[i].getElementName();
					if (children[i] instanceof IField
							&& name.indexOf('(') != -1) {
						name = name.substring(0, name.indexOf('('));
					}
					if (name.equals(childName)) {
						if (nextName == null) {
							return children[i];
						} else if (children[i] instanceof IParent) {
							return findChildrenByName(nextName,
									(IParent) children[i]);
						}
					}
				}
			}
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private boolean checkSelection(String source, int selectionSourceStart,
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
}
