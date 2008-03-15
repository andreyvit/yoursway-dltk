package org.eclipse.dltk.python.parser.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;
import org.eclipse.dltk.ast.references.VariableReference;

import com.sun.org.apache.regexp.internal.recompile;

public class PythonCallExpression extends Expression implements ExtendedVariableReferenceInterface{

	private final Expression function;
	private final ExpressionList arguments;

	public PythonCallExpression(Expression function, ExpressionList arguments) {
		if (function == null)
			throw new IllegalArgumentException();
		this.function = function;
		this.arguments = arguments;
	}
	
	@Override
	public int getKind() {
		return 0;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			function.traverse(visitor);
			if (arguments != null)
				arguments.traverse(visitor);
			visitor.endvisit(this);
		}
	}

	public List getFlatNodeList() {
		List list;
		if(function instanceof ExtendedVariableReferenceInterface){
			list = ((ExtendedVariableReferenceInterface) function).getFlatNodeList();
		} else {
			list = new ArrayList();
			list.add(function);
		}
		list.add(this);
		return list;
	}

	public ASTNode getArgs() {
		return arguments;
	}

	public ASTNode getReceiver() {
		if (function instanceof PythonVariableAccessExpression){
			PythonVariableAccessExpression expr = (PythonVariableAccessExpression) function;
			return expr.getReceiver();
		}
		return null;
	}

	public String getMethodName() {
		if (function instanceof PythonVariableAccessExpression){
			PythonVariableAccessExpression expr = (PythonVariableAccessExpression) function;
			return expr.getName();
		}
		return null;
	}
	
	public String getProcedureName() {
		if (function instanceof VariableReference){
			VariableReference expr = (VariableReference) function;
			return expr.getName();
		}
		return null;
	}

}
