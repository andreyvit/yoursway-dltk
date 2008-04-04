package org.eclipse.dltk.examples.python.internal.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class ExamplePythonCodeScanner extends AbstractScriptScanner {
	private static String[] fgKeywords = { "and", "del", "for", "is", "raise",
			"assert", "elif", "from", "lambda", "break", "else", "global",
			"not", "try", "class", "except", "if", "or", "while", "continue",
			"exec", "import", "pass", "yield", "def", "finally", "in", "print",
			"self", "return" };
	private static String fgTokenProperties[] = new String[] {
			IExamplePythonColorConstants.PYTHON_COMMENT,
			IExamplePythonColorConstants.PYTHON_DEFAULT,
			IExamplePythonColorConstants.PYTHON_KEYWORD };

	public ExamplePythonCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		this.initialize();
	}

	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	protected List createRules() {
		List/* <IRule> */rules = new ArrayList/* <IRule> */();
		IToken keyword = this.getToken(IExamplePythonColorConstants.PYTHON_KEYWORD);
		IToken comment = this.getToken(IExamplePythonColorConstants.PYTHON_COMMENT);
		IToken other = this.getToken(IExamplePythonColorConstants.PYTHON_DEFAULT);
		// Add rule for single line comments.
		rules.add(new EndOfLineRule("#", comment));
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new ExamplePythonWhitespaceDetector()));
		// Add word rule for keywords.
		WordRule wordRule = new WordRule(new ExamplePythonWordDetector(), other);
		for (int i = 0; i < fgKeywords.length; i++) {
			wordRule.addWord(fgKeywords[i], keyword);
		}
		rules.add(wordRule);
		this.setDefaultReturnToken(other);
		return rules;
	}
	public class ExamplePythonWhitespaceDetector implements IWhitespaceDetector {
		public boolean isWhitespace(char character) {
			return Character.isWhitespace(character);
		}
	}
	public class ExamplePythonWordDetector implements IWordDetector {
		public boolean isWordPart(char character) {
			return Character.isJavaIdentifierPart(character);
		}
		public boolean isWordStart(char character) {
			return Character.isJavaIdentifierStart(character);
		}
	}
}
