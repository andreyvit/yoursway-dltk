/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.core.text;

import javax.swing.text.BadLocationException;

import org.eclipse.dltk.ruby.core.utils.RubySyntaxUtils;

public abstract class RubyContext {

	public static final int MODE_FULL = 0;

	public static final int MODE_DOT_NAME = 1;

	public static final int INSIDE_NOTHING = 0;

	public static final int INSIDE_GLOBAL_VARIABLE = 1;

	public static final int INSIDE_INSTANCE_VARIABLE = 2;

	public static final int INSIDE_CLASS_VARIABLE = 3;

	public static final int INSIDE_IDENTIFIER = 4;

	public static final int INSIDE_CONSTANT = 5;

	public static final int INSIDE_NUMERIC_CONSTANT = 6;

	private final ModifierState modifierState;

	private final SlashStatus slashStatus;

	private final String name;

	private RubyContext(String name, ModifierState modifierState, SlashStatus slashStatus,
			HeredocStatus heredocStatus) {
		this.name = name;
		this.modifierState = modifierState;
		this.slashStatus = slashStatus;
	}

	public ModifierState getModifierState() {
		return modifierState;
	}

	public SlashStatus getSlashStatus() {
		return slashStatus;
	}

	public boolean ignoreKeywords() {
		return false;
	}

	public abstract static class ModifierState {

		private ModifierState() {
		}

		public final static ModifierState UNKNOWN = new ModifierState() {
		};

		public final static ModifierState IGNORED = new ModifierState() {
		};

		public final static ModifierState STATEMENT = new ModifierState() {
		};

		public final static ModifierState MODIFIER = new ModifierState() {
		};

	}

	public abstract static class SlashStatus {

		private SlashStatus() {
		}

		public final static SlashStatus UNKNOWN = new SlashStatus() {
		};

		public final static SlashStatus UNEXPECTED = new SlashStatus() {
		};

		public final static SlashStatus IGNORED = new SlashStatus() {
		};

		public final static SlashStatus OPERATOR = new SlashStatus() {
		};

		public final static SlashStatus REGEXP = new SlashStatus() {
		};

		public final static SlashStatus SPACE_DEPENDENT = new SlashStatus() {
		};

	}

	public abstract static class UnaryBinaryStatus {

		private UnaryBinaryStatus() {
		}

		public final static SlashStatus UNKNOWN = new SlashStatus() {
		};

		public final static SlashStatus UNEXPECTED = new SlashStatus() {
		};

		public final static SlashStatus IGNORED = new SlashStatus() {
		};

		public final static SlashStatus UNARY = new SlashStatus() {
		};

		public final static SlashStatus BINARY = new SlashStatus() {
		};

		public final static SlashStatus SPACE_DEPENDENT = new SlashStatus() {
		};

	}

	public abstract static class HeredocStatus {

		private HeredocStatus() {
		}

		public final static HeredocStatus UNKNOWN = new HeredocStatus() {
		};

		public final static HeredocStatus UNEXPECTED = new HeredocStatus() {
		};

		public final static HeredocStatus IGNORED = new HeredocStatus() {
		};

		public final static HeredocStatus SHIFT = new HeredocStatus() {
		};

		public final static HeredocStatus HEREDOC = new HeredocStatus() {
		};

		public final static HeredocStatus SPACE_DEPENDENT = new HeredocStatus() {
		};

	}

	public String toString() {
		return name;
	}

	/**
	 * The context returned when determination cannot take place because of the
	 * specified mode.
	 */
	public static final RubyContext UNKNOWN = new RubyContext("COMMAND_START",
			ModifierState.UNKNOWN, SlashStatus.UNKNOWN, HeredocStatus.UNKNOWN) {

	};

	/**
	 * The context found at the very start of a statement, i.e., at the
	 * beginning of a line (if the previous line did not end with an operator)
	 * and after a semicolon.
	 */
	public static final RubyContext COMMAND_START = new RubyContext("COMMAND_START",
			ModifierState.STATEMENT, SlashStatus.REGEXP, HeredocStatus.HEREDOC) {

	};

	/**
	 * The context found inside a statement where an operator could not be
	 * placed.
	 */
	public static final RubyContext ARGUMENT = new RubyContext("ARGUMENT ", ModifierState.MODIFIER,
			SlashStatus.SPACE_DEPENDENT, HeredocStatus.SPACE_DEPENDENT) {

	};

	/**
	 * The context found in the middle of a statement where a binary operator
	 * can be met.
	 */
	public static final RubyContext AFTER_EXPRESSION = new RubyContext("AFTER_EXPRESSION",
			ModifierState.MODIFIER, SlashStatus.OPERATOR, HeredocStatus.SHIFT) {

	};

