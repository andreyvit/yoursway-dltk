package org.eclipse.dltk.tcl.internal.parsers.raw;

public class BracesSubstitution extends TclElement implements ISubstitution {

	public static boolean iAm(CodeScanner scanner) {
		int c = scanner.read();
		if (c == -1)
			return false;
		scanner.unread();
		return (c == '{');
	}

	public boolean readMe(CodeScanner input) throws TclParseException {
		if (!iAm(input))
			return false;
		setStart(input.getPosition());
		input.read();
		int c;
		int nest = 1;
		while (nest > 0) {
			c = input.read();
			if (c == -1) {
				SimpleTclParser.handleError(new ErrorDescription(
						"unexpected eof while processing braces started at "
								+ getStart(), -1, ErrorDescription.ERROR));
				break;
			}
			if (c == '\\') {
				c = input.read();
				if (c == '{' || c == '}')
					continue;
				if (c == '\r') {
					int c1 = input.read();
					if (c1 == '\n') {
						do {
							c = input.read();
						} while (c != -1 && TclTextUtils.isTrueWhitespace(c));
						input.unread();
						continue;
					} else
						input.unread();
				}
			}
			if (c == '{') {
				nest++;
				continue;
			}
			if (c == '}') {
				nest--;
				continue;
			}
		}
		if (!input.isEOF()) {
			setEnd(input.getPosition() - 1);
		} else
			setEnd(input.getPosition());
		return true;
	}

}
