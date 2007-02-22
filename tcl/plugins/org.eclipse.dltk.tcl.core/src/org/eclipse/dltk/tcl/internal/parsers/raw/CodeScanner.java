package org.eclipse.dltk.tcl.internal.parsers.raw;

public class CodeScanner {

	public static final int EOF = -1;

	private String content;
	private int pos;

	public CodeScanner(String content) {
		this.content = content;
		pos = 0;
	}

	public int read() {
		if (isEOF())
			return EOF;
		char c = content.charAt(pos);
		pos++;
		return c;
	}

	public boolean isEOF() {
		return pos >= content.length();
	}

	public void unread() {
		pos--;
	}

	public int getPosition() {
		if (isEOF()) {
			return content.length() - 1;
		} else {
			return pos;
		}
	}

}