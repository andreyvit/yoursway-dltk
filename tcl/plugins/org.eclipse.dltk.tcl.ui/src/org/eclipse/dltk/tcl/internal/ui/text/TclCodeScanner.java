/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.tcl.core.TclKeywordsManager;
import org.eclipse.dltk.tcl.internal.ui.rules.TclCommentRule;
import org.eclipse.dltk.tcl.internal.ui.rules.TclFloatNumberRule;
import org.eclipse.dltk.tcl.internal.ui.rules.TclVariableRule;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;


public class TclCodeScanner extends AbstractScriptScanner {
	
	private static String fgTokenProperties[] = new String[] {
		TclColorConstants.TCL_SINGLE_LINE_COMMENT,
		TclColorConstants.TCL_DEFAULT,
		TclColorConstants.TCL_KEYWORD,
		TclColorConstants.TCL_KEYWORD_RETURN,
		TclColorConstants.TCL_NUMBER,
		TclColorConstants.TCL_VARIABLE,
	};
	
	private static String returnKeyword = "return";
	
	public TclCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}

	
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}		
	
	protected List createRules() {		
		List/*<IRule>*/ rules = new ArrayList/*<IRule>*/();

		IToken keyword = getToken(TclColorConstants.TCL_KEYWORD);
		IToken comment = getToken(TclColorConstants.TCL_SINGLE_LINE_COMMENT);
		IToken other = getToken(TclColorConstants.TCL_DEFAULT);
		IToken variable = getToken(TclColorConstants.TCL_VARIABLE);
		IToken number = getToken(TclColorConstants.TCL_NUMBER);
		
		// Add rule for single line comments.
		//rules.add(new EndOfLineRule("#", comment));
		rules.add(new TclCommentRule(comment));
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new TclWhitespaceDetector()));
		rules.add(new TclVariableRule(variable));		
		rules.add( new TclFloatNumberRule( number ) );
		
		// Add word rule for keywords, types, and constants.
		WordRule wordRule = new WordRule(new TclWordDetector(), other);
		String[] keywords = TclKeywordsManager.getKeywords();
		for( int i = 0; i < keywords.length; i++ ) {
			wordRule.addWord(keywords[i], keyword);
		}
	        
		IToken returnToken = getToken(TclPreferenceConstants.EDITOR_KEYWORD_RETURN_COLOR);
	        wordRule.addWord(returnKeyword, returnToken);
		
		rules.add(wordRule);

		setDefaultReturnToken(other);
		
		return rules;
	}

}
