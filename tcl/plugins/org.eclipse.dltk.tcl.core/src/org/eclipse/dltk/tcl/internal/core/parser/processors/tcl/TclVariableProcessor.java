package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.TclVariableDeclaration;

public class TclVariableProcessor extends AbstractTclCommandProcessor {

	public TclVariableProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command,
				offset, parent);
		if( statement == null ) {
			return null;
		}
		if (statement.getCount() < 2) {
			this.report(parser, "Syntax error: at least one argument expected.", statement, ProblemSeverities.Error);
			return null;
		}
		Expression variableName = statement.getAt(1);
		Expression variableValue = null;
		if (statement.getCount() == 3)
			variableValue = statement.getAt(2);
		if (variableName == null) {
			throw new RuntimeException("empty variable name");
		}
		SimpleReference variable = TclParseUtil.makeVariable(variableName);
		if (variable != null) {
			TclVariableDeclaration var = new TclVariableDeclaration(variable,
					variableValue, statement.sourceStart(), statement
							.sourceEnd());
			this.addToParent(parent, var);
			return var;
		}
		return null;
	}
}
