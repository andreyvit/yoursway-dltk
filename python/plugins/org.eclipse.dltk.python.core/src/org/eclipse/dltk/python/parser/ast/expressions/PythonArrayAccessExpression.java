package org.eclipse.dltk.python.parser.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;

public class PythonArrayAccessExpression extends Expression implements ExtendedVariableReferenceInterface{

	private final Expression array;
	private final Expression index;

	public PythonArrayAccessExpression(Expression array, Expression index) {
		if (array == null)
			throw new IllegalArgumentException();
		this.array = array;
		this.index = index;
	}
	
	@Override
	public int getKind() {
		return 0;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			array.traverse(visitor);
			if(index != null)
				index.traverse(visitor);
			visitor.endvisit(this);
		}
	}
	
	public List getFlatNodeList() {
		List list;
		if(array instanceof ExtendedVariableReferenceInterface){
			list = ((ExtendedVariableReferenceInterface) array).getFlatNodeList();
		} else {
			list = new ArrayList();
			list.add(array);
		}
		list.add(new IndexHolder(this.sourceStart(), this.sourceEnd(), index));
		return list;
	}
}
