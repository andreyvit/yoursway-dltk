package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IExternalSourceModule;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProblemRequestor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;

/**
 * Base class for all external source module representations.
 */
public abstract class AbstractExternalSourceModule extends AbstractSourceModule
		implements IExternalSourceModule, IStorage {

	protected AbstractExternalSourceModule(ModelElement parent, String name,
			WorkingCopyOwner owner) {
		this(parent, name, owner, true);
	}

	protected AbstractExternalSourceModule(ModelElement parent, String name,
			WorkingCopyOwner owner, boolean readOnly) {
		super(parent, name, owner, true);
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#becomeWorkingCopy(org.eclipse.dltk.core.IProblemRequestor,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void becomeWorkingCopy(IProblemRequestor problemRequestor,
			IProgressMonitor monitor) {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.dltk.core.ICodeAssist#codeComplete(int,
	 *      org.eclipse.dltk.core.CompletionRequestor)
	 */
	public void codeComplete(int offset, CompletionRequestor requestor) {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.dltk.core.ICodeAssist#codeComplete(int,
	 *      org.eclipse.dltk.core.CompletionRequestor,
	 *      org.eclipse.dltk.core.WorkingCopyOwner)
	 */
	public void codeComplete(int offset, CompletionRequestor requestor,
			WorkingCopyOwner owner) {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#commitWorkingCopy(boolean,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void commitWorkingCopy(boolean force, IProgressMonitor monitor)
			throws ModelException {
		throw new ModelException(new ModelStatus(
				IModelStatusConstants.INVALID_ELEMENT_TYPES, this));
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceManipulation#delete(boolean,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, IProgressMonitor monitor) {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#discardWorkingCopy()
	 */
	public void discardWorkingCopy() {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.core.runtime.PlatformObject#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter == IStorage.class) {
			return this;
		}

		return super.getAdapter(adapter);
	}

	/*
	 * @see org.eclipse.dltk.core.IModelElement#getResource()
	 */
	public IResource getResource() {
		return null;
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#getWorkingCopy(org.eclipse.dltk.core.WorkingCopyOwner,
	 *      org.eclipse.dltk.core.IProblemRequestor,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public ISourceModule getWorkingCopy(WorkingCopyOwner workingCopyOwner,
			IProblemRequestor problemRequestor, IProgressMonitor monitor) {
		return this;
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#isWorkingCopy()
	 */
	public boolean isWorkingCopy() {
		return true;
	}

	/*
	 * @see org.eclipse.dltk.internal.core.Openable#makeConsistent(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void makeConsistent(IProgressMonitor monitor) throws ModelException {
		// makeConsistent(false/*don't create AST*/, 0, monitor);
		openWhenClosed(createElementInfo(), monitor);
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceManipulation#move(org.eclipse.dltk.core.IModelElement,
	 *      org.eclipse.dltk.core.IModelElement, java.lang.String, boolean,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IModelElement container, IModelElement sibling,
			String rename, boolean replace, IProgressMonitor monitor)
			throws ModelException {
		// this may be slightly misleading to the user...
		copy(container, sibling, rename, replace, monitor);
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceModule#reconcile(boolean,
	 *      org.eclipse.dltk.core.WorkingCopyOwner,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void reconcile(boolean forceProblemDetection,
			WorkingCopyOwner workingCopyOwner, IProgressMonitor monitor) {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.dltk.core.ISourceManipulation#rename(java.lang.String,
	 *      boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void rename(String name, boolean replace, IProgressMonitor monitor) {
		// external, do nothing
	}

	/*
	 * @see org.eclipse.dltk.internal.core.Openable#closing(java.lang.Object)
	 */
	protected void closing(Object info) {
		// lifetime of the working copy
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#getProblemReporter(java.lang.String)
	 */
	protected IProblemReporter getProblemReporter(String natureId) {
		// external, no reporter required
		return null;
	}

	/*
	 * @see org.eclipse.dltk.internal.core.AbstractSourceModule#validateSorceModule(org.eclipse.dltk.core.IDLTKLanguageToolkit,
	 *      org.eclipse.core.resources.IResource)
	 */
	protected IStatus validateSorceModule(IDLTKLanguageToolkit toolkit,
			IResource resource) {
		// external, resource will always be null
		IPath path = getFullPath();
		if (toolkit == null) {
			toolkit = DLTKLanguageManager.findToolkit(path);
		}

		if (toolkit != null) {
			return toolkit.validateSourceModule(path);
		}

		return null;
	}
}
