package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.InstanceContext;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodReturnTypeGoal extends AbstractGoal {

	private final String methodName;
	private final IEvaluatedType[] arguments;

	public String getMethodName() {
		return methodName;
	}

	public IEvaluatedType[] getArguments() {
		return arguments;
	}

	public MethodReturnTypeGoal(InstanceContext context, String methodName, IEvaluatedType[] arguments) {
		super(context);
		this.methodName = methodName;
		this.arguments = arguments;
	}

}
