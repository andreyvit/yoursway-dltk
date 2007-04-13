package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;

import org.eclipse.dltk.ast.statements.CompoundStatement;

public class DynamicBackquoteStringExpression extends CompoundStatement {

	public DynamicBackquoteStringExpression(int start, int end) {
		super(start, end, new ArrayList());
	}
	
}
