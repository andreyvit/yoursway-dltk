package org.eclipse.dltk.examples.python.internal.ui.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IToken;
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
		initialize();
	}

	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	protected List createRules() {
		List/* <IRule> */rules = new ArrayList/* <IRule> */();
		IToken keyword = getToken(IExamplePythonColorConstants.PYTHON_KEYWORD);
		IToken comment = getToken(IExamplePythonColorConstants.PYTHON_COMMENT);
		IToken other = getToken(IExamplePythonColorConstants.PYTHON_DEFAULT);
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
		setDefaultReturnToken(other);
		return rules;
	}
}
