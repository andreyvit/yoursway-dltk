package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

public class RubyVariableWordDetector implements IWordDetector {

	public boolean isWordPart(char character) {
		return Character.isJavaIdentifierPart(character) || character == '@'|| character == '-';
	}
		
	public boolean isWordStart(char character) {
		return Character.isJavaIdentifierPart(character);
	}
}
