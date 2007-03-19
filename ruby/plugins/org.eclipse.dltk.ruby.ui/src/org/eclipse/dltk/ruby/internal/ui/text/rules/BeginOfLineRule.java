package org.eclipse.dltk.ruby.internal.ui.text.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class BeginOfLineRule implements IRule {
	private IToken token;
	private char start;

	public BeginOfLineRule(IToken token, char start) {
		super();
		this.token = token;
		this.start = start;
	}

	protected char[] getAdditional() {
		return new char[0];
	}

	public IToken evaluate(ICharacterScanner scanner) {
		int beforeNewLine = 0;
		if (scanner.getColumn() > 0) {
			for (int i = scanner.getColumn(); i > 0; --i) {
				scanner.unread();
				int cc = scanner.read();
				scanner.unread();
				beforeNewLine++;
				if ((char) cc == '\n' || (char) cc == '\r') {
					break;
				}
				if (!Character.isWhitespace((char) cc)) {
					for (int j = 0; j < beforeNewLine; ++j) {
						scanner.read();
					}
					return Token.UNDEFINED;
				}
			}
		}

		for (int j = 0; j < beforeNewLine; ++j) {
			scanner.read();
		}

		if (scanner.read() == start) {
			while (true) {
				int c = scanner.read();
				if ((char) c == '\n' || (char) c == '\r'
						|| c == ICharacterScanner.EOF) {
					break;
				}
			}
			scanner.unread();
			return token;
		}

		scanner.unread();
		return Token.UNDEFINED;
	}
}