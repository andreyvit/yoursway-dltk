package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * Ascriptaware white space detector.
 */
public class JavascriptWhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * Method declared on IWhitespaceDetector
	 */
	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
}
