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
