package org.eclipse.dltk.tcl.internal.core.parser.processors.tcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.core.TclParseUtil;
import org.eclipse.dltk.tcl.core.ast.TclVariableDeclaration;
import org.eclipse.dltk.tcl.internal.core.codeassist.TclVisibilityUtils;

public class TclVariableProcessor extends AbstractTclCommandProcessor {

	public TclVariableProcessor() {
	}

	public ASTNode process(TclStatement statement, ITclParser parser, 
			ASTNode parent) {
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
			if (TclVisibilityUtils.isPrivate(variable.getName())) {
				var.setModifier(Modifiers.AccPrivate);
			} else {
				var.setModifier(Modifiers.AccPublic);
			}
			this.addToParent(parent, var);
			return var;
		}
		return null;
	}
}
