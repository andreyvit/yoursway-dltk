package org.eclipse.dltk.itcl.internal.core.search;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
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
import org.eclipse.dltk.itcl.internal.core.IncrTclResolver;
import org.eclipse.dltk.itcl.internal.core.parser.IncrTclCommandDetector.IncrTclGlobalClassParameter;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclExInstanceVariable;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclInstanceVariable;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclMethodCallStatement;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclMethodDeclaration;
import org.eclipse.dltk.itcl.internal.core.search.mixin.model.IncrTclInstProc;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.tcl.core.extensions.ISelectionExtension;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclResolver;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclSelectionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnKeywordOrFunction;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;

public class IncrTclSelectionExtension implements ISelectionExtension {

	public void selectionOnKeywordOrFunction(SelectionOnKeywordOrFunction key,
			TclSelectionEngine engine) {
		ASTNode originalNode = key.getOriginalNode();
		if (originalNode instanceof TclStatement) {
			processXOTclCommandCalls((TclStatement) originalNode, engine);
		}
	}

	private void processXOTclInstanceVariable(IncrTclInstanceVariable node,
			TclSelectionEngine engine) {
		SimpleReference classInstanceName = node.getClassInstanceName();
		if (classInstanceName.sourceStart() <= engine.getActualSelectionStart()
				&& engine.getActualSelectionStart() <= classInstanceName
						.sourceEnd()) {
			TypeDeclaration declaringType = node.getDeclaringType();
			IModelElement type = engine.findElementFromNode(declaringType);
			if (type != null) {
				engine.addSelectionElement(type);
			}
		}
	}

	private void processXOTclCommandCalls(TclStatement node,
			TclSelectionEngine engine) {
		// System.out.println("CoOL:" + node);
		if (node.getCount() == 0) {
			return;
		}
		ASTNode parent = TclParseUtil.getPrevParent(engine.getParser()
				.getModule(), node);
		String prefix = TclParseUtil.getElementFQN(parent,
				IMixinRequestor.MIXIN_NAME_SEPARATOR, engine.getParser()
						.getModule());
		Expression commandExpr = node.getAt(0);
		String command = TclParseUtil.getNameFromNode(commandExpr);
		if (command != null && command.startsWith("::")) {
			String name = command.substring(2);
			// Check class proc call
			String[] split = name.split("::");
			IModelElement[] typeMixin = IncrTclResolver.findTypeMixin(engine
					.tclNameToKey(name), split[split.length - 1]);
			checkMixinTypeForMethod(node, commandExpr, typeMixin, prefix,
					engine);
		} else if (command != null) {
			String[] split = command.split("::");
			if (parent instanceof ModuleDeclaration) {
				IModelElement[] typeMixin = IncrTclResolver.findTypeMixin(
						engine.tclNameToKey(command), split[split.length - 1]);
				checkMixinTypeForMethod(node, commandExpr, typeMixin, prefix,
						engine);
			} else {
				IModelElement[] typeMixin = IncrTclResolver.findTypeMixin(
						prefix + IMixinRequestor.MIXIN_NAME_SEPARATOR
								+ engine.tclNameToKey(command),
						split[split.length - 1]);
				checkMixinTypeForMethod(node, commandExpr, typeMixin, prefix,
						engine);
			}
		}
	}

