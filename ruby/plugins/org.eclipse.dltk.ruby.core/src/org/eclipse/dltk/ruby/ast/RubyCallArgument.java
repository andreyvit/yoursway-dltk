package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class RubyCallArgument extends Expression {
	
	public final static int SIMPLE = 0;	
	
	public final static int VARARG = 1;
	
	public final static int BLOCK = 2;
	
	private Statement value;
	
	private int kind;

	public Statement getValue() {
		return value;
	}

	public void setValue(Statement value) {
		this.value = value;
	}

	public RubyCallArgument(Statement value) {
		super(value.sourceStart(), value.sourceEnd());
		this.value = value;
	}
	
	public RubyCallArgument(Statement value, int kind) {
		super(value.sourceStart(), value.sourceEnd());
		this.value = value;
		this.kind = kind;
	}
	
	public int getArgumentKind() {
		return kind;
	}
	
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void printNode(CorePrinter output) {
		// TODO Auto-generated method stub

	}

	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub

	}

}
