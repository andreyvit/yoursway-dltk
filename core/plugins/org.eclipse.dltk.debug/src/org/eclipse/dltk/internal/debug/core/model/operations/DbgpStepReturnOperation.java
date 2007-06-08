/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model.operations;

import org.eclipse.dltk.dbgp.IDbgpStatus;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.model.IScriptThread;

public class DbgpStepReturnOperation extends DbgpStepOperation {
	private static final String JOB_NAME = "StepReturn operation";

	public DbgpStepReturnOperation(IScriptThread thread, IResultHandler finish) {
		super(thread, JOB_NAME, finish);
	}

	protected IDbgpStatus step() throws DbgpException {
		return getCore().stepOut(getContinuationHandler());
	}
}
