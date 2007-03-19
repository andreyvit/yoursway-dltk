package org.eclipse.dltk.ti.types;


/**
 * Represents type as some user class (possibly built-in
 * class, like in Ruby). Each such class should be presented 
 * inside a DLTK MixinModel.  
 */
public abstract class ClassType implements IEvaluatedType {

	public String getTypeName() {
		return null;
	}
	
	public abstract String getModelKey ();

}
