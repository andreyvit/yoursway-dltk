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

import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.dbgp.IDbgpSession;

/**
 * A thread in a Script virtual machine.
 * <p>
 * Clients are not intended to implement this interface.
 * </p>
 * 
 * @see org.eclipse.debug.core.model.IThread
 */
public interface IScriptThread extends IThread  /*, IFilteredStep */{

	int ERR_THREAD_NOT_SUSPENDED = -3;

	IDbgpSession getDbgpSession();

	/**
	 * Returns a variable with the given name, or <code>null</code> if unable
	 * to resolve a variable with the name, or if this thread is not currently
	 * suspended.
	 * <p>
	 * Variable lookup works only when a thread is suspended. Lookup is
	 * performed in all stack frames, in a top-down order, returning the first
	 * successful match, or <code>null</code> if no match is found.
	 * </p>
	 * 
	 * @param variableName
	 *            the name of the variable to search for
	 * @return a variable, or <code>null</code> if none
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the Interpreter. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                </ul>
	 */
	// IScriptVariable findVariable(String variableName) throws DebugException;
	/**
	 * Invokes the given evaluation with the specified progress monitor. This
	 * thread fires a resume event when the evaluation begins, and a suspend
	 * event when the evaluation completes or throws an exception. The events
	 * are given a detail as specified by <code>evaluationDetail</code> (one
	 * of <code>DebugEvent.EVALUATION</code> or
	 * <code>DebugEvent.EVALUATION_IMPLICIT</code>).
	 * 
	 * @param evaluation
	 *            the evaluation to perform
	 * @param monitor
	 *            progress monitor (may be <code>null</code>
	 * @param evaluationDetail
	 *            one of <code>DebugEvent.EVALUATION</code> or
	 *            <code>DebugEvent.EVALUATION_IMPLICIT</code>
	 * @param hitBreakpoints
	 *            whether or not breakpoints should be honored in this thread
	 *            during the evaluation. If <code>false</code>, breakpoints
	 *            hit in this thread during the evaluation will be ignored.
	 * @exception DebugException
	 *                if an exception occurs performing the evaluation
	 * 
	 */
	// public void runEvaluation(IEvaluationRunnable evaluation,
	// IProgressMonitor monitor, int evaluationDetail, boolean hitBreakpoints)
	// throws DebugException;
}
