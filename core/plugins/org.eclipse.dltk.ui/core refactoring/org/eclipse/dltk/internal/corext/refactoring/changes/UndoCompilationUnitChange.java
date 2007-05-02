/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.changes;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.ContentStamp;
import org.eclipse.ltk.core.refactoring.UndoTextFileChange;
import org.eclipse.text.edits.UndoEdit;


/* package */ class UndoSourceModuleChange extends UndoTextFileChange {
	
	private ISourceModule fCUnit;

	public UndoSourceModuleChange(String name, ISourceModule unit, UndoEdit undo, ContentStamp stampToRestore, int saveMode) throws CoreException {
		super(name, getFile(unit), undo, stampToRestore, saveMode);
		fCUnit= unit;
	}

	private static IFile getFile(ISourceModule cunit) throws CoreException {
		IFile file= (IFile)cunit.getResource();
		if (file == null)
			throw new CoreException(new Status(
				IStatus.ERROR, 
				DLTKUIPlugin.PLUGIN_ID, 
				IStatus.ERROR, 
				Messages.format(
					RefactoringCoreMessages.UndoSourceModuleChange_no_resource, 
					cunit.getElementName()), 
				null)
			);
		return file;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object getModifiedElement() {
		return fCUnit;
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected Change createUndoChange(UndoEdit edit, ContentStamp stampToRestore) throws CoreException {
		return new UndoSourceModuleChange(getName(), fCUnit, edit, stampToRestore, getSaveMode());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Change perform(IProgressMonitor pm) throws CoreException {
		pm.beginTask("", 2); //$NON-NLS-1$
		fCUnit.becomeWorkingCopy(null, new SubProgressMonitor(pm,1));
		try {
			return super.perform(new SubProgressMonitor(pm,1));
		} finally {
			fCUnit.discardWorkingCopy();
		}
	}
}
