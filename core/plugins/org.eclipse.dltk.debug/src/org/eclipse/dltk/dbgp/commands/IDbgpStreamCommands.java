/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.commands;

import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public interface IDbgpStreamCommands {
	final int DISABLE = 0;

	final int COPY = 1;

	final int REDIRECT = 2;

	boolean configureStdout(int value) throws DbgpException;

	boolean configureStderr(int value) throws DbgpException;
}
