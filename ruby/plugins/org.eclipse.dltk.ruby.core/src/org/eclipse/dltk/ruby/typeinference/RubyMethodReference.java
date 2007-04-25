package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ti.goals.ItemReference;
import org.eclipse.dltk.ti.goals.PossiblePosition;

public class RubyMethodReference extends ItemReference {

	private CallExpression node;
	
	public RubyMethodReference(String name, String parentModelKey,
			PossiblePosition pos, int accuracy) {
		super(name, parentModelKey, pos, accuracy);
	}

	public CallExpression getNode() {
		return node;
	}

	public void setNode(CallExpression node) {
		this.node = node;
	}
	
}
