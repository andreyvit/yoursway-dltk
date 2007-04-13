package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyCallArgumentsList extends CallArgumentsList {
	
	public void addArgument (Statement value, int kind) {
		RubyCallArgument r = new RubyCallArgument(value, kind);
		this.addExpression(r);
	}

}
