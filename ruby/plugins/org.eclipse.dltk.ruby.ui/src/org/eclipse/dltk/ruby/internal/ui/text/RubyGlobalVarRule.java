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
