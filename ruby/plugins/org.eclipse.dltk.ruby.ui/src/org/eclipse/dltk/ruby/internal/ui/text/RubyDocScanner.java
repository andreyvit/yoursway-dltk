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

import org.eclipse.dltk.ruby.internal.ui.text.rules.BeginOfLineRule;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class RubyDocScanner extends AbstractScriptScanner {
	private static final String[] fgTokenProperties = new String[] {
		RubyColorConstants.RUBY_DOC,
		RubyColorConstants.RUBY_DOC_TOPIC		
	};

	public RubyDocScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);

		initialize();
	}
	
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	
	protected List createRules() {
		List/*<IRule>*/ rules = new ArrayList/*<IRule>*/();
		
		IToken topic = getToken(RubyColorConstants.RUBY_DOC_TOPIC);
		IToken other = getToken(RubyColorConstants.RUBY_DOC);
		
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new RubyWhitespaceDetector()));
		
		rules.add(new BeginOfLineRule(topic, '='));
		setDefaultReturnToken(other);

		return rules;
	}

}
