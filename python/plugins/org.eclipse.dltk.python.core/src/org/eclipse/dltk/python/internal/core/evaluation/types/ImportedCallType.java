/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation.types;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ImportedCallType implements IEvaluatedType
{	
	private IModelElement fModelElement = null;
	public ImportedCallType( IModelElement element ) {

		this.fModelElement = element;
	}
	
	public IModelElement getElement() {
		return this.fModelElement;
	}

	public String getTypeName()
	{				
		return "imported call type";
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}
}
