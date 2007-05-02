/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards;

import org.eclipse.dltk.core.IBuildpathEntry;


/**
 * Buildpath container pages that implement {@link IBuildpathContainerPage} can 
 * optionally implement {@link IBuildpathContainerPageExtension2} to return more
 * than one element when creating new containers. If implemented, the method {@link #getNewContainers()}
 * is used instead of the method {@link IBuildpathContainerPage#getSelection() } to get the
 * newly selected containers. {@link IBuildpathContainerPage#getSelection() } is still used
 * for edited elements.
 *
	 *
 */
public interface IBuildpathContainerPageExtension2 {
	
	/**
	 * Method {@link #getNewContainers()} is called instead of {@link IBuildpathContainerPage#getSelection() }
	 * to get the newly added containers. {@link IBuildpathContainerPage#getSelection() } is still used
	 * to get the edited elements.
	 * @return the buildpath entries created on the page. All returned entries must be on kind
	 * {@link IBuildpathEntry#CPE_CONTAINER}
	 */
	public IBuildpathEntry[] getNewContainers();

}
