package org.eclipse.dltk.xotcl.internal.core.search;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclSelectionEngine;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnAST;
import org.eclipse.dltk.tcl.internal.core.codeassist.selection.SelectionOnNode;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclProcCallStatement;

public class XOTclSelectionEngineOld extends TclSelectionEngine {
	protected void searchInMethod(final ASTNode node, IParent element,
			ASTNode nde) {
		MethodDeclaration method = (MethodDeclaration) nde;
		String methodName = method.getName();
		if (methodName.indexOf("::") != -1) {
			String pName = methodName
					.substring(0, methodName.lastIndexOf("::"));
			pName = pName.replaceAll("::", "\\$");
			if (pName.equals("$")) {
				element = this.getSourceModule();
			} else {
				try {
					element = findTypeFrom(
							this.getSourceModule().getChildren(), "", pName,
							'$');
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
		// For XOTcl types we need to perform some other operations to find
		// corretn model element.
		if ((method.getModifiers() & IXOTclModifiers.AccXOTcl) != 0
				&& method instanceof XOTclMethodDeclaration) {
			XOTclMethodDeclaration me = (XOTclMethodDeclaration) method;
			ASTNode xoTclType = me.getDeclaringXOTclType();
			if (xoTclType instanceof TypeDeclaration) {
				TypeDeclaration declaringXOTclType = (TypeDeclaration) xoTclType;
				if (declaringXOTclType.getName().indexOf("::") == -1) { // Only
					// simple
					// case are
					// supported.
					IModelElement element2 = this.findChildrenByName(
							declaringXOTclType.getName(), element);
					if (element2 != null) {
						element = (IParent) element2;
					}
				}
			}
		}
		IModelElement e = findChildrenByName(methodName, (IParent) element);
		if (e != null && e instanceof IParent) {
			List stats = ((MethodDeclaration) nde).getStatements();
			searchAddElementsTo(stats, node, (IParent) e);
		}
	}

	protected String getNodeChildName(ASTNode node) {
		if (node instanceof FieldDeclaration) {
			FieldDeclaration field = (FieldDeclaration) node;
			return field.getName();
		}
		return super.getNodeChildName(node);
	}

	protected void select(ASTNode astNode, ASTNode astNodeParent) {
		if (astNode instanceof SelectionOnNode) {
			SelectionOnNode selOn = ((SelectionOnNode) astNode);
			ASTNode node = selOn.getNode();
			if (node instanceof XOTclMethodCallStatement) {
				handleInstanceMethodCall(selOn, (XOTclMethodCallStatement) node);
			} else if (node instanceof XOTclProcCallStatement) {
				handleClassProcCall(selOn, (XOTclProcCallStatement) node);
			} else if (node instanceof XOTclInstanceVariable) {
				handleInstanceVariable(selOn, (XOTclInstanceVariable) node);
			} else if (node instanceof XOTclMethodDeclaration) {
				handleMethodDeclaration(selOn.getPosition(),
						(XOTclMethodDeclaration) node);
			}
		} else if (astNode instanceof SelectionOnAST) {
			ASTNode node = ((SelectionOnAST) astNode).getNode();
			if (node instanceof XOTclMethodDeclaration) {
				// handleMethodDeclaration(node., (XOTclMethodDeclaration)node);
			}
		}
		if (this.selectionElements.size() > 0) {
			return;
		}
		super.select(astNode, astNodeParent);
	}

	private void handleMethodDeclaration(int position,
			XOTclMethodDeclaration node) {
		if (node.getTypeNameRef().sourceStart() <= position
				&& position < node.getTypeNameRef().sourceEnd()) {
			addElementFromASTNode(node.getDeclaringXOTclType());
		}
	}

	private void handleInstanceVariable(SelectionOnNode selOn,
			XOTclInstanceVariable st) {
		int position = selOn.getPosition();
		if (st.getClassInstanceName().sourceStart() <= position
				&& position < st.getClassInstanceName().sourceEnd()) {
			TypeDeclaration type = st.getDeclaringType();
			addElementFromASTNode(type);
		}
	}

	private void handleClassProcCall(SelectionOnNode selOn,
			XOTclProcCallStatement st) {
		int position = selOn.getPosition();
		SimpleReference callName = st.getCallName();
		if (callName.sourceStart() <= position
				&& position < callName.sourceEnd()) {
			if (st.getObject() instanceof TypeDeclaration) {
				TypeDeclaration type = (TypeDeclaration) st.getObject();
				MethodDeclaration[] methods = type.getMethods();
				searchMethodsList(callName, methods);
			}
		} else if (st.getInstNameRef().sourceStart() <= position
				&& position < st.getInstNameRef().sourceEnd()) {
			if (st.getObject() instanceof TypeDeclaration) {
				TypeDeclaration type = (TypeDeclaration) st.getObject();
				addElementFromASTNode(type);
			}
		}
	}

	private void searchMethodsList(SimpleReference callName,
			MethodDeclaration[] methods) {
		for (int i = 0; i < methods.length; i++) {
			MethodDeclaration m = methods[i];
			if ((m.getModifiers() & IXOTclModifiers.AccXOTcl) != 0) {
				if (m.getName().equals(callName.getName())) {
					addElementFromASTNode(m);
					return;
				}
			}
		}
	}

	protected IModelElement findElementParent(ASTNode node, String name,
			IParent parent) {
		if (node instanceof XOTclMethodDeclaration) {
			XOTclMethodDeclaration me = (XOTclMethodDeclaration) node;
			if (me.getDeclaringXOTclType() instanceof TypeDeclaration) {
				TypeDeclaration declaringXOTclType = (TypeDeclaration) me.getDeclaringXOTclType();
				// if (declaringXOTclType.getName().indexOf("::") == -1) { //
				// Only
				// simple
				// case are
				// supported.
				IModelElement element2 = this.findChildrenByName(
						declaringXOTclType.getName(), parent);
				if (element2 != null) {
					return this.findChildrenByName(me.getName(),
							(IParent) element2);
				}
				// }
			}
		}
		return null;
	}

	private void handleInstanceMethodCall(SelectionOnNode selOn,
			XOTclMethodCallStatement st) {
		int position = selOn.getPosition();
		SimpleReference callName = st.getCallName();
		if (callName.sourceStart() <= position
				&& position < callName.sourceEnd()) {
			XOTclInstanceVariable variable = st.getInstanceVariable();
			TypeDeclaration type = variable.getDeclaringType();
			MethodDeclaration[] methods = type.getMethods();
			searchMethodsList(callName, methods);
		} else if (st.getInstNameRef().sourceStart() <= position
				&& position < st.getInstNameRef().sourceEnd()) {
			XOTclInstanceVariable variable = st.getInstanceVariable();
			TypeDeclaration type = variable.getDeclaringType();
			addElementFromASTNode(type);
		}
	}
}
