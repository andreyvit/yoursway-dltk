package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.core.IMethod;

public class ModelFunctionType implements IFunctionType
{
	private IMethod fMethod;	
	
	public ModelFunctionType( IMethod method ) {
		this.fMethod = method;
	}
	
	
	
	public boolean equals( Object obj ) {

		if( obj instanceof ModelFunctionType ) {
			ModelFunctionType m = (ModelFunctionType)obj;			
			if( this.fMethod == m.fMethod ) {
				return true;
			}
		}
		return false;
	}


	public String getTypeName( ) {
		if( fMethod != null ) {
			return "function:" + fMethod.getElementName();
		}
		return "function: !!unknown!!";
	}


	public IMethod getFunction( ) {
		return this.fMethod;
	}

}
