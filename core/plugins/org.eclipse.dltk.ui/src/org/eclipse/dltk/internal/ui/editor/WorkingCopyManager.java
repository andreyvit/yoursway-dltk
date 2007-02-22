package org.eclipse.dltk.internal.ui.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.IWorkingCopyManager;
import org.eclipse.ui.IEditorInput;


public class WorkingCopyManager implements IWorkingCopyManager {

	private ISourceModuleDocumentProvider fDocumentProvider;
	
	// XXX: always empty, remove in March 2007
	private Map fMap;
	private boolean fIsShuttingDown;

	/**
	 * Creates a new working copy manager that co-operates with the given
	 * compilation unit document provider.
	 *
	 * @param provider the provider
	 */
	public WorkingCopyManager(ISourceModuleDocumentProvider provider) {
		fDocumentProvider= provider;
	}
	
	// XXX: never called
	public void connect(IEditorInput input) throws CoreException {
		fDocumentProvider.connect(input);
	}

	// XXX: never called
	public void disconnect(IEditorInput input) {
		fDocumentProvider.disconnect(input);
	}
	
	// XXX: never called
	public void shutdown() {
		if (!fIsShuttingDown) {
			fIsShuttingDown= true;
			try {
				if (fMap != null) {
					fMap.clear();
					fMap= null;
				}
				fDocumentProvider.shutdown();
			} finally {
				fIsShuttingDown= false;
			}
		}
	}
	
	public ISourceModule getWorkingCopy(IEditorInput input) {
		return getWorkingCopy(input, true);
	}

	/**
	 * Returns the working copy remembered for the compilation unit encoded in the
	 * given editor input.
	 * <p>
	 * Note: This method must not be part of the public {@link IWorkingCopyManager} API.
	 * </p>
	 *
	 * @param input the editor input
	 * @param primaryOnly if <code>true</code> only primary working copies will be returned
	 * @return the working copy of the compilation unit, or <code>null</code> if the
	 *   input does not encode an editor input, or if there is no remembered working
	 *   copy for this compilation unit
	 *
	 */
	public ISourceModule getWorkingCopy(IEditorInput input, boolean primaryOnly) {
		ISourceModule unit= fMap == null ? null : (ISourceModule) fMap.get(input);
		if (unit == null)
			unit= fDocumentProvider.getWorkingCopy(input);
		if (unit != null && (!primaryOnly || DLTKModelUtil.isPrimary(unit)))
			return unit;
		return null;
	}
	
	// XXX: never called, remove in March 2007
	public void setWorkingCopy(IEditorInput input, ISourceModule workingCopy) {
		if (fDocumentProvider.getDocument(input) != null) {
			if (fMap == null)
				fMap= new HashMap();
			fMap.put(input, workingCopy);
		}
	}

	// XXX: never called, remove in March 2007
	public void removeWorkingCopy(IEditorInput input) {
		fMap.remove(input);
		if (fMap.isEmpty())
			fMap= null;
	}
}
