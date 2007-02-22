/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.changes;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;


public class DeleteFileChange extends AbstractDeleteChange {

	private final IPath fPath;
	private final boolean fIsExecuteChange;
	
	public DeleteFileChange(IFile file, boolean executeChange) {
		Assert.isNotNull(file, "file");  //$NON-NLS-1$
		fPath= Utils.getResourcePath(file);
		fIsExecuteChange= executeChange;
	}
	
	private IFile getFile(){
		return Utils.getFile(fPath);
	}
	
	/* non java-doc
	 * @see IChange#getName()
	 */
	public String getName() {
		return Messages.format(RefactoringCoreMessages.DeleteFileChange_1, fPath.lastSegment()); 
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException {
		if (fIsExecuteChange) {
			// no need for checking since we already prompt the
			// user if the file is dirty or read only
			return super.isValid(pm, NONE);
		} else {
			return super.isValid(pm, READ_ONLY | DIRTY);
		}
	}

	/* non java-doc
	 * @see IChange#getModifiedLanguageElement()
	 */
	public Object getModifiedElement() {
		return getFile();
	}

	/* non java-doc
	 * @see DeleteChange#doDelete(IProgressMonitor)
	 */
	protected void doDelete(IProgressMonitor pm) throws CoreException {
		IFile file= getFile();
		Assert.isNotNull(file);
		Assert.isTrue(file.exists());
		pm.beginTask("", 2); //$NON-NLS-1$
		saveFileIfNeeded(file, new SubProgressMonitor(pm, 1));
		file.delete(false, true, new SubProgressMonitor(pm, 1));
		pm.done();
	}
}

