package org.eclipse.dltk.javascript.core.builders;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.builder.IScriptBuilder;

public class JavaScriptBuilder implements IScriptBuilder ,IExecutableExtension{

	public IStatus[] buildModelElements(IDLTKProject project, List elements,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus[] buildResources(IDLTKProject project, List resources,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getDependencies(IDLTKProject project, List resources) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {		
	}

}
