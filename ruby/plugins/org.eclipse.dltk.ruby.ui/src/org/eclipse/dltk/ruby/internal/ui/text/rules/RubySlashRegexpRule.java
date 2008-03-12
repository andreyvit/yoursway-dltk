/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.rules;

import org.eclipse.dltk.ruby.core.utils.RubySyntaxUtils;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

public class RubySlashRegexpRule extends MultiLineRule {

	public RubySlashRegexpRule(IToken token) {
		super("/", "/", token, '\\'); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		IToken token = super.evaluate(scanner, resume);
		if (token.isUndefined())
			return token;
		processRegexpOptions(scanner);
		return token;
	}

	private void processRegexpOptions(ICharacterScanner scanner) {
		while(true) {
			int c = scanner.read();
			if (c == ICharacterScanner.EOF)
				break;
			if (!RubySyntaxUtils.isValidRegexpModifier((char) c))
				break;
		}
		scanner.unread();
	}

}
