package org.eclipse.dltk.ruby.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class RubyNature implements IProjectNature
{
	public static final String NATURE_ID = RubyPlugin.PLUGIN_ID + ".nature";
	
	private IProject project;

	public RubyNature() {
		
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
