/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.javascript.core.internal.JavaScriptCorePlugin;
import org.eclipse.wst.javascript.core.internal.contenttype.ContentTypeIdForJavaScript;
import org.eclipse.wst.javascript.core.internal.preferences.JavaScriptCorePreferenceNames;
import org.eclipse.wst.javascript.ui.internal.editor.JavaScriptUIMessages;
import org.eclipse.wst.javascript.ui.internal.editor.Logger;

class NewJSFileWizardPage extends WizardNewFileCreationPage {
	
	private IContentType fContentType;
	private List fValidExtensions = null;
	
	public NewJSFileWizardPage(String pageName, IStructuredSelection selection) {
        super(pageName, selection);
    }
	
	/**
	 * This method is overriden to set the selected folder to web contents 
	 * folder if the current selection is outside the web contents folder. 
	 */
	protected void initialPopulateContainerNameField() {
		super.initialPopulateContainerNameField();
		
		IPath fullPath = getContainerFullPath();
		IProject project = getProjectFromPath(fullPath);
		IPath webContentPath = getWebContentPath(project);
		
		if (webContentPath != null && !webContentPath.isPrefixOf(fullPath)) {
			setContainerFullPath(webContentPath);
		}
	}
	
	/**
	 * This method is overriden to set additional validation specific to 
	 * javascript files. 
	 */
	protected boolean validatePage() {
		setMessage(null);
		setErrorMessage(null);
		
		if (!super.validatePage()) {
			return false;
		}
		
		String fileName = getFileName();
		IPath fullPath = getContainerFullPath();
		if ((fullPath != null) && (fullPath.isEmpty() == false) && (fileName != null)) {
			// check that filename does not contain invalid extension
			if (!extensionValidForContentType(fileName)) {
				setErrorMessage(NLS.bind(JavaScriptUIMessages._ERROR_FILENAME_MUST_END_JS, getValidExtensions().toString()));
				return false;
			}
			// no file extension specified so check adding default
			// extension doesn't equal a file that already exists
			if (fileName.lastIndexOf('.') == -1) {
				String newFileName = addDefaultExtension(fileName);
				IPath resourcePath = fullPath.append(newFileName);

				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IStatus result = workspace.validatePath(resourcePath.toString(), IResource.FOLDER);
				if (!result.isOK()) {
					// path invalid
					setErrorMessage(result.getMessage());
					return false;
				}

				if ((workspace.getRoot().getFolder(resourcePath).exists() || workspace.getRoot().getFile(resourcePath).exists())) {
					setErrorMessage(JavaScriptUIMessages.ResourceGroup_nameExists);
					return false;
				}
			}
			
			// get the IProject for the selection path
			IProject project = getProjectFromPath(fullPath);
			// if inside web project, check if inside webContent folder
			if (project != null && isWebProject(project)) {
				// check that the path is inside the webContent folder
				IPath webContentPath = getWebContentPath(project);
				if (!webContentPath.isPrefixOf(fullPath)) {
					setMessage(JavaScriptUIMessages._WARNING_FOLDER_MUST_BE_INSIDE_WEB_CONTENT, WARNING);
				}
			}
		}

		return true;
	}
	
	/**
	 * Get content type associated with this new file wizard
	 * 
	 * @return IContentType
	 */
	private IContentType getContentType() {
		if (fContentType == null)
			fContentType = Platform.getContentTypeManager().getContentType(ContentTypeIdForJavaScript.ContentTypeID_JAVASCRIPT);
		return fContentType;
	}

	/**
	 * Get list of valid extensions for JavaScript Content type
	 * 
	 * @return
	 */
	private List getValidExtensions() {
		if (fValidExtensions == null) {
			IContentType type = getContentType();
			fValidExtensions = new ArrayList(Arrays.asList(type.getFileSpecs(IContentType.FILE_EXTENSION_SPEC)));
		}
		return fValidExtensions;
	}
	
	/**
	 * Verifies if fileName is valid name for content type. Takes base content
	 * type into consideration.
	 * 
	 * @param fileName
	 * @return true if extension is valid for this content type
	 */
	private boolean extensionValidForContentType(String fileName) {
		boolean valid = false;

		IContentType type = getContentType();
		// there is currently an extension
		if (fileName.lastIndexOf('.') != -1) {
			// check what content types are associated with current extension
			IContentType[] types = Platform.getContentTypeManager().findContentTypesFor(fileName);
			int i = 0;
			while (i < types.length && !valid) {
				valid = types[i].isKindOf(type);
				++i;
			}
		}
		else
			valid = true; // no extension so valid
		return valid;
	}

	/**
	 * Adds default extension to the filename
	 * 
	 * @param filename
	 * @return
	 */
	String addDefaultExtension(String filename) {
		StringBuffer newFileName = new StringBuffer(filename);

		Preferences preference = JavaScriptCorePlugin.getDefault().getPluginPreferences();
		String ext = preference.getString(JavaScriptCorePreferenceNames.DEFAULT_EXTENSION);

		newFileName.append("."); //$NON-NLS-1$
		newFileName.append(ext);

		return newFileName.toString();
	}
	
	/**
	 * Returns the project that contains the specified path
	 * 
	 * @param path the path which project is needed
	 * @return IProject object. If path is <code>null</code> the return value 
	 * 		   is also <code>null</code>. 
	 */
	private IProject getProjectFromPath(IPath path) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = null;
		
		if (path != null) {
			if (workspace.validatePath(path.toString(), IResource.PROJECT).isOK()) {
				project = workspace.getRoot().getProject(path.toString());
			} else {
				project = workspace.getRoot().getFile(path).getProject();
			}
		}
		
		return project;
	}
	
	/**
	 * Checks if the specified project is a web project. 
	 * 
	 * @param project project to be checked
	 * @return true if the project is web project, otherwise false
	 */
	private boolean isWebProject(IProject project) {
		IFacetedProject faceted = null;
		try {
			faceted = ProjectFacetsManager.create(project);
		} catch (CoreException e) {
			Logger.log(Logger.WARNING_DEBUG, e.getMessage(), e);
		}
		
		if (faceted != null && 
			(faceted.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.WST_WEB_MODULE)) || 
			 faceted.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE)))) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the web contents folder of the specified project
	 * 
	 * @param project the project which web contents path is needed
	 * @return IPath of the web contents folder
	 */
	private IPath getWebContentPath(IProject project) {
		IPath path = null;
		
		if (project != null && isWebProject(project)) {			
			IVirtualComponent component = ComponentCore.createComponent(project);
			path = component.getRootFolder().getWorkspaceRelativePath();
		}
		
		return path;
	}
}
