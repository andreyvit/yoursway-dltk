/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.debug.core.model;

import org.eclipse.core.runtime.CoreException;

/**
 * A method entry breakpoint suspends execution on the first executable line of
 * a method when entered.
 */
public interface IScriptMethodEntryBreakpoint extends IScriptLineBreakpoint {

	// Method name
	String getMethodName() throws CoreException;

	// Break conditions
	void setBreakOnEntry(boolean value) throws CoreException;

	void setBreakOnExit(boolean value) throws CoreException;

	boolean shouldBreakOnEntry();

	boolean shouldBreakOnExit();

	// Secondary identifiers
	String getSecondaryId();

	void setSecondaryId(String id) throws CoreException;
}
