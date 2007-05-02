/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;

 
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntryResolver;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntryResolver2;


/**
 * Proxy to a runtime buildpath entry resolver extension.
 */
public class RuntimeBuildpathEntryResolver implements IRuntimeBuildpathEntryResolver2 {

	private IConfigurationElement fConfigurationElement;
	
	private IRuntimeBuildpathEntryResolver fDelegate;
	
	/**
	 * Constructs a new resolver on the given configuration element
	 */
	public RuntimeBuildpathEntryResolver(IConfigurationElement element) {
		fConfigurationElement = element;
	}
	
	/**
	 * @see IRuntimeBuildpathEntryResolver#resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry, ILaunchConfiguration)
	 */
	public IRuntimeBuildpathEntry[] resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry entry, ILaunchConfiguration configuration) throws CoreException {
		return getResolver().resolveRuntimeBuildpathEntry(entry, configuration);
	}
	
	/**
	 * Returns the resolver delegate (and creates if required) 
	 */
	protected IRuntimeBuildpathEntryResolver getResolver() throws CoreException {
		if (fDelegate == null) {
			fDelegate = (IRuntimeBuildpathEntryResolver)fConfigurationElement.createExecutableExtension("class"); //$NON-NLS-1$
		}
		return fDelegate;
	}
	
	/**
	 * Returns the container id this resolver is registered for, or <code>null</code>
	 */
	public String getContainerId() {
		return fConfigurationElement.getAttribute("container"); //$NON-NLS-1$
	}	
	
	/**
	 * Returns the runtime buildpath entry id this resolver is registered
	 * for,or <code>null</code> if none.
	 */
	public String getRuntimeBuildpathEntryId() {
		return fConfigurationElement.getAttribute("runtimeBuildpathEntryId"); //$NON-NLS-1$
	}

	/**
	 * @see IRuntimeBuildpathEntryResolver#resolveInterpreterInstall(IBuildpathEntry)
	 */
	public IInterpreterInstall resolveInterpreterInstall(String lang, IBuildpathEntry entry) throws CoreException {
		return getResolver().resolveInterpreterInstall(lang, entry);
	}

	/**
	 * @see IRuntimeBuildpathEntryResolver#resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry, IScriptProject)
	 */
	public IRuntimeBuildpathEntry[] resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry entry, IDLTKProject project) throws CoreException {
		return getResolver().resolveRuntimeBuildpathEntry(entry, project);
	}

	public boolean isInterpreterInstallReference(String lang, IBuildpathEntry entry) {
		try {
			IRuntimeBuildpathEntryResolver resolver = getResolver();
			if (resolver instanceof IRuntimeBuildpathEntryResolver2) {
				IRuntimeBuildpathEntryResolver2 resolver2 = (IRuntimeBuildpathEntryResolver2) resolver;
				return resolver2.isInterpreterInstallReference(lang, entry);
			} else {
				return resolver.resolveInterpreterInstall(lang, entry) != null;
			}
		} catch (CoreException e) {
			return false;
		}
	}

}
