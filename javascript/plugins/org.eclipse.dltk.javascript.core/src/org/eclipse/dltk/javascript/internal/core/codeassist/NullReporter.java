/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.javascript.internal.core.codeassist;

import com.xored.org.mozilla.javascript.ErrorReporter;
import com.xored.org.mozilla.javascript.EvaluatorException;

public final class NullReporter implements ErrorReporter {
	public void error(String message, String sourceName, int line,
			String lineSource, int offset) {
		// TODO Auto-generated method stub

	}

	public EvaluatorException runtimeError(String message,
			String sourceName, int line, String lineSource, int lineOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	public void warning(String message, String sourceName, int line,
			String lineSource, int lineOffset) {
		// TODO Auto-generated method stub

	}
}