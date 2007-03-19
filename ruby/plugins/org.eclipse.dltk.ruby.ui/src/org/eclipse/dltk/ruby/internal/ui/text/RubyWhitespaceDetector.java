package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class RubyWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char character) {
		return Character.isWhitespace(character);
	}
}
