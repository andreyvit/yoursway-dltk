/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text;

/**
 * Color keys used for syntax highlighting DLTK code and DLTKdoc compliant
 * comments. A <code>IColorManager</code> is responsible for mapping concrete
 * colors to these keys.
 * <p>
 * This interface declares static final fields only; it is not intended to be
 * implemented.
 * </p>
 * 
 */
public final class DLTKColorConstants {
	
	private DLTKColorConstants(){		
	}

	/**
	 * Note: This constant is for internal use only. Clients should not use this
	 * constant. The prefix all color constants start with (value
	 * <code>"DLTK_"</code>).
	 */
	public static final String PREFIX = "DLTK_"; //$NON-NLS-1$

	/**
	 * The color key for multi-line comments in DLTK code (value
	 * <code>"DLTK_multi_line_comment"</code>).
	 */
	public static final  String DLTK_MULTI_LINE_COMMENT = "DLTK_multi_line_comment"; //$NON-NLS-1$

	/**
	 * The color key for single-line comments in DLTK code (value
	 * <code>"DLTK_single_line_comment"</code>).
	 */
	public static final  String DLTK_SINGLE_LINE_COMMENT = "DLTK_single_line_comment"; //$NON-NLS-1$

	/**
	 * The color key for DLTK keywords in DLTK code (value
	 * <code>"DLTK_keyword"</code>).
	 */
	public static final  String DLTK_KEYWORD = "DLTK_keyword"; //$NON-NLS-1$

	/**
	 * The color key for string and character literals in DLTK code (value
	 * <code>"DLTK_string"</code>).
	 */
	public static final  String DLTK_STRING = "DLTK_string"; //$NON-NLS-1$

	/**
	 * The color key for keyword 'return' in DLTK code (value
	 * <code>"DLTK_keyword_return"</code>).
	 * 
	 *
	 */
	public static final  String DLTK_KEYWORD_RETURN = "DLTK_keyword_return"; //$NON-NLS-1$

	/**
	 * The color key for operators and brackets in DLTK code (value
	 * <code>"DLTK_operator"</code>).
	 * 
	 *
	 */
	public static final  String DLTK_OPERATOR = "DLTK_operator"; //$NON-NLS-1$

	public static final String DLTK_CLASS_DEFINITION = "DLTK_class_definition"; //$NON-NLS-1$

	public static final String DLTK_FUNCTION_DEFINITION = "DLTK_function_definition"; //$NON-NLS-1$
	/**
	 * The color key for numbers in DLTK code (value <code>"DLTK_number"</code>).
	 */
	public static final  String DLTK_NUMBER = "DLTK_number"; //$NON-NLS-1$

	/**
	 * The color key for everything in DLTK code for which no other color is
	 * specified (value <code>"DLTK_default"</code>).
	 */
	public static final String DLTK_DEFAULT = "DLTK_default"; //$NON-NLS-1$
	
	/**
	 * The color key for everything in DLTK documentation for which no other color is
	 * specified (value <code>"DLTK_doc"</code>).
	 */
	public static final String DLTK_DOC = "DLTK_doc"; //$NON-NLS-1$

	/**
	 * The color key for task tags in DLTK comments (value
	 * <code>"DLTK_comment_task_tag"</code>).
	 * 
	 *
	 */
	public static final String TASK_TAG = "DLTK_comment_task_tag"; //$NON-NLS-1$

	/**
	 * The color key for DLTKDoc keywords (<code>@foo</code>) in DLTKDoc comments (value <code>"DLTK_doc_keyword"</code>).
	 */
	public static final String DLTKDOC_KEYWORD = "DLTK_doc_keyword"; //$NON-NLS-1$

	/**
	 * The color key for HTML tags (<code>&lt;foo&gt;</code>) in DLTKDoc
	 * comments (value <code>"DLTK_doc_tag"</code>).
	 */
	public static final String DLTKDOC_TAG = "DLTK_doc_tag"; //$NON-NLS-1$

	/**
	 * The color key for DLTKDoc links (<code>{foo}</code>) in DLTKDoc
	 * comments (value <code>"DLTK_doc_link"</code>).
	 */
	public static final String DLTKDOC_LINK = "DLTK_doc_link"; //$NON-NLS-1$

	/**
	 * The color key for everything in DLTKDoc comments for which no other color
	 * is specified (value <code>"DLTK_doc_default"</code>).
	 */
	public static final String DLTKDOC_DEFAULT = "DLTK_doc_default"; //$NON-NLS-1$	

	// ---------- Properties File Editor ----------

	/**
	 * The color key for keys in a properties file (value
	 * <code>"pf_coloring_key"</code>).
	 * 
	 *
	 */
	public static final String PROPERTIES_FILE_COLORING_KEY = "pf_coloring_key"; //$NON-NLS-1$

	/**
	 * The color key for comments in a properties file (value
	 * <code>"pf_coloring_comment"</code>).
	 * 
	 *
	 */

	public static final String PROPERTIES_FILE_COLORING_COMMENT = "pf_coloring_comment"; //$NON-NLS-1$

	/**
	 * The color key for values in a properties file (value
	 * <code>"pf_coloring_value"</code>).
	 * 
	 *
	 */
	public static final String PROPERTIES_FILE_COLORING_VALUE = "pf_coloring_value"; //$NON-NLS-1$

	/**
	 * The color key for assignment in a properties file. (value
	 * <code>"pf_coloring_assignment"</code>).
	 * 
	 *
	 */
	public static final String PROPERTIES_FILE_COLORING_ASSIGNMENT = "pf_coloring_assignment"; //$NON-NLS-1$

	/**
	 * The color key for arguments in values in a properties file. (value
	 * <code>"pf_coloring_argument"</code>).
	 * 
	 *
	 */
	public static final String PROPERTIES_FILE_COLORING_ARGUMENT = "pf_coloring_argument"; //$NON-NLS-1$
}
