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
import org.w3c.dom.Element;

/**
 * Enhancements to <code>IRuntimeBuildpathEntry</code> to support
 * extensible runtime buildpath entries. Contributed runtime buildpath
 * entries have a type of <code>OTHER</code>, and are contributed to
 * the <code>runtimeBuildpathEntries</code> extension point.
 * <p>
 * Clients are not intended to implement this interface, as new types
 * of runtime buildpath entries are only intended to be contributed
 * by the Script debugger.
 * </p>
 */
public interface IRuntimeBuildpathEntry2 extends IRuntimeBuildpathEntry {
	
	/**
	 * Initializes this runtime buildpath entry from the given memento.
	 * 
	 * @param memento memento created by a buildpath entry of the same type
	 * @throws CoreException if unable to initialize from the given memento
	 */
	public void initializeFrom(Element memento) throws CoreException;
	
	/**
	 * Returns the unique identifier of the extension that contributed
	 * this buildpath entry type, or <code>null</code> if this buildpath
	 * entry type was not contributed.
	 * 
	 * @return the unique identifier of the extension that contributed
	 *  this buildpath entry type, or <code>null</code> if this buildpath
	 *  entry type was not contributed
	 */
	public String getTypeId();
	
	/**
	 * Returns whether this buildpath entry is composed of other entries.
	 * 
	 * @return whether this buildpath entry is composed of other entries
	 */
	public boolean isComposite();
	
	/**
	 * Returns the buildpath entries this entry is composed of, or an
	 * empty collection if this entry is not a composite entry.
	 * 
	 * @param configuration the context (launch configuration) in which
	 *  this runtime buildpath entry is being queried for contained
	 * 	entries, possibly <code>null</code> 
	 * @return the buildpath entries this entry is composed of, or an
	 * empty collection if this entry is not a composite entry
	 * @throws CoreException if unable to retrieve contained entries
	 */
	public IRuntimeBuildpathEntry[] getRuntimeBuildpathEntries(ILaunchConfiguration configuration) throws CoreException;
	
	/**
	 * Returns a human readable name for this buildpath entry.
	 * 
	 * @return a human readable name for this buildpath entry
	 */
	public String getName();
}
