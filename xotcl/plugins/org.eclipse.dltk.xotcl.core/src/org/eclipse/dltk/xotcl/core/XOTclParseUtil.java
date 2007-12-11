package org.eclipse.dltk.xotcl.core;

import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclASTUtil;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclExInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;

public class XOTclParseUtil {
	public static FieldDeclaration findXOTclInstanceVariableDeclarationFrom(
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
				if ((childs.get(i) instanceof XOTclInstanceVariable)) {
					XOTclInstanceVariable inst = (XOTclInstanceVariable) childs
							.get(i);
					String elementFQN = TclParseUtil.getElementFQN(inst, "::",
							module);
					if (inst.getName().equals(commandNameValue)
							|| elementFQN.equals(commandNameValue)) {
						return inst;
					}
				} else if (childs.get(i) instanceof XOTclExInstanceVariable) {
					XOTclExInstanceVariable inst = (XOTclExInstanceVariable) childs
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

	public static XOTclObjectDeclaration findXOTclObjectInstanceFrom(
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
				if (!(childs.get(i) instanceof XOTclObjectDeclaration)) {
					continue;
				}
				XOTclObjectDeclaration inst = (XOTclObjectDeclaration) childs
						.get(i);
				if (inst.getName().equals(commandNameValue)) {
					return inst;
				}
			}
		}
		return null;
	}

}
