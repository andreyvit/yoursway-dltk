package org.eclipse.dltk.ruby.internal.core;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.ast.statements.Block;

public class RubyClassDeclaration extends TypeDeclaration {

	private Expression name;
	private Block body;
	
	
	public RubyClassDeclaration(DLTKToken name) {
		super(name);
	}

	public RubyClassDeclaration(Expression superClass, Expression name, Block body, int start, int end) {
		super (null); //wtf?
		ExpressionList el = new ExpressionList();
		el.addExpression(superClass);
		this.setSuperClasses(el);
		this.name = name;
		this.body = body;
		setStart(start);
		setEnd(end);
	}
	
	public Expression getClassName() {
		return name;
	}
	
	public Block getBody() {
		return body;
	}
	
}
