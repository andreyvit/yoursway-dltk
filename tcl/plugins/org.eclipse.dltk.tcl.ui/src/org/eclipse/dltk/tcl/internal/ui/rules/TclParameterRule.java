/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class TclParameterRule implements IRule
{
	private IToken token;

	public TclParameterRule( IToken token ) {
		super();
		this.token = token;
	}

	public IToken evaluate(ICharacterScanner scanner)
	{
		if( scanner.read() == '-' ) {
			if( Character.isJavaIdentifierStart((char) scanner.read()) ) {
				for( ;; ) {
					int c = scanner.read();
					if( !( Character.isJavaIdentifierPart((char) c) )  )
						break;
				}
				scanner.unread();
				return token;
			}
			else
				scanner.unread();
		}

		scanner.unread();
		return Token.UNDEFINED;
	}
}