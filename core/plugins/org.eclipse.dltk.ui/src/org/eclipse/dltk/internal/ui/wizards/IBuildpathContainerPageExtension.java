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
import org.eclipse.dltk.core.IDLTKProject;


/**
 * Buildpath container pages that implement <code>IBuildpathContainerPage</code> can 
 * optionally implement <code>IBuildpathContainerPageExtension</code> to get additional
 * information about the context when the page is opened. Method <code>initialize()</code>
 * is called before  <code>IBuildpathContainerPage.setSelection</code>.
 *
	 *
 */
public interface IBuildpathContainerPageExtension {
	
	/**
	 * Method <code>initialize()</code> is called before  <code>IBuildpathContainerPage.setSelection</code>
	 * to give additional information about the context the buildpath container entry is configured in. This information
	 * only reflects the underlying dialogs current selection state. The user still can make changes after the
	 * the buildpath container pages has been closed or decide to cancel the operation.
	 * @param project The project the new or modified entry is added to. The project does not have to exist. 
	 * Project can be <code>null</code>.
	 * @param currentEntries The class path entries currently selected to be set as the projects buildpath. This can also
	 * include the entry to be edited.
	 */
	public void initialize(IDLTKProject project, IBuildpathEntry[] currentEntries);

}
