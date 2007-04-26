package org.eclipse.dltk.ruby.ast;

import java.util.List;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyCallArgumentsList extends CallArgumentsList {
	
	public void addArgument (Statement value, int kind) {
		if (value == null)
			return;
		RubyCallArgument r = new RubyCallArgument(value, kind);
		this.addExpression(r);
	}
	
	public void autosetOffsets () {
		List expressions = this.getExpressions();
		int size = expressions.size();
		if (size > 0) {
			Statement first = (Statement) expressions.get(0);
			Statement last = (Statement) expressions.get(size - 1);
			this.setStart(first.sourceStart());
			this.setEnd (last.sourceEnd());
		}
	}

}
