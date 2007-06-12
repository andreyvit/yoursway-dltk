/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp;

public class DbgpServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public DbgpServerException() {
		super();
	}

	public DbgpServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbgpServerException(String message) {
		super(message);
	}

	public DbgpServerException(Throwable cause) {
		super(cause);
	}
}
