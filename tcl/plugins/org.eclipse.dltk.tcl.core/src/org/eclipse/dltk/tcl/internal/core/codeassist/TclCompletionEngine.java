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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.codeassist.IAssistParser;
import org.eclipse.dltk.codeassist.ScriptCompletionEngine;
import org.eclipse.dltk.codeassist.complete.CompletionNodeFound;
import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.compiler.env.lookup.Scope;
import org.eclipse.dltk.core.CompletionContext;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.core.search.SearchEngine;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchParticipant;
import org.eclipse.dltk.core.search.SearchPattern;
import org.eclipse.dltk.core.search.SearchRequestor;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordArgumentOrFunctionArgument;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnVariable;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.TclCompletionParser;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinUtils;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclProc;
import org.eclipse.dltk.tcl.internal.parser.TclParseUtils;

public class TclCompletionEngine extends ScriptCompletionEngine {

	protected TclCompletionParser parser;
	protected org.eclipse.dltk.core.ISourceModule sourceModule;
	protected final static boolean TRACE_COMPLETION_TIME = false;

	public TclCompletionEngine(/*
								 * ISearchableEnvironment environment,
								 * CompletionRequestor requestor, Map options,
								 * IScriptProject project
								 */) {
		// super(environment, requestor, options, project);
		this.parser = new TclCompletionParser();
	}

	public void complete(ISourceModule sourceModule, int completionPosition,
			int pos) {
		this.sourceModule = (org.eclipse.dltk.core.ISourceModule) sourceModule
				.getModelElement();
		if (DEBUG) {
			System.out.print("COMPLETION IN "); //$NON-NLS-1$
			System.out.print(sourceModule.getFileName());
			System.out.print(" AT POSITION "); //$NON-NLS-1$
			System.out.println(completionPosition);
			System.out.println("COMPLETION - Source :"); //$NON-NLS-1$
			System.out.println(sourceModule.getSourceContents());
		}

		this.requestor.beginReporting();

		boolean contextAccepted = false;
		try {
			this.fileName = sourceModule.getFileName();
			this.actualCompletionPosition = completionPosition;
			this.offset = pos;
			ModuleDeclaration parsedUnit = (ModuleDeclaration) this.parser
					.parse(sourceModule);

			if (parsedUnit != null) {
				if (DEBUG) {
					System.out.println("COMPLETION - Diet AST :"); //$NON-NLS-1$
					System.out.println(parsedUnit.toString());
				}
				try {

					this.source = sourceModule.getSourceContents()
							.toCharArray();
					parseBlockStatements(parsedUnit,
							this.actualCompletionPosition);
					if (DEBUG) {
						System.out.println("COMPLETION - AST :"); //$NON-NLS-1$
						System.out.println(parsedUnit.toString());
					}

				} catch (CompletionNodeFound e) {
					// completionNodeFound = true;
					if (e.astNode != null) {
						if (DEBUG) {
							System.out.print("COMPLETION - Completion node : "); //$NON-NLS-1$
							System.out.println(e.astNode.toString());
							if (this.parser.getAssistNodeParent() != null) {
								System.out.print("COMPLETION - Parent Node : "); //$NON-NLS-1$
								System.out.println(this.parser
										.getAssistNodeParent());
							}
						}
						// if null then we found a problem in the completion
						// node

						contextAccepted = complete(e.astNode, this.parser
								.getAssistNodeParent(), e.scope,
								e.insideTypeAnnotation);
					}
				}
			}

			if (this.noProposal && this.problem != null) {
				if (!contextAccepted) {
					contextAccepted = true;
					CompletionContext context = new CompletionContext();
					context.setOffset(completionPosition);
					context.setTokenKind(CompletionContext.TOKEN_KIND_UNKNOWN);

					this.requestor.acceptContext(context);
				}

				this.requestor.completionFailure(this.problem);

				if (DEBUG) {
					this.printDebug(this.problem);
				}
			}
		} finally {
			if (!contextAccepted) {
				contextAccepted = true;
				CompletionContext context = new CompletionContext();
				context.setTokenKind(CompletionContext.TOKEN_KIND_UNKNOWN);
				context.setOffset(completionPosition);

				this.requestor.acceptContext(context);
			}
			this.requestor.endReporting();
		}
	}

