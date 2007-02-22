package org.eclipse.dltk.ruby.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ruby.internal.ui.text.rules.StartWithRule;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class RubyCodeScanner extends AbstractScriptScanner {
	private static String[] fgKeywords = { "alias", "alias_method", "and", "BEGIN", "begin", "break", "case", "class", "def", "defined", "do",
			"else", "elsif", "END", "end", "ensure", "for", "if", "in", "module", "next", "not", "or", "redo", "rescue", "retry", "return", "super",
			"then", "undef", "unless", "until", "when", "while", "yield",
			// pseudo-keywords (standard methods used as keywords)
			"private", "protected", "public", "attr", "attr_accessor", "attr_reader", "attr_writer", "include", "require", "extend", "lambda",
			"proc", "block_given?", "at_exit", "try", "catch", "raise", "throw", "=begin", "=end", "module_function" };
	
	// TODO: use
	private static String[] additionalKeywords = {"initialize", "new", "loop"};

	private static String[] fgPseudoVariables = { "true", "false", "self", "nil" };

//	private static String[] fgPredefinedVariables = { "$!", "$@", "$&", "$`", "$'", "$+", "$1", "$2", "$3", "$4", "$5", "$6", "$7", "$8", "$9", "$~",
//			"$=", "$/", "$\\", "$,", "$;", "$.", "$<", "$>", "$_", "$0", "$*", "$$", "$?", "$:", "$\"", "$DEBUG", "$FILENAME", "$LOAD_PATH",
//			"$stderr", "$stdin", "$stdout", "$VERBOSE", "$-0", "$-a", "$-d", "$-F", "$-i", "$-I", "$-l", "$-p", "$-v", "$-w" };

	private static String fgReturnKeyword = "return";

	private static String fgTokenProperties[] = new String[] { RubyColorConstants.RUBY_SINGLE_LINE_COMMENT, RubyColorConstants.RUBY_DEFAULT,
			RubyColorConstants.RUBY_KEYWORD, RubyColorConstants.RUBY_KEYWORD_RETURN, RubyColorConstants.RUBY_NUMBER,
			RubyColorConstants.RUBY_VARIABLE, RubyColorConstants.RUBY_CLASS_VARIABLE, RubyColorConstants.RUBY_CONSTANT_VARIABLE,
			RubyColorConstants.RUBY_GLOBAL_VARIABLE, RubyColorConstants.RUBY_INSTANCE_VARIABLE, RubyColorConstants.RUBY_PREDEFINED_VARIABLE,
			RubyColorConstants.RUBY_PSEUDO_VARIABLE, RubyColorConstants.RUBY_SYMBOLS };

	public RubyCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}

	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	protected List createRules() {
		List/* <IRule> */rules = new ArrayList/* <IRule> */();
		IToken keyword = getToken(RubyColorConstants.RUBY_KEYWORD);
		IToken keywordReturn = getToken(RubyColorConstants.RUBY_KEYWORD_RETURN);
		IToken comment = getToken(RubyColorConstants.RUBY_SINGLE_LINE_COMMENT);
		IToken other = getToken(RubyColorConstants.RUBY_DEFAULT);
//		IToken number = getToken(RubyColorConstants.RUBY_NUMBER);
		
		IToken classVariable = getToken(RubyColorConstants.RUBY_CLASS_VARIABLE);
		IToken instanceVariable = getToken(RubyColorConstants.RUBY_INSTANCE_VARIABLE);
		IToken pseudoVariable = getToken(RubyColorConstants.RUBY_PSEUDO_VARIABLE);
		IToken globalVariable = getToken(RubyColorConstants.RUBY_GLOBAL_VARIABLE);
		IToken symbols = getToken(RubyColorConstants.RUBY_SYMBOLS);

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("#", comment));
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new RubyWhitespaceDetector()));
		// rules.add(new FloatNumberRule(number));
							
		// Global Variables
		rules.add(new StartWithRule(globalVariable, '$') {
			private char[] addition = new char[]{'=','-'};
			protected char[] getAdditional() {
				return addition;
			}			
		});
		
		// Add word rule for keywords, types, and constants.
		WordRule wordRule = new WordRule(new RubyWordDetector(), other);
		for (int i = 0; i < fgKeywords.length; i++) {
			wordRule.addWord(fgKeywords[i], keyword);
		}
		for (int i = 0; i < fgPseudoVariables.length; i++) {
			wordRule.addWord(fgPseudoVariables[i], pseudoVariable);
		}
		wordRule.addWord(fgReturnKeyword, keywordReturn);
		rules.add(wordRule);
		
		
		// Instance Variables
		rules.add(new StartWithRule(instanceVariable, '@', true) );
		
		// Class Variables
		rules.add(new StartWithRule(classVariable, "@@".toCharArray() ) );
		
		// Symbols
		rules.add(new StartWithRule(symbols, ':', true));				
				
		setDefaultReturnToken(other);
		return rules;
	}
}
