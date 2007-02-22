/**
 * 
 */
package org.eclipse.dltk.ruby.core.text;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * To update the hash function in this file, change the keyword constants as you
 * like and then run the Ruby script in
 * <tt>support/keywords.hash.func/process.rb</tt>. You will need GNU gperf
 * installed.
 * 
 * @author Andrey Tarantsov
 */
public class RubyKeyword {

	private static final int NONE = 0x0;

	private final String name;

	private final int flags;

	private static final Map keywords = new HashMap();

	private final RubyContext introducedContext;

	private RubyKeyword(String name, int flags, RubyContext introducedContext) {
		this.name = name;
		this.flags = flags;
		this.introducedContext = introducedContext;
		keywords.put(name, this);
	}

	public RubyContext getIntroducedContext() {
		return introducedContext;
	}

	public String toString() {
		return name;
	}

	public static RubyKeyword byName(String name) {
		return getKeyword(name, name.length());
	}

	public static final RubyKeyword KW_IF = new RubyKeyword("if", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_ELSE = new RubyKeyword("else", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_ELSIF = new RubyKeyword("elsif", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_UNLESS = new RubyKeyword("unless", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_WHILE = new RubyKeyword("while", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_UNTIL = new RubyKeyword("until", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_IN = new RubyKeyword("in", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_CASE = new RubyKeyword("case", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_WHEN = new RubyKeyword("when", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_BEGIN = new RubyKeyword("begin", NONE,
			RubyContext.COMMAND_START);

	public static final RubyKeyword KW_ENSURE = new RubyKeyword("ensure", NONE,
			RubyContext.COMMAND_START);

	// XXX: taken from JRuby, but seems more logical to use NAME context
	public static final RubyKeyword KW_MODULE = new RubyKeyword("module", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_FOR = new RubyKeyword("for", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_THEN = new RubyKeyword("then", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_DO = new RubyKeyword("do", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_AND = new RubyKeyword("and", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_OR = new RubyKeyword("or", NONE,
			RubyContext.EXPRESSION_START);

	public static final RubyKeyword KW_NOT = new RubyKeyword("not", NONE,
			RubyContext.EXPRESSION_START);

	// /////

	public static final RubyKeyword KW_RESCUE = new RubyKeyword("rescue", NONE,
			RubyContext.KEYWORD_ARGUMENT);

	public static final RubyKeyword KW_RETURN = new RubyKeyword("return", NONE,
			RubyContext.KEYWORD_ARGUMENT);

	public static final RubyKeyword KW_BREAK = new RubyKeyword("break", NONE,
			RubyContext.KEYWORD_ARGUMENT);

	public static final RubyKeyword KW_NEXT = new RubyKeyword("next", NONE,
			RubyContext.KEYWORD_ARGUMENT);

	// ////

	public static final RubyKeyword KW_YIELD = new RubyKeyword("yield", NONE, RubyContext.ARGUMENT);

	public static final RubyKeyword KW_DEFINED = new RubyKeyword("defined?", NONE,
			RubyContext.ARGUMENT);

	public static final RubyKeyword KW_SUPER = new RubyKeyword("super", NONE, RubyContext.ARGUMENT);

	// ////

	public static final RubyKeyword KW_DEF = new RubyKeyword("def", NONE, RubyContext.NAME);

	public static final RubyKeyword KW_UNDEF = new RubyKeyword("undef", NONE, RubyContext.NAME);

	public static final RubyKeyword KW_ALIAS = new RubyKeyword("alias", NONE, RubyContext.NAME);

	public static final RubyKeyword KW_CLASS = new RubyKeyword("class", NONE, RubyContext.NAME);

	// ////

	public static final RubyKeyword KW_END = new RubyKeyword("end", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_SELF = new RubyKeyword("self", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_FALSE = new RubyKeyword("false", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_TRUE = new RubyKeyword("true", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_RETRY = new RubyKeyword("retry", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_NIL = new RubyKeyword("nil", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_REDO = new RubyKeyword("redo", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_STATIC_BEGIN = new RubyKeyword("BEGIN", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_STATIC_END = new RubyKeyword("END", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_LINE = new RubyKeyword("__LINE__", NONE,
			RubyContext.AFTER_EXPRESSION);

	public static final RubyKeyword KW_FILE = new RubyKeyword("__FILE__", NONE,
			RubyContext.AFTER_EXPRESSION);

	//

	public static final RubyKeyword METH_SUB = new RubyKeyword("sub", NONE, RubyContext.ARGUMENT);

	public static final RubyKeyword METH_SUB_BANG = new RubyKeyword("sub!", NONE,
			RubyContext.ARGUMENT);

	public static final RubyKeyword METH_GSUB = new RubyKeyword("gsub", NONE, RubyContext.ARGUMENT);

	public static final RubyKeyword METH_GSUB_BANG = new RubyKeyword("gsub!", NONE,
			RubyContext.ARGUMENT);

	public static final RubyKeyword METH_SCAN = new RubyKeyword("scan", NONE, RubyContext.ARGUMENT);

	public static final RubyKeyword METH_INDEX = new RubyKeyword("index", NONE,
			RubyContext.ARGUMENT);

	public static final RubyKeyword METH_MATCH = new RubyKeyword("match", NONE,
			RubyContext.ARGUMENT);

	private static final int MIN_WORD_LENGTH = 2;

	private static final int MAX_WORD_LENGTH = 8;

	private static final int MIN_HASH_VALUE = 3;

	private static final int MAX_HASH_VALUE = 121;

	private static final byte[] asso_values = { 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 15, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 0, 122, 122, 0, 122, 0, 15, 15, 0, 122, 122, 122, 122, 0, 122, 0, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 0, 122, 10, 51,
			15, 0, 0, 50, 25, 15, 0, 122, 20, 10, 5, 40, 10, 25, 122, 10, 0, 0, 30, 122, 45, 30, 5,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122 };

	private static int hash(String str, int len) {
		int hval = len;
		switch (hval) {
		default:
		case 3:
			hval += asso_values[str.charAt(2) & 255];
		case 2:
		case 1:
			hval += asso_values[str.charAt(0) & 255];
			break;
		}
		return hval + asso_values[str.charAt(len - 1) & 255];
	}

	private static final RubyKeyword[] wordlist = { null, null, null, KW_END, KW_ELSE,
			KW_STATIC_BEGIN, KW_ENSURE, null, KW_LINE, null, KW_YIELD, KW_MODULE, KW_DO, KW_AND,
			null, KW_ALIAS, KW_RESCUE, null, KW_STATIC_END, KW_CASE, KW_RETRY, null, KW_OR,
			KW_FILE, KW_REDO, METH_MATCH, null, null, null, null, KW_CLASS, null, null, null,
			KW_TRUE, METH_INDEX, null, null, null, null, KW_SUPER, null, KW_IN, KW_NOT, KW_THEN,
			KW_UNTIL, KW_UNLESS, null, null, null, KW_WHILE, null, KW_IF, null, METH_SCAN,
			KW_ELSIF, KW_RETURN, null, KW_DEFINED, null, null, null, null, KW_NIL, KW_SELF,
			KW_FALSE, null, null, null, null, METH_SUB_BANG, null, null, KW_FOR, KW_NEXT,
			METH_GSUB_BANG, KW_BREAK, null, null, null, null, null, null, null, null, KW_UNDEF,
			null, null, null, KW_WHEN, null, null, null, null, null, null, null, null, null, null,
			null, null, null, KW_DEF, null, METH_SUB, null, null, null, null, METH_GSUB, null,
			null, null, null, null, null, null, null, null, null, KW_BEGIN };

	public static RubyKeyword getKeyword(String str, int len) {
		if (len <= MAX_WORD_LENGTH && len >= MIN_WORD_LENGTH) {
			int key = hash(str, len);
			if (key <= MAX_HASH_VALUE && key >= MIN_HASH_VALUE) {
				if (wordlist[key] != null && str.equals(wordlist[key].name)) {
					return wordlist[key];
				}
			}
		}
		return null;
	}

}