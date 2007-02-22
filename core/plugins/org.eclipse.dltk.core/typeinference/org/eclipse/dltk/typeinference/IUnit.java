package org.eclipse.dltk.typeinference;


public interface IUnit extends INodeElement {
	
	Stability getStability();
	
	void setStability(Stability stability);
	
	/**
	 * @deprecated
	 */
	IScope getScope();
	
}
