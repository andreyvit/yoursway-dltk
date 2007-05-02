/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import org.eclipse.dltk.core.IBuildpathEntry;


/**
 * Optional enhancements to {@link IRuntimeBuildpathEntryResolver}.
 * <p>
 * Clients may implement this interface.
 * </p>
	 *
 */
public interface IRuntimeBuildpathEntryResolver2 extends IRuntimeBuildpathEntryResolver {
	
	/**
	 * Returns whether the given buildpath entry references a Interpreter install.
	 * 
	 * @param entry buildpath entry
	 * @return whether the given buildpath entry references a Interpreter install
	 */
	public boolean isInterpreterInstallReference(String lang, IBuildpathEntry entry);
}
