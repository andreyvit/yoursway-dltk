package org.eclipse.dltk.python.parser.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.expressions.ExpressionList;

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
			if (arguments == null)
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

}
