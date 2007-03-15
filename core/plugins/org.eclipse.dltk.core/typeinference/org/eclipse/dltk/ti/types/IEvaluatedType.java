package org.eclipse.dltk.ti.types;

public interface IEvaluatedType {
	/**
	 * 
	 * @deprecated
	 */
	public String getTypeName();
	
	/**
	 * Returns <code>true</code> if it is subtype of a given
	 * type 
	 */
	public boolean subtypeOf(IEvaluatedType type);
	
}
