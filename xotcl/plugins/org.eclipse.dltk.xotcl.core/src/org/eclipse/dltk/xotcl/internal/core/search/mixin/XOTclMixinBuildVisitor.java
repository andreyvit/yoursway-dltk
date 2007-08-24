package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.core.mixin.IMixinRequestor.ElementInfo;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;

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
		StringBuffer prefix = new StringBuffer();
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
			ASTNode ns = (ASTNode) iterator.next();
			String name = null;
			if (ns instanceof ModuleDeclaration) {
				name = "";
			} else if (ns instanceof TypeDeclaration) {
				name = ((TypeDeclaration) ns).getName();
			} else if (ns instanceof MethodDeclaration) {
				name = ((MethodDeclaration) ns).getName();
			}
			if (name.startsWith("::")) {
				prefix.delete(0, prefix.length());
			}
			if (name.length() > 0) {
				prefix.append(tclNameToKey(name)
						+ IMixinRequestor.MIXIN_NAME_SEPARATOR);
			}
		}

		String result = prefix.toString();
		if (result.endsWith(IMixinRequestor.MIXIN_NAME_SEPARATOR)) {
			return result.substring(0, result.length()
					- IMixinRequestor.MIXIN_NAME_SEPARATOR.length());
		}
		return result;
	}

	private String getKeyFromLevelsName(List nodes, String nodeName) {
		String prefix = getKeyFromLevels(nodes);
		String[] split = nodeName.split("::");
		return prefix + IMixinRequestor.MIXIN_NAME_SEPARATOR
				+ tclNameToKey(split[split.length - 1]);
	}

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
		return name.replaceAll("::", IMixinRequestor.MIXIN_NAME_SEPARATOR);
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
			this.requestor.reportElement(info);
		}
		// System.out.println("Report proc or instproc:" + info.key);
		return true;
	}

	private void visitXOTclMethod(MethodDeclaration s) {
		XOTclMethodDeclaration method = (XOTclMethodDeclaration) s;

		ElementInfo info = new ElementInfo();

		String name = s.getName();
		ASTNode declaringXOTclType = method.getDeclaringXOTclType();
		if (declaringXOTclType != null
				&& declaringXOTclType instanceof TypeDeclaration) {
			List levels = TclParseUtil.findLevelsTo(this.moduleDeclaration,
					declaringXOTclType);
			info.key = this.getKeyFromLevels(levels)
					+ IMixinRequestor.MIXIN_NAME_SEPARATOR + tclNameToKey(name);
		}
		this.requestor.reportElement(info);
		// System.out.println("Report proc or instproc:" + info.key);
	}

	public boolean visit(Expression s) throws Exception {
		// TODO Auto-generated method stub
		return super.visit(s);
	}

	public boolean visit(Statement s) throws Exception {
		return super.visit(s);
	}

	public boolean visit(TypeDeclaration s) throws Exception {
		// Skip type names with code execution
		if (s.getName().indexOf('[') != -1 || s.getName().indexOf('$') != -1) {
			return true;
		}
		// This is Tcl namespaces
		if ((s.getModifiers() & IXOTclModifiers.AccXOTcl) == 0) {
			ElementInfo info = new ElementInfo();

			info.key = this.getNamespacePrefix() + tclNameToKey(s.getName());
			this.requestor.reportElement(info);
			// System.out.println("Report Tcl namespace:" + info.key);
			this.namespaceNames.push(s);
		} else {
			ElementInfo info = new ElementInfo();

			info.key = this.getNamespacePrefix() + tclNameToKey(s.getName());
			this.requestor.reportElement(info);
			// System.out.println("Report XOTcl type:" + info.key);
			this.namespaceNames.push(s);
		}
		return true;
	}
}
