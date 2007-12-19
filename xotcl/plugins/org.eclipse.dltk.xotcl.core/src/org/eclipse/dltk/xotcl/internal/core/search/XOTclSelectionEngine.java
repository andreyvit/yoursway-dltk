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
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclResolver;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclSelectionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnAST;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnNode;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnVariable;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclExInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;
import org.eclipse.dltk.xotcl.internal.core.XOTclResolver;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclCommandDetector.XOTclGlobalClassParameter;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclInstProc;

public class XOTclSelectionEngine extends TclSelectionEngine {

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
			ASTNode originalNode = key.getOriginalNode();
			if (originalNode instanceof TclStatement) {
				processXOTclCommandCalls((TclStatement) originalNode);
			}
		} else if (astNode instanceof SelectionOnVariable) {
			SelectionOnVariable completion = (SelectionOnVariable) astNode;
			findVariables(completion.getName(), astNodeParent, astNode
					.sourceStart());
		} else if (astNode instanceof SelectionOnAST) {
			ASTNode node = ((SelectionOnAST) astNode).getNode();
			if (node instanceof XOTclMethodDeclaration) {
				processSelectXOTclMethodDeclaration(
						(ExtendedTclMethodDeclaration) node,
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
						(ExtendedTclMethodDeclaration) node,
						this.actualSelectionStart);
			} else if (node instanceof TclStatement) {
				// We need to check for XOTcl command calls.
				processXOTclCommandCalls((TclStatement) node);
			} else if (node instanceof XOTclInstanceVariable) {
				processXOTclInstanceVariable((XOTclInstanceVariable) node);
			} else if (node instanceof XOTclExInstanceVariable) {
				XOTclExInstanceVariable ex = (XOTclExInstanceVariable) node;
				XOTclGlobalClassParameter declaringClassParameter = ex
						.getDeclaringClassParameter();
				IModelElement resolveElement = declaringClassParameter
						.resolveElement();
				if (resolveElement != null) {
					selectionElements.add(resolveElement);
				}
			}
		}
	}

	private void processXOTclInstanceVariable(XOTclInstanceVariable node) {
		SimpleReference classInstanceName = node.getClassInstanceName();
		if (classInstanceName.sourceStart() <= this.actualSelectionStart
				&& this.actualSelectionStart <= classInstanceName.sourceEnd()) {
			TypeDeclaration declaringType = node.getDeclaringType();
			IModelElement type = this.findElementFromNode(declaringType);
			if (type != null) {
				this.selectionElements.add(type);
			}
		}
	}

	private void processXOTclCommandCalls(TclStatement node) {
		if (node.getCount() == 0) {
			return;
		}
		ASTNode parent = TclParseUtil.getPrevParent(this.parser.getModule(),
				node);
		String prefix = TclParseUtil.getElementFQN(parent,
				IMixinRequestor.MIXIN_NAME_SEPARATOR, this.parser.getModule());
		Expression commandExpr = node.getAt(0);
		String command = TclParseUtil.getNameFromNode(commandExpr);
		if (command != null && command.startsWith("::")) {
			String name = command.substring(2);
			// Check class proc call
			String[] split = name.split("::");
			IModelElement[] typeMixin = XOTclResolver.findTypeMixin(
					tclNameToKey(name), split[split.length - 1]);
			checkMixinTypeForMethod(node, commandExpr, typeMixin, prefix);
		} else if (command != null) {
			String[] split = command.split("::");
			if (parent instanceof ModuleDeclaration) {
				IModelElement[] typeMixin = XOTclResolver.findTypeMixin(
						tclNameToKey(command), split[split.length - 1]);
				checkMixinTypeForMethod(node, commandExpr, typeMixin, prefix);
			} else {
				IModelElement[] typeMixin = XOTclResolver.findTypeMixin(prefix
						+ IMixinRequestor.MIXIN_NAME_SEPARATOR
						+ tclNameToKey(command), split[split.length - 1]);
				checkMixinTypeForMethod(node, commandExpr, typeMixin, prefix);
			}
		}
	}

	private boolean checkMixinTypeForMethod(TclStatement node,
			Expression commandExpr, IModelElement[] typeMixin, String prefix) {
		for (int i = 0; i < typeMixin.length; i++) {
			// Select type if point on type
			if (commandExpr.sourceStart() <= this.actualSelectionStart
					&& this.actualSelectionStart <= commandExpr.sourceEnd()) {
				this.selectionElements.add(typeMixin[i]);
				return true;
			}
			Expression callExpr = node.getAt(1);
			if (node.getCount() > 1 && typeMixin[i] instanceof IParent) {
				if (callExpr.sourceStart() <= this.actualSelectionStart
						&& this.actualSelectionStart <= callExpr.sourceEnd()) {
					String call = TclParseUtil.getNameFromNode(callExpr);
					IParent eParent = (IParent) typeMixin[i];
					if (call != null) {
						// search for method call in selected type
						IModelElement[] children = null;
						try {
							children = eParent.getChildren();
						} catch (ModelException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (children != null) {
							for (int j = 0; j < children.length; j++) {
								if (children[j].getElementType() == IModelElement.METHOD
										&& children[j].getElementName().equals(
												call)) {
									this.selectionElements.add(children[j]);
									return true;
								}
							}
						}
						// We need to check super type of this type.
						if (eParent instanceof IType) {
							IType type = (IType) eParent;
							try {
								String[] superClasses = type.getSuperClasses();
								String command = TclParseUtil
										.getNameFromNode(commandExpr);
								if (superClasses != null) {
									for (int j = 0; j < superClasses.length; j++) {

										IModelElement[] ptypeMixin = XOTclResolver
												.findTypeMixin(
														(prefix.length() > 0 ? prefix
																+ IMixinRequestor.MIXIN_NAME_SEPARATOR
																: "")
																+ tclNameToKey(superClasses[j]),
														superClasses[j]);
										String[] split = superClasses[j]
												.split("::");
										checkMixinTypeForMethod(node,
												commandExpr, ptypeMixin,
												split[split.length - 1]);
									}
								}
							} catch (ModelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return false;
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

					IModelElement methodElement = TclResolver
							.findChildrenByName(node.getCallName().getName(),
									(IParent) parent);
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
			ExtendedTclMethodDeclaration node, int position) {
		ASTNode type = node.getDeclaringType();
		if (type instanceof TypeDeclaration) {
			SimpleReference ref = node.getTypeNameRef();
			IModelElement parent = findElementFromNode(type);
			if (parent != null && parent instanceof IParent) {
				if (node.getNameStart() <= position
						&& position <= node.getNameEnd()) {

					IModelElement methodElement = TclResolver
							.findChildrenByName(node.getName(),
									(IParent) parent);
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
		FieldDeclaration instanceVar = call.getInstanceVariable();
		SimpleReference callName = call.getCallName();
		SimpleReference instName = call.getInstNameRef();
		if (instanceVar != null && instanceVar instanceof XOTclInstanceVariable) {
			// Check for method
			if (callName.sourceStart() <= position
					&& position <= callName.sourceEnd()) {
				XOTclInstanceVariable instanceVariable = (XOTclInstanceVariable) instanceVar;
				TypeDeclaration declaringType = instanceVariable
						.getDeclaringType();
				if (declaringType != null) {
					IModelElement parent = findElementFromNode(declaringType);
					if (parent != null) {
						if (checkMethodFrom(declaringType, callName, parent)) {
							return;
						}
						// Check mixin for selected class.
						String typeMixin = TclParseUtil.getElementFQN(
								declaringType,
								IMixinRequestor.MIXIN_NAME_SEPARATOR,
								this.parser.getModule())
								+ IMixinRequestor.MIXIN_NAME_SEPARATOR;

						findMethodMixin(typeMixin
								+ tclNameToKey(callName.getName()), callName
								.getName());
						// Check super.
						ASTNode nde = TclParseUtil.getPrevParent(this.parser
								.getModule(), declaringType);
						List supersToHandle = new ArrayList();
						fillSuperClassesTo(declaringType, supersToHandle);
						TypeDeclaration prev = declaringType;
						while (supersToHandle.size() > 0) {
							String superClassName = (String) supersToHandle
									.get(0);
							supersToHandle.remove(0);
							TypeDeclaration sType = TclParseUtil
									.findXOTclTypeDeclarationFrom(this.parser
											.getModule(), nde, superClassName);
							if (sType != null) {
								prev = sType;
								fillSuperClassesTo(sType, supersToHandle);
								IModelElement sParent = findElementFromNode(sType);
								if (checkMethodFrom(sType, callName, sParent)) {
									return;
								}
								String sTypeMixin = TclParseUtil.getElementFQN(
										sType,
										IMixinRequestor.MIXIN_NAME_SEPARATOR,
										this.parser.getModule())
										+ IMixinRequestor.MIXIN_NAME_SEPARATOR;

								findXOTclMethodMixin(sTypeMixin
										+ tclNameToKey(callName.getName()),
										callName.getName());
								// System.out.println("COOL");
							} else {
								String sTypeMixin = "";
								if (prev != null) {
									ASTNode prevParent = TclParseUtil
											.getPrevParent(this.parser
													.getModule(), prev);
									if (prevParent instanceof ModuleDeclaration) {
										sTypeMixin = tclNameToKey(superClassName)
												+ IMixinRequestor.MIXIN_NAME_SEPARATOR;
									} else {
										sTypeMixin = TclParseUtil
												.getElementFQN(
														prevParent,
														IMixinRequestor.MIXIN_NAME_SEPARATOR,
														this.parser.getModule())
												+ IMixinRequestor.MIXIN_NAME_SEPARATOR
												+ tclNameToKey(superClassName)
												+ IMixinRequestor.MIXIN_NAME_SEPARATOR;
									}
								}

								findXOTclMethodMixin(sTypeMixin
										+ tclNameToKey(callName.getName()),
										callName.getName());
								// Lets also look to toplevel
								findXOTclMethodMixin(tclNameToKey(superClassName)
										+ IMixinRequestor.MIXIN_NAME_SEPARATOR
										+ tclNameToKey(callName.getName()),
										callName.getName());
							}
						}
					}
				}
			}
		} else if (instanceVar != null
				&& instanceVar instanceof XOTclExInstanceVariable) {
			// Check for method
			XOTclExInstanceVariable instanceVariable = (XOTclExInstanceVariable) instanceVar;
			IType type = (IType) instanceVariable.getDeclaringClassParameter()
					.resolveElement();
			if (type != null
					&& (instName.sourceStart() <= position && position <= instName
							.sourceEnd())) {
				this.selectionElements.add(type);
			} else if (type != null && callName.sourceStart() <= position
					&& position <= callName.sourceEnd()) {

				String typeMixin = TclParseUtil.getFQNFromModelElement(type, "::");
				if( typeMixin.startsWith("::")) {
					typeMixin = typeMixin.substring(2);
				}
				findXOTclMethodMixin(tclNameToKey(typeMixin) + IMixinRequestor.MIXIN_NAME_SEPARATOR + tclNameToKey(callName.getName()),
						callName.getName());
				// Check super.
				List supersToHandle = new ArrayList();
				String[] superClasses = null;
				try {
					superClasses = type.getSuperClasses();
				} catch (ModelException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
				if (superClasses != null) {
					for (int i = 0; i < superClasses.length; i++) {
						supersToHandle.add(superClasses[i]);
					}
				}
				String prevClass = type.getFullyQualifiedName();
				if (prevClass.startsWith("::")) {
					prevClass = prevClass.substring(2);
				}
				while (supersToHandle.size() > 0) {
					String superClassName = (String) supersToHandle.get(0);
					supersToHandle.remove(0);
					if (superClassName.startsWith("::")) {
						findXOTclMethodMixin(superClassName.substring(2)
								+ tclNameToKey(callName.getName()), callName
								.getName());
					} else {
						// remove one level from prev class
					}
				}
			}
		}
		if (this.selectionElements.size() == 0) {
			if (instName.sourceStart() <= position
					&& position <= instName.sourceEnd()) {
				addElementFromASTNode(instanceVar);
			}
		}
	}

	private void findXOTclMethodMixin(String pattern, String name) {
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern + "*");
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& allObjects[j] instanceof XOTclInstProc) {
					XOTclInstProc field = (XOTclInstProc) allObjects[j];
					if (name.equals(field.getName())) {
						this.selectionElements.add(field.getModelElement());
						return;
					}
				}
			}
		}
	}
}
