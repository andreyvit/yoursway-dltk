/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ruby.internal.ui.text.rules.RubyFloatNumberRule;
import org.eclipse.dltk.ruby.internal.ui.text.rules.StartWithRule;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class RubyCodeScanner extends AbstractScriptScanner {
	private static String[] fgKeywords = { "alias", "alias_method", "and", "BEGIN", "begin", "break", "case", "class", "def", "defined", "do", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
			"else", "elsif", "END", "end", "ensure", "for", "if", "in", "module", "next", "not", "or", "redo", "rescue", "retry", "return", "super", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$
			"then", "undef", "unless", "until", "when", "while", "yield", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
			// pseudo-keywords (standard methods used as keywords)
			"private", "protected", "public", "attr", "attr_accessor", "attr_reader", "attr_writer", "include", "require", "extend", "lambda", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$
			"proc", "block_given?", "at_exit", "try", "catch", "raise", "throw", "=begin", "=end", "module_function" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
	
	// TODO: use
	private static String[] additionalKeywords = {"initialize", "new", "loop"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	private static String[] fgPseudoVariables = { "true", "false", "self", "nil" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

//	private static String[] fgPredefinedVariables = { "$!", "$@", "$&", "$`", "$'", "$+", "$1", "$2", "$3", "$4", "$5", "$6", "$7", "$8", "$9", "$~",
//			"$=", "$/", "$\\", "$,", "$;", "$.", "$<", "$>", "$_", "$0", "$*", "$$", "$?", "$:", "$\"", "$DEBUG", "$FILENAME", "$LOAD_PATH",
//			"$stderr", "$stdin", "$stdout", "$VERBOSE", "$-0", "$-a", "$-d", "$-F", "$-i", "$-I", "$-l", "$-p", "$-v", "$-w" };

	private static String fgReturnKeyword = "return"; //$NON-NLS-1$

	private static String fgTokenProperties[] = new String[] { IRubyColorConstants.RUBY_SINGLE_LINE_COMMENT, IRubyColorConstants.RUBY_DEFAULT,
			IRubyColorConstants.RUBY_KEYWORD, IRubyColorConstants.RUBY_KEYWORD_RETURN, IRubyColorConstants.RUBY_NUMBER,
			IRubyColorConstants.RUBY_VARIABLE, IRubyColorConstants.RUBY_CLASS_VARIABLE, IRubyColorConstants.RUBY_CONSTANT_VARIABLE,
			IRubyColorConstants.RUBY_GLOBAL_VARIABLE, IRubyColorConstants.RUBY_INSTANCE_VARIABLE, IRubyColorConstants.RUBY_PREDEFINED_VARIABLE,
			IRubyColorConstants.RUBY_PSEUDO_VARIABLE, IRubyColorConstants.RUBY_SYMBOLS };

	public RubyCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}

	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	protected List createRules() {
		List/* <IRule> */rules = new ArrayList/* <IRule> */();
		IToken keyword = getToken(IRubyColorConstants.RUBY_KEYWORD);
		IToken keywordReturn = getToken(IRubyColorConstants.RUBY_KEYWORD_RETURN);
		IToken comment = getToken(IRubyColorConstants.RUBY_SINGLE_LINE_COMMENT);
		IToken other = getToken(IRubyColorConstants.RUBY_DEFAULT);
		IToken number = getToken(IRubyColorConstants.RUBY_NUMBER);
		
		IToken classVariable = getToken(IRubyColorConstants.RUBY_CLASS_VARIABLE);
		IToken instanceVariable = getToken(IRubyColorConstants.RUBY_INSTANCE_VARIABLE);
		IToken pseudoVariable = getToken(IRubyColorConstants.RUBY_PSEUDO_VARIABLE);
		IToken globalVariable = getToken(IRubyColorConstants.RUBY_GLOBAL_VARIABLE);
		IToken symbols = getToken(IRubyColorConstants.RUBY_SYMBOLS);

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("#", comment)); //$NON-NLS-1$
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new RubyWhitespaceDetector()));		
		// Add rule for numbers
		rules.add(new RubyFloatNumberRule(number));
							
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
		rules.add(new StartWithRule(classVariable, "@@".toCharArray() ) ); //$NON-NLS-1$
		
		// Symbols
		rules.add(new StartWithRule(symbols, ':', true));				
				
		setDefaultReturnToken(other);
		return rules;
	}
}
