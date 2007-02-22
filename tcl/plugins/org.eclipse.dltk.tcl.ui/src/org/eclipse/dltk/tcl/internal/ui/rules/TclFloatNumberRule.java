package org.eclipse.dltk.tcl.internal.ui.rules;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * An implementation of <code>IRule</code> detecting a numerical value.
 */
public class TclFloatNumberRule implements IRule {
	protected static final int UNDEFINED = -1;
	protected IToken fToken;
	protected int fColumn = UNDEFINED;

	public TclFloatNumberRule(IToken token) {
		Assert.isNotNull(token);
		fToken = token;
	}

	public void setColumnConstraint(int column) {
		if (column < 0)
			column = UNDEFINED;
		fColumn = column;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		int c = scanner.read();
		int p = c;
		if (Character.isDigit((char) c) || c == '.') {
			if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {
				do {
					p = c;
					c = scanner.read();
				} while (Character.isDigit((char) c));
				if ((c != 'e' && c != 'E')) {
					scanner.unread();
				}				
				if (p == '.') {
					scanner.unread();
					return Token.UNDEFINED;
				}
				return fToken;
			}
		}
		scanner.unread();
		return Token.UNDEFINED;
	}
}
