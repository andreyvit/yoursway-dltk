/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ModelFunctionType implements IFunctionType {
	private IMethod fMethod;

	public ModelFunctionType(IMethod method) {
		this.fMethod = method;
	}

	public boolean equals(Object obj) {

		if (obj instanceof ModelFunctionType) {
			ModelFunctionType m = (ModelFunctionType) obj;
			if (this.fMethod == m.fMethod) {
				return true;
			}
		}
		return false;
	}
	

	public int hashCode() {
		return this.fMethod.hashCode();
	}

	public String getTypeName() {
		if (fMethod != null) {
			return "function:" + fMethod.getElementName(); //$NON-NLS-1$
		}
		return "function: !!unknown!!"; //$NON-NLS-1$
	}

	public IMethod getFunction() {
		return this.fMethod;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
