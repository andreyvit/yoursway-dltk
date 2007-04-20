package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;

import org.eclipse.dltk.ast.statements.CompoundStatement;

public class RubyDynamicBackquoteStringExpression extends CompoundStatement {

	public RubyDynamicBackquoteStringExpression(int start, int end) {
		super(start, end, new ArrayList());
	}
	
}
