/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class RubyGlobalVarRule implements IPredicateRule {

	private final Token defaultToken;

	public RubyGlobalVarRule(Token defaultToken) {
		this.defaultToken = defaultToken;
	}

	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return evaluate(scanner);
	}

	public IToken getSuccessToken() {
		return defaultToken;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		int read = scanner.read();
		if (read == '$') {
			read = scanner.read();
			if (read == '\'') {
				return defaultToken;
			}
			scanner.unread();
		}
		scanner.unread();
		return Token.UNDEFINED;
	}

}
