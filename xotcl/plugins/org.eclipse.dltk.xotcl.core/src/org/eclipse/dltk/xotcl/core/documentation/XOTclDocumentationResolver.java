package org.eclipse.dltk.xotcl.core.documentation;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclASTUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclDocumentationNode;
import org.eclipse.dltk.xotcl.internal.core.XOTclResolver;

public class XOTclDocumentationResolver {
	public static String getDocumentationFor(IMember member) {
		StringBuffer descriptions = new StringBuffer();
		try {
			ModuleDeclaration module = XOTclResolver.parseModule(member);
			String memberkey = TclParseUtil.getNameFromModelElement(member);
			List sts = TclParseUtil.findLevelFromModule(module, member,
					memberkey);
			// Check scope documentation level
			for (int q = 0; q < sts.size(); q++) {
				ASTNode parent = TclParseUtil.getPrevParent(module,
						(ASTNode) sts.get(q));
				String elementFQN = "::"
						+ TclParseUtil.getElementFQN(parent, "::", module);

				String shortDescr = memberkey
						.substring(elementFQN.length() + 2);
				if (elementFQN.equals("::")) {
					shortDescr = memberkey.substring(2);
				}
				if (sts.size() > 0) {
					List statements = TclASTUtil.getStatements(parent);
					for (int i = 0; i < statements.size(); i++) {

						if (statements.get(i) instanceof XOTclDocumentationNode) {
							XOTclDocumentationNode doc = (XOTclDocumentationNode) statements
									.get(i);
							String description = doc.getDescription(shortDescr);
							if (description != null) {
								descriptions.append(description);
							}
							// doc.getDescription()
							// if(doc.getDescription(member.getElementName()));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descriptions.toString();
	}
}