	protected boolean complete(ASTNode astNode, ASTNode astNodeParent,
			Scope scope, boolean insideTypeAnnotation) {
		setSourceRange(astNode.sourceStart(), astNode.sourceEnd());
		if (astNode instanceof CompletionOnKeywordOrFunction) {
			CompletionOnKeywordOrFunction key = (CompletionOnKeywordOrFunction) astNode;
			processCompletionOnKeywords(key);
			processCompletionOnFunctions(astNodeParent, key);
		} else if (astNode instanceof CompletionOnVariable) {
			processCompletionOnVariables(astNode);
		} else if (astNode instanceof CompletionOnKeywordArgumentOrFunctionArgument) {
			CompletionOnKeywordArgumentOrFunctionArgument compl = (CompletionOnKeywordArgumentOrFunctionArgument) astNode;
			if (compl.argumentIndex() == 1) {
				// Completion on two argument keywords
				TclStatement st = compl.getStatement();
				Expression at = st.getAt(0);
				if (at instanceof SimpleReference) {
					String prefix = ((SimpleReference) at).getName() + " "
							+ new String(compl.getToken());
					processPartOfKeywords(compl, prefix, null);
				}
			}
		}
		return true;
	}

	protected void processPartOfKeywords(
			CompletionOnKeywordArgumentOrFunctionArgument compl, String prefix,
			Set methodNames) {
		String[] possibleKeywords = compl.getPossibleKeywords();
		List k = new ArrayList();
		for (int i = 0; i < possibleKeywords.length; i++) {
			String kkw = possibleKeywords[i];
			if (kkw.startsWith(prefix)) {
				k.add(kkw.substring(kkw.indexOf(" ") + 1));
			}
		}
		String kw[] = (String[]) k.toArray(new String[k.size()]);
		char[][] choices = new char[kw.length][];
		for (int i = 0; i < kw.length; ++i) {
			choices[i] = kw[i].toCharArray();
		}
		findKeywords(compl.getToken(), choices, true);
		if (methodNames != null) {
			methodNames.addAll(k);
		}
	}

	protected void processCompletionOnVariables(ASTNode astNode) {
		if (!this.requestor.isIgnored(CompletionProposal.LOCAL_VARIABLE_REF)
				&& !this.requestor
						.isIgnored(CompletionProposal.VARIABLE_DECLARATION)) {
			CompletionOnVariable completion = (CompletionOnVariable) astNode;
			findVariables(completion.getToken(), completion.getInNode(),
					completion.canHandleEmpty(), astNode.sourceStart(),
					completion.getProvideDollar(), null);
		}
	}

	protected void processCompletionOnFunctions(ASTNode astNodeParent,
			CompletionOnKeywordOrFunction key) {
		if (!this.requestor.isIgnored(CompletionProposal.METHOD_DECLARATION)) {
			List methodNames = new ArrayList();
			findLocalFunctions(key.getToken(), key.canCompleteEmptyToken(),
					astNodeParent, methodNames);
			char[] token = key.getToken();
			token = removeLastColonFromToken(token);
			Set set = new HashSet();
			set.addAll(methodNames);
			findNamespaceFunctions(token, set);
		}
	}

	protected void processCompletionOnKeywords(CompletionOnKeywordOrFunction key) {
		if (!this.requestor.isIgnored(CompletionProposal.KEYWORD)) {
			String[] kw = key.getPossibleKeywords();
			completeForKeywordOrFunction(key, kw);
		}
	}

