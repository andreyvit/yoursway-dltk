package org.eclipse.dltk.ruby.typeinference;

import org.eclipse.dltk.ddp.AbstractGoal;
import org.eclipse.dltk.ddp.IContext;
import org.eclipse.dltk.evaluation.types.IEvaluatedType;

public class MethodReturnTypeGoal extends AbstractGoal {

	public MethodReturnTypeGoal(InstanceContext context, String methodName, IEvaluatedType[] arguments) {
		super(context);
	}

}
