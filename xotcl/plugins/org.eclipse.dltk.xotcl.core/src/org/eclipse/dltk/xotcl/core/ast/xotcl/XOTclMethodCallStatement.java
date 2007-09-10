package org.eclipse.dltk.xotcl.core.ast.xotcl;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.SimpleReference;

public class XOTclMethodCallStatement extends CallExpression {
	private XOTclInstanceVariable instanceVariable;
	public XOTclMethodCallStatement(SimpleReference name, XOTclInstanceVariable var, CallArgumentsList args) {
		super(var, name.getName(), args);
		this.instanceVariable = var;
	}
	public int getKind() {
		return 0;
	}
	public XOTclInstanceVariable getInstanceVariable() {
		return (XOTclInstanceVariable) getReceiver();
	}
	
	public void setInstNameRef(SimpleReference at) {
		this.receiver = at;
	}
	public SimpleReference getInstNameRef() {
		return this.getInstanceVariable().getRef();
	}
}
