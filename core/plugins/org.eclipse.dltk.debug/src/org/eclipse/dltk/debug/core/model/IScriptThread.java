/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.debug.core.model;

import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.dbgp.IDbgpSession;
import org.eclipse.dltk.dbgp.breakpoints.IDbgpBreakpoint;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationEngine;
import org.eclipse.dltk.internal.debug.core.model.IScriptThreadStreamProxy;

public interface IScriptThread extends IThread /* , IFilteredStep */{
	int ERR_THREAD_NOT_SUSPENDED = -3;

	IDbgpSession getDbgpSession();
	
	IDbgpBreakpoint getDbgpBreakpoint(String id);

	IScriptThreadStreamProxy getStreamProxy();

	IScriptEvaluationEngine getEvaluationEngine();
	
	int getSuspendCount();
}
