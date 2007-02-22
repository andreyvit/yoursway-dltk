/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;

 
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.IRuntimeBuildpathProvider;


/**
 * Proxy to a runtime buildpath provider extension.
 */
public class RuntimeBuildpathProvider implements IRuntimeBuildpathProvider {

	private IConfigurationElement fConfigurationElement;
	
	private IRuntimeBuildpathProvider fDelegate;
	
	/**
	 * Constructs a new resolver on the given configuration element
	 */
	public RuntimeBuildpathProvider(IConfigurationElement element) {
		fConfigurationElement = element;
	}
		
	/**
	 * Returns the resolver delegate (and creates if required) 
	 */
	protected IRuntimeBuildpathProvider getProvider() throws CoreException {
		if (fDelegate == null) {
			fDelegate = (IRuntimeBuildpathProvider)fConfigurationElement.createExecutableExtension("class"); //$NON-NLS-1$
		}
		return fDelegate;
	}
	
	public String getIdentifier() {
		return fConfigurationElement.getAttribute("id"); //$NON-NLS-1$
	}
	/**
	 * @see IRuntimeBuildpathProvider#computeUnresolvedBuildpath(ILaunchConfiguration)
	 */
	public IRuntimeBuildpathEntry[] computeUnresolvedBuildpath(ILaunchConfiguration configuration) throws CoreException {
		return getProvider().computeUnresolvedBuildpath(configuration);
	}

	/**
	 * @see IRuntimeBuildpathProvider#resolveBuildpath(IRuntimeBuildpathEntry[], ILaunchConfiguration)
	 */
	public IRuntimeBuildpathEntry[] resolveBuildpath(IRuntimeBuildpathEntry[] entries, ILaunchConfiguration configuration) throws CoreException {
		return getProvider().resolveBuildpath(entries, configuration);
	}

}
