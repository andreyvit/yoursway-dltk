/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.scriptview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.model.IWorkbenchAdapter;


/**
 * Representation of class path containers in Script UI.
 */
public class BuildPathContainer implements IAdaptable, IWorkbenchAdapter {
	private IDLTKProject fProject;
	private IBuildpathEntry fBuildPathEntry;
	private IBuildpathContainer fContainer;

	public static class RequiredProjectWrapper implements IAdaptable, IWorkbenchAdapter {

		private final IModelElement fProject;
		private static ImageDescriptor DESC_OBJ_PROJECT;	
		{
			ISharedImages images= DLTKUIPlugin.getDefault().getWorkbench().getSharedImages(); 
			DESC_OBJ_PROJECT= images.getImageDescriptor(IDE.SharedImages.IMG_OBJ_PROJECT);
		}

		public RequiredProjectWrapper(IModelElement project) {
			this.fProject= project;
		}
		
		public IModelElement getProject() {
			return fProject; 
		}
		
		public Object getAdapter(Class adapter) {
			if (adapter == IWorkbenchAdapter.class) 
				return this;
			return null;
		}

		public Object[] getChildren(Object o) {
			return null;
		}

		public ImageDescriptor getImageDescriptor(Object object) {
			return DESC_OBJ_PROJECT;
		}

		public String getLabel(Object o) {
			return fProject.getElementName();
		}

		public Object getParent(Object o) {
			return null;
		}
	}

	public BuildPathContainer(IDLTKProject parent, IBuildpathEntry entry) {
		fProject= parent;
		fBuildPathEntry= entry;
		try {
			fContainer= DLTKCore.getBuildpathContainer(entry.getPath(), parent);
		} catch (ModelException e) {
			fContainer= null;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof BuildPathContainer) {
			BuildPathContainer other = (BuildPathContainer)obj;
			if (fProject.equals(other.fProject) &&
				fBuildPathEntry.equals(other.fBuildPathEntry)) {
				return true;	
			}
			
		}
		return false;
	}

	public int hashCode() {
		return fProject.hashCode()*17+fBuildPathEntry.hashCode();
	}

	public Object[] getProjectFragments() {
		IProjectFragment[] fragments = fProject.findProjectFragments(fBuildPathEntry);
		List frags = new ArrayList();
		for( int i = 0; i < fragments.length; ++i ) {
			try {
				if( fragments[i].hasChildren()) {
					frags.add(fragments[i]);
				}
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}
		return frags.toArray();
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IWorkbenchAdapter.class) 
			return this;
		if ((adapter == IResource.class) && (fContainer instanceof IAdaptable))
			return ((IAdaptable)fContainer).getAdapter(IResource.class);
		return null;
	}

	public Object[] getChildren(Object o) {
		return concatenate(getProjectFragments(), getRequiredProjects());
	}

	private Object[] getRequiredProjects() {
		List list= new ArrayList();
		if (fContainer != null) {
			IBuildpathEntry[] buildpathEntries= fContainer.getBuildpathEntries();
			IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
			for (int i= 0; i < buildpathEntries.length; i++) {
				IBuildpathEntry entry= buildpathEntries[i];
				if (entry.getEntryKind() == IBuildpathEntry.BPE_PROJECT) {
					IResource resource= root.findMember(entry.getPath());
					if (resource instanceof IProject)
						list.add(new RequiredProjectWrapper(DLTKCore.create(resource)));
				}
			}
		}
		return list.toArray();
	}

	protected static Object[] concatenate(Object[] a1, Object[] a2) {
		int a1Len= a1.length;
		int a2Len= a2.length;
		Object[] res= new Object[a1Len + a2Len];
		System.arraycopy(a1, 0, res, 0, a1Len);
		System.arraycopy(a2, 0, res, a1Len, a2Len); 
		return res;
	}

	public ImageDescriptor getImageDescriptor(Object object) {
		return DLTKPluginImages.DESC_OBJS_LIBRARY;
	}

	public String getLabel(Object o) {
		if (fContainer != null)
			return fContainer.getDescription();
		
		IPath path= fBuildPathEntry.getPath();
		String containerId= path.segment(0);
		BuildpathContainerInitializer initializer= DLTKCore.getBuildpathContainerInitializer(containerId);
		if (initializer != null) {
			String description= initializer.getDescription(path, fProject);
			return Messages.format(ScriptMessages.BuildPathContainer_unbound_label, description); 
		}
		return Messages.format(ScriptMessages.BuildPathContainer_unknown_label, path.toString()); 
	}

	public Object getParent(Object o) {
		return getScriptProject();
	}

	public IDLTKProject getScriptProject() {
		return fProject;
	}
	
	public IBuildpathEntry getBuildpathEntry() {
		return fBuildPathEntry;
	}
	
	public static boolean contains(IDLTKProject project, IBuildpathEntry entry, IProjectFragment root) {
		IProjectFragment[] roots= project.findProjectFragments(entry);
		for (int i= 0; i < roots.length; i++) {
			if (roots[i].equals(root))
				return true;
		}
		return false;
	}
}
