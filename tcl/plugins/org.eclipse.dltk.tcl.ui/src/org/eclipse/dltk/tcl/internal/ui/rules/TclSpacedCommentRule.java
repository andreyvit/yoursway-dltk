package org.eclipse.dltk.tcl.internal.ui.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class TclSpacedCommentRule implements IPredicateRule {
	
	IToken token;
	
	public TclSpacedCommentRule(IToken t) {
		token = t;
	}
	
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return evaluate(scanner);
	}

	public IToken getSuccessToken() {
		return token;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		scanner.unread();
		int c = scanner.read();
		if (c != '\n') //not line start
			return Token.UNDEFINED;
		//skip spaces
		int count = 0;
		while (true) {
			count++;
			c = scanner.read ();
			if (c == ' ' || c == '\t')
				continue;
			if (c != '#') {
				for (int i = 0; i < count; i++)
					scanner.unread();
				return Token.UNDEFINED;
			} else
				break;
		} 
		while (true) {
			c = scanner.read();
			if (c == '\n')
				break;
		}
		return getSuccessToken();
	}
}
