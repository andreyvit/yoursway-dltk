package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;

import org.eclipse.dltk.ast.ASTListNode;

public class RubyDynamicBackquoteStringExpression extends ASTListNode {

	public RubyDynamicBackquoteStringExpression(int start, int end) {
		super(start, end, new ArrayList());
	}
	
}
