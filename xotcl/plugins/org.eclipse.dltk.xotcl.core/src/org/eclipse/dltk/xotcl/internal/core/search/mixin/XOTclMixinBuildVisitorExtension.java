package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.tcl.core.extensions.IMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinBuildVisitor;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclField;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClassInstance;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclInstProc;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclObject;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclProc;

public class XOTclMixinBuildVisitorExtension implements
		IMixinBuildVisitorExtension {

	public boolean visit(MethodDeclaration s, TclMixinBuildVisitor original) {
		if (s instanceof XOTclMethodDeclaration) {
			this.visitXOTclMethod(s, original);
			return true;
		}
		return false;
	}

	private void visitXOTclMethod(MethodDeclaration s,
			TclMixinBuildVisitor original) {
		ExtendedTclMethodDeclaration method = (ExtendedTclMethodDeclaration) s;

		ElementInfo info = new ElementInfo();

		String name = s.getName();
		ASTNode declaringXOTclType = method.getDeclaringType();
		if (declaringXOTclType != null
				&& declaringXOTclType instanceof TypeDeclaration) {
			List levels = TclParseUtil.findLevelsTo(original.getModuleDeclaration(),
					declaringXOTclType);
			info.key = original.getKeyFromLevels(levels)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR + original.tclNameToKey(name);
		}
		if (original.getSignature()) {
			switch (method.getKind()) {
			case ExtendedTclMethodDeclaration.KIND_INSTPROC:
				info.object = new XOTclInstProc();
				break;
			case ExtendedTclMethodDeclaration.KIND_PROC:
				info.object = new XOTclProc();
				break;
			}
		}
		original.getRequestor().reportElement(info);
		// System.out.println("Report proc or instproc:" + info.key);
	}

	public boolean visit(TypeDeclaration s,
			TclMixinBuildVisitor original) {
		if ((s.getModifiers() & IXOTclModifiers.AccXOTcl) != 0) {
			ElementInfo info = new ElementInfo();

			info.key = original.getNamespacePrefix() + original.tclNameToKey(s.getName());
			// System.out.println("Report Tcl namespace:" + info.key);
			original.pushNamespaceName(s);
			if (original.getSignature()) {
				if (s instanceof XOTclObjectDeclaration) {
					info.object = new XOTclObject();
				} else {
					XOTclClass tclClass = new XOTclClass();
					info.object = tclClass;
					tclClass.setNamespace(original.getNamespacePrefix());
				}
			}
			original.getRequestor().reportElement(info);
			return true;
		}
		return false;
	}

	public boolean visit(Statement s, TclMixinBuildVisitor original) {
		if (s instanceof XOTclInstanceVariable) {
			XOTclInstanceVariable instanceVar = (XOTclInstanceVariable) s;
			List levels = TclParseUtil.findLevelsTo(original.getModuleDeclaration(),
					instanceVar);
			ElementInfo info = new ElementInfo();
			info.key = original.getKeyFromLevels(levels);
			if (original.getSignature()) {
				info.object = new XOTclClassInstance();
			}
			original.getRequestor().reportElement(info);
			return true;
		} else if (s instanceof XOTclFieldDeclaration) {
			XOTclFieldDeclaration var = (XOTclFieldDeclaration) s;
			String name = var.getName();
			TypeDeclaration type = var.getDeclaringType();
			List levels = TclParseUtil.findLevelsTo(original.getModuleDeclaration(),
					type);

			ElementInfo info = new ElementInfo();
			info.key = original.getKeyFromLevels(levels)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR
					+ original.tclNameToKey(name);
			if (original.getSignature()) {
				info.object = new TclField();
			}
			original.getRequestor().reportElement(info);
			return true;
		}
		return false;
	}

}
