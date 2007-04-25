package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.ti.goals.ItemReference;
import org.eclipse.dltk.ti.goals.PossiblePosition;

public class RubyFieldReference extends ItemReference {

	private final Statement node;
	
	public RubyFieldReference(String name, String parentModelKey, PossiblePosition pos, Statement node) {
		super(name, parentModelKey, pos);
		this.node = node;
	}

	/**
	 * Node could be VariableReference or CallExpression or another value 
	 * changing node
	 */
	public Statement getNode() {
		return node;
	}
	
	

}
