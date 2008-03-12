package org.eclipse.dltk.launching.sourcelookup;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IPersistableSourceLocator;
import org.eclipse.debug.core.model.IStackFrame;

public class AbstractScriptLookupDirector implements IPersistableSourceLocator {

	private static final String ATTR_PROJECT = "project"; //$NON-NLS-1$
	private String project;
	
	public String getMemento() throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	public void initializeDefaults(ILaunchConfiguration configuration)
			throws CoreException {
		project = configuration.getAttribute(ATTR_PROJECT, (String) null); 
	}

	protected IProject getProject()
	{
		return ResourcesPlugin.getWorkspace().getRoot().getProject(project);
	}
	
	public void initializeFromMemento(String memento) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	public Object getSourceElement(IStackFrame stackFrame) {
		// TODO Auto-generated method stub
		return null;
	}

}
