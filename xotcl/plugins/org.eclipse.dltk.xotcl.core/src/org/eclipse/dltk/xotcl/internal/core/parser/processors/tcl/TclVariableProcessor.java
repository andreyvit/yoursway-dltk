package org.eclipse.dltk.xotcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.TclParseUtil;
import org.eclipse.dltk.xotcl.core.ast.TclVariableDeclaration;

public class TclVariableProcessor extends AbstractTclCommandProcessor {

	public TclVariableProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset, ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command, offset);
		if (statement.getCount() < 3) {
			// TODO: Add error reporting here.
			return null;
		}
		Expression variableName = statement.getAt(1);
		Expression variableValue = statement.getAt(2);
		if (variableName == null) {
			throw new RuntimeException("empty variable name");
		}
		SimpleReference variable = TclParseUtil.makeVariable(variableName);
		TclVariableDeclaration var = new TclVariableDeclaration(variable, variableValue, statement.sourceStart(), statement.sourceEnd());
		this.addToParent(parent, var);
		return var;
	}
}
