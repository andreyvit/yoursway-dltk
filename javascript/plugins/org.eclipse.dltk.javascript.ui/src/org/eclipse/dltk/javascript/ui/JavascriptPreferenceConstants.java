/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.ui;

import org.eclipse.dltk.javascript.internal.ui.text.JavascriptColorConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.preferences.NewDLTKProjectPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;


public class JavascriptPreferenceConstants extends PreferenceConstants
{

	/*
	 * Single line comment
	 */
	/**
	 * A named preference that holds the color used to render single line comments.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_COLOR = JavascriptColorConstants.JS_SINGLE_LINE_COMMENT;

	/**
	 * A named preference that controls whether single line comments are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in bold. If <code>false</code> the are
	 * rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_BOLD = JavascriptColorConstants.JS_SINGLE_LINE_COMMENT + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in italic.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in italic. If <code>false</code> the
	 * are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_ITALIC = JavascriptColorConstants.JS_SINGLE_LINE_COMMENT + EDITOR_ITALIC_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in strikethrough.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in strikethrough. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_STRIKETHROUGH = JavascriptColorConstants.JS_SINGLE_LINE_COMMENT + EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in underline.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in underline. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 * 
	 *
	 */
	public final static String EDITOR_SINGLE_LINE_COMMENT_UNDERLINE = JavascriptColorConstants.JS_SINGLE_LINE_COMMENT + EDITOR_UNDERLINE_SUFFIX;

	/*
	 * Key worlds
	 */
	/**
	 * A named preference that holds the color used to render keyword.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_KEYWORD_COLOR = JavascriptColorConstants.JS_KEYWORD;

	/**
	 * A named preference that controls whether kwyword are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in bold. If <code>false</code> the are
	 * rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_KEYWORD_BOLD = JavascriptColorConstants.JS_KEYWORD + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that controls whether keyword are rendered in italic.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in italic. If <code>false</code> the
	 * are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_KEYWORD_ITALIC = JavascriptColorConstants.JS_KEYWORD + EDITOR_ITALIC_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in strikethrough.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in strikethrough. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_KEYWORD_STRIKETHROUGH = JavascriptColorConstants.JS_KEYWORD + EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * A named preference that controls whether keyword are rendered in underline.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in underline. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 * 
	 *
	 */
	public final static String EDITOR_KEYWORD_UNDERLINE = JavascriptColorConstants.JS_KEYWORD + EDITOR_UNDERLINE_SUFFIX;
	/*
	 * keyword return color
	 */
	/**
	 * A named preference that holds the color used to render keyword.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_KEYWORD_RETURN_COLOR = JavascriptColorConstants.JS_KEYWORD_RETURN;

	/**
	 * A named preference that controls whether kwyword are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in bold. If <code>false</code> the are
	 * rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_KEYWORD_RETURN_BOLD = JavascriptColorConstants.JS_KEYWORD_RETURN + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that controls whether keyword are rendered in italic.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in italic. If <code>false</code> the
	 * are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_KEYWORD_RETURN_ITALIC = JavascriptColorConstants.JS_KEYWORD_RETURN + EDITOR_ITALIC_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in strikethrough.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in strikethrough. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_KEYWORD_RETURN_STRIKETHROUGH = JavascriptColorConstants.JS_KEYWORD_RETURN + EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * A named preference that controls whether keyword are rendered in underline.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in underline. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 * 
	 *
	 */
	public final static String EDITOR_KEYWORD_RETURN_UNDERLINE = JavascriptColorConstants.JS_KEYWORD_RETURN + EDITOR_UNDERLINE_SUFFIX;

	/*
	 * Numbers
	 */
	/**
	 * A named preference that holds the color used to render NUMBER.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_NUMBER_COLOR = JavascriptColorConstants.JS_NUMBER;

	/**
	 * A named preference that controls whether number are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in bold. If <code>false</code> the are
	 * rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_NUMBER_BOLD = JavascriptColorConstants.JS_NUMBER + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that controls whether NUMBER are rendered in italic.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in italic. If <code>false</code> the
	 * are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_NUMBER_ITALIC = JavascriptColorConstants.JS_NUMBER + EDITOR_ITALIC_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in strikethrough.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in strikethrough. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_NUMBER_STRIKETHROUGH = JavascriptColorConstants.JS_NUMBER + EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * A named preference that controls whether NUMBER are rendered in underline.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in underline. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 * 
	 *
	 */

	public final static String EDITOR_NUMBER_UNDERLINE = JavascriptColorConstants.JS_NUMBER + EDITOR_UNDERLINE_SUFFIX;		
	
	/*
	 * Strings
	 */
	/**
	 * A named preference that holds the color used to render STRING.
	 * <p>
	 * Value is of type <code>String</code>. A RGB color value encoded as a string using class <code>PreferenceConverter</code>
	 * </p>
	 * 
	 * @see org.eclipse.jface.resource.StringConverter
	 * @see org.eclipse.jface.preference.PreferenceConverter
	 */
	public final static String EDITOR_STRING_COLOR = JavascriptColorConstants.JS_STRING;

