package org.eclipse.dltk.ruby.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;

public class RubyExceptionList extends Expression {
	
	private final List args = new ArrayList ();
	private VariableReference var;
	
	public RubyExceptionList(int start, int end, VariableReference var) {
		super(start, end);
		this.var = var;
	}
	
	public void addArg(Statement e) {
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
		if (visitor.visit(this)) {
			if (var != null)
				var.traverse(visitor);
			if (args != null) {
				for (Iterator iterator = args.iterator(); iterator
						.hasNext();) {
					Statement a = (Statement) iterator.next();
					if (a != null)
						a.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

}
