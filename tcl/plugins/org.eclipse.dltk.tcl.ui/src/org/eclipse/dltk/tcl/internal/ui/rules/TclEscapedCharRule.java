package org.eclipse.dltk.tcl.internal.ui.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class TclEscapedCharRule implements IPredicateRule {
	
	IToken token;
	char esc;
	
	public TclEscapedCharRule(IToken token, char escape) {
		this.token = token;
		esc = escape;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
		if (c == esc) {
			scanner.read();
			return getSuccessToken();			 
		} else 
			scanner.unread();
		return Token.UNDEFINED;
	}

	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return evaluate(scanner);
	}

	public IToken getSuccessToken() {
		return token;
	}
}
