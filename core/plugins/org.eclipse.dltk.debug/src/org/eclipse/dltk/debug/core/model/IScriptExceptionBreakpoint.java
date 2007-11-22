package org.eclipse.dltk.debug.core.model;

 
import org.eclipse.core.runtime.CoreException;

/**
 * A breakpoint that suspends execution when a corresponding exception
 * is thrown in a target VM. An exception breakpoint can be configured
 * to suspend execution when the corresponding exception is thrown in
 * a caught or uncaught location. As well, the location can be filtered
 * inclusively or exclusively by type name patterns.
 * <p>
 * Clients are not intended to implement this interface.
 * </p>
 * @since 2.0
 */
public interface IScriptExceptionBreakpoint extends IScriptBreakpoint {
	/**
	 * Returns whether this breakpoint suspends execution when the
	 * associated exception is thrown in a caught location (in
	 * a try/catch statement).
	 * 
	 * @return <code>true</code> if this is a caught exception
	 *  breakpoint
	 * @exception CoreException if unable to access the property from
	 * 	this breakpoint's underlying marker
	 */
	public boolean isCaught() throws CoreException;
	/**
	 * Returns whether this breakpoint suspends execution when the
	 * associated exception is thrown in an uncaught location (not
	 * caught by a try/catch statement).
	 * 
	 * @return <code>true</code> if this is an uncaught exception
	 *  breakpoint.
	 * @exception CoreException if unable to access the property from
	 * 	this breakpoint's underlying marker
	 */
	public boolean isUncaught() throws CoreException;		
	/**
	 * Sets whether this breakpoint suspends execution when the associated
	 * exception is thrown in a caught location (in a try/catch
	 * statement).
	 *
	 * @param caught whether or not this breakpoint suspends execution when the
	 *  associated exception is thrown in a caught location
	 * @exception CoreException if unable to set the property on
	 * 	this breakpoint's underlying marker
	 */
	public void setCaught(boolean caught) throws CoreException;
	/**
	 * Sets whether this breakpoint suspends execution when the associated
	 * exception is thrown in an uncaught location.
	 * 
	 * @param uncaught whether or not this breakpoint suspends execution when the
	 *  associated exception is thrown in an uncaught location
	 * @exception CoreException if unable to set the property
	 * 	on this breakpoint's underlying marker
	 */	
	public void setUncaught(boolean uncaught) throws CoreException;
	/**
	 * Returns the fully qualified type name of the exception that
	 * last caused this breakpoint to suspend, of <code>null</code>
	 * if this breakpoint has not caused a thread to suspend. Note
	 * that this name may be a sub type of the exception that this
	 * breakpoint is associated with.
	 * 
	 * @return fully qualified exception name or <code>null</code>
	 */
	public String getExceptionTypeName();
	
	/**
	 * Sets whether this breakpoint suspends execution when subclass
	 * of the associated exception is thrown.
	 *
	 * @param true if execution should be suspended when subclass thrown
	 * @exception CoreException if unable to set the property on
	 * 	this breakpoint's underlying marker
	 */
	public void setSuspendOnSubclasses(boolean suspend) throws CoreException;
	
	/**
	 * @return true whether execution is suspended when subclass of 
	 * exception is thrown
	 * @throws CoreException 
	 */
	public boolean isSuspendOnSubclasses() throws CoreException;
	
	
	/**
	 * Returns the fully qualified type name of the exception 
	 * this breakpoint is associated with
	 * @return fully qualified exception name 
	 * @throws CoreException 
	 */
	public String getTypeName() throws CoreException;
}

