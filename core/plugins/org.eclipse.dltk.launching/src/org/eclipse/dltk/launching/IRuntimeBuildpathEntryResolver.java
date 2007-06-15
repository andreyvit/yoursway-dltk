/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;


/**
 * Resolves variable and/or container runtime buildpath entries in
 * the context of a launch configuration or Script project. A resolver can be declared
 * as an extension (<code>org.eclipse.dltk.launching.runtimeClasspathEntryResolver</code>),
 * or be registered with the <code>ScriptRuntime</code> programmatically.
 * <p>
 * A resolver is registered for a specific buildpath
 * <code>VARIABLE</code> and/or <code>CONTAINER</code>. A resolver is
 * consulted when a runtime buildpath entry is needs to be resolved.
 * </p>
 * A resolver extension is defined in <code>plugin.xml</code>.
 * Following is an example definition of a runtime buildpath entry
 * resolver extension.
 * <pre>
 * &lt;extension point="org.eclipse.dltk.launching.runtimeClasspathEntryResolvers"&gt;
 *   &lt;runtimeClasspathEntryResolver 
 *      id="com.example.ExampleResolver"
 *      class="com.example.ExampleResolverImpl"
 *      variable="VAR_NAME"
 *      container="CONTAINER_ID"
 *   &lt;/runtimeClasspathEntryResolver&gt;
 * &lt;/extension&gt;
 * </pre>
 * The attributes are specified as follows:
 * <ul>
 * <li><code>id</code> specifies a unique identifier for this extension.</li>
 * <li><code>class</code> specifies the fully qualified name of the Script class
 *   that implements <code>IRuntimeBuildpathEntryResolver</code>.</li>
 * <li><code>variable</code> name of the buildpath variable this resolver
 * 	is registered for.</li>
 * <li><code>container</code> identifier of the buildpath container this
 * 	resolver is registered for.</li>
 * </ul>
 * At least one of <code>variable</code> or <code>container</code> must be
 * specified.
 * </p>
 * <p>
 * Clients may implement this interface.
 * </p>
	 *
 */
public interface IRuntimeBuildpathEntryResolver {
	
	/**
	 * Returns resolved runtime buildpath entries for the given runtime buildpath entry,
	 * in the context of the given launch configuration.
	 * 
	 * @param entry runtime buildpath entry to resolve, of type
	 * 	<code>VARIABLE</code> or <code>CONTAINTER</code>
	 * @param configuration the context in which the runtime buildpath entry
	 * 	needs to be resolved
	 * @return resolved entries (zero or more)
	 * @exception CoreException if unable to resolve the entry  
	 */
	public IRuntimeBuildpathEntry[] resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry entry, ILaunchConfiguration configuration) throws CoreException;
	
	/**
	 * Returns resolved runtime buildpath entries for the given runtime buildpath entry,
	 * in the context of the given Script project.
	 * 
	 * @param entry runtime buildpath entry to resolve, of type
	 * 	<code>VARIABLE</code> or <code>CONTAINTER</code>
	 * @param project context in which the runtime buildpath entry
	 * 	needs to be resolved
	 * @return resolved entries (zero or more)
	 * @exception CoreException if unable to resolve the entry  
	 */
	public IRuntimeBuildpathEntry[] resolveRuntimeBuildpathEntry(IRuntimeBuildpathEntry entry, IScriptProject project) throws CoreException;	
	
	/**
	 * Returns a Interpreter install associated with the given buildpath entry,
	 * or <code>null</code> if none.
	 * 
	 * @param entry buildpath entry
	 * @return Interpreter install associated with entry or <code>null</code> if none
	 * @exception CoreException if unable to resolve a Interpreter
	 */
	public IInterpreterInstall resolveInterpreterInstall(String lang, IBuildpathEntry entry) throws CoreException;
}
