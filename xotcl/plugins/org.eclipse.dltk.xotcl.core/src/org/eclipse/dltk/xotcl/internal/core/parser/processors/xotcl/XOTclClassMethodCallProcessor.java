package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclMethodCallStatement;

public class XOTclClassMethodCallProcessor extends AbstractTclCommandProcessor {

	public XOTclClassMethodCallProcessor() {
		// TODO Auto-generated constructor stub
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		Object param = this.getDetectedParameter();
		if (param == null || !(param instanceof XOTclInstanceVariable)) {
			return null;
		}
		TclStatement statement = (TclStatement) parser.processLocal(command,
				offset, parent);
		XOTclInstanceVariable inst = (XOTclInstanceVariable) param;
		Expression nameExpr = statement.getAt(1);
		if (!(nameExpr instanceof SimpleReference)) {
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
		XOTclMethodCallStatement call = new XOTclMethodCallStatement(statement
				.sourceStart(), statement.sourceEnd(), name, inst, args);
		call.setInstNameRef((SimpleReference) statement.getAt(0));
		call.setStart(statement.sourceStart());
		call.setEnd(statement.sourceEnd());
		this.addToParent(parent, call);
		return call;
	}
}
