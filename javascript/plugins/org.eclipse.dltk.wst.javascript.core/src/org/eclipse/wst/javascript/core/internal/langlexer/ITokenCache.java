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
package org.eclipse.wst.javascript.core.internal.langlexer;



import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;

/**
 * @author jason
 */
public interface ITokenCache {

	public void add(Token tk);

	public void remove(Token tk);

	/**
	 * Get a list of strings that should be placed in the cache if they are found as tokens.
	 */
	public String[] getKeyKeywordArray();
	// todo: design note: technically it might be better to change this interface so that all tokens get sent to the token cache to be inspected, but since almost all lexers seem to do basically the same thing, I've chosen to put that code in one place.  The generic code does need to know what keywords are special though and that's why the above method is here.
}