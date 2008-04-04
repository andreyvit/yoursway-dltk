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

import org.eclipse.dltk.ruby.internal.ui.text.IRubyColorConstants;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.WhitespaceRule;

public class RubySingleQuoteStringScanner extends AbstractScriptScanner {

	private static final String[] fgTokenProperties = new String[] { IRubyColorConstants.RUBY_STRING };

	public RubySingleQuoteStringScanner(IColorManager manager,
			IPreferenceStore store) {
		super(manager, store);

		initialize();
	}

	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}

	protected List createRules() {
		List rules = new ArrayList();

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new RubyWhitespaceDetector()));
		setDefaultReturnToken(getToken(IRubyColorConstants.RUBY_STRING));

		return rules;
	}

}
