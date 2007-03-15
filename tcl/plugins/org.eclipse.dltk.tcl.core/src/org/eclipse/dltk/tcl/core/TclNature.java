package org.eclipse.dltk.tcl.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class TclNature implements IProjectNature
{	
	private IProject project;
	
	public static final String NATURE_ID = TclPlugin.PLUGIN_ID + ".nature";

	public TclNature() {
		
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
