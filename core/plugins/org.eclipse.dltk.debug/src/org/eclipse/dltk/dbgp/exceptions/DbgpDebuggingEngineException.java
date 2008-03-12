/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.dbgp.exceptions;

import java.text.MessageFormat;

public class DbgpDebuggingEngineException extends DbgpException {
	private static final long serialVersionUID = 1L;

	/* Command parsing errors */
	public static final int NO_ERROR = 0;

	public static final int PASSE_ERROR_IN_COMMAND = 1;

	public static final int DUPLICATE_ARGUMENTS_IN_COMMAND = 2;

	// missing a required option
	public static final int INVALID_OPTIONS = 3;

	public static final int UNIMPLEMENTED_COMMAND = 4;

	// Is used for async commands. For instance
	// if the engine is in state "run" than only "break" and "status"
	// are available
	public static final int COMMAND_NOT_AVAILABLE = 5;

	/* File related errors */

	// as a reply to a "source" command if the requested source file can't be
	// opened
	public static final int FILE_CAN_NOT_OPEN_FILE = 100;

	// stream redirect failed
	public static final int STREAM_REDIRECT_FAILED = 101;

	/* Breakpoint, or code flow errors */

	// for some reason the breakpoint could not be set due to problems
	// registering it
	public static final int BREAKPOINT_COULD_NOT_BE_SET = 200;

	// for example I don't support 'watch' yet and thus return this error
	public static final int BREAKPOINT_TYPE_NOT_SUPPORTED = 201;

	// the IDE tried to set a breakpoint on a line that does not exist in the
	// file (ie "line 0" or lines past the end of the file
	public static final int INVALID_PREAKPOINT = 202;

	// the IDE tried to set a breakpoint on a line which does not have any
	// executable code. The
	// debugger engine is NOT required to return this type if it
	// is impossible to determine if there is code on a given
	// location. (For example, in the PHP debugger backend this
	// will only be returned in some special cases where the current
	// scope falls into the scope of the breakpoint to be set
	public static final int NO_CODE_ON_BREAKPOINT_LINE = 203;

	// using an unsupported breakpoint state was attempted
	public static final int INVALID_BREAKPOINT_STATE = 204;

	// used in breakpoint_get etc. to show that there is no breakpoint with the
	// given ID
	public static final int NO_SUCH_BREAKPOINT = 205;

	// use from eval() (or perhaps property_get for a full name get
	public static final int ERROR_EVALUATING_CODE = 206;

	// the expression used for a non-eval() was invalid
	public static final int IVALID_EXPRESSION = 207;

	/* Data errors */

	// when the requested property to get did
	// not exist, this is NOT used for an existing but uninitialized
	// property, which just gets the type "uninitialised" (See:
	// PreferredTypeNames)
	public static final int CAN_NOT_GET_PROPERTY = 300;

	// the -d stack depth parameter did not exist (ie, there were less stack
	// elements than the number requested) or the parameter was < 0
	public static final int STACH_DEPTH_INVALID = 301;

	// an non existing context was requested
	public static final int CONTEXT_INVALID = 302;

	/* Protocol errors */
	public static final int ENCODING_NOT_SUPPROTED = 900;

	public static final int INTERNAL_EXCEPTION = 998;

	public static final int UNKNOWN_ERROR = 999;

	private final int code;

	public DbgpDebuggingEngineException(int code) {
		this.code = code;
	}

	public DbgpDebuggingEngineException(int code, String message) {
		super(
				MessageFormat
						.format(
								Messages.DbgpDebuggingEngineException_dbgpDebuggingEngineException,
								new Object[] { new Integer(code), message }));
		this.code = code;
	}

	public DbgpDebuggingEngineException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public DbgpDebuggingEngineException(int code, String message,
			Throwable cause) {
		super(
				MessageFormat
						.format(
								Messages.DbgpDebuggingEngineException_dbgpDebuggingEngineException2,
								new Object[] { message, new Integer(code) }),
				cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
