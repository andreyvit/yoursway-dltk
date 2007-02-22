package org.eclipse.dltk.tcl.internal.parsers.raw;

import java.text.ParseException;

public class TclParseException extends ParseException {

	public TclParseException(String s, int errorOffset) {
		super(s, errorOffset);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
