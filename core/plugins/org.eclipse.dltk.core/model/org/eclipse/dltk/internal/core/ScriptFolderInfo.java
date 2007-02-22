package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.ModelException;


public class ScriptFolderInfo extends OpenableElementInfo {
	private Object[] foreignResources;

	void setForeignResources(Object[] resources) {
		foreignResources = resources;
	}

	public Object[] getForeignResources(IResource resource, ProjectFragment projectFragment) {
		if (this.foreignResources == null) {
			try {
				this.foreignResources = ProjectFragmentInfo.computeFolderForeignResources((DLTKProject) projectFragment.getScriptProject(),
						(IContainer) resource, projectFragment.fullInclusionPatternChars(), projectFragment.fullExclusionPatternChars());
			} catch (ModelException e) {
				// root doesn't exist: consider package has no nonScriptResources
				this.foreignResources = NO_NON_SCRIPT_RESOURCES;
			}
		}
		return this.foreignResources;
	}

	boolean containsScriptResources() {
		return this.children.length != 0;
	}
}
