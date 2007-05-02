/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.util;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Tests whether given ranges satisfy an arbitrary criteria.
 * 
 * @author Andrey Tarantsov
 */
public interface IRangeFilter {
	
	boolean allowRange(IDocument document, int start, int length) throws BadLocationException;

}