	private boolean checkMixinTypeForMethod(TclStatement node,
			Expression commandExpr, IModelElement[] typeMixin, String prefix,
			TclSelectionEngine engine) {
		for (int i = 0; i < typeMixin.length; i++) {
			// Select type if point on type
			if (commandExpr.sourceStart() <= engine.getActualSelectionStart()
					&& engine.getActualSelectionStart() <= commandExpr
							.sourceEnd()) {
				engine.addSelectionElement(typeMixin[i]);
				return true;
			}
			Expression callExpr = node.getAt(1);
			if (node.getCount() > 1 && typeMixin[i] instanceof IParent) {
				if (callExpr.sourceStart() <= engine.getActualSelectionStart()
						&& engine.getActualSelectionStart() <= callExpr
								.sourceEnd()) {
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
									engine.addSelectionElement(children[j]);
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

										IModelElement[] ptypeMixin = IncrTclResolver
												.findTypeMixin(
														(prefix.length() > 0 ? prefix
																+ IMixinRequestor.MIXIN_NAME_SEPARATOR
																: "")
																+ engine
																		.tclNameToKey(superClasses[j]),
														superClasses[j]);
										String[] split = superClasses[j]
												.split("::");
										checkMixinTypeForMethod(node,
												commandExpr, ptypeMixin,
												split[split.length - 1], engine);
									}
								}
							} catch (ModelException e) {
								if (DLTKCore.DEBUG) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private void processSelectXOTclMethodDeclaration(
			ExtendedTclMethodDeclaration node, int position,
			TclSelectionEngine engine) {
		ASTNode type = node.getDeclaringType();
		if (type instanceof TypeDeclaration) {
			SimpleReference ref = node.getTypeNameRef();
			IModelElement parent = engine.findElementFromNode(type);
			if (parent != null && parent instanceof IParent) {
				if (node.getNameStart() <= position
						&& position <= node.getNameEnd()) {

					IModelElement methodElement = TclResolver
							.findChildrenByName(node.getName(),
									(IParent) parent);
					engine.addSelectionElement(methodElement);
					return;
				} else if (ref != null && ref.sourceStart() <= position
						&& position <= ref.sourceEnd()) {
					engine.addSelectionElement(parent);
				}
			}
		}
	}

	private void processSelectXOTclMethod(IncrTclMethodCallStatement call,
			int position, TclSelectionEngine engine) {
		FieldDeclaration instanceVar = call.getInstanceVariable();
		SimpleReference callName = call.getCallName();
		SimpleReference instName = call.getInstNameRef();
		if (instanceVar != null
				&& instanceVar instanceof IncrTclInstanceVariable) {
			// Check for method
			if (callName.sourceStart() <= position
					&& position <= callName.sourceEnd()) {
				IncrTclInstanceVariable instanceVariable = (IncrTclInstanceVariable) instanceVar;
				TypeDeclaration declaringType = instanceVariable
						.getDeclaringType();
				if (declaringType != null) {
					IModelElement parent = engine
							.findElementFromNode(declaringType);
					if (parent != null) {
						if (engine.checkMethodFrom(declaringType, callName,
								parent)) {
							return;
						}
						// Check mixin for selected class.
						String typeMixin = TclParseUtil.getElementFQN(
								declaringType,
								IMixinRequestor.MIXIN_NAME_SEPARATOR, engine
										.getParser().getModule())
								+ IMixinRequestor.MIXIN_NAME_SEPARATOR;

						engine.findMethodMixin(typeMixin
								+ engine.tclNameToKey(callName.getName()),
								callName.getName());
						// Check super.
						ASTNode nde = TclParseUtil.getPrevParent(engine
								.getParser().getModule(), declaringType);
						List supersToHandle = new ArrayList();
						engine
								.fillSuperClassesTo(declaringType,
										supersToHandle);
						TypeDeclaration prev = declaringType;
						while (supersToHandle.size() > 0) {
							String superClassName = (String) supersToHandle
									.get(0);
							supersToHandle.remove(0);
							TypeDeclaration sType = TclParseUtil
									.findXOTclTypeDeclarationFrom(engine
											.getParser().getModule(), nde,
											superClassName);
							if (sType != null) {
								prev = sType;
								engine
										.fillSuperClassesTo(sType,
												supersToHandle);
								IModelElement sParent = engine
										.findElementFromNode(sType);
								if (engine.checkMethodFrom(sType, callName,
										sParent)) {
									return;
								}
								String sTypeMixin = TclParseUtil.getElementFQN(
										sType,
										IMixinRequestor.MIXIN_NAME_SEPARATOR,
										engine.getParser().getModule())
										+ IMixinRequestor.MIXIN_NAME_SEPARATOR;

								findXOTclMethodMixin(sTypeMixin
										+ engine.tclNameToKey(callName
												.getName()),
										callName.getName(), engine);
								// System.out.println("COOL");
							} else {
								String sTypeMixin = "";
								if (prev != null) {
									ASTNode prevParent = TclParseUtil
											.getPrevParent(engine.getParser()
													.getModule(), prev);
									if (prevParent instanceof ModuleDeclaration) {
										sTypeMixin = engine
												.tclNameToKey(superClassName)
												+ IMixinRequestor.MIXIN_NAME_SEPARATOR;
									} else {
										sTypeMixin = TclParseUtil
												.getElementFQN(
														prevParent,
														IMixinRequestor.MIXIN_NAME_SEPARATOR,
														engine.getParser()
																.getModule())
												+ IMixinRequestor.MIXIN_NAME_SEPARATOR
												+ engine
														.tclNameToKey(superClassName)
												+ IMixinRequestor.MIXIN_NAME_SEPARATOR;
									}
								}

								findXOTclMethodMixin(sTypeMixin
										+ engine.tclNameToKey(callName
												.getName()),
										callName.getName(), engine);
								// Lets also look to toplevel
								findXOTclMethodMixin(engine
										.tclNameToKey(superClassName)
										+ IMixinRequestor.MIXIN_NAME_SEPARATOR
										+ engine.tclNameToKey(callName
												.getName()),
										callName.getName(), engine);
							}
						}
					}
				}
			}
		} else if (instanceVar != null
				&& instanceVar instanceof IncrTclExInstanceVariable) {
			// Check for method
			IncrTclExInstanceVariable instanceVariable = (IncrTclExInstanceVariable) instanceVar;
			IType type = (IType) instanceVariable.getDeclaringClassParameter()
					.resolveElement();
			if (type != null
					&& (instName.sourceStart() <= position && position <= instName
							.sourceEnd())) {
				engine.addSelectionElement(type);
			} else if (type != null && callName.sourceStart() <= position
					&& position <= callName.sourceEnd()) {

				String typeMixin = TclParseUtil.getFQNFromModelElement(type,
						"::");
				if (typeMixin.startsWith("::")) {
					typeMixin = typeMixin.substring(2);
				}
				findXOTclMethodMixin(engine.tclNameToKey(typeMixin)
						+ IMixinRequestor.MIXIN_NAME_SEPARATOR
						+ engine.tclNameToKey(callName.getName()), callName
						.getName(), engine);
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
								+ engine.tclNameToKey(callName.getName()),
								callName.getName(), engine);
					} else {
						// remove one level from prev class
					}
				}
			}
		}
		if (engine.getSelectionElementsSize() == 0) {
			if (instName.sourceStart() <= position
					&& position <= instName.sourceEnd()) {
				engine.addElementFromASTNode(instanceVar);
			}
		}
	}

	private void findXOTclMethodMixin(String pattern, String name,
			TclSelectionEngine engine) {
		IMixinElement[] find = TclMixinModel.getInstance().find(pattern + "*");
		int pos = pattern.indexOf(IMixinRequestor.MIXIN_NAME_SEPARATOR);
		if (find.length == 0 && pos != -1) {
			String newPattern = pattern.substring(0, pos);
			find = TclMixinModel.getInstance().find(newPattern + "*");
		}
		for (int i = 0; i < find.length; i++) {
			Object[] allObjects = find[i].getAllObjects();
			for (int j = 0; j < allObjects.length; j++) {
				if (allObjects[j] != null
						&& allObjects[j] instanceof IncrTclInstProc) {
					IncrTclInstProc field = (IncrTclInstProc) allObjects[j];
					if (name.equals(field.getName())) {
						engine.addSelectionElement(field.getModelElement());
						return;
					}
				}
			}
		}
	}

	public void selectionOnAST(ASTNode node, TclSelectionEngine engine) {
		if (node instanceof IncrTclMethodDeclaration) {
			processSelectXOTclMethodDeclaration(
					(ExtendedTclMethodDeclaration) node, engine
							.getActualSelectionStart(), engine);
			if (engine.getSelectionElementsSize() > 0) {
				return;
			}
		}
	}

	public void selectionOnNode(ASTNode node, int position,
			TclSelectionEngine engine) {
		if (node instanceof IncrTclMethodCallStatement) {
			processSelectXOTclMethod((IncrTclMethodCallStatement) node,
					position, engine);
		} else if (node instanceof IncrTclMethodDeclaration) {
			processSelectXOTclMethodDeclaration(
					(ExtendedTclMethodDeclaration) node, engine
							.getActualSelectionStart(), engine);
		} else if (node instanceof TclStatement) {
			// We need to check for XOTcl command calls.
			processXOTclCommandCalls((TclStatement) node, engine);
		} else if (node instanceof IncrTclInstanceVariable) {
			processXOTclInstanceVariable((IncrTclInstanceVariable) node, engine);
		} else if (node instanceof IncrTclExInstanceVariable) {
			IncrTclExInstanceVariable ex = (IncrTclExInstanceVariable) node;
			IncrTclGlobalClassParameter declaringClassParameter = ex
					.getDeclaringClassParameter();
			IModelElement resolveElement = declaringClassParameter
					.resolveElement();
			if (resolveElement != null) {
				engine.addSelectionElement(resolveElement);
			}
		}
	}
}
