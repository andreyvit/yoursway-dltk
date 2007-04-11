package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;

public class ExceptionList extends Expression {
	
	private final List args = new ArrayList ();
	private VariableReference var;
	
	public ExceptionList(int start, int end, VariableReference var) {
		super(start, end);
		this.var = var;
	}
	
	public void addArg(Expression e) {
		args.add(e);
	}
	
	public List getArgs () {
		return args;
	}
	
	public VariableReference getVar () {
		return var;
	}
	
	public void setVar (VariableReference v) {
		this.var = v;
	}

	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void traverse(ASTVisitor visitor) throws Exception {
		// TODO Auto-generated method stub

	}

}
