package org.eclipse.dltk.python.parser.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.references.VariableReference;

public class PythonVariableAccessExpression extends Expression implements ExtendedVariableReferenceInterface{

	private final Expression receiver;
	private final VariableReference variable;

	public PythonVariableAccessExpression(Expression receiver, VariableReference variable) {
		if (receiver == null || variable == null)
			throw new IllegalArgumentException();
		this.receiver = receiver;
		this.variable = variable;
	}
	
	public Expression receiver() {
		return receiver;
	}
	
	public VariableReference variable() {
		return variable;
	}
	
	public String fqnRepresentation() {
		if (receiver instanceof SimpleReference) {
			SimpleReference simpleReference = (SimpleReference) receiver;
			String name = simpleReference.getName();
			if (name == null)
				return null;
			return name + "." + variable.getName();
		}
		if (receiver instanceof PythonVariableAccessExpression) {
			PythonVariableAccessExpression receiverExpr = (PythonVariableAccessExpression) receiver;
			String fqn = receiverExpr.fqnRepresentation();
			if (fqn == null)
				return null;
			return fqn + "." + variable.getName();
		}
		return null;
	}
	
	
	@Override
	public int getKind() {
		return 0;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			receiver.traverse(visitor);
			variable.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public List getFlatNodeList() {
		List list;
		if(receiver instanceof ExtendedVariableReferenceInterface){
			list = ((ExtendedVariableReferenceInterface) receiver).getFlatNodeList();
		} else {
			list = new ArrayList();
			list.add(receiver);
		}
		list.add(variable);
		return list;
	}

}
