package org.eclipse.dltk.xotcl.internal.core.parser.processors.xotcl;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.tcl.ast.TclStatement;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclCommand;
import org.eclipse.dltk.xotcl.core.AbstractTclCommandProcessor;
import org.eclipse.dltk.xotcl.core.ITclParser;
import org.eclipse.dltk.xotcl.core.ast.xotcl.XOTclInstanceVariable;

public class XOTclClassNewInstanceProcessor extends AbstractTclCommandProcessor {

	public XOTclClassNewInstanceProcessor() {
	}

	public ASTNode process(TclCommand command, ITclParser parser, int offset,
			ASTNode parent) {
		Object param = this.getDetectedParameter();
		if( param == null || !(param instanceof TypeDeclaration) ) {
			return null;
		}
		TypeDeclaration type = (TypeDeclaration) param;
		TclStatement statement = (TclStatement) parser.processLocal(command, offset);
		Expression e = statement.getAt(1);
		String commandOrName = this.extractSimpleReference(e);
		if( commandOrName == null) return null;
		
		if( commandOrName.equals("create")) {
			e = statement.getAt(2);
			commandOrName = this.extractSimpleReference(e);
			if( commandOrName == null) return null;
		}
		XOTclInstanceVariable var = new XOTclInstanceVariable(commandOrName, e.sourceStart(), e.sourceEnd(), statement.sourceStart(), statement.sourceEnd());
		var.setClassInstanceName((SimpleReference) statement.getAt(0));
		var.setDeclaringType(type);
		this.addToParent(parent, var);
		return var;
	}
}
