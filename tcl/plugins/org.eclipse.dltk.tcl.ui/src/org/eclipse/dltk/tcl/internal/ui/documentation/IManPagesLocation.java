/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.documentation;

import java.io.Reader;

public interface IManPagesLocation {
	
	/**
	 * Should find inside location for an information about keyword
	 * @param keyword
	 * @return Reader with html code
	 */
	public Reader getHtmlInfo (String keyword);
	
}
