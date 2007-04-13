package org.eclipse.dltk.ruby.internal.core;

import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.CompoundStatement;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyClassDeclaration extends TypeDeclaration {

	private Statement name;
	private Block body;
	
	
	public RubyClassDeclaration(DLTKToken name) {
		super(name);
	}

	public RubyClassDeclaration(Expression superClass, Statement name, Block body, int start, int end) {
		super("", name.sourceStart(), name.sourceEnd(), start, end);
		CompoundStatement el = new CompoundStatement();
		el.addStatement(superClass);
		this.setSuperClasses(el);
		this.name = name;
		this.body = body;
		setStart(start);
		setEnd(end);
	}
	
	public Statement getClassName() {
		return name;
	}
	
	public Block getBody() {
		return body;
	}
	
}
