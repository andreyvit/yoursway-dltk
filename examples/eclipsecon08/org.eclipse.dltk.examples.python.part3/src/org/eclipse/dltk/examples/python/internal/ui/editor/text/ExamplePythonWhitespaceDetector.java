package org.eclipse.dltk.examples.python.internal.ui.editor.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * Ascriptaware white space detector.
 */
public class ExamplePythonWhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * Method declared on IWhitespaceDetector
	 */
	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
}
