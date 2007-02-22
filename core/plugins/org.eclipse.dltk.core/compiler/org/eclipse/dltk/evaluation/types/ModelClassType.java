package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.core.IType;


public class ModelClassType implements IClassType
{
	private IType fClass;	
	
	public ModelClassType( IType classElement ) {
		this.fClass = classElement;		
	}	
	
	
	public boolean equals( Object obj ) {

		if( obj instanceof ModelClassType ) {
			ModelClassType m = (ModelClassType)obj;			
			if( this.fClass == m.fClass ) {
				return true;
			}
		}
		return false;
	}


	public String getTypeName( ) {
		if( fClass != null ) {
			return "class:" + fClass.getElementName();
		}
		return "class: !!unknown!!";
	}


	public IType getTypeDeclaration( ) {

		return this.fClass; 
	}	
}
