/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.compiler.problem;

import org.eclipse.dltk.compiler.problem.CategorizedProblem;

/*
 * Special unchecked exception type used 
 * to abort from the compilation process
 *
 * should only be thrown from within problem handlers.
 */
public class AbortCompilation extends RuntimeException {

	public Throwable exception;
	public CategorizedProblem problem;
	
	/* special fields used to abort silently (e.g. when cancelling build process) */
	public boolean isSilent;
	public RuntimeException silentException;

	private static final long serialVersionUID = -2047226595083244852L; // backward compatible
	
	public AbortCompilation() {
		// empty
	}

	public AbortCompilation( CategorizedProblem problem) {
		this();
		this.problem = problem;
	}

	public AbortCompilation(Throwable exception) {
		this();
		this.exception = exception;
	}

	public AbortCompilation(boolean isSilent, RuntimeException silentException) {
		this();
		this.isSilent = isSilent;
		this.silentException = silentException;
	}
}
