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
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.internal.core.AbstractExternalSourceModule;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclCompletionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordArgumentOrFunctionArgument;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.completion.CompletionOnVariable;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.xotcl.core.XOTclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclExInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;
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
			if (!this.requestor.isIgnored(CompletionProposal.KEYWORD)) {
				String[] kw = key.getPossibleKeywords();
				completeForKeywordOrFunction(key, kw);
			}
			/*
			 * TODO: Add search for functions. Variables start with $ so it will
			 * not be here... To all functions are possible. Functions with
			 * ::will not be here.
			 * 
			 */
			if (!this.requestor
					.isIgnored(CompletionProposal.METHOD_DECLARATION)) {
				List methodNames = new ArrayList();
				findLocalFunctions(key.getToken(), key.canCompleteEmptyToken(),
						astNodeParent, methodNames);
				char[] token = key.getToken();
				token = removeLastColonFromToken(token);
				findNamespaceFunctions(token, methodNames);
			}
			if (!this.requestor.isIgnored(CompletionProposal.TYPE_REF)) {
				List methodNames = new ArrayList();
				char[] token = key.getToken();
				token = removeLastColonFromToken(token);
				findXOTclClasses(token, methodNames);
			}
		} else if (astNode instanceof CompletionOnVariable) {
			if (!this.requestor
					.isIgnored(CompletionProposal.LOCAL_VARIABLE_REF)
					&& !this.requestor
							.isIgnored(CompletionProposal.VARIABLE_DECLARATION)) {
				CompletionOnVariable completion = (CompletionOnVariable) astNode;
				findVariables(completion.getToken(), completion.getInNode(),
						completion.canHandleEmpty(), astNode.sourceStart(),
						completion.getProvideDollar(), null);
			}
		} else if (astNode instanceof CompletionOnKeywordArgumentOrFunctionArgument) {
			CompletionOnKeywordArgumentOrFunctionArgument compl = (CompletionOnKeywordArgumentOrFunctionArgument) astNode;
			if (compl.argumentIndex() == 1) {
				// Completion on two argument keywords
				TclStatement st = compl.getStatement();
				Expression at = st.getAt(0);
				if (at instanceof SimpleReference) {
					String name = ((SimpleReference) at).getName();
					String prefix = name + " " + new String(compl.getToken());
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
					// Check for class and its methods.
					completeClassMethods(name, compl.getToken());

					// Lets find instance with specified name.
					FieldDeclaration var = XOTclParseUtil
							.findXOTclInstanceVariableDeclarationFrom(
									this.parser.getModule(), TclParseUtil
											.getPrevParent(this.parser
													.getModule(), st), name);
					if (var != null) {
						completionForInstanceVariableMethods(var, compl
								.getToken());
					}
				}
			}
		}
		return true;
	}

	private void completionForInstanceVariableMethods(FieldDeclaration var,
			char[] token) {
		String keyPrefix = null;
		if (var instanceof XOTclInstanceVariable) {
			XOTclInstanceVariable ivar = (XOTclInstanceVariable) var;
			TypeDeclaration declaringType = ivar.getDeclaringType();
			keyPrefix = TclParseUtil.getElementFQN(declaringType,
					IMixinRequestor.MIXIN_NAME_SEPARATOR, this.parser
							.getModule());
			if( keyPrefix.startsWith(IMixinRequestor.MIXIN_NAME_SEPARATOR)) {
				keyPrefix = keyPrefix.substring(1);
			}

		} else if (var instanceof XOTclExInstanceVariable) {
			XOTclExInstanceVariable ivar = (XOTclExInstanceVariable) var;
			String className = ivar.getDeclaringClassParameter().getClassName();
			if( className.startsWith("::")) {
				className = className.substring(2);
			}
			keyPrefix = className.replaceAll("::", IMixinRequestor.MIXIN_NAME_SEPARATOR);
		}
		Set methods = new HashSet();
		findInstProcsFromMixin(methods, keyPrefix + IMixinRequestor.MIXIN_NAME_SEPARATOR + "*");
		findMethodsShortName(token, methods);
		addKeywords(token, XOTclKeywords.XOTclCommandClassArgs);
		addKeywords(token, XOTclKeywords.XOTclCommandObjectArgs);
	}

	protected boolean methodCanBeAdded(ASTNode nde) {
		if (nde instanceof XOTclMethodDeclaration) {
			return false;
		}
		return super.methodCanBeAdded(nde);
	}

	private void completeClassMethods(String name, char[] cs) {
		Set completions = new HashSet();

		if (name.startsWith("::")) {
			name = name.substring(2);
		}
		findClassesFromMixin(completions, name);
		if (completions.size() >= 1) { // We found class with such name, so
			// this is method completion.
			Set methods = new HashSet();
			findProcsFromMixin(methods, name + "::*");
			findMethodsShortName(cs, methods);
			// We also need to add Object and Class methods
			// We need to add superclass methods
			addKeywords(cs, XOTclKeywords.XOTclCommandClassArgs);
			addKeywords(cs, XOTclKeywords.XOTclCommandObjectArgs);
		}
	}

	private void addKeywords(char[] cs, String[] keywords) {
		List k = new ArrayList();
		String token = new String(cs);
		for( int i = 0; i < keywords.length; ++i  ) {
			String kkw = keywords[i];
			if (kkw.startsWith(token)) {
				k.add(kkw);
			}
		}
		String kw[] = (String[]) k.toArray(new String[k.size()]);
		char[][] choices = new char[kw.length][];
		for (int i = 0; i < kw.length; ++i) {
			choices[i] = kw[i].toCharArray();
		}
		findKeywords(cs, choices, true);
	}

	private void findMethodsShortName(char[] cs, Set methods) {
		List methodsList = toList(methods);
		List methodNames = new ArrayList();
		for (Iterator iterator = methodsList.iterator(); iterator.hasNext();) {
			IMethod method = (IMethod) iterator.next();
			methodNames.add(method.getElementName());
		}
		findMethods(cs, true, methodsList, methodNames);
	}

	protected void findProcsFromMixin(final Set methods, String tok) {
		long delta = 200;
		long time = System.currentTimeMillis();
		IMixinElement[] find = TclMixinModel.getInstance().find(
				tok.replaceAll("::", IMixinRequestor.MIXIN_NAME_SEPARATOR),
				delta);
		if (TRACE_COMPLETION_TIME) {
			System.out.println("findMethod from mixin: request model:"
					+ Long.toString(System.currentTimeMillis() - time));
		}
		time = System.currentTimeMillis();
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null && allObjects[j] instanceof XOTclProc) {
					XOTclProc field = (XOTclProc) allObjects[j];
					IModelElement method = field.getModelElement();
					if (method != null) {
						// We should filter external source modules with same
						// external path.
						if (moduleFilter(methods, (IMethod) method)) {
							methods.add(method);
						}
					}
				}
			}
			// if(System.currentTimeMillis()-time > delta) {
			// return;
			// }
		}
	}

	protected void findInstProcsFromMixin(final Set methods, String tok) {
		long delta = 200;
		long time = System.currentTimeMillis();
		IMixinElement[] find = TclMixinModel.getInstance().find(
				tok.replaceAll("::", IMixinRequestor.MIXIN_NAME_SEPARATOR),
				delta);
		if (TRACE_COMPLETION_TIME) {
			System.out.println("findMethod from mixin: request model:"
					+ Long.toString(System.currentTimeMillis() - time));
		}
		time = System.currentTimeMillis();
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& allObjects[j] instanceof XOTclInstProc) {
					XOTclInstProc field = (XOTclInstProc) allObjects[j];
					IModelElement method = field.getModelElement();
					if (method != null) {
						// We should filter external source modules with same
						// external path.
						if (moduleFilter(methods, (IMethod) method)) {
							methods.add(method);
						}
					}
				}
			}
		}
	}

	private void findXOTclClasses(char[] token, List methodNames) {
		final Set classes = new HashSet();

		// IDLTKSearchScope scope = SearchEngine.createWorkspaceScope(toolkit);
		String to = new String(token);
		if (token != null && token.length >= 3 && token[0] == ':') {
			String[] tokens = to.split("::");
			if (tokens.length < 2) {
				return;
			}
			final String tok = to.substring(2);
			findClassesFromMixin(classes, tok + "*");
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
			if (DLTKCore.VERBOSE_COMPLETION) {
				System.out
						.println("Completion methods cound:" + classes.size());
			}
		} else if (token != null && token.length >= 1 && token[0] != ':') {
			String[] tokens = to.split("::");
			if (tokens.length == 0) {
				return;
			}
			String tok = tokens[0];

			findClassesFromMixin(classes, tok + "*");
			int nonNoneCount = 0;
			for (int i = 0; i < tokens.length; ++i) {
				if (tokens[i].length() > 0) {
					nonNoneCount++;
				}
			}
			if (DLTKCore.VERBOSE_COMPLETION) {
				System.out
						.println("Completion methods cound:" + classes.size());
			}
		}
		findTypes(token, true, toList(classes));
	}

	protected void findClassesFromMixin(final Set completions, String tok) {
		long delta = 200;
		long time = System.currentTimeMillis();
		IMixinElement[] find = TclMixinModel.getInstance().find(
				tok.replaceAll("::", IMixinRequestor.MIXIN_NAME_SEPARATOR),
				delta);
		if (TRACE_COMPLETION_TIME) {
			System.out.println("findMethod from mixin: request model:"
					+ Long.toString(System.currentTimeMillis() - time));
		}
		time = System.currentTimeMillis();
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& allObjects[j] instanceof XOTclClass) {
					XOTclClass field = (XOTclClass) allObjects[j];
					IModelElement method = field.getModelElement();
					if (method != null) {
						// We should filter external source modules with same
						// external path.
						if (moduleFilter(completions, (IType) method)) {
							completions.add(method);
						}
					}
				}
			}
		}
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
