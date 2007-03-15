package org.eclipse.dltk.internal.javascript.reference.resolvers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

public class SourceBasedResolverFactory implements IResolverFactory,IExecutableExtension{

	public IReferenceResolver create() {
		return new SourceBasedResolver();
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		
	}

}
