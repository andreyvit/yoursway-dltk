package org.eclipse.dltk.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.internal.core.ModelStatus;



public class ModelException extends CoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CoreException nestedCoreException;
	
	/**
	 * Creates a model exception that wrappers the given <code>Throwable</code>.
	 * The exception contains a script-specific status object with severity
	 * <code>IStatus.ERROR</code> and the given status code.
	 *
	 * @param e the <code>Throwable</code>
	 * @param code one of the model-specific status codes declared in
	 *   <code>IScriptModelStatusConstants</code>
	 * @see IModelStatusConstants
	 * @see org.eclipse.core.runtime.IStatus#ERROR
	 */
	public ModelException(Throwable e, int code) {
		this(new ModelStatus(code, e)); 
	}
	
	/**
	 * Creates a model exception for the given script-specific status object.
	 *
	 * @param status the script-specific status object
	 */
	public ModelException(IModelStatus status) {
		super(status);
	}
	
	/**
	 * Creates a model exception for the given <code>CoreException</code>.
	 * Equivalent to 
	 * <code>ModelException(exception,IModelStatusConstants.CORE_EXCEPTION</code>.
	 *
	 * @param exception the <code>CoreException</code>
	 */
	public ModelException(CoreException exception) {
		super(exception.getStatus());
		this.nestedCoreException = exception;
	}

	/**
	 * Returns the underlying <code>Throwable</code> that caused the failure.
	 *
	 * @return the wrappered <code>Throwable</code>, or <code>null</code> if the
	 *   direct case of the failure was at the script model layer
	 */
	public Throwable getException() {
		if (this.nestedCoreException == null) {
			return getStatus().getException();
		} else {
			return this.nestedCoreException;
		}
	}
	
	/**
	 * Returns the model status object for this exception.
	 * Equivalent to <code>(IModelStatus) getStatus()</code>.
	 *
	 * @return a status object
	 */
	public IModelStatus getModelStatus() {
		IStatus status = this.getStatus();
		if (status instanceof IModelStatus) {
			return (IModelStatus)status;
		} else {
			return new ModelStatus(this.nestedCoreException);
		}
	}	
	/**
	 * Returns whether this exception indicates that a script model element does not
	 * exist. Such exceptions have a status with a code of
	 * <code>IModelStatusConstants.ELEMENT_DOES_NOT_EXIST</code> or
	 * <code>IModelStatusConstants.ELEMENT_NOT_ON_CLASSPATH</code>.
	 * This is a convenience method.
	 *
	 * @return <code>true</code> if this exception indicates that a script model
	 *   element does not exist
	 * @see IModelStatus#isDoesNotExist()
	 * @see IModelStatusConstants#ELEMENT_DOES_NOT_EXIST
	 * @see IModelStatusConstants#ELEMENT_NOT_ON_CLASSPATH
	 */
	public boolean isDoesNotExist() {
		IModelStatus modelStatus = getModelStatus();
		return modelStatus != null && modelStatus.isDoesNotExist();
	}	
}
