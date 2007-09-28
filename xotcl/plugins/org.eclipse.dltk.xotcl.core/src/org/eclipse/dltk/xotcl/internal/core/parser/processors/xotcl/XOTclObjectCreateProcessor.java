package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclObjectDeclaration;

public class XOTclObjectCreateProcessor extends AbstractTclCommandProcessor {
	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		TclStatement statement = (TclStatement) parser.processLocal(command, offset, parent);
		Expression e = statement.getAt(1);
		String commandOrName = extractSimpleReference(e);
		if( commandOrName == null) {
			this.report(parser, "A command or an object name expected.", e, ProblemSeverities.Error);
			return null;
		}
		
		if( commandOrName.equals("create")) {
			e = statement.getAt(2);
			commandOrName = extractSimpleReference(e);
			if( commandOrName == null) {
				this.report(parser, "An object name expected.", e, ProblemSeverities.Error);
				return null;
			}
		}
		XOTclObjectDeclaration var = new XOTclObjectDeclaration(commandOrName, e.sourceStart(), e.sourceEnd(), statement.sourceStart(), statement.sourceEnd());
		var.setModifiers(IXOTclModifiers.AccXOTcl | IXOTclModifiers.AccXOTclObject);
		this.addToParent(parent, var);
		return var;
	}
}
