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