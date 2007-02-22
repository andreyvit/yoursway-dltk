package org.eclipse.dltk.tcl.internal.parsers.raw;

import java.util.ArrayList;
import java.util.List;

public class QuotesSubstitution  extends TclElement implements ISubstitution {
	
	private List contents;
	
	public QuotesSubstitution() {
		contents = new ArrayList ();
	}
	
	public List getContents () {
		return contents;
	}

	public static boolean iAm(CodeScanner scanner) {
		int c = scanner.read();
		if (c == -1) {
			return false;
		}
		scanner.unread();
		return (c == '"');
	}
	
	private void append (char c) {
		Object o = null;
		if (contents.size() > 0) 
			o = contents.get(contents.size() - 1);
		if (o != null && o instanceof String) {
			contents.set(contents.size() - 1, (String)o + c);
		} else {
			contents.add("" + c);
		}
	}

	public boolean readMe(CodeScanner input) throws TclParseException {
		if (!iAm(input))
			return false;
		setStart(input.getPosition());
		input.read();
		int c;
		while (true) {
			ISubstitution s = SimpleTclParser.getCVB(input);			
			 
			if (s != null) {
				s.readMe(input);
				contents.add(s);
			} else {
				c = input.read ();
				if (c == -1) {
					SimpleTclParser.handleError(new ErrorDescription("unexpected eof while processing quotes started at " + getStart(), -1, ErrorDescription.ERROR));
					break;
				}
				append ((char)c);
				if (c == '"') {
					break;
				}
			}			
		}
		if (!input.isEOF()) {
			/*c = input.read();
			if (!TclTextUtils.isWhitespace(c) && ( c != CodeScanner.EOF) && (c != ']') && (c != ';')) {
				boolean cont = SimpleTclParser.handleError(new ErrorDescription("extra characters after closing-quote", input.getPosition(), ErrorDescription.ERROR));
				if (!cont)
					throw new TclParseException("extra characters after closing-quote", input.getPosition());
				do {
					c = input.read();
				} while (c != -1  && !TclTextUtils.isWhitespace(c));
				input.unread();
			} else
				input.unread();*/
			setEnd (input.getPosition() - 1);
		} else
			setEnd (input.getPosition());
		
		return true;
	}
}
