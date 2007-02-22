package org.eclipse.dltk.tcl.internal.ui.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * A tcl aware white space detector.
 */
public class TclWhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * Method declared on IWhitespaceDetector
	 */
	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
}