	/**
	 * The context s
	 */
	public static final RubyContext ARGUMENT_OR_AFTER_EXPRESSION = new RubyContext(
			"ARGUMENT_OR_AFTER_EXPRESSION", ModifierState.MODIFIER, SlashStatus.OPERATOR,
			HeredocStatus.HEREDOC) {

	};

	/**
	 * The context found where a name is expected and no handling of keywords
	 * should take place.
	 */
	public static final RubyContext NAME = new RubyContext("NAME", ModifierState.IGNORED,
			SlashStatus.IGNORED, HeredocStatus.UNEXPECTED) {

		public boolean ignoreKeywords() {
			return true;
		}

	};

	/**
	 * The context found where a name is expected and no handling of keywords
	 * should take place.
	 */
	public static final RubyContext AFTER_DOT = new RubyContext("AFTER_DOT", ModifierState.IGNORED,
			SlashStatus.IGNORED, HeredocStatus.UNEXPECTED) {

		public boolean ignoreKeywords() {
			return true;
		}

	};

	/**
	 * The context found after a keyword which expects an argument.
	 */
	public static final RubyContext EXPRESSION_START = new RubyContext("EXPRESSION_START",
			ModifierState.STATEMENT, SlashStatus.REGEXP, HeredocStatus.HEREDOC) {

	};

	/**
	 * The context found after a keyword which expects an argument.
	 */
	public static final RubyContext KEYWORD_ARGUMENT = new RubyContext("KEYWORD_ARGUMENT",
			ModifierState.STATEMENT, SlashStatus.REGEXP, HeredocStatus.HEREDOC) {

	};

	/**
	 * The context found inside a comment.
	 */
	public static final RubyContext COMMENT = new RubyContext("COMMENT", ModifierState.IGNORED,
			SlashStatus.IGNORED, HeredocStatus.IGNORED) {

	};

	/**
	 * The context found inside an identifier (method name, constant name or
	 * variable name).
	 */
	public static final RubyContext INSIDE = new RubyContext("INSIDE_CLASS_VAR",
			ModifierState.IGNORED, SlashStatus.IGNORED, HeredocStatus.IGNORED) {

	};

	private static boolean isNewLineChar(char ch) {
		return ch == '\n' || ch == '\r';
	}

	public static class HeuristicLookupResult {

		public RubyContext context;

		public int keyOffset;

		public int startOfIdentifier = -1;

		public HeuristicLookupResult preidentifierContext;

		public int inside = INSIDE_NOTHING;

		public RubyKeyword keyword;

	}

