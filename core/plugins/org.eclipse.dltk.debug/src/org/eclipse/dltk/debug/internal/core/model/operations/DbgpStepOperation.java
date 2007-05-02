/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.internal.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.commands.IDbgpCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;

public abstract class DbgpStepOperation extends DbgpOperation {
	public DbgpStepOperation(IDbgpCommands commands, String name, IResultHandler finish) {
		super(commands, name, finish);
	}

	protected void process() throws DbgpException {
		callFinish(step());
	}

	protected abstract IDbgpStatus step() throws DbgpException;
}