	protected void completeForKeywordOrFunction(
			CompletionOnKeywordOrFunction key, String[] kw) {
		char[][] choices = new char[kw.length][];
		boolean add = false;
		char[] token = key.getToken();
		if (token != null && token.length > 0 && token[0] == ':') {
			add = true;
		}
		for (int i = 0; i < kw.length; ++i) {
			if (add) {
				choices[i] = ("::" + kw[i]).toCharArray();
			} else {
				choices[i] = kw[i].toCharArray();
			}
		}
		findKeywords(key.getToken(), choices, key.canCompleteEmptyToken());
	}

	protected char[] removeLastColonFromToken(char[] token) {
		// remove : on the end.
		if (token.length > 2 && token[token.length - 1] == ':'
				&& token[token.length - 2] != ':') {
			char co2[] = new char[token.length - 1];
			System.arraycopy(token, 0, co2, 0, co2.length);
			token = co2;
		}
		return token;
	}

	protected void findNamespaceFunctions(final char[] token,
			final Set methodNames) {

		final Set methods = new HashSet();
		String to_ = new String(token);
		String to = to_;
		if (to.startsWith("::")) {
			to = to.substring(2);
		}
		if (to.length() == 0) {
			return;
		}
		methods.addAll(methodNames);
		findMethodFromMixin(methods, to + "*");
		methods.removeAll(methodNames);
		// Remove all with same names
		removeSameFrom(methodNames, methods, to_);
		// findTypes(token, true, toList(types));
		findMethods(token, false, toList(methods));
	}

	protected void removeSameFrom(final Set methodNames, final Set elements,
			String to_) {
		for (Iterator iterator = methodNames.iterator(); iterator.hasNext();) {
			Object name = (Object) iterator.next();
			if (name instanceof String) {
				// We need to remove all elements with name from methods.
				Object elementToRemove = null;
				for (Iterator me = elements.iterator(); me.hasNext();) {
					Object m = (Object) me.next();
					if (m instanceof IMethod) {
						IMethod method = (IMethod) m;
						String qname = processMethodName(method, to_);
						if (qname.equals((String) name)) {
							elementToRemove = m;
							break;
						}
					} else if (m instanceof IType) {
						IType type = (IType) m;
						String qname = processTypeName(type, to_);
						if (qname.equals((String) name)) {
							elementToRemove = m;
							break;
						}
					}
				}
				if (elementToRemove != null) {
					elements.remove(elementToRemove);
				}
			}
		}
	}

	protected void findMixinTclElement(final Set completions, String tok,
			Class mixinClass) {
		String pattern = tok.replaceAll("::",
				IMixinRequestor.MIXIN_NAME_SEPARATOR);
		IModelElement[] elements = TclMixinUtils.findModelElementsFromMixin(
				pattern, mixinClass);
		long start = System.currentTimeMillis();
		for (int i = 0; i < elements.length; i++) {
			// We should filter external source modules with same
			// external path.
			if (moduleFilter(completions, elements[i])) {
				completions.add(elements[i]);
			}
			if (System.currentTimeMillis() - start > 100) {
				return;
			}
		}
	}

	protected void findMethodFromMixin(final Set methods, String tok) {
		findMixinTclElement(methods, tok, TclProc.class);
	}

	protected boolean moduleFilter(Set completions, IModelElement modelElement) {
		// org.eclipse.dltk.core.ISourceModule sourceModule =
		// (org.eclipse.dltk.core.ISourceModule) modelElement
		// .getAncestor(IModelElement.SOURCE_MODULE);
		// firstly lets filter member names

		String fullyQualifiedName = TclParseUtil.getFQNFromModelElement(
				modelElement, "::");

		for (Iterator iterator = completions.iterator(); iterator.hasNext();) {
			Object o = iterator.next();
			if (!(o instanceof IModelElement)) {
				if (o instanceof String) {
					if (fullyQualifiedName.equals((String) o)) {
						return false;
					}
				}
				continue;
			}
			IModelElement element = (IModelElement) o;
			String eName = TclParseUtil.getFQNFromModelElement(element, "::");
			if (eName.equals(fullyQualifiedName)) {
				return false;
			}
		}
		return true;
	}

