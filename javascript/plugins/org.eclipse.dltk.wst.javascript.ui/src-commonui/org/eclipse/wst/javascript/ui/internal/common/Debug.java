/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Jens Lukowski/Innoopract - initial renaming/restructuring
 *     
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;





public final class Debug {
	public static final boolean checkForMemoryLeaks = false;

	public static final boolean collectStats = false;

	public static final int DEBUG = 0;

	public static final boolean DEBUG_THREADLOCAL = false;

	public static final boolean debugBreakpoints = false;
	public static final boolean debugCaretMediator = false;
	public static final boolean debugDisplayTreePositions = false;
	//
	public static final boolean debugMediator = false;
	//
	public static final boolean debugNotification = false;
	public static final boolean debugNotificationAndEvents = false;

	public static final boolean debugNotifyDeferred = false;
	public static final boolean debugReconciling = false;
	//
	public static final boolean debugRtfFormatProvider = false;
	//
	public static final boolean debugStructuredDocument = false;
	public static final boolean debugTaglibs = false;
	//
	public static final boolean debugTokenizer = false;
	//
	public static final boolean debugTreeModel = false;
	public static final boolean debugUpdateTreePositions = false;
	public static final boolean displayInfo = false;

	/** effects output of Logger */
	public static final boolean displayToConsole = true;
	public static final boolean displayWarnings = false;
	//
	public static final boolean failedTests = false;
	public static final boolean headParsing = false;
	public static final boolean jsDebugContextAssist = false;
	//
	public static final boolean jsDebugSyntaxColoring = false;

	public static final boolean LOCKS = false;
	// 
	public static final boolean perfTest = false;
	public static final boolean perfTestAdapterClassLoading = false;
	public static final boolean perfTestFormat = false;
	public static final boolean perfTestRawStructuredDocumentOnly = false;
	public static final boolean perfTestStructuredDocumentEventOnly = false;
	public static final boolean perfTestStructuredDocumentOnly = false;

	//
	public static final boolean syntaxHighlighting = false;
	//
	public static final boolean useStandardEolInWidget = false;

	/**
	 * Debug constructor comment.
	 */
	Debug() {
		super();
	}
}
