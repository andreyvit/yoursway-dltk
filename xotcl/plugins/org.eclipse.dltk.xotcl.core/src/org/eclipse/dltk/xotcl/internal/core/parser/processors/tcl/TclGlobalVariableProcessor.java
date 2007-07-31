package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTListNode;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.TclGlobalVariableDeclaration;

public class TclGlobalVariableProcessor extends AbstractTclCommandProcessor {

	public TclGlobalVariableProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command, offset);
		int statementsCount = statement.getCount();
		if (statementsCount < 2) {
			// TODO: Add error reporting here.
			return null;
		}
		ASTNode ret = null;
		for (int i = 1; i < statementsCount; i++) {
			Expression at = statement.getAt(i);
			Expression variableName = at;
			if (variableName == null) {
				throw new RuntimeException("empty variable name");
			}
			SimpleReference variable = TclParseUtil.makeVariable(variableName);
			TclGlobalVariableDeclaration var = new TclGlobalVariableDeclaration(variable, at.sourceStart(), at.sourceEnd());
			if( ret == null ) {
				ret = var;
			}
			else {
				if(!( ret instanceof ASTListNode  )) {
					ASTListNode list = new ASTListNode();
					list.addNode(ret);
					list.setStart(ret.sourceStart());
					list.setEnd(ret.sourceEnd());
					ret = list;
				}
				((ASTListNode)ret).addNode(var);
				((ASTListNode)ret).setEnd(var.sourceEnd());
			}
		}
		if( ret != null ) {
			addToParent(parent, ret);
		}
		return ret;
	}
}