	/**
	 * A named preference that controls whether STRING are rendered in bold.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in bold. If <code>false</code> the are
	 * rendered using no font style attribute.
	 * </p>
	 */
	public final static String EDITOR_STRING_BOLD = JavascriptColorConstants.JS_STRING + EDITOR_BOLD_SUFFIX;

	/**
	 * A named preference that controls whether STRING are rendered in italic.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in italic. If <code>false</code> the
	 * are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_STRING_ITALIC = JavascriptColorConstants.JS_STRING + EDITOR_ITALIC_SUFFIX;

	/**
	 * A named preference that controls whether single line comments are rendered in strikethrough.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in strikethrough. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 */
	public final static String EDITOR_STRING_STRIKETHROUGH = JavascriptColorConstants.JS_STRING + EDITOR_STRIKETHROUGH_SUFFIX;

	/**
	 * A named preference that controls whether STRING are rendered in underline.
	 * <p>
	 * Value is of type <code>Boolean</code>. If <code>true</code> single line comments are rendered in underline. If <code>false</code>
	 * the are rendered using no italic font style attribute.
	 * </p>
	 * 
	 *
	 */

	public final static String EDITOR_STRING_UNDERLINE = JavascriptColorConstants.JS_STRING + EDITOR_UNDERLINE_SUFFIX;

	public final static String EDITOR_FUNCTION_DEFINITION_COLOR = JavascriptColorConstants.JS_FUNCTION_DEFINITION;
	
	public static void initializeDefaultValues( IPreferenceStore store ) {
		PreferenceConstants.initializeDefaultValues(store);
		
		PreferenceConverter.setDefault( store, JavascriptPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR, new RGB( 63, 127, 95 ) );
		PreferenceConverter.setDefault( store, JavascriptPreferenceConstants.EDITOR_KEYWORD_COLOR, new RGB( 127, 0, 85 ) );
		PreferenceConverter.setDefault( store, JavascriptPreferenceConstants.EDITOR_KEYWORD_RETURN_COLOR, new RGB( 127, 0, 85 ) );
		PreferenceConverter.setDefault( store, JavascriptPreferenceConstants.EDITOR_STRING_COLOR, new RGB( 42, 0, 255 ) );
		PreferenceConverter.setDefault( store, JavascriptPreferenceConstants.EDITOR_NUMBER_COLOR, new RGB( 128, 0, 0 ) );
		PreferenceConverter.setDefault( store, JavascriptPreferenceConstants.EDITOR_FUNCTION_DEFINITION_COLOR, new RGB( 0, 0, 0 ) );

		store.setDefault( JavascriptPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_BOLD, false );
		store.setDefault( JavascriptPreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_ITALIC, false );				

		store.setDefault( JavascriptPreferenceConstants.EDITOR_KEYWORD_BOLD, true );
		store.setDefault( JavascriptPreferenceConstants.EDITOR_KEYWORD_ITALIC, false );
		store.setDefault( JavascriptPreferenceConstants.EDITOR_KEYWORD_RETURN_BOLD, true );
		store.setDefault( JavascriptPreferenceConstants.EDITOR_KEYWORD_RETURN_ITALIC, false );
		
		store.setDefault( PreferenceConstants.EDITOR_SMART_INDENT, true);
		store.setDefault( PreferenceConstants.EDITOR_CLOSE_STRINGS, true);
		store.setDefault( PreferenceConstants.EDITOR_CLOSE_BRACKETS, true);
		store.setDefault( PreferenceConstants.EDITOR_CLOSE_BRACES, true);
		store.setDefault( PreferenceConstants.EDITOR_SMART_TAB, true);
		store.setDefault( PreferenceConstants.EDITOR_SMART_PASTE, true);
		store.setDefault( PreferenceConstants.EDITOR_SMART_HOME_END, true);
		store.setDefault( PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION, true);		
		store.setDefault( PreferenceConstants.EDITOR_TAB_WIDTH, 8);
		store.setDefault( PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);
		
		// folding
		store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);
//		store.setDefault(PreferenceConstants.EDITOR_FOLDING_INNERTYPES, false);
//		store.setDefault(PreferenceConstants.EDITOR_FOLDING_METHODS, false);
//		store.setDefault(PreferenceConstants.EDITOR_FOLDING_IMPORTS, true);
		
		store.setDefault (CodeFormatterConstants.FORMATTER_TAB_CHAR, CodeFormatterConstants.TAB);
		store.setDefault (CodeFormatterConstants.FORMATTER_TAB_SIZE, "4");
		store.setDefault (CodeFormatterConstants.FORMATTER_INDENTATION_SIZE, "4");
		
		NewDLTKProjectPreferencePage.initDefaults(store);
		
		store.setDefault(PreferenceConstants.APPEARANCE_COMPRESS_PACKAGE_NAMES, false);
		store.setDefault(PreferenceConstants.APPEARANCE_METHOD_RETURNTYPE, false);
		store.setDefault(PreferenceConstants.APPEARANCE_METHOD_TYPEPARAMETERS, true);
		store.setDefault(PreferenceConstants.APPEARANCE_PKG_NAME_PATTERN_FOR_PKG_VIEW, ""); //$NON-NLS-1$
		
		store.setDefault(PreferenceConstants.SHOW_SOURCE_MODULE_CHILDREN, true);
		
		store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, ".");
	}
}
