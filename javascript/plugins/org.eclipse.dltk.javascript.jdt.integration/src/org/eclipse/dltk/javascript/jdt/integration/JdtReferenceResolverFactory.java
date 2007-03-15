package org.eclipse.dltk.javascript.jdt.integration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.internal.javascript.reference.resolvers.IReferenceResolver;
import org.eclipse.dltk.internal.javascript.reference.resolvers.IResolverFactory;

public class JdtReferenceResolverFactory implements IResolverFactory,IExecutableExtension{

	public IReferenceResolver create() {
		return new JdtReferenceResolver();
	}

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		
	}

}
