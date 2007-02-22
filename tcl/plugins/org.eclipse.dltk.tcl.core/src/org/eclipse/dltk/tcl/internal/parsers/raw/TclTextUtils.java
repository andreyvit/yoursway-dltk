package org.eclipse.dltk.tcl.internal.parsers.raw;

public class TclTextUtils {

	public static void runToLineEnd(CodeScanner scanner) {
		boolean esc = false;
		while (true) {
			int c = scanner.read();
			switch (c) {
			case '\\':
				esc = true;
				break;
			case '\r':
				break;
			case '\n':
			case CodeScanner.EOF:
				if (!esc)
					return;
			default:
				esc = false;
			}
		}
	}

	public static boolean isWhitespace(int c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}

	public static boolean isTrueWhitespace(int c) {
		return (c == ' ' || c == '\t');
	}

	public static boolean isHexDigit(int c) {
		return ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
	}

	public static boolean isDigit(int c) {
		return (c >= '0' && c <= '9');
	}

	public static boolean isOctDigit(int c) {
		return (c >= '0' && c <= '7');
	}

	public static boolean isIdentifier(int c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
				|| (c >= '0' && c <= '9') || (c == '-')|| (c == '_') || (c == ':'));
	}

	public static boolean isNewLine(CodeScanner input) {
		int c = input.read();
		if (c == '\n') {
			input.unread();
			return true;
		} else if (c == '\r') {
			c = input.read();
			input.unread();
			input.unread();
			if (c == '\n')
				return true;
		} else {
			if (c != -1)
				input.unread();
		}
		return false;
	}

	public static void skipNewLine(CodeScanner input) {
		int c = input.read();
		if (c == '\n') {
			return;
		} else if (c == '\r') {
			c = input.read();
			if (c == '\n')
				return;
			input.unread();
		}
		input.unread();

	}

}
