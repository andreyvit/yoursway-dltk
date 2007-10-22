package org.eclipse.dltk.xotcl.internal.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.env.lookup.Scope;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.internal.core.AbstractExternalSourceModule;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclASTUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclCompletionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordArgumentOrFunctionArgument;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnVariable;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.XOTclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclExInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClassInstance;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclInstProc;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclProc;

public class XOTclCompletionEngine extends TclCompletionEngine {
	public XOTclCompletionEngine() {
		this.parser = new XOTclCompletionParser();
	}

	protected boolean complete(ASTNode astNode, ASTNode astNodeParent,
			Scope scope, boolean insideTypeAnnotation) {
		setSourceRange(astNode.sourceStart(), astNode.sourceEnd());
		if (astNode instanceof CompletionOnKeywordOrFunction) {
			CompletionOnKeywordOrFunction key = (CompletionOnKeywordOrFunction) astNode;
			processCompletionOnKeywords(key);
			
			processCompletionOnFunctions(astNodeParent, key);
			
			if (!this.requestor.isIgnored(CompletionProposal.TYPE_REF)) {
				Set methodNames = new HashSet();
				char[] token = key.getToken();
				token = removeLastColonFromToken(token);
				findLocalXOTclClasses(token, methodNames, astNodeParent);
				// findLocalClasses
				findXOTclClasses(token, methodNames);
			}
			if (!this.requestor.isIgnored(CompletionProposal.FIELD_REF)) {
				Set methodNames = new HashSet();
				char[] token = key.getToken();
				token = removeLastColonFromToken(token);
				// findLocalClassInstances
				findXOTclClassInstances(token, methodNames);
			}
		} else if (astNode instanceof CompletionOnVariable) {
			processCompletionOnVariables(astNode);
		} else if (astNode instanceof CompletionOnKeywordArgumentOrFunctionArgument) {
			CompletionOnKeywordArgumentOrFunctionArgument compl = (CompletionOnKeywordArgumentOrFunctionArgument) astNode;
			if (compl.argumentIndex() == 1) {
				// Completion on two argument keywords
				TclStatement st = compl.getStatement();
				Expression at = st.getAt(0);
				if (at instanceof SimpleReference) {
					String name = ((SimpleReference) at).getName();
					String prefix = name + " " + new String(compl.getToken());
					
					Set methodNames = new HashSet();
					processPartOfKeywords(compl, prefix, methodNames);
					// Check for class and its methods.
					completeClassMethods(name, compl.getToken(), methodNames);

					// Lets find instance with specified name.
					FieldDeclaration var = XOTclParseUtil
							.findXOTclInstanceVariableDeclarationFrom(
									this.parser.getModule(), TclParseUtil
											.getPrevParent(this.parser
													.getModule(), st), name);
					if (var == null) {
						var = searchFieldFromMixin(name);
					}
					if (var != null) {
						completionForInstanceVariableMethods(var, compl
								.getToken(), methodNames);
					}
				}
			}
		}
		return true;
	}

	private void findLocalXOTclClasses(char[] token, Set methodNames,
			ASTNode astNodeParent) {
		ASTNode parent = TclParseUtil.getScopeParent(parser.getModule(),
				astNodeParent);
		List statements = TclASTUtil.getStatements(parent);
		for (Iterator iterator = statements.iterator(); iterator.hasNext();) {
			ASTNode nde = (ASTNode) iterator.next();
			if (nde instanceof TypeDeclaration
					&& ((((TypeDeclaration) nde).getModifiers() & IXOTclModifiers.AccXOTcl) != 0)) {
				
			}
		}
	}

	private void findXOTclClassInstances(char[] token, Set methodNames) {
		// IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		String to = new String(token);
		if (to.startsWith("::")) {
			to = to.substring(2);
		}
		if (to.length() == 0) {
			return;
		}
		findClassesInstanceFromMixin(methodNames, to + "*");

		findFields(token, true, toList(methodNames), "");
	}

	private FieldDeclaration searchFieldFromMixin(String name) {
		return null;
	}

	private void completionForInstanceVariableMethods(FieldDeclaration var,
			char[] token, Set methodNames) {
		String keyPrefix = null;
		if (var instanceof XOTclInstanceVariable) {
			XOTclInstanceVariable ivar = (XOTclInstanceVariable) var;
			TypeDeclaration declaringType = ivar.getDeclaringType();
			keyPrefix = TclParseUtil.getElementFQN(declaringType,
					IMixinRequestor.MIXIN_NAME_SEPARATOR, this.parser
							.getModule());
			if (keyPrefix.startsWith(IMixinRequestor.MIXIN_NAME_SEPARATOR)) {
				keyPrefix = keyPrefix.substring(1);
			}

		} else if (var instanceof XOTclExInstanceVariable) {
			XOTclExInstanceVariable ivar = (XOTclExInstanceVariable) var;
			String className = ivar.getDeclaringClassParameter().getClassName();
			if (className.startsWith("::")) {
				className = className.substring(2);
			}
			keyPrefix = className.replaceAll("::",
					IMixinRequestor.MIXIN_NAME_SEPARATOR);
		}
		Set methods = new HashSet();
		findInstProcsFromMixin(methods, keyPrefix
				+ IMixinRequestor.MIXIN_NAME_SEPARATOR + "*");
		findMethodsShortName(token, methods, methodNames);
		// We need to handle supers

		addKeywords(token, XOTclKeywords.XOTclCommandClassArgs, methodNames);
		addKeywords(token, XOTclKeywords.XOTclCommandObjectArgs, methodNames);
	}

