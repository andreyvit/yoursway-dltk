package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import java.util.List;
import java.util.Stack;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinBuildVisitor;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclField;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclInstProc;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclObject;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclProc;

public class XOTclMixinBuildVisitor extends TclMixinBuildVisitor {
	private boolean signature = false;
	private ISourceModule sourceModule;
	private ModuleDeclaration moduleDeclaration;
	private IMixinRequestor requestor;
	Stack namespaceNames = new Stack();

	public XOTclMixinBuildVisitor(ModuleDeclaration moduleDeclaration,
			ISourceModule module, boolean signature, IMixinRequestor requestor) {
		super(moduleDeclaration, module, signature, requestor);
	}

	

	public boolean visit(MethodDeclaration s) throws Exception {
		if (s instanceof XOTclMethodDeclaration) {
			visitXOTclMethod(s);
			return true;
		}
		return super.visit(s);
	}

	private void visitXOTclMethod(MethodDeclaration s) {
		ExtendedTclMethodDeclaration method = (ExtendedTclMethodDeclaration) s;

		ElementInfo info = new ElementInfo();

		String name = s.getName();
		ASTNode declaringXOTclType = method.getDeclaringType();
		if (declaringXOTclType != null
				&& declaringXOTclType instanceof TypeDeclaration) {
			List levels = TclParseUtil.findLevelsTo(this.moduleDeclaration,
					declaringXOTclType);
			info.key = this.getKeyFromLevels(levels)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR + tclNameToKey(name);
		}
		if (signature) {
			switch (method.getKind()) {
			case ExtendedTclMethodDeclaration.KIND_INSTPROC:
				info.object = new XOTclInstProc();
				break;
			case ExtendedTclMethodDeclaration.KIND_PROC:
				info.object = new XOTclProc();
				break;
			}
		}
		this.requestor.reportElement(info);
		// System.out.println("Report proc or instproc:" + info.key);
	}

	public boolean visit(Statement s) throws Exception {
		if (s instanceof XOTclFieldDeclaration) {
			XOTclFieldDeclaration var = (XOTclFieldDeclaration) s;
			String name = var.getName();
			TypeDeclaration type = var.getDeclaringType();
			List levels = TclParseUtil.findLevelsTo(this.moduleDeclaration,
					type);

			ElementInfo info = new ElementInfo();
			info.key = this.getKeyFromLevels(levels)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR
					+ this.tclNameToKey(name);
			if (signature) {
				info.object = new TclField();
			}
			this.requestor.reportElement(info);
		}
		return super.visit(s);
	}

	public boolean visit(TypeDeclaration s) throws Exception {
		// Skip type names with code execution
		if (s.getName().indexOf('[') != -1 || s.getName().indexOf('$') != -1) {
			return true;
		}
		// This is Tcl namespaces
		if ((s.getModifiers() & IXOTclModifiers.AccXOTcl) != 0) {
			ElementInfo info = new ElementInfo();

			info.key = this.getNamespacePrefix() + tclNameToKey(s.getName());
			// System.out.println("Report Tcl namespace:" + info.key);
			this.namespaceNames.push(s);
			if (signature) {
				if (s instanceof XOTclObjectDeclaration) {
					info.object = new XOTclObject();
				} else {
					XOTclClass tclClass = new XOTclClass();
					info.object = tclClass;
					tclClass.setNamespace(this.getNamespacePrefix());
				}
			}
			this.requestor.reportElement(info);
		}
		return super.visit(s);
	}
}
