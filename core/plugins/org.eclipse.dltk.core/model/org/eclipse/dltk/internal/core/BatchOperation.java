package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.ModelException;


/**
 * An operation created as a result of a call to DLTKCore.run(IWorkspaceRunnable, IProgressMonitor)
 * that encapsulates a user defined IWorkspaceRunnable.
 */
public class BatchOperation extends ModelOperation {
	protected IWorkspaceRunnable runnable;
	public BatchOperation(IWorkspaceRunnable runnable) {
		this.runnable = runnable;
	}

	protected boolean canModifyRoots() {
		// anything in the workspace runnable can modify the roots
		return true;
	}
	
	protected void executeOperation() throws ModelException {
		try {
			this.runnable.run(this.progressMonitor);
		} catch (CoreException ce) {
			if (ce instanceof ModelException) {
				throw (ModelException)ce;
			} else {
				if (ce.getStatus().getCode() == IResourceStatus.OPERATION_FAILED) {
					Throwable e= ce.getStatus().getException();
					if (e instanceof ModelException) {
						throw (ModelException) e;
					}
				}
				throw new ModelException(ce);
			}
		}
	}
		
	protected IModelStatus verify() {
		// cannot verify user defined operation
		return ModelStatus.VERIFIED_OK;
	}

	
}

