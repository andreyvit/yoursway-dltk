package org.eclipse.dltk.examples.python.internal.ui.editor.text;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * A python aware word detector.
 */
public class ExamplePythonWordDetector implements IWordDetector {

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
