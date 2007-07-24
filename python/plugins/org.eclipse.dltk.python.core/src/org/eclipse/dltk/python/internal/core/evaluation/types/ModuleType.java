/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation.types;

import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ModuleType implements IEvaluatedType
{
	private String fModule = null;
	
	public ModuleType( String module ) {
		this.fModule = module;
	}
	
	public String getModule() {
		return this.fModule;
	}

	public String getTypeName( ) {
		if( this.fModule != null ) {
			return "module:" + this.fModule;
		}
		else {
			return "unknown module";
		}
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
