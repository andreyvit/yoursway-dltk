/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.navigator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

public abstract class ProjectFragmentContainer implements IAdaptable {
	
	private static WorkbenchAdapterImpl fgAdapterInstance= new WorkbenchAdapterImpl();
	
	private static class WorkbenchAdapterImpl implements IWorkbenchAdapter {

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object o) {
			if (o instanceof ProjectFragmentContainer)
				return ((ProjectFragmentContainer) o).getChildren();
			return new Object[0];
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
		 */
		public ImageDescriptor getImageDescriptor(Object o) {
			if (o instanceof ProjectFragmentContainer)
				return ((ProjectFragmentContainer) o).getImageDescriptor();
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getLabel(java.lang.Object)
		 */
		public String getLabel(Object o) {
			if (o instanceof ProjectFragmentContainer)
				return ((ProjectFragmentContainer) o).getLabel();
			return new String();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.model.IWorkbenchAdapter#getParent(java.lang.Object)
		 */
		public Object getParent(Object o) {
			if (o instanceof ProjectFragmentContainer)
				return ((ProjectFragmentContainer) o).getScriptProject();
			return null;
		}
	}
	
	private IScriptProject fProject;

	public ProjectFragmentContainer(IScriptProject project) {
		Assert.isNotNull(project);
		fProject= project;
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IWorkbenchAdapter.class) 
			return fgAdapterInstance;
		return null;
	}

	public abstract IAdaptable[] getChildren();
	
	public abstract IProjectFragment[] getProjectFragments();
	
	public abstract String getLabel();
	
	public abstract ImageDescriptor getImageDescriptor();
	
	public IScriptProject getScriptProject() {
		return fProject;
	}
}
