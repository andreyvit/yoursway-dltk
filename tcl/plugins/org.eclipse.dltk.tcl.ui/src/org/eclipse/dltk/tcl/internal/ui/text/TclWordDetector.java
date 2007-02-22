package org.eclipse.dltk.tcl.internal.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * A Tcl aware word detector.
 */
public class TclWordDetector implements IWordDetector {

	/* (non-Javadoc)
	 * Method declared on IWordDetector.
	 */
	public boolean isWordPart(char character) {
		return Character.isJavaIdentifierPart(character) || character == ':';
	}
	
	/* (non-Javadoc)
	 * Method declared on IWordDetector.
	 */
	public boolean isWordStart(char character) {
		return Character.isJavaIdentifierPart(character) || character == '.' || character == '(' || character == ')';
	}
}
