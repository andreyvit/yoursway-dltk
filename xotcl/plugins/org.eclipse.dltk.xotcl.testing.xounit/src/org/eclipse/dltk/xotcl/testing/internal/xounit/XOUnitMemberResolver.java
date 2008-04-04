package org.eclipse.dltk.xotcl.testing.internal.xounit;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.testing.AbstractTestingElementResolver;
import org.eclipse.dltk.testing.ITestingElementResolver;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodDeclaration;

public class XOUnitMemberResolver extends AbstractTestingElementResolver
		implements ITestingElementResolver {

	protected ASTNode findNode(final String testName,
			final ModuleDeclaration decl, String method) {
		final ASTNode result[] = new ASTNode[1];
		final String tname = testName.replaceAll("\\.", "::");
		try {
			decl.traverse(new ASTVisitor() {
				public boolean visit(MethodDeclaration s) throws Exception {
					if (s instanceof XOTclMethodDeclaration
							&& s.getName().startsWith("test")) {
						String fqn = TclParseUtil.getElementFQN(s, "::", decl);
						if (!fqn.startsWith("::")) {
							fqn = "::" + fqn;
						}
						if (fqn.equals(tname)) {
							result[0] = s;
							return false;
						}
					}
					return super.visit(s);
				}
			});
		} catch (Exception e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
		return result[0];
	}
}
