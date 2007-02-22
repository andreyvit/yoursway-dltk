package org.jruby.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.jruby.lexer.yacc.ISourcePosition;

/**
 * The main Ruby parser class, application of Generation Gap pattern.
 * 
 * @author Andrey Tarantsov
 */
public class RubyParser extends DefaultRubyParser {

	private Collection errors = new ArrayList();

	public void yyerror(String message, String[] expected, String found) {
		if (expected == null || expected.length == 0) {
			handleError(message, message);
		} else {
			StringBuffer msg = new StringBuffer();
			msg.append(message);
			msg.append(" on ");
			msg.append(found);
			msg.append(", expecting");
			for (int n = 0; n < expected.length; ++n) {
				msg.append(" ");
				msg.append(expected[n]);
			}
			handleError(message + " on " + found, msg.toString());
		}
	}
	
	public void handleError(String shortMessage, String message) {
		System.err.println(message);
		errors.add(message);
		warnings.error(lexer.getPosition(), message);
	}
	
	public ISourcePosition getLastTokenPosition() {
		return lexer.getPosition();
	}

}
