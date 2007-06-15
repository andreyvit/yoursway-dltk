/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.NewElementWizard;
import org.eclipse.jface.resource.ImageDescriptor;


public abstract class BuildPathWizard extends NewElementWizard {
	
	private boolean fDoFlushChange;
	private final BPListElement fEntryToEdit;
	private IProjectFragment fProjectFragment;	
	private final ArrayList fExistingEntries;

	public BuildPathWizard(BPListElement[] existingEntries, BPListElement newEntry, String titel, ImageDescriptor image) {		
		if (image != null)
			setDefaultPageImageDescriptor(image);
		
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(titel);

		fEntryToEdit= newEntry;
		fExistingEntries= new ArrayList(Arrays.asList(existingEntries));
		fDoFlushChange= true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
		if (fDoFlushChange) {
			IScriptProject scriptProject= getEntryToEdit().getScriptProject();
			
			BuildpathsBlock.flush(getExistingEntries(), scriptProject, monitor);
			
			IProject project= scriptProject.getProject();
			IPath projPath= project.getFullPath();
			IPath path= getEntryToEdit().getPath();
			
			if (!projPath.equals(path) && projPath.isPrefixOf(path)) {
				path= path.removeFirstSegments(projPath.segmentCount());
			}
			
			IFolder folder= project.getFolder(path);
			fProjectFragment= scriptProject.getProjectFragment(folder);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public IModelElement getCreatedElement() {
		return fProjectFragment;
	}
	
	public void setDoFlushChange(boolean b) {
		fDoFlushChange= b;
	}
	
	public ArrayList getExistingEntries() {
		return fExistingEntries;
	}	
		

	protected BPListElement getEntryToEdit() {
		return fEntryToEdit;
	}

	public List/*<BPListElement>*/ getInsertedElements() {
		return new ArrayList();
	}

	public List/*<BPListElement>*/ getRemovedElements() {
		return new ArrayList();
	}

	public List/*<BPListElement>*/ getModifiedElements() {
		ArrayList result= new ArrayList(1);
		result.add(fEntryToEdit);
		return result;
	}
	
	public abstract void cancel();

}
