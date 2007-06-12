/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.ti.goals;

import org.eclipse.dltk.ti.InstanceContext;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MethodReturnTypeGoal extends AbstractTypeGoal {

	private final String methodName;
	private final IEvaluatedType[] arguments;

	public String getMethodName() {
		return methodName;
	}

	public IEvaluatedType[] getArguments() {
		return arguments;
	}

	public MethodReturnTypeGoal(InstanceContext context, String methodName,
			IEvaluatedType[] arguments) {
		super(context);
		this.methodName = methodName;
		this.arguments = arguments;
	}

}
