package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.dltk.ruby.core.text.RubyContext;
import org.eclipse.dltk.ruby.core.utils.RubySyntaxUtils;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

public class RubySlashRegexpRule extends MultiLineRule {

	public RubySlashRegexpRule(IToken token) {
		super("/", "/", token, '\\');
	}

	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		RubyPartitionScanner s = (RubyPartitionScanner) scanner;
		int offset = s.getOffsetForLaterContextLookup();
		IToken token = super.evaluate(scanner, resume);
		if (token.isUndefined())
			return token;
		if (s.getContext(offset).getSlashStatus() != RubyContext.SlashStatus.REGEXP)
			return Token.UNDEFINED;
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
