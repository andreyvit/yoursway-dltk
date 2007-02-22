package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.dltk.core.DLTKLanguageManager;


public class ModelInfo extends OpenableElementInfo {

	/**
	 * A array with all the foreign projects contained by this model
	 */
	Object[] foreignResources;
	
	private Object[] computeForeignResources() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		int length = projects.length;
		Object[] resources = null;
		int index = 0;
		for (int i = 0; i < length; i++) {
			IProject project = projects[i];
			if (!DLTKLanguageManager.hasScriptNature(project)) {
				if (resources == null) {
					resources = new Object[length];
				}
				resources[index++] = project;
			}
		}
		if (index == 0) return NO_NON_SCRIPT_RESOURCES;
		if (index < length) {
			System.arraycopy(resources, 0, resources = new Object[index], 0, index);
		}
		return resources;
	}

	public Object[] getForeignResources() {
		if (this.foreignResources == null) {
			this.foreignResources = computeForeignResources();
		}
		return this.foreignResources;
	}	
}
