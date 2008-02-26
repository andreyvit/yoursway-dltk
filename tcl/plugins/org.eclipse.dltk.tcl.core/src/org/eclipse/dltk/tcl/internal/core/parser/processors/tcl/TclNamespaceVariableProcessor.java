package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.TclNamespaceVariable;

public class TclNamespaceVariableProcessor extends AbstractTclCommandProcessor {

	public TclNamespaceVariableProcessor() {
	}

	public ASTNode process(TclStatement statement, ITclParser parser, 
			ASTNode parent) {
		int statementsCount = statement.getCount();
		if (statementsCount < 2) {
			this.report(parser, Messages.TclProcProcessor_Wrong_Number_of_Arguments, statement, ProblemSeverities.Error);
			return null;
		}
		ASTNode ret = null;
		for (int i = 1; i < statementsCount; i+= 2) {
			Expression at = statement.getAt(i);
			Expression variableName = at;
			if (variableName == null) {
				throw new RuntimeException("empty variable name");
			}
			SimpleReference variable = TclParseUtil.makeVariable(variableName);
			if (variable != null) {
				TclNamespaceVariable var = new TclNamespaceVariable(variable,
						at.sourceStart(), at.sourceEnd());
				if (ret == null) {
					ret = var;
				} else {
					if (!(ret instanceof Block)) {
						Block list = new Block();
						list.addStatement(ret);
						list.setStart(ret.sourceStart());
						list.setEnd(ret.sourceEnd());
						ret = list;
					}
					((Block) ret).addStatement(var);
					((Block) ret).setEnd(var.sourceEnd());
				}
			}
		}
		if (ret != null) {
			addToParent(parent, ret);
		}
		return ret;
	}
}
