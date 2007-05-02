/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.evaluation.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.ti.types.IEvaluatedType;


public class MultiTypeType implements IEvaluatedType
{	
	private List/*<IEvaluatedType>*/ fTypes = new ArrayList/*<IEvaluatedType>*/();
	
	public MultiTypeType() {
		
	}
	
	/**
	 * Add using equal.
	 */
	public void addType( IEvaluatedType type ) {
		Iterator i = fTypes.iterator();
		while( i.hasNext() ) {
			IEvaluatedType ltype = (IEvaluatedType)i.next();
			if( ltype.equals(type) ) {
				return;
			}
		}
		fTypes.add( type );
	}
	public String getTypeName( ) {
		String names = "";
		Iterator i = fTypes.iterator();
		while( i.hasNext() ) {
			IEvaluatedType type = (IEvaluatedType)i.next();
			names += type.getTypeName() + " ";
		}
		return "multitype:" + names;
	}
	
	public List/*< IEvaluatedType >*/ getTypes() {
		return this.fTypes;
	}

	public int size( ) {
		if( this.fTypes != null ) {
			return this.fTypes.size();
		}
		return 0;
	}

	public IEvaluatedType get( int i ) {
		if( this.fTypes != null ) {
			return (IEvaluatedType)this.fTypes.get( i );
		}
		return null;
	}

	public boolean subtypeOf(IEvaluatedType type) {
		// TODO Auto-generated method stub
		return false;
	}

}