	protected List toList(Set types) {
		return Arrays.asList(types.toArray());
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
		SearchPattern pattern = SearchPattern.createPattern(patternString,
				searchFor, limitTo, matchRule);
		new SearchEngine().search(pattern,
				new SearchParticipant[] { SearchEngine
						.getDefaultSearchParticipant() }, scope, requestor,
				null);
	}

	protected void findLocalFunctions(char[] token,
			boolean canCompleteEmptyToken, ASTNode astNodeParent,
			List methodNames) {

		token = removeLastColonFromToken(token);
		List methods = new ArrayList();
		fillFunctionsByLevels(token, astNodeParent, methods, methodNames);
		List nodeMethods = new ArrayList();
		List nodeMethodNames = new ArrayList();
		List otherMethods = new ArrayList();
		List otherMethodNames = new ArrayList();
		TclResolver resolver = new TclResolver(this.sourceModule,
				this.parser.module);
		if (methods.size() > 0) {
			for (int i = 0; i < methods.size(); i++) {
				MethodDeclaration method = (MethodDeclaration) methods.get(i);
				IModelElement modelElement = resolver
						.findModelElementFrom(method);
				if (modelElement != null) {
					nodeMethods.add(modelElement);
					nodeMethodNames.add(methodNames.get(i));
				} else {
					otherMethods.add(method);
					otherMethodNames.add(methodNames.get(i));
				}
				// TclParseUtil.fi
			}
			findMethods(token, canCompleteEmptyToken, nodeMethods,
					nodeMethodNames);
			findLocalMethods(token, canCompleteEmptyToken, otherMethods,
					otherMethodNames);
		}
	}

	private void fillFunctionsByLevels(char[] token, ASTNode parent,
			List methods, List gmethodNames) {
		List levels = TclParseUtil.findLevelsTo(parser.getModule(), parent);
		int len = levels.size();
		List visited = new ArrayList();
		List methodNames = new ArrayList();
		// visited.addAll(levels);
		for (int j = 0; j < len; ++j) {
			ASTNode astNodeParent = (ASTNode) levels.get(len - 1 - j);
			boolean topLevel = false;
			if (token != null && token.length > 0 && token[0] == ':') {
				topLevel = true;
			}
			if (astNodeParent instanceof TypeDeclaration && !topLevel) {
				// Add all method here.
				TypeDeclaration decl = (TypeDeclaration) astNodeParent;
				List statements = decl.getStatements();
				if (statements != null) {
					processMethods(methods, methodNames, statements, "",
							visited, parent);
				}
			} else if (astNodeParent instanceof ModuleDeclaration) {
				ModuleDeclaration decl = (ModuleDeclaration) astNodeParent;
				List statements = decl.getStatements();
				if (statements != null) {
					processMethods(methods, methodNames, statements,
							topLevel ? "::" : "", visited, parent);
				}
			}
		}
		gmethodNames.addAll(methodNames);
	}

