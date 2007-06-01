package org.eclipse.dltk.validators.internal.core.externalchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.dltk.core.DLTKCore;

public class WildcardMatcher {

	private List tokenList = new ArrayList();
	private List wcards;

	public WildcardMatcher(List wcards) {
		this.wcards = wcards;
	}

	public ExternalCheckerProblem match(Rule pattern, String input)
			throws WildcardException {
		tokenList = parseWildcard(pattern.getDescription());
		String bigpattern = makeBigPattern(pattern.getDescription(), wcards);
		Pattern pat = Pattern.compile(bigpattern);
		Matcher matcher = pat.matcher(input);

		if (matcher.matches()) {
			int linenumber;
			int inndex = getIndexOfLineNumber();
			if (inndex >= 0) {
				for (int i = 0; i <= matcher.groupCount(); i++) {
					if (matcher.group(i) == null) {
						inndex++;
					}
				}
				String strlinenumber = matcher.group(inndex + 1);
				if (DLTKCore.DEBUG) {
					for (int i = 0; i <= matcher.groupCount(); i++) {
						System.out.println(matcher.group(i));
					}
				}
				linenumber = Integer.parseInt(strlinenumber);
			} else {
				linenumber = -1;
			}
			ExternalCheckerProblem problem = new ExternalCheckerProblem(pattern
					.getType(), input, linenumber);
			return problem;
		}
		return null;
	}

	public ArrayList parseWildcard(String wildcard) {
		ArrayList list = new ArrayList();

		StringBuffer sb = new StringBuffer();
		final int CLEAN = 0;
		final int FULL = 1;
		int sbstatus = CLEAN;

		for (int j = 0; j < wildcard.length(); j++) {
			if (wildcard.charAt(j) == '%') {
				if (sbstatus == FULL) {
					list.add(new WildcardToken("string", sb.toString()));
					sbstatus = CLEAN;
					sb.delete(0, sb.length());
				}
				list.add(new WildcardToken("wcard", recognizeWildcard(wildcard
						.charAt(j + 1))));

				j = j + 1;
				continue;
			} else {
				sbstatus = FULL;
				sb.append(wildcard.charAt(j));
			}
		}
		if (sbstatus == FULL) {
			list.add(new WildcardToken("string", sb.toString()));
			sbstatus = CLEAN;
			sb.delete(0, sb.length());
		}
		return list;
	}

	public String recognizeWildcard(char symbol) {
		for (int i = 0; i < wcards.size(); i++) {
			CustomWildcard card = (CustomWildcard) wcards.get(i);
			if (card.getLetter().indexOf(symbol) != -1) {
				return card.getLetter();
			}
		}
		return null;
	}

	private static String makeBigPattern(String input, List wcards) {
		int status;
		final int UNDEFINED = 0;
		final int IN_STRING = 1;

		status = UNDEFINED;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != '%') {
				if (status == UNDEFINED) {
					status = IN_STRING;
					sb.append("(");
				}
				if (Character.isWhitespace(c)) {
					sb.append("\\s");
				} else {
					sb.append(c);
				}
			} else {
				if (status == IN_STRING) {
					sb.append(")");

				}

				String pattern = getPattern(input.charAt(i + 1), wcards);
				sb.append("(");
				sb.append(pattern);
				sb.append(")");
				i = i + 1;
				status = UNDEFINED;
			}
		}
		if (status == IN_STRING) {
			sb.append(")");
		}
		return sb.toString();
	}

	private static String getPattern(char c, List wcards) {
		String s = null;
		for (int i = 0; i < wcards.size(); i++) {
			CustomWildcard cwcard = (CustomWildcard) wcards.get(i);
			if (cwcard.getLetter().indexOf(c) != -1) {
				s = cwcard.getSpattern();
			}
		}
		return s;
	}

	private int getIndexOfLineNumber() {
		for (int i = 0; i < tokenList.size(); i++) {
			WildcardToken tok = (WildcardToken) tokenList.get(i);
			String value = (String) tok.getValue();
			if (value.equals("n")) {
				return i;
			}
		}
		return -1;
	}
}
