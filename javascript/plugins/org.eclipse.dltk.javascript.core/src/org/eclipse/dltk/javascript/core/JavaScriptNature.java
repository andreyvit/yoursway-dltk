package org.eclipse.dltk.javascript.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;


public class JavaScriptNature implements IProjectNature
{
	public static final String NATURE_ID = JavaScriptPlugin.PLUGIN_ID + ".nature";
	
	private IProject project;

	public JavaScriptNature() {
		
	}
	public void configure() throws CoreException {

	}

	public void deconfigure() throws CoreException {

	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject value) {
		project = value;
	}

}
