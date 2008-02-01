package org.eclipse.dltk.itcl.internal.core.parser.processors;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.FieldDeclaration;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.itcl.internal.core.parser.ast.IncrTclMethodCallStatement;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.tcl.core.ITclParser;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;

public class IncrTclClassMethodCallCommandProcessor extends
		AbstractTclCommandProcessor {

	public IncrTclClassMethodCallCommandProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		Object param = this.getDetectedParameter();
		if (param == null || !(param instanceof FieldDeclaration)) {
			return null;
		}
		TclStatement statement = (TclStatement) parser.processLocal(command,
				offset, parent);
		FieldDeclaration inst = (FieldDeclaration) param;
		Expression nameExpr = statement.getAt(1);
		if (!(nameExpr instanceof SimpleReference)) {
			this.report(parser, "A method name expected.", statement, ProblemSeverities.Error);
			return null;
		}
		SimpleReference name = (SimpleReference) nameExpr;
		CallArgumentsList args = null;
		if (statement.getCount() > 2) {
			args = new CallArgumentsList(name.sourceEnd() + 1, statement
					.sourceEnd());
			for (int i = 2; i < statement.getCount(); i++) {
				args.addNode(statement.getAt(i));
			}
		}
		IncrTclMethodCallStatement call = new IncrTclMethodCallStatement(statement
				.sourceStart(), statement.sourceEnd(), name, inst, args);
		call.setInstNameRef((SimpleReference) statement.getAt(0));
		call.setStart(statement.sourceStart());
		call.setEnd(statement.sourceEnd());
		this.addToParent(parent, call);
		return call;
	}

}