	private void processMethods(List methods, List methodNames,
			List statements, String namePrefix, List visited, ASTNode realParent) {
		for (int i = 0; i < statements.size(); ++i) {
			ASTNode nde = (ASTNode) statements.get(i);
			if (nde instanceof MethodDeclaration) {
				String mName = ((MethodDeclaration) nde).getName();
				if (!mName.startsWith("::")) {
					mName = namePrefix + mName;
				}
				if (realParent instanceof MethodDeclaration) {
					String name = ((MethodDeclaration) realParent).getName();
					String prefix = namePrefix
							+ ((MethodDeclaration) nde).getName();
					if (name.startsWith("::") && !prefix.startsWith("::")) {
						prefix = "::" + prefix;
					}
					if (!name.equals(prefix)) {
						int i1 = name.lastIndexOf("::");
						int i2 = prefix.lastIndexOf("::");
						if (i1 != -1 && i2 != -1) {
							String p1 = name.substring(0, i1);
							String p2 = prefix.substring(0, i2);
							if (p1.startsWith(p2)) {
								// System.out.println("#");
								String nn = prefix.substring(i2 + 2);
								if (!methodNames.contains(nn) /*
																 * &&
																 * !methods.contains(nde)
																 */) {
									if (methodCanBeAdded(nde)) {
										methods.add(nde);
										methodNames.add(nn);
									}
								}
							}
						}
					}
				}
				if (!methodNames.contains(mName) /* && !methods.contains(nde) */) {
					if (methodCanBeAdded(nde)) {
						methods.add(nde);
						methodNames.add(mName);
					}
				}
			} else if (nde instanceof TypeDeclaration && !visited.contains(nde)) {
				List tStatements = ((TypeDeclaration) nde).getStatements();
				visited.add(nde);
				if (realParent instanceof MethodDeclaration) {
					String name = ((MethodDeclaration) realParent).getName();
					String prefix = namePrefix
							+ ((TypeDeclaration) nde).getName();
					if (name.startsWith("::") && !prefix.startsWith("::")) {
						prefix = "::" + prefix;
					}
					if (name.startsWith(namePrefix)) {
						processMethods2(methods, methodNames, tStatements, "",
								realParent);
					}
				}
				String nn = ((TypeDeclaration) nde).getName();
				if (nn.startsWith("::")) {
					nn = nn.substring(2);
				}
				String pr = namePrefix + nn;
				processMethods(methods, methodNames, tStatements, pr + "::",
						visited, realParent);

			}
			visited.add(nde);
		}
	}

	protected boolean methodCanBeAdded(ASTNode nde) {
		return true;
	}

	private void processMethods2(List methods, List methodNames,
			List statements, String namePrefix, ASTNode realParent) {
		for (int i = 0; i < statements.size(); ++i) {
			ASTNode nde = (ASTNode) statements.get(i);
			if (nde instanceof MethodDeclaration) {
				String mName = namePrefix + ((MethodDeclaration) nde).getName();
				if (mName.startsWith("::::")) {
					mName = mName.substring(2);
				}
				if (!methodNames.contains(mName) && !methods.contains(nde)) {
					if (methodCanBeAdded(nde)) {
						methods.add(nde);
						methodNames.add(mName);
					}
				}
			} else if (nde instanceof TypeDeclaration) {
				List tStatements = ((TypeDeclaration) nde).getStatements();
				processMethods2(methods, methodNames, tStatements, namePrefix
						+ ((TypeDeclaration) nde).getName() + "::", realParent);
			}
		}
	}

