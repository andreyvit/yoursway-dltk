package org.eclipse.dltk.itcl.internal.core.search.mixin;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclFieldDeclaration;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclInstanceVariable;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclMethodDeclaration;
import org.eclipse.dltk.itcl.internal.core.search.mixin.model.IncrTclClass;
import org.eclipse.dltk.itcl.internal.core.search.mixin.model.IncrTclClassInstance;
import org.eclipse.dltk.itcl.internal.core.search.mixin.model.IncrTclInstProc;
import org.eclipse.dltk.itcl.internal.core.search.mixin.model.IncrTclProc;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.tcl.core.extensions.IMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinBuildVisitor;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclField;

public class IncrTclMixinBuildVisitorExtension implements
		IMixinBuildVisitorExtension {

	public boolean visit(MethodDeclaration s, TclMixinBuildVisitor original) {
		if (s instanceof IncrTclMethodDeclaration) {
			this.visitIncrTclMethod(s, original);
			return true;
		}
		return false;
	}

	private void visitIncrTclMethod(MethodDeclaration s,
			TclMixinBuildVisitor original) {
		ExtendedTclMethodDeclaration method = (ExtendedTclMethodDeclaration) s;

		ElementInfo info = new ElementInfo();

		String name = s.getName();
		ASTNode declaringType = method.getDeclaringType();
		if (declaringType != null
				&& declaringType instanceof TypeDeclaration) {
			List levels = TclParseUtil.findLevelsTo(original
					.getModuleDeclaration(), declaringType);
			info.key = original.getKeyFromLevels(levels)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR
					+ original.tclNameToKey(name);
		}
		if (original.getSignature()) {
			switch (method.getKind()) {
			case ExtendedTclMethodDeclaration.KIND_INSTPROC:
				info.object = new IncrTclInstProc();
				break;
			case ExtendedTclMethodDeclaration.KIND_PROC:
				info.object = new IncrTclProc();
				break;
			}
		}
		if (info.key != null) {
			original.getRequestor().reportElement(info);
		}
		// System.out.println("Report proc or instproc:" + info.key);
	}

	public boolean visit(TypeDeclaration s, TclMixinBuildVisitor original) {
		if ((s.getModifiers() & IIncrTclModifiers.AccIncrTcl) != 0) {
			ElementInfo info = new ElementInfo();

			info.key = original.getNamespacePrefix()
					+ original.tclNameToKey(s.getName());
			if (info.key.startsWith("{")) {
				info.key = info.key.substring(1);
			}
			// System.out.println("Report Tcl namespace:" + info.key);
			original.pushNamespaceName(s);
			if (original.getSignature()) {
				// if (s instanceof XOTclObjectDeclaration) {
				// info.object = new IncrTclObject();
				// } else {
				IncrTclClass tclClass = new IncrTclClass();
				info.object = tclClass;
				tclClass.setNamespace(original.getNamespacePrefix());
				// }
			}
			original.getRequestor().reportElement(info);
			return true;
		}
		return false;
	}

	public boolean visit(Statement s, TclMixinBuildVisitor original) {
		if (s instanceof IncrTclInstanceVariable) {
			IncrTclInstanceVariable instanceVar = (IncrTclInstanceVariable) s;
			List levels = TclParseUtil.findLevelsTo(original
					.getModuleDeclaration(), instanceVar);
			ElementInfo info = new ElementInfo();
			info.key = original.getKeyFromLevels(levels);
			if (original.getSignature()) {
				info.object = new IncrTclClassInstance();
			}
			original.getRequestor().reportElement(info);
			return true;
		} else if (s instanceof IncrTclFieldDeclaration) {
			IncrTclFieldDeclaration var = (IncrTclFieldDeclaration) s;
			String name = var.getName();
			TypeDeclaration type = var.getDeclaringType();
			List levels = TclParseUtil.findLevelsTo(original
					.getModuleDeclaration(), type);

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
