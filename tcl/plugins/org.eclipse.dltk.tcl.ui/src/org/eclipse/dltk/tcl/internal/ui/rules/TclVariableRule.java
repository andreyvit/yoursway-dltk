/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.rules;

import org.eclipse.dltk.tcl.internal.parsers.raw.CodeScanner;
import org.eclipse.dltk.tcl.internal.parsers.raw.TclParseException;
import org.eclipse.dltk.tcl.internal.parsers.raw.VariableSubstitution;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;


public class TclVariableRule implements IRule
{
	private IToken token;

	public TclVariableRule( IToken token ) {
		super();
		this.token = token;
	}

	private class SkipCodeScanner extends CodeScanner {

		ICharacterScanner scanner;
		
		public SkipCodeScanner (ICharacterScanner scanner) {
			super (null);
			this.scanner = scanner;
		}
		
		public int read () {
			return scanner.read();
		}
		
		public boolean isEOF () {
			int res = scanner.read();
			if (res == -1)
				return true;
			scanner.unread();
			return false;
		}
		
		public void unread () {
			scanner.unread();
		}
		
		public int getPosition () {
			return scanner.getColumn();
		}
		
	}
	
	public IToken evaluate(ICharacterScanner scanner)
	{
		SkipCodeScanner scs = new SkipCodeScanner(scanner);
		if (!VariableSubstitution.iAm(scs)) {
			return Token.UNDEFINED;
		}
		
		VariableSubstitution vs = new VariableSubstitution();
		try {
			vs.readMe(scs);
		} catch (TclParseException e) {
			//e.printStackTrace();
			return Token.UNDEFINED;
		}
		
		return token;
	}

}