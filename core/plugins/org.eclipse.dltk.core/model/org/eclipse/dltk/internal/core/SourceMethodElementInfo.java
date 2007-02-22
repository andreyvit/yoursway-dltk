package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.DLTKCore;

class SourceMethodElementInfo extends MemberElementInfo {

	/**
	 * For a source method (that is, a method contained in a source module)
	 * this is a collection of the names of the parameters for this method,
	 * in the order the parameters are delcared. 
	 */
	private String[] argumentNames;
	private String[] argumentInitializers;
	
	protected void setArgumentNames(String[] names) {
		this.argumentNames = names;
	}
	
	public String[] getArgumentNames() {
		return this.argumentNames;
	}
	protected void setArgumentInializers(String[] initializers) {
		this.argumentInitializers = initializers;
	}
	
	public String[] getArgumentInitializers() {
		return this.argumentInitializers;
	}

	public boolean isConstructor() {
		if( DLTKCore.DEBUG) {
			System.err.println("Implement is Constructor");
		}
		return false;
	}
}
