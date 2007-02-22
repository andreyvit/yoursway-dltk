package org.eclipse.dltk.core.tests.util;

public class Util {

	/**
	 * Generate a display string from the given String.
	 * @param inputString the given input string
	 *
  	 */
	public static String displayString(String inputString){
		return displayString(inputString, 0);
	}
	/**
	 * Generate a display string from the given String.
	 * It converts:
	 * <ul>
	 * <li>\t to \t</li>
	 * <li>\r to \\r</li>
	 * <li>\n to \n</li>
	 * <li>\b to \\b</li>
	 * <li>\f to \\f</li>
	 * <li>\" to \\\"</li>
	 * <li>\' to \\'</li>
	 * <li>\\ to \\\\</li>
	 * <li>All other characters are unchanged.</li>
	 * </ul>
	 * This method doesn't convert \r\n to \n. 
	 * <p>
	 * Example of use:
	 * <o>
	 * <li>
	 * <pre>
	 * input string = "abc\ndef\tghi",
	 * indent = 3
	 * result = "\"\t\t\tabc\\n" +
	 * 			"\t\t\tdef\tghi\""
	 * </pre>
	 * </li>
	 * <li>
	 * <pre>
	 * input string = "abc\ndef\tghi\n",
	 * indent = 3
	 * result = "\"\t\t\tabc\\n" +
	 * 			"\t\t\tdef\tghi\\n\""
	 * </pre>
	 * </li>
	 * <li>
	 * <pre>
	 * input string = "abc\r\ndef\tghi\r\n",
	 * indent = 3
	 * result = "\"\t\t\tabc\\r\\n" +
	 * 			"\t\t\tdef\tghi\\r\\n\""
	 * </pre>
	 * </li>
	 * </ol>
	 * </p>
	 * 
	 * @param inputString the given input string
	 * @param indent number of tabs are added at the begining of each line.
	 *
	 * @return the displayed string
	*/
	public static String displayString(String inputString, int indent) {
		return displayString(inputString, indent, false);
	}
	public static String displayString(String inputString, int indent, boolean shift) {
		if (inputString == null)
			return "null";
		int length = inputString.length();
		StringBuffer buffer = new StringBuffer(length);
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(inputString, "\n\r", true);
		for (int i = 0; i < indent; i++) buffer.append("\t");
		if (shift) indent++;
		buffer.append("\"");
		while (tokenizer.hasMoreTokens()){

			String token = tokenizer.nextToken();
			if (token.equals("\r")) {
				buffer.append("\\r");
				if (tokenizer.hasMoreTokens()) {
					token = tokenizer.nextToken();
					if (token.equals("\n")) {
						buffer.append("\\n");
						if (tokenizer.hasMoreTokens()) {
							buffer.append("\" + \n");
							for (int i = 0; i < indent; i++) buffer.append("\t");
							buffer.append("\"");
						}
						continue;
					}
					buffer.append("\" + \n");
					for (int i = 0; i < indent; i++) buffer.append("\t");
					buffer.append("\"");
				} else {
					continue;
				}
			} else if (token.equals("\n")) {
				buffer.append("\\n");
				if (tokenizer.hasMoreTokens()) {
					buffer.append("\" + \n");
					for (int i = 0; i < indent; i++) buffer.append("\t");
					buffer.append("\"");
				}
				continue;
			}	

			StringBuffer tokenBuffer = new StringBuffer();
			for (int i = 0; i < token.length(); i++){ 
				char c = token.charAt(i);
				switch (c) {
					case '\r' :
						tokenBuffer.append("\\r");
						break;
					case '\n' :
						tokenBuffer.append("\\n");
						break;
					case '\b' :
						tokenBuffer.append("\\b");
						break;
					case '\t' :
						tokenBuffer.append("\t");
						break;
					case '\f' :
						tokenBuffer.append("\\f");
						break;
					case '\"' :
						tokenBuffer.append("\\\"");
						break;
					case '\'' :
						tokenBuffer.append("\\'");
						break;
					case '\\' :
						tokenBuffer.append("\\\\");
						break;
					default :
						tokenBuffer.append(c);
				}
			}
			buffer.append(tokenBuffer.toString());
		}
		buffer.append("\"");
		return buffer.toString();
	}
	
	public static String convertToIndependantLineDelimiter(String source) {
		if (source.indexOf('\n') == -1 && source.indexOf('\r') == -1) return source;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0, length = source.length(); i < length; i++) {
			char car = source.charAt(i);
			if (car == '\r') {
				buffer.append('\n');
				if (i < length-1 && source.charAt(i+1) == '\n') {
					i++; // skip \n after \r
				}
			} else {
				buffer.append(car);
			}
		}
		return buffer.toString();
	}
	
}