	protected void findVariables(char[] token, ASTNode parent,
			boolean canCompleteEmptyToken, int beforePosition,
			boolean provideDollar, List cho) {
		List gChoices = new ArrayList();
		if (cho != null) {
			gChoices.addAll(cho);
		}
		if (token.length > 0 && token[0] != '$') {
			provideDollar = false;
		}
		token = removeLastColonFromToken(token);
		if (parent instanceof MethodDeclaration) {
			MethodDeclaration method = (MethodDeclaration) parent;
			List choices = new ArrayList();
			List statements = method.getArguments();
			if (statements != null) {
				for (int i = 0; i < statements.size(); ++i) {
					Argument a = (Argument) statements.get(i);
					if (a != null) {
						String n = a.getName();
						checkAddVariable(choices, n);
					}
				}
			}
			// Process variable setters.
			statements = method.getStatements();
			checkVariableStatements(beforePosition, choices, statements);
			char[][] cc = new char[choices.size()][];
			for (int i = 0; i < choices.size(); ++i) {
				cc[i] = ((String) choices.get(i)).toCharArray();
				gChoices.add(choices.get(i));
			}
			findLocalVariables(token, cc, canCompleteEmptyToken, provideDollar);
		} else if (parent instanceof ModuleDeclaration) {
			ModuleDeclaration module = (ModuleDeclaration) parent;
			checkVariables(token, canCompleteEmptyToken, beforePosition, module
					.getStatements(), provideDollar, gChoices);
		} else if (parent instanceof TypeDeclaration) {
			TypeDeclaration type = (TypeDeclaration) parent;
			checkVariables(token, canCompleteEmptyToken, beforePosition, type
					.getStatements(), provideDollar, gChoices);
			String prefix = "";
			if (provideDollar) {
				prefix = "$" + prefix;
			}
			List statements = type.getStatements();
			for (int l = 0; l < statements.size(); ++l) {
				findASTVariables((ASTNode) statements.get(l), prefix, token,
						canCompleteEmptyToken, gChoices);
			}
		}
		String prefix = "";
		List choices = new ArrayList();
		if ((token.length > 0 && (token[0] == ':' || token[0] == '$'))
				|| token.length == 0 || (token.length > 2 && token[1] == ':')) {
			prefix = "::";
			if (provideDollar) {
				prefix = "$" + prefix;
			}
			findASTVariables(this.parser.getModule(), prefix, token,
					canCompleteEmptyToken, choices);
		}
		// remove dublicates
		for (int i = 0; i < gChoices.size(); ++i) {
			String c = (String) gChoices.get(i);
			if (choices.contains(c)) {
				choices.remove(c);
			}
			if (c.startsWith("$")) {
				String cc = c.substring(1);
				if (choices.contains(cc)) {
					choices.remove(cc);
				}
			}

		}
		char[][] cc = new char[choices.size()][];
		for (int i = 0; i < choices.size(); ++i) {
			cc[i] = ((String) choices.get(i)).toCharArray();
			gChoices.add(choices.get(i));
		}
		findLocalVariables(token, cc, canCompleteEmptyToken, true);

		findGlobalVariables(token, gChoices, provideDollar);
		// Find one level up
		if (!(checkValidParetNode(parent))) {
			// Lets find scope parent
			List findLevelsTo = TclParseUtil.findLevelsTo(parser.getModule(),
					parent);
			ASTNode realParent = null;
			for (Iterator iterator = findLevelsTo.iterator(); iterator
					.hasNext();) {
				ASTNode nde = (ASTNode) iterator.next();
				if (checkValidParetNode(nde)) {
					realParent = nde;
				}
			}
			if (realParent != null && !realParent.equals(parent)) {
				findVariables(token, realParent, canCompleteEmptyToken,
						beforePosition, provideDollar, gChoices);
			}
		}
	}

	private boolean checkValidParetNode(ASTNode parent) {
		return parent instanceof MethodDeclaration
				|| parent instanceof ModuleDeclaration
				|| parent instanceof TypeDeclaration;
	}

