package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.ExtendedTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclFieldDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.TclField;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.TclNamespace;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.TclProc;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclClass;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclInstProc;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclObject;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.XOTclProc;

public class XOTclMixinBuildVisitor extends ASTVisitor {
	private boolean signature = false;
	private ISourceModule sourceModule;
	private ModuleDeclaration moduleDeclaration;
	private IMixinRequestor requestor;
	Stack namespaceNames = new Stack();

	public XOTclMixinBuildVisitor(ModuleDeclaration moduleDeclaration,
			ISourceModule module, boolean signature, IMixinRequestor requestor) {
		this.signature = signature;
		this.sourceModule = module;
		this.moduleDeclaration = moduleDeclaration;
		this.requestor = requestor;
	}

	public boolean endvisit(TypeDeclaration s) throws Exception {
		// Skip type names with code execution
		if (s.getName().indexOf('[') != -1 || s.getName().indexOf('$') != -1) {
			return true;
		}
		this.namespaceNames.pop();
		return true;
	}

	private String getKeyFromLevels(List nodes) {
		return TclParseUtil.getElementFQN(nodes,
				IMixinRequestor.MIXIN_NAME_SEPARATOR, this.moduleDeclaration);
	}

	// private String getKeyFromLevelsName(List nodes, String nodeName) {
	// String prefix = getKeyFromLevels(nodes);
	// String[] split = nodeName.split("::");
	// return prefix + IMixinRequestor.MIXIN_NAME_SEPARATOR
	// + tclNameToKey(split[split.length - 1]);
	// }

	private String getNamespacePrefix() {
		StringBuffer prefix = new StringBuffer();
		for (Iterator iterator = this.namespaceNames.iterator(); iterator
				.hasNext();) {
			TypeDeclaration ns = (TypeDeclaration) iterator.next();
			String name = ns.getName();
			if (ns.getName().startsWith("::")) {
				// Remove all previous
				prefix.delete(0, prefix.length());
				name = name.substring(2);
			}
			prefix.append(tclNameToKey(name)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR);
		}
		return prefix.toString();
	}

	private String tclNameToKey(String name) {
		return TclParseUtil.tclNameTo(name,
				IMixinRequestor.MIXIN_NAME_SEPARATOR);
	}

	public boolean visit(MethodDeclaration s) throws Exception {
		if (s instanceof XOTclMethodDeclaration) {
			visitXOTclMethod(s);
			return true;
		}
		// Other methods visit
		ElementInfo info = new ElementInfo();
		String name = s.getName();
		if (name.startsWith("::")) {
			info.key = tclNameToKey(name.substring(2));
		} else {
			if (name.indexOf("::") == -1) {
				info.key = this.getNamespacePrefix() + tclNameToKey(name);
			} else {
				TypeDeclaration type = TclParseUtil.findTclTypeDeclarationFrom(
						this.moduleDeclaration, s);
				if (type != null) {
					List levels = TclParseUtil.findLevelsTo(
							this.moduleDeclaration, type);
					String[] mName = name.split("::");
					info.key = this.getKeyFromLevels(levels)	
							+ IMixinRequestor.MIXIN_NAME_SEPARATOR
							+ mName[mName.length - 1];
				}
			}
		}
		if (info.key != null) {
			if (signature) {
				info.object = new TclProc();
			}
			this.requestor.reportElement(info);
		}
		// System.out.println("Report proc or instproc:" + info.key);
		return true;
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
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR + 
					tclNameToKey(name);
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

	public boolean visit(Expression s) throws Exception {
		// TODO Auto-generated method stub
		return super.visit(s);
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
		} else if (s instanceof FieldDeclaration) {
			FieldDeclaration field = (FieldDeclaration) s;
			ElementInfo info = new ElementInfo();
			// We need to filter arrays.
			if (!TclParseUtil.isArrayVariable(field.getName())) {
				List levels = TclParseUtil.findLevelsTo(this.moduleDeclaration, s);
				info.key = TclParseUtil.getElementFQN(levels, IMixinRequestor.MIXIN_NAME_SEPARATOR, moduleDeclaration)//this.getNamespacePrefix()
						+ IMixinRequestor.MIXIN_NAME_SEPARATOR
						+ this.tclNameToKey(field.getName());
				if( info.key.startsWith("{")) {
					info.key = info.key.substring(1);
				}
			} else {
				info.key = this.getNamespacePrefix()
//						+ IMixinRequestor.MIXIN_NAME_SEPARATOR
						+ this.tclNameToKey(TclParseUtil.extractArrayName(field
								.getName()));
			}
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
		} else {
			ElementInfo info = new ElementInfo();

			info.key = this.getNamespacePrefix() + tclNameToKey(s.getName());
			if( info.key.startsWith("{")) {
				info.key = info.key.substring(1);
			}
			// System.out.println("Report XOTcl type:" + info.key);
			this.namespaceNames.push(s);
			if( signature ) {
				info.object = new TclNamespace();
			}
			this.requestor.reportElement(info);
		}
		return true;
	}
}