	protected boolean methodCanBeAdded(ASTNode nde) {
		if (nde instanceof XOTclMethodDeclaration) {
			return false;
		}
		return super.methodCanBeAdded(nde);
	}

	private void completeClassMethods(String name, char[] cs, Set methodNames) {
		Set completions = new HashSet();

		if (name.startsWith("::")) {
			name = name.substring(2);
		}
		findClassesFromMixin(completions, name);
		if (completions.size() >= 1) { // We found class with such name, so
			// this is method completion.
			Set methods = new HashSet();
			methods.addAll(methodNames);
			findProcsFromMixin(methods, name + "::*");
			methods.removeAll(methodNames);
			findMethodsShortName(cs, methods, methodNames);
			// We also need to add Object and Class methods
			// We need to add superclass methods
			addKeywords(cs, XOTclKeywords.XOTclCommandClassArgs, methodNames);
			addKeywords(cs, XOTclKeywords.XOTclCommandObjectArgs, methodNames);
		}
	}

	private void addKeywords(char[] cs, String[] keywords, Set methodNames) {
		List k = new ArrayList();
		String token = new String(cs);
		for (int i = 0; i < keywords.length; ++i) {
			String kkw = keywords[i];
			if (kkw.startsWith(token)) {
				if (!methodNames.contains(kkw)) {
					k.add(kkw);
					methodNames.add(kkw);
				}
			}
		}
		String kw[] = (String[]) k.toArray(new String[k.size()]);
		char[][] choices = new char[kw.length][];
		for (int i = 0; i < kw.length; ++i) {
			choices[i] = kw[i].toCharArray();
		}
		findKeywords(cs, choices, true);
	}

	private void findMethodsShortName(char[] cs, Set methods, Set allMethods) {
		List methodsList = toList(methods);
		List methodNames = new ArrayList();
		for (Iterator iterator = methodsList.iterator(); iterator.hasNext();) {
			IMethod method = (IMethod) iterator.next();
			String methodName = method.getElementName();
			if (!methodNames.contains(methodName)) {
				methodNames.add(methodName);
				allMethods.add(methodName);
			}
		}
		findMethods(cs, true, methodsList, methodNames);
	}

	protected void findProcsFromMixin(final Set methods, String tok) {
		findMixinTclElement(methods, tok, XOTclProc.class);
	}

	protected void findInstProcsFromMixin(final Set methods, String tok) {
		findMixinTclElement(methods, tok, XOTclInstProc.class);
	}

	private void findXOTclClasses(char[] token, Set methodNames) {

		// IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		String to_ = new String(token);
		String to = to_;
		if (to.startsWith("::")) {
			to = to.substring(2);
		}
		if (to.length() == 0) {
			return;
		}
		Set methods = new HashSet();
		methods.addAll(methodNames);
		findClassesFromMixin(methods, to + "*");
		methods.removeAll(methodNames);
		removeSameFrom(methodNames, methods, to_);

		findTypes(token, true, toList(methods));
	}

	protected void findClassesFromMixin(final Set completions, String tok) {
		findMixinTclElement(completions, tok, XOTclClass.class);
	}

	protected void findClassesInstanceFromMixin(final Set completions,
			String tok) {
		findMixinTclElement(completions, tok, XOTclClassInstance.class);
	}

	protected boolean moduleFilter(Set methods, IType type) {
		org.eclipse.dltk.core.ISourceModule sourceModule = (org.eclipse.dltk.core.ISourceModule) type
				.getAncestor(IModelElement.SOURCE_MODULE);
		if (!(sourceModule instanceof AbstractExternalSourceModule)) {
			return true;
		}
		String fullyQualifiedName = type.getFullyQualifiedName();
		for (Iterator iterator = methods.iterator(); iterator.hasNext();) {
			IType element = (IType) iterator.next();
			if (element.getFullyQualifiedName().equals(fullyQualifiedName)) {
				org.eclipse.dltk.core.ISourceModule eModule = (org.eclipse.dltk.core.ISourceModule) element
						.getAncestor(IModelElement.SOURCE_MODULE);
				if (sourceModule.getPath().equals(eModule.getPath())) {
					return false;
				}
			}
		}
		return true;
	}
}
