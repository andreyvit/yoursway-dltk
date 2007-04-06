package org.eclipse.dltk.validators.internal.core;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.builder.IScriptBuilder;
import org.eclipse.dltk.validators.core.ValidatorRuntime;

public class ValidatorBuilder implements IScriptBuilder {

	public IStatus[] buildModelElements(IDLTKProject project, List elements,
			IProgressMonitor monitor) {
		ValidatorRuntime.executeActiveValidatorsWithConsole(null, elements, null);
		return null;
	}

	public IStatus[] buildResources(IDLTKProject project, List resources,
			IProgressMonitor monitor) {
		ValidatorRuntime.executeActiveValidatorsWithConsole(null, null, resources);
		return null;
	}

	public List getDependencies(IDLTKProject project, List resources) {
		//We don't provide dependencies here.
		return null;
	}

}
