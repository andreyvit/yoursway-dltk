/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.corext.refactoring.nls.changes.CreateTextFileChange;
import org.eclipse.dltk.internal.corext.refactoring.util.ResourceUtil;
import org.eclipse.dltk.internal.ui.model.DLTKElementResourceMapping;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.participants.ReorgExecutionLog;


public class CreateCopyOfSourceModuleChange extends CreateTextFileChange {

	private ISourceModule fOldCu;
	private INewNameQuery fNameQuery;
	
	public CreateCopyOfSourceModuleChange(IPath path, String source, ISourceModule oldCu, INewNameQuery nameQuery) {
		super(path, source, null, "java"); //$NON-NLS-1$
		fOldCu= oldCu;
		fNameQuery= nameQuery;
		setEncoding(oldCu);
	}
	
	public Change perform(IProgressMonitor pm) throws CoreException {
		ISourceModule unit= fOldCu;
		ResourceMapping mapping= DLTKElementResourceMapping.create(unit);
		final Change result= super.perform(pm);
		markAsExecuted(unit, mapping);
		return result;
	}
	
	private void setEncoding(ISourceModule cunit) {
		IResource resource= cunit.getResource();
		// no file so the encoding is taken from the target
		if (!(resource instanceof IFile))
			return;
		IFile file= (IFile)resource;
		try {
			String encoding= file.getCharset(false);
			if (encoding != null) {
				setEncoding(encoding, true);
			} else {
				encoding= file.getCharset(true);
				if (encoding != null) {
					setEncoding(encoding, false);
				}
			}
		} catch (CoreException e) {
			// do nothing. Take encoding from target
		}
	}

	/*
	 * @see CreateFileChange#getOldFile()
	 */
	protected IFile getOldFile(IProgressMonitor pm) {
		pm.beginTask("", 10); //$NON-NLS-1$
		String oldSource= super.getSource();
		IPath oldPath= super.getPath();
		String newTypeName= fNameQuery.getNewName();
		try {
			String newSource= getCopiedFileSource(new SubProgressMonitor(pm, 9), fOldCu, newTypeName);
			setSource(newSource);
			setPath(constructNewPath(newTypeName));
			return super.getOldFile(new SubProgressMonitor(pm, 1));
		} catch (CoreException e) {
			setSource(oldSource);
			setPath(oldPath);
			return super.getOldFile(pm);
		}
	}

	private IPath constructNewPath(String newTypeName) {
		String newCUName= DLTKModelUtil.getRenamedCUName(fOldCu, newTypeName);
		return ResourceUtil.getResource(fOldCu).getParent().getFullPath().append(newCUName);
	}

	private static String getCopiedFileSource(IProgressMonitor pm, ISourceModule cu, String newTypeName) throws CoreException {
		ISourceModule wc= cu.getPrimary().getWorkingCopy(null);
		try {
			if (DLTKCore.DEBUG) {
				System.err.println("TODO: CreateCopyOfSourceModuleChange add content pewview...");
			}
//			TextChangeManager manager= createChangeManager(pm, wc, newTypeName);
//			String result= manager.get(wc).getPreviewContent(new NullProgressMonitor());
//			return result;
			return wc.getSource();
		} finally {
			wc.discardWorkingCopy();
		}
	}
	private void markAsExecuted(ISourceModule unit, ResourceMapping mapping) {
		ReorgExecutionLog log= (ReorgExecutionLog)getAdapter(ReorgExecutionLog.class);
		if (log != null) {
			log.markAsProcessed(unit);
			log.markAsProcessed(mapping);
		}
	}
}