	private void findGlobalVariables(char[] token, final List choices,
			final boolean provideDollar) {
		final List fields = new ArrayList();
		final List types = new ArrayList();
		boolean provideDots = false;
		SearchRequestor requestor = new SearchRequestor() {
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				Object element = match.getElement();
				if (element instanceof IType) {
					IType type = (IType) element;
					if ((type.getFlags() & Modifiers.AccNameSpace) == 0) {
						return;
					}
					if (!(type.getParent() instanceof ISourceModule)) {
						return;
					}
					String tName = type.getTypeQualifiedName();
					if (!choices.contains(tName)) {
						types.add(type);
					}
					// if( token.length > 3 ) {
					processTypeFields(choices, fields, type);
					// }
				} else if (element instanceof IField) {
					IField field = (IField) element;
					if ((field.getParent() instanceof IType)) {
						return;
					}
					String mn = field.getTypeQualifiedName("$", false)
							.replaceAll("\\$", "::");
					if (!mn.startsWith("::")) {
						mn = "::" + mn;
					}
					if (provideDollar) {
						mn = "$" + mn;
					}
					if (!choices.contains(mn)
							&& !choices.contains(mn.substring(2))) {
						fields.add(field);
						choices.add(mn);
					}
				}
			}

			private void processTypeFields(final List methodNames,
					final List methods, IType type) throws ModelException {
				IField[] tmethods = type.getFields();
				for (int i = 0; i < tmethods.length; ++i) {
					String mn = tmethods[i].getTypeQualifiedName("$", false)
							.replaceAll("\\$", "::");
					if (!mn.startsWith("::")) {
						mn = "::" + mn;
					}
					if (!methodNames.contains(mn)) {
						methods.add(tmethods[i]);
						methodNames.add(mn);
					}
				}
				IType[] types = type.getTypes();
				for (int i = 0; i < types.length; ++i) {
					processTypeFields(methodNames, methods, types[i]);
				}
			}
		};
		IDLTKLanguageToolkit toolkit = null;
		try {
			toolkit = DLTKLanguageManager
					.getLanguageToolkit(this.scriptProject);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		if (token.length >= 1 && token[0] == '$') {
			char[] token2 = new char[token.length - 1];
			for (int i = 0; i < token.length - 1; ++i) {
				token2[i] = token[i + 1];
			}
			token = token2;
		}
		String to = new String(token);
		if (token != null && token.length >= 3 && token[0] == ':' /*
																	 * &&
																	 * token[1] ==
																	 * ':'
																	 */) {
			provideDots = true;
			String[] tokens = to.split("::");
			if (tokens.length < 2) {
				return;
			}
			final String tok = tokens[1];
			try {
				search(tok + "*", IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				int nonNoneCount = 0;
				String mtok = null;
				for (int i = 0; i < tokens.length; ++i) {
					if (tokens[i].length() > 0) {
						nonNoneCount++;
						if (mtok == null) {
							mtok = tokens[i];
						}
					}
				}
				if (nonNoneCount == 1 && tok.length() >= 2) {
					search(new String(mtok) + "*", IDLTKSearchConstants.FIELD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if (DLTKCore.VERBOSE_COMPLETION) {
				System.out.println("Completion methods cound:" + fields.size());
			}
		} else if (token != null && token.length >= 1 && token[0] != ':') {
			try {
				String[] tokens = to.split("::");
				if (tokens.length == 0) {
					return;
				}
				String tok = tokens[0];
				search(tok + "*", IDLTKSearchConstants.TYPE,
						IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				int nonNoneCount = 0;
				for (int i = 0; i < tokens.length; ++i) {
					if (tokens[i].length() > 0) {
						nonNoneCount++;
					}
				}
				if (nonNoneCount == 1 && tok.length() >= 2) {
					search(tok + "*", IDLTKSearchConstants.FIELD,
							IDLTKSearchConstants.DECLARATIONS, scope, requestor);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if (DLTKCore.VERBOSE_COMPLETION) {
				System.out.println("Completion methods cound:" + fields.size());
			}
		}
		findTypes(token, true, types);
		findFields(token, false, fields, provideDollar ? "$" : "");
	}

	protected void findASTVariables(ASTNode node, String prefix, char[] token,
			boolean canCompleteEmptyToken, List choices) {
		List statements = null;
		String add = "";
		if (node instanceof ModuleDeclaration) {
			statements = ((ModuleDeclaration) node).getStatements();
		} else if (node instanceof TypeDeclaration) {
			statements = ((TypeDeclaration) node).getStatements();
			String nme = ((TypeDeclaration) node).getName();
			if (nme.startsWith("::")) {
				add = nme.substring(2) + "::";
			} else {
				add = nme + "::";
			}
		}
		if (statements != null) {
			for (int i = 0; i < statements.size(); ++i) {
				ASTNode nde = (ASTNode) statements.get(i);
				if (nde instanceof TclStatement) {
					String[] variable = TclParseUtils
							.returnVariable((TclStatement) nde);
					if (variable != null) {
						for (int u = 0; u < variable.length; ++u) {
							String prev = preProcessVariable(variable[u])
									.substring(1);
							String var = prefix + add;
							if (var.endsWith("::") && prev.startsWith("::")) {
								var = var + prev.substring(2);
							} else {
								var = var + prev;
							}
							if (!choices.contains(var)) {
								choices.add(var);
							}
						}
					}
				} else if (nde instanceof FieldDeclaration) {
					FieldDeclaration field = (FieldDeclaration) nde;
					checkAddVariable(choices, prefix + add + field.getName());
				}
				findASTVariables(nde, prefix + add, token,
						canCompleteEmptyToken, choices);
			}
		}
	}

	private void checkVariables(char[] token, boolean canCompleteEmptyToken,
			int beforePosition, List statements, boolean provideDollar,
			List gChoices) {
		List choices = new ArrayList();
		// Process variable setters.
		checkVariableStatements(beforePosition, choices, statements);
		char[][] cc = new char[choices.size()][];
		for (int i = 0; i < choices.size(); ++i) {
			cc[i] = ((String) choices.get(i)).toCharArray();
			gChoices.add(choices.get(i));
		}
		findLocalVariables(token, cc, canCompleteEmptyToken, provideDollar);
	}

	protected void checkVariableStatements(int beforePosition,
			final List choices, List statements) {
		if (statements != null) {
			for (int i = 0; i < statements.size(); ++i) {
				ASTNode node = (ASTNode) statements.get(i);
				if (node instanceof FieldDeclaration) {
					FieldDeclaration decl = (FieldDeclaration) node;
					this.checkAddVariable(choices, decl.getName());
				} else if (node instanceof TclStatement
						&& node.sourceEnd() < beforePosition) {
					TclStatement s = (TclStatement) node;
					checkTclStatementForVariables(choices, s);
					Expression commandId = s.getAt(0);
					// if (commandId != null
					// && commandId instanceof SimpleReference) {
					// String name = ((SimpleReference) commandId).getName();
					// if (name.equals("if")) {
					// processIf(s, beforePosition, choices);
					// } else if (name.equals("while")) {
					// processWhile(s, beforePosition, choices);
					// } else if (name.equals("for")) {
					// processFor(s, beforePosition, choices);
					// }
					// }
				} else {
					ASTVisitor visitor = new ASTVisitor() {
						public boolean visit(Statement s) throws Exception {
							if (s instanceof FieldDeclaration) {
								String name = TclParseUtil.getElementFQN(s,
										"::", parser.module);
								checkAddVariable(choices,
								/* ((FieldDeclaration) s).getName() */name);
							} else if (s instanceof TclStatement) {
								checkTclStatementForVariables(choices,
										(TclStatement) s);
							}
							return super.visit(s);
						}

					};
					try {
						node.traverse(visitor);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void checkTclStatementForVariables(final List choices,
			TclStatement s) {
		String[] variable = TclParseUtils.returnVariable(s);
		if (variable != null) {
			for (int u = 0; u < variable.length; ++u) {
				checkAddVariable(choices, variable[u]);
			}
		}
	}

	protected void checkAddVariable(List choices, String n) {
		String str = preProcessVariable(n);
		if (!choices.contains(str)) {
			choices.add(str);
		}
	}

	private String preProcessVariable(String n) {
		String str;
		if (n.startsWith("$")) {
			return n;
		}
		if (n.indexOf(' ') != -1) {
			str = "$" + '{' + n + '}';
		} else {
			str = "$" + n;
		}
		return str;
	}

	public IAssistParser getParser() {
		return parser;
	}

	// TODO: Remove this. Actually all are done in extending of tcl statements
	// in TclCompletionParser.
	protected int getEndOfEmptyToken() {
		// TODO: Add more complicated code here...
		return this.actualCompletionPosition;
	}

	protected String processMethodName(IMethod method, String tok) {
		return TclParseUtils.processMethodName(method, tok);
	}

	protected String processFieldName(IField method, String tok) {
		return TclParseUtils.processFieldName(method, tok);
	}

	protected String processTypeName(IType method, String tok) {
		String name = TclParseUtils.processTypeName(method, tok);
		if (name.startsWith("::")
				&& ((tok.length() > 1 && tok.charAt(0) != ':' && tok.charAt(1) != ':') || tok
						.length() == 0)) {
			name = name.substring(2);
		}
		return name;

	}
}
