/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.core.evaluation.types;

import org.eclipse.dltk.core.IModule;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class ImportedType implements IEvaluatedType
{
	private IModule fModule = null;
	private IEvaluatedType fType = null;
	public ImportedType( IEvaluatedType type, IModule module ) {

		this.fType = type;
		this.fModule = module;
	}
	
	
	public boolean equals( Object obj ) {

		if( obj instanceof ImportedType ) {
			ImportedType o2 = (ImportedType)obj;			
			if( this.fModule != null && this.fModule.equals( o2.getModule() ) ) {
				if( this.fType != null && this.fType.equals( o2.getType() ) ) {
					return true;
				}
			}
		}
		return false;
	}
	public IModule getModule() {
		return this.fModule;
	}
	public IEvaluatedType getType() {
		return this.fType;
	}

	public String getTypeName()
	{
		if( this.fType != null && this.fModule != null ) { 
			return this.fType.getTypeName();
		}
		else {
			return "imported type";
		}
	}


	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}
}
