package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * A javascript aware word detector.
 */
public class JavascriptWordDetector implements IWordDetector {

	/* (non-Javadoc)
	 * Method declared on IWordDetector.
	 */
	public boolean isWordPart(char character) {
		return Character.isJavaIdentifierPart(character);
	}
	
	/* (non-Javadoc)
	 * Method declared on IWordDetector.
	 */
	public boolean isWordStart(char character) {
		return Character.isJavaIdentifierStart(character);
	}
}
