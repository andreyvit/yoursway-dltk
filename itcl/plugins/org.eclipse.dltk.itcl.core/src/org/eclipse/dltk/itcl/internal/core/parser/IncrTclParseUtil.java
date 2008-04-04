package org.eclipse.dltk.itcl.internal.core.parser;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclExInstanceVariable;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclInstanceVariable;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclASTUtil;

public class IncrTclParseUtil {
	public static FieldDeclaration findInstanceVariableDeclarationFrom(
			ModuleDeclaration module, ASTNode parent, String commandNameValue) {
		List levels = TclParseUtil.findLevelsTo(module, parent);
		int len = levels.size();
		for (int j = 0; j < len; ++j) {
			ASTNode astNodeParent = (ASTNode) levels.get(len - 1 - j);
			List childs = TclASTUtil.getStatements(astNodeParent);
			if (childs == null) {
				continue;
			}
			for (int i = 0; i < childs.size(); i++) {
				if ((childs.get(i) instanceof IncrTclInstanceVariable)) {
					IncrTclInstanceVariable inst = (IncrTclInstanceVariable) childs
							.get(i);
					String elementFQN = TclParseUtil.getElementFQN(inst, "::",
							module);
					if (inst.getName().equals(commandNameValue)
							|| elementFQN.equals(commandNameValue)) {
						return inst;
					}
				} else if (childs.get(i) instanceof IncrTclExInstanceVariable) {
					IncrTclExInstanceVariable inst = (IncrTclExInstanceVariable) childs
							.get(i);
					String elementFQN = TclParseUtil.getElementFQN(inst, "::",
							module);
					if (inst.getName().equals(commandNameValue)
							|| elementFQN.equals(commandNameValue)) {
						return inst;
					}
				}
			}
		}
		return null;
	}
}