	/**
	 * Does a quick test to determine the context of the given position. Does
	 * not use document's partitions and does not look back more than a few
	 * non-whitespace characters.
	 * 
	 * The primary purpose of this function is to distinguish between
	 * <code>COMMAND_START</code>, <code>OPERAND</code>,
	 * <code>AFTER_EXPRESSION</code>, <code>EXPRESSION_START</code> and
	 * <code>NAME</code> contexts. Other contexts are returned only if noticed
	 * by coincidence.
	 * 
	 * Currently the only such context is <code>COMMENT</code>, which is
	 * returned only if no non-whitespace characters exist between the
	 * <code>#</code> and the given position.
	 * 
	 * Note that a continuation line can begin with an operator only if the
	 * continuation was explicitly introduced using a backslash. This literally
	 * saves our lives because otherwise we wouldn't be able to differentiate
	 * <code>COMMAND_START</code> and <code>OPERAND</code> contexts inside
	 * long continuations.
	 * 
	 * @param document
	 * @param offset
	 * @param mode
	 *            TODO
	 * @return
	 * @throws BadLocationException
	 */
	public static HeuristicLookupResult determineContext(CharSequence document, final int offset,
			int mode) {
		int keyOffset = offset - 1;
		char ch = 0;
		for (; keyOffset >= 0; keyOffset--)
			if (!Character.isWhitespace(ch = document.charAt(keyOffset)))
				break;
			else if (isNewLineChar(ch)) {
				for (keyOffset -= 1; keyOffset >= 0; --keyOffset)
					if (!isNewLineChar(ch = document.charAt(keyOffset)))
						break;
				if (keyOffset < 0)
					break;
				else if (ch != '\\') {
					HeuristicLookupResult result = determineContext(document, keyOffset + 1, mode);
					if (result.context == COMMAND_START || result.context == EXPRESSION_START
							|| result.context == NAME || result.context == AFTER_DOT)
						return result;
					else {
						HeuristicLookupResult myresult = new HeuristicLookupResult();
						myresult.context = COMMAND_START;
						myresult.keyOffset = keyOffset;
						return myresult;
					}
				}
				// line continuation sequence -- eat and continue searching
			}

		HeuristicLookupResult myresult = new HeuristicLookupResult();
		myresult.keyOffset = keyOffset;
		if (keyOffset < 0) {
			myresult.context = COMMAND_START;
			return myresult;
		}

		switch (ch) {
		case ':': // in :: and in symbols
		case '.':
			myresult.context = AFTER_DOT;
			return myresult;
		case '!':
			myresult.context = EXPRESSION_START;
			return myresult;
		case '(':
		case ';':
			myresult.context = COMMAND_START;
			return myresult;
		case '{':
			myresult.context = EXPRESSION_START;
			return myresult;
		case '=':
			// TODO: handle =begin, =end (ignoring case)
			myresult.context = EXPRESSION_START;
			return myresult;
		case '/':
			// TODO: check for regexp? or do this in another func?
			// TODO: check for previous NAME context and set ARGUMENT if found
			myresult.context = EXPRESSION_START;
			return myresult;
		case '>':
		case '<':
		case '&':
		case '+':
		case '-':
		case '^':
		case '~':
		case '%':
		case '[':
		case ',':
			myresult.context = EXPRESSION_START;
			return myresult;
		case ')':
		case ']':
		case '}':
			myresult.context = AFTER_EXPRESSION;
			return myresult;
		case '|':
			// This can be a binary operator or a block header. We take the safe
			// choice for now.
			/*
			 * TODO: scan back and determine if this is a block (easy because
			 * block args are restricted, but requires indefinite lookbehind)
			 */
			myresult.context = EXPRESSION_START;
			return myresult;
		case '*':
			// This might be an exponentiation operator (**) or a binary/unary
			// star (*). However it's all EXPRESSION_START context.
			myresult.context = EXPRESSION_START;
			return myresult;
		case '#':
			// A comment or a (possibly escaped) character. Fortunately
			// comments and characters cannot be continued.
			if (keyOffset >= 1) {
				char c2 = document.charAt(keyOffset - 1);
				if (c2 == '?') {
					myresult.context = AFTER_EXPRESSION;
					return myresult;
				}
				if (c2 == '\\' && keyOffset >= 2 && document.charAt(keyOffset - 2) == '?') {
					myresult.context = AFTER_EXPRESSION;
					return myresult;
				}
			}
			myresult.context = COMMENT;
			return myresult;
		}

		if ((mode & MODE_DOT_NAME) == MODE_DOT_NAME) {
			myresult.context = UNKNOWN;
			return myresult;
		}

		// Test for context-introducing keywords
		int keywordStart = RubySyntaxUtils.getInclusiveStartOfIdentifierEndingAt(document,
				keyOffset);
		myresult.startOfIdentifier = keywordStart;
		if (keywordStart != -1) {
			// is this a variable or constant?
			char startCh = document.charAt(keywordStart);
			switch (startCh) {
			case '$':
				myresult.inside = INSIDE_GLOBAL_VARIABLE;
				break;
			case '@':
				if (keywordStart + 1 < document.length()
						&& document.charAt(keywordStart + 1) == '@')
					myresult.inside = INSIDE_CLASS_VARIABLE;
				else
					myresult.inside = INSIDE_INSTANCE_VARIABLE;
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				myresult.inside = INSIDE_NUMERIC_CONSTANT;
				break;
			default:
				if (Character.isUpperCase(startCh))
					myresult.inside = INSIDE_CONSTANT;
				else
					myresult.inside = INSIDE_IDENTIFIER;
			}

			// check for INSIDE state
			if (keyOffset == offset - 1 && offset < document.length()
					&& RubySyntaxUtils.isIdentifierCharacter(document.charAt(offset))
					&& RubySyntaxUtils.isStrictIdentifierCharacter(document.charAt(offset - 1))) {
				myresult.context = INSIDE;
				return myresult;
			}

			// Quick recursive lookup to see if this identifier should be
			// ignored
			myresult.preidentifierContext = determineContext(document, keywordStart, MODE_DOT_NAME);
			RubyContext kwContext = myresult.preidentifierContext.context;
			if (kwContext == AFTER_DOT || kwContext == NAME) {
				myresult.context = ARGUMENT;
				return myresult;
			}

			String name = document.subSequence(keywordStart, keyOffset + 1).toString();
			if (Character.isDigit(name.charAt(0))) {
				myresult.context = AFTER_EXPRESSION;
				return myresult;
			}

			RubyKeyword keyword = RubyKeyword.byName(name);
			myresult.keyword = keyword;
			if (keyword != null) {
				RubyContext context = keyword.getIntroducedContext();
				if (context != null) {
					myresult.context = context;
					return myresult;
				}
			}
		}

		switch (myresult.inside) {
		case INSIDE_GLOBAL_VARIABLE:
		case INSIDE_CLASS_VARIABLE:
		case INSIDE_INSTANCE_VARIABLE:
		case INSIDE_NUMERIC_CONSTANT:
			myresult.context = AFTER_EXPRESSION;
			break;
		default:
			myresult.context = ARGUMENT_OR_AFTER_EXPRESSION;
		}
		return myresult;
	}

}
