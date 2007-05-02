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
package org.eclipse.dltk.javascript.internal.core.mixin;

import com.xored.org.mozilla.javascript.ErrorReporter;
import com.xored.org.mozilla.javascript.EvaluatorException;

final class NullReporter implements ErrorReporter {
	public void error(String arg0, String arg1, int arg2, String arg3,
			int arg4) {

	}

	public EvaluatorException runtimeError(String arg0, String arg1,
			int arg2, String arg3, int arg4) {
		return null;
	}

	public void warning(String arg0, String arg1, int arg2, String arg3,
			int arg4) {

	}
}
