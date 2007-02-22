package org.jruby.ast;

import java.util.List;

import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.evaluator.Instruction;
import org.jruby.lexer.yacc.ISourcePosition;

public class ErrorNode extends Node {

	public ErrorNode(ISourcePosition position) {
		super(position, -42);
	}

	public Instruction accept(NodeVisitor visitor) {
		return null;
	}

	public List childNodes() {
		return null;
	}

}
