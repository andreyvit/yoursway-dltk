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
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.IBuildpathContainerPage;
import org.eclipse.dltk.ui.DLTKUIPlugin;


/**
  */
public class BuildpathContainerDescriptor {

	private IConfigurationElement fConfigElement;
	private IBuildpathContainerPage fPage;
	

	private static final String ATT_EXTENSION = "buildpathContainerPage"; //$NON-NLS-1$

	private static final String ATT_ID = "id"; //$NON-NLS-1$
	private static final String ATT_NAME = "name"; //$NON-NLS-1$
	private static final String ATT_PAGE_CLASS = "class"; //$NON-NLS-1$
	private static final String ATT_NATURE = "nature"; //$NON-NLS-1$

	public BuildpathContainerDescriptor(IConfigurationElement configElement) throws CoreException {
		super();
		fConfigElement = configElement;
		fPage= null;

		String id = fConfigElement.getAttribute(ATT_ID);
		String name = configElement.getAttribute(ATT_NAME);
		String pageClassName = configElement.getAttribute(ATT_PAGE_CLASS);
		String nature = configElement.getAttribute(ATT_NATURE);

		if (name == null) {
			throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (missing name): " + id, null)); //$NON-NLS-1$
		}
		if (nature == null) {
			throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (missing nature): " + nature, null)); //$NON-NLS-1$
		}
		if (pageClassName == null) {
			throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (missing page class name): " + id, null)); //$NON-NLS-1$
		}
	}

	public IBuildpathContainerPage createPage() throws CoreException  {
		if (fPage == null) {
			Object elem= CoreUtility.createExtension(fConfigElement, ATT_PAGE_CLASS);
			if (elem instanceof IBuildpathContainerPage) {
				fPage= (IBuildpathContainerPage) elem;
			} else {
				String id= fConfigElement.getAttribute(ATT_ID);
				throw new CoreException(new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, "Invalid extension (page not of type IBuildpathContainerPage): " + id, null)); //$NON-NLS-1$
			}
		}
		return fPage;
	}
	
	public IBuildpathContainerPage getPage() {
		return fPage;
	}
	
	public void setPage(IBuildpathContainerPage page) {
		fPage= page;
	}
	
	public void dispose() {
		if (fPage != null) {
			fPage.dispose();
			fPage= null;
		}
	}

	public String getName() {
		return fConfigElement.getAttribute(ATT_NAME);
	}
	
	public String getPageClass() {
		return fConfigElement.getAttribute(ATT_PAGE_CLASS);
	}	
	
	public String getNature () {
		return fConfigElement.getAttribute(ATT_NATURE );
	}

	public boolean canEdit(IBuildpathEntry entry) {
		String id = fConfigElement.getAttribute(ATT_ID);
		if (entry.getEntryKind() == IBuildpathEntry.BPE_CONTAINER) {
			String type = entry.getPath().segment(0);
			return id.equals(type);
		}
		return false;
	}
	
	public static BuildpathContainerDescriptor[] getDescriptors() {
		return getDescriptors(null);
	}

	public static BuildpathContainerDescriptor[] getDescriptors(IDLTKProject proj) {
		ArrayList containers= new ArrayList();
		
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(DLTKUIPlugin.PLUGIN_ID, ATT_EXTENSION);
		if (extensionPoint != null) {
			BuildpathContainerDescriptor defaultPage= null;
			String defaultPageName= BuildpathContainerDefaultPage.class.getName();
			
			IConfigurationElement[] elements = extensionPoint.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				try {
					BuildpathContainerDescriptor curr= new BuildpathContainerDescriptor(elements[i]);					
					if (defaultPageName.equals(curr.getPageClass())) {
						defaultPage= curr;
					} else {
						if (proj == null || proj.getProject().hasNature(curr.getNature()))
							containers.add(curr);
					}
				} catch (CoreException e) {
					DLTKUIPlugin.log(e);
				}
			}
			if (defaultPageName != null && containers.isEmpty() && defaultPage != null) {
				// default page only added of no other extensions found
				containers.add(defaultPage);
			}
		}
		return (BuildpathContainerDescriptor[]) containers.toArray(new BuildpathContainerDescriptor[containers.size()]);
	}

}
