/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common.style;

/**
 * Contains the symbolic name of styles used by LineStyleProvider,
 * ColorManager, and any others who may be interested
 */
public interface IStyleConstantsJavaScript {
	public static final String DEFAULT = "DEFAULT";//$NON-NLS-1$
	public static final String KEYWORD = "KEYWORD";//$NON-NLS-1$
	public static final String COMMENT = "COMMENT";//$NON-NLS-1$
	public static final String LITERAL = "LITERAL";//$NON-NLS-1$
	public static final String UNFINISHED_COMMENT = "UNFINISHED_COMMENT";//$NON-NLS-1$
}
