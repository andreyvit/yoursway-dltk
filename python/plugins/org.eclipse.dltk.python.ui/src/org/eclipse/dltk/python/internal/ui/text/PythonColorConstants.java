/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.text;

import org.eclipse.dltk.ui.text.DLTKColorConstants;

public final class PythonColorConstants {
	private PythonColorConstants() {
	}

	/**
	 * The color key for string and character literals in Python code.
	 */
	public static final String PYTHON_STRING = DLTKColorConstants.DLTK_STRING; //$NON-NLS-1$

	/**
	 * The color key for Python comments.
	 */
	public static final String PYTHON_SINGLE_LINE_COMMENT = DLTKColorConstants.DLTK_SINGLE_LINE_COMMENT; //$NON-NLS-1$	

	/**
	 * The color key for Python numbers.
	 */
	public static final String PYTHON_NUMBER = DLTKColorConstants.DLTK_NUMBER; //$NON-NLS-1$

	/**
	 * The color key for Python keywords.
	 */
	public static final String PYTHON_KEYWORD = DLTKColorConstants.DLTK_KEYWORD; //$NON-NLS-1$

	/**
	 * The color key for Python keyword 'return'.
	 */
	public static final String PYTHON_KEYWORD_RETURN = DLTKColorConstants.DLTK_KEYWORD_RETURN; //$NON-NLS-1$	

	/**
	 * The color key for Python code.
	 */
	public static final String PYTHON_DEFAULT = DLTKColorConstants.DLTK_DEFAULT; //$NON-NLS-1$

	/**
	 * The color key for Python class definition.
	 */
	public static final String PYTHON_CLASS_DEFINITION = DLTKColorConstants.DLTK_CLASS_DEFINITION; //$NON-NLS-1$

	/**
	 * The color key for Python function definition.
	 */
	public static final String PYTHON_FUNCTION_DEFINITION = DLTKColorConstants.DLTK_FUNCTION_DEFINITION;

	/**
	 * The color key for Python decorator.
	 */
	public static final String PYTHON_DECORATOR = "DLTK_decorator";
}
