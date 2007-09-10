package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.SimpleReference;

public class XOTclMethodCallStatement extends CallExpression {
	private XOTclInstanceVariable instanceVariable;

	public XOTclMethodCallStatement(int start, int end, SimpleReference name,
			XOTclInstanceVariable var, CallArgumentsList args) {
		super(start, end, var, name, args);
		this.instanceVariable = var;
	}

	public int getKind() {
		return 0;
	}

	public XOTclInstanceVariable getInstanceVariable() {
		return (XOTclInstanceVariable) instanceVariable;
	}

	public void setInstNameRef(SimpleReference at) {
		this.receiver = at;
	}

	public SimpleReference getInstNameRef() {
		return ((SimpleReference)this.getReceiver());
	}
}
