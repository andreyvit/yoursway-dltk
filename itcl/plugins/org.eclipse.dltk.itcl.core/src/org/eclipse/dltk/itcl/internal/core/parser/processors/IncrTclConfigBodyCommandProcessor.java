package org.eclipse.dltk.itcl.internal.core.parser.processors;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclConfigBody;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class IncrTclConfigBodyCommandProcessor extends
		AbstractTclCommandProcessor {

	public IncrTclConfigBodyCommandProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		TclStatement statement = parser.processLocal(command, offset, parent);
		if (statement == null
				|| (statement != null && statement.getCount() == 0)) {
			return null;
		}
		if (statement.getCount() < 3) {
			this.report(parser, "Wrong number of arguments", statement
					.sourceStart(), statement.sourceEnd(),
					ProblemSeverities.Error);
			addToParent(parent, statement);
			return statement;
		}
		Expression procName = statement.getAt(1);

		String sName = IncrTclUtils.extractMethodName(procName);
		if (sName == null || sName.length() == 0) {
			this.report(parser, "Wrong number of arguments", statement
					.sourceStart(), statement.sourceEnd(),
					ProblemSeverities.Error);
			addToParent(parent, statement);
			return statement;
		}
		Expression procCode = statement.getAt(2);

		IncrTclConfigBody configBody = new IncrTclConfigBody(statement
				.sourceStart(), statement.sourceEnd());
		configBody.setName(sName);
		configBody.setNameStart(procName.sourceStart());
		configBody.setNameEnd(procName.sourceEnd());
		// For correct modifiers we need to add
		Block block = new Block(procCode.sourceStart(), procCode.sourceEnd());
		configBody.acceptBody(block);
		IncrTclUtils.parseAddToBlock(parser, procCode, block);

		configBody.setModifier(IIncrTclModifiers.AccIncrTcl);
		this.addToParent(parent, configBody);

		return configBody;
	}
}
