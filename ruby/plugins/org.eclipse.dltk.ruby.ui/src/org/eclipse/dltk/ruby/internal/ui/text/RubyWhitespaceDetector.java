package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * A script aware white space detector.
 */
public class RubyWhitespaceDetector implements IWhitespaceDetector {

	/* (non-Javadoc)
	 * Method declared on IWhitespaceDetector
	 */
	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
}
