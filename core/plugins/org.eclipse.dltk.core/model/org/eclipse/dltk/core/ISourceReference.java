package org.eclipse.dltk.core;

/**
 * Common protocol for model elements that have associated source code.
 */
public interface ISourceReference {
	/**
	 * Returns whether this element exists in the model.
	 *
	 * @return <code>true</code> if this element exists in the script model
	 *
	 */
	boolean exists();
			
	/**
	 * Returns the source range associated with this element.
	 * <p>
	 * For class files, this returns the range of the entire compilation unit 
	 * associated with the class file (if there is one).
	 * </p>
	 *
	 * @return the source range, or <code>null</code> if this element has no 
	 *   associated source code
	 * @exception ModelException if an exception occurs while accessing its corresponding resource
	 */
	ISourceRange getSourceRange() throws ModelException;
	
	/**
	 * Returns the source code associated with this element.
	 * This extracts the substring from the source buffer containing this source
	 * element. This corresponds to the source range that would be returned by
	 * <code>getSourceRange</code>.
	 * <p>
	 * For class files, this returns the source of the entire compilation unit 
	 * associated with the class file (if there is one).
	 * </p>
	 *
	 * @return the source code, or <code>null</code> if this element has no 
	 *   associated source code
	 * @exception ModelException if an exception occurs while accessing its corresponding resource
	 */
	String getSource() throws ModelException;
}
