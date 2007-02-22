package org.eclipse.dltk.evaluation.types;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;

public class ClassType implements IClassType
{
	private TypeDeclaration fClass;
	private ModuleDeclaration fModule;
	
	public ClassType( ModuleDeclaration module, TypeDeclaration method ) {
		this.fClass = method;
		this.fModule = module;
	}
	
	
	
	public boolean equals( Object obj ) {

		if( obj instanceof ClassType ) {
			ClassType m = (ClassType)obj;			
			if( this.fClass == m.fClass && this.fModule == m.fModule ) {
				return true;
			}
		}
		return false;
	}


	public String getTypeName( ) {
		if( fClass != null ) {
			return "class:" + fClass.getName();
		}
		return "class: !!unknown!!";
	}


	public TypeDeclaration getTypeDeclaration( ) {

		return this.fClass; 
	}

}
