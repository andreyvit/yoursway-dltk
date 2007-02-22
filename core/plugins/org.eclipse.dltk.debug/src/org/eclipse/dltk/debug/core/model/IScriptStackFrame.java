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

import org.eclipse.debug.core.model.IStackFrame;

/**
 * A stack frame in a thread on a Script virtual machine.
 * <p>
 * Clients are not intended to implement this interface.
 * </p>
 * <p>
 * <code>IJavaStackFrame</code> also implements
 * {@link org.eclipse.debug.core.model.IDropToFrame}.
 * </p>
 * 
 * @see org.eclipse.debug.core.model.IStackFrame
 */

public interface IScriptStackFrame extends IStackFrame {

	/**
	 * Returns the line number of the instruction pointer in this stack frame
	 * that corresponds to the line in the associated source element in the
	 * specified stratum, or <code>-1</code> if line number information is
	 * unavailable.
	 * 
	 * @param stratum
	 *            the stratum to use.
	 * @return line number of instruction pointer in this stack frame, or
	 *         <code>-1</code> if line number information is unavailable
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the debug target. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                </ul>
	 * 
	 * 
	 */
	// public int getLineNumber(String stratum) throws DebugException;
	/**
	 * Returns the source name debug attribute associated with the declaring
	 * type of this stack frame, or <code>null</code> if the source name debug
	 * attribute not present.
	 * 
	 * @return source name debug attribute, or <code>null</code>
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the Interpreter. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                <li>This stack frame is no longer valid. That is, the
	 *                thread containing this stack frame has since been resumed.</li>
	 *                </ul>
	 */
	// public String getSourceName() throws DebugException;
	/**
	 * Returns the source name debug attribute associated with the declaring
	 * type of this stack frame in the specified stratum, or <code>null</code>
	 * if the source name debug attribute not present.
	 * 
	 * @param stratum
	 *            the stratum to use.
	 * @return source name debug attribute, or <code>null</code>
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the Interpreter. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                <li>This stack frame is no longer valid. That is, the
	 *                thread containing this stack frame has since been resumed.</li>
	 *                </ul>
	 * 
	 * 
	 */
	// public String getSourceName(String stratum) throws DebugException;
	/**
	 * Returns the source path debug attribute associated with this stack frame
	 * in the specified stratum, or <code>null</code> if the source path is
	 * not known.
	 * 
	 * @param stratum
	 *            the stratum to use.
	 * @return source path debug attribute, or <code>null</code>
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the Interpreter. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                <li>This stack frame is no longer valid. That is, the
	 *                thread containing this stack frame has since been resumed.</li>
	 *                </ul>
	 * 
	 */
	// public String getSourcePath(String stratum) throws DebugException;
	/**
	 * Returns the source path debug attribute associated with this stack frame,
	 * or <code>null</code> if the source path is not known.
	 * 
	 * @return source path debug attribute, or <code>null</code>
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the Interpreter. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                <li>This stack frame is no longer valid. That is, the
	 *                thread containing this stack frame has since been resumed.</li>
	 *                </ul>
	 * 
	 */
	// public String getSourcePath() throws DebugException;
	/**
	 * Returns a collection of local variables that are visible at the current
	 * point of execution in this stack frame. The list includes arguments.
	 * 
	 * @return collection of locals and arguments
	 * @exception DebugException
	 *                if this method fails. Reasons include:
	 *                <ul>
	 *                <li>Failure communicating with the Interpreter. The
	 *                DebugException's status code contains the underlying
	 *                exception responsible for the failure.</li>
	 *                <li>This stack frame is no longer valid. That is, the
	 *                thread containing this stack frame has since been resumed.</li>
	 *                </ul>
	 * 
	 */
	// public IScriptVariable[] getLocalVariables() throws DebugException;
	/**
	 * Returns whether local variable information was available when local
	 * variables were retrieved from the target for this frame. Returns
	 * <code>true</code> if locals have never been retrieved. This data is
	 * available after the fact, since variable retrieval is expensive.
	 * 
	 * @return whether local variable information was available when variables
	 *         were retrieved from the target. Returns <code>true</code> if
	 *         locals have never been retrieved
	 * 
	 * 
	 */
	// public boolean wereLocalsAvailable();
}
