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