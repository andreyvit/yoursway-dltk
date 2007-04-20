package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;

public class RubyRedoExpression extends CallExpression {

	public RubyRedoExpression(int start, int end) {
		super(start, end, null, "redo", CallArgumentsList.EMPTY);
	}
	
	


}
