/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.syntax;

import org.eclipse.dltk.ruby.core.text.RubyContext;
import org.eclipse.dltk.ui.text.blocks.Block;
import org.eclipse.dltk.ui.text.blocks.BlocksConfiguration;
import org.eclipse.dltk.ui.text.blocks.Keyword;
import org.eclipse.dltk.ui.text.blocks.KeywordRole;
import org.eclipse.dltk.ui.text.blocks.Recognition;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class RubyBlocks {

	public static final Recognition IF_STATEMENT = new Recognition("\\b", "\\b") {

		public boolean canMatchAt(IDocument document, int matchOffset) throws BadLocationException {
			RubyContext context = RubyContextUtils.determineContext(document, matchOffset,
					RubyContext.MODE_FULL);
			return context.getModifierState() == RubyContext.ModifierState.STATEMENT;
		}

	};

	public static final Recognition KEYWORD_RECOGNITION = new Recognition("\\b", "\\b") {

		public boolean canMatchAt(IDocument document, int matchOffset) throws BadLocationException {
			RubyContext context = RubyContextUtils.determineContext(document, matchOffset,
					RubyContext.MODE_DOT_NAME);
			return context != RubyContext.AFTER_DOT && context != RubyContext.NAME;
		}

	};

	public static final Keyword KW_LEFT_PAREN = new Keyword("(", KeywordRole.BEGINNING,
			Recognition.ANYWHERE);

	public static final Keyword KW_RIGHT_PAREN = new Keyword(")", KeywordRole.ENDING,
			Recognition.ANYWHERE);

	public static final Keyword KW_LEFT_SQUARE_BRACE = new Keyword("[", KeywordRole.BEGINNING,
			Recognition.ANYWHERE);

	public static final Keyword KW_RIGHT_SQUARE_BRACE = new Keyword("]", KeywordRole.ENDING,
			Recognition.ANYWHERE);

	public static final Keyword KW_LEFT_CURLY_BRACE = new Keyword("{", KeywordRole.BEGINNING,
			Recognition.ANYWHERE);

	public static final Keyword KW_RIGHT_CURLY_BRACE = new Keyword("}", KeywordRole.ENDING,
			Recognition.ANYWHERE);

	public static final Keyword KW_END = new Keyword("end", KeywordRole.ENDING, KEYWORD_RECOGNITION);

	public static final Keyword KW_DO = new Keyword("do", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_MODULE = new Keyword("module", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_CLASS = new Keyword("class", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_DEF = new Keyword("def", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_IF = new Keyword("if", KeywordRole.BEGINNING, IF_STATEMENT);

	public static final Keyword KW_UNLESS = new Keyword("unless", KeywordRole.BEGINNING,
			IF_STATEMENT);

	public static final Keyword KW_ELSE = new Keyword("else", KeywordRole.MIDDLE,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_ELSIF = new Keyword("elsif", KeywordRole.MIDDLE,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_FOR = new Keyword("for", KeywordRole.BEGINNING, IF_STATEMENT);

	public static final Keyword KW_BEGIN = new Keyword("begin", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_RESCUE = new Keyword("rescue", KeywordRole.MIDDLE, IF_STATEMENT);

	public static final Keyword KW_ENSURE = new Keyword("ensure", KeywordRole.MIDDLE,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_CASE = new Keyword("case", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_WHEN = new Keyword("when", KeywordRole.MIDDLE,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_WHILE = new Keyword("while", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Keyword KW_REPEAT = new Keyword("repeat", KeywordRole.BEGINNING,
			KEYWORD_RECOGNITION);

	public static final Block PAREN = new Block(KW_LEFT_PAREN, KW_RIGHT_PAREN);

	public static final Block SQUARE_BRACE = new Block(KW_LEFT_SQUARE_BRACE, KW_RIGHT_SQUARE_BRACE);

	public static final Block BLOCK_BRACE = new Block(KW_LEFT_CURLY_BRACE, KW_RIGHT_CURLY_BRACE);

	public static final Block BLOCK_DO = new Block(KW_DO, KW_END);

	public static final Block MODULE = new Block(KW_MODULE, KW_END);

	public static final Block CLASS = new Block(KW_CLASS, KW_END);

	public static final Block DEF = new Block(KW_DEF, KW_END);

	public static final Block IF = new Block(KW_IF, KW_END, new Keyword[] { KW_ELSE, KW_ELSIF });

	public static final Block UNLESS = new Block(KW_UNLESS, KW_END, new Keyword[] { KW_ELSE });

	public static final Block FOR = new Block(KW_FOR, KW_END);

	public static final Block BEGIN = new Block(KW_BEGIN, KW_END, new Keyword[] { KW_RESCUE,
			KW_ENSURE, KW_ELSE });

	public static final Block CASE = new Block(KW_CASE, KW_END, new Keyword[] { KW_WHEN, KW_ELSE });

	public static final Block WHILE = new Block(KW_WHILE, KW_END);

	public static final Block REPEAT = new Block(KW_REPEAT, KW_END);

	public static final BlocksConfiguration ALL_BLOCKS = new BlocksConfiguration(new Block[] {
			RubyBlocks.PAREN, RubyBlocks.SQUARE_BRACE, RubyBlocks.BLOCK_BRACE, RubyBlocks.BLOCK_DO,
			RubyBlocks.MODULE, RubyBlocks.CLASS, RubyBlocks.DEF, RubyBlocks.UNLESS, RubyBlocks.FOR,
			RubyBlocks.BEGIN, RubyBlocks.CASE, RubyBlocks.IF, RubyBlocks.REPEAT, RubyBlocks.WHILE });

}
