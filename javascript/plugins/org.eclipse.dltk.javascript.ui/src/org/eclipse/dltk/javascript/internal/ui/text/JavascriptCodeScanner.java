package org.eclipse.dltk.javascript.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.javascript.core.JavaScriptKeywords;
import org.eclipse.dltk.javascript.internal.ui.rules.FloatNumberRule;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class JavascriptCodeScanner extends AbstractScriptScanner {
	
	private static String fgReturnKeyword = "return";
	private static String fgTokenProperties[] = new String[] {
			JavascriptColorConstants.JS_SINGLE_LINE_COMMENT, JavascriptColorConstants.JS_DEFAULT, JavascriptColorConstants.JS_KEYWORD,
			JavascriptColorConstants.JS_KEYWORD_RETURN, JavascriptColorConstants.JS_NUMBER, 
			JavascriptColorConstants.JS_FUNCTION_DEFINITION
	};

	public JavascriptCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}

	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	protected List createRules() {
		List/* <IRule> */rules = new ArrayList/* <IRule> */();
		IToken keyword = getToken(JavascriptColorConstants.JS_KEYWORD);
		IToken keywordReturn = getToken(JavascriptColorConstants.JS_KEYWORD_RETURN);
		IToken comment = getToken(JavascriptColorConstants.JS_SINGLE_LINE_COMMENT);
		IToken other = getToken(JavascriptColorConstants.JS_DEFAULT);
		IToken def = getToken(JavascriptColorConstants.JS_FUNCTION_DEFINITION);
		IToken number = getToken(JavascriptColorConstants.JS_NUMBER);
		// Add rule for single line comments.
		rules.add(new EndOfLineRule("//", comment));
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new JavascriptWhitespaceDetector()));
		// Add word rule for keywords, types, and constants.
		JavascriptWordRule wordRule = new JavascriptWordRule(new JavascriptWordDetector(), other, null, def);
		for (int i = 0; i < JavaScriptKeywords.getJavaScriptKeywords().length; i++) {
			wordRule.addWord(JavaScriptKeywords.getJavaScriptKeywords()[i], keyword);
		}
		wordRule.addWord(fgReturnKeyword, keywordReturn);
		rules.add(wordRule);
		rules.add(new FloatNumberRule(number));
		setDefaultReturnToken(other);
		return rules;
	}
}
