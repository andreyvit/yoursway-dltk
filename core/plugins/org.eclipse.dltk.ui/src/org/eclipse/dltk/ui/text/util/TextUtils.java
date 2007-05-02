/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.util;

public abstract class TextUtils {
	
	private TextUtils() {
		throw new AssertionError("Cannot instantiate utility class");
	}

	/**
	 * (Copied from <code>Pattern</code> class, JRE 5.) Returns a literal
	 * pattern <code>String</code> for the specified <code>String</code>.
	 * 
	 * <p>
	 * This method produces a <code>String</code> that can be used to create a
	 * <code>Pattern</code> that would match the string <code>s</code> as if
	 * it were a literal pattern.
	 * </p>
	 * Metacharacters or escape sequences in the input sequence will be given no
	 * special meaning.
	 * 
	 * @param s
	 *            The string to be literalized
	 * @return A literal string replacement
	 * @since JRE 1.5
	 */
	public static String Pattern_quote(String s) {
		int slashEIndex = s.indexOf("\\E");
		if (slashEIndex == -1)
			return "\\Q" + s + "\\E";
	
		StringBuffer sb = new StringBuffer(s.length() * 2);
		sb.append("\\Q");
		slashEIndex = 0;
		int current = 0;
		while ((slashEIndex = s.indexOf("\\E", current)) != -1) {
			sb.append(s.substring(current, slashEIndex));
			current = slashEIndex + 2;
			sb.append("\\E\\\\E\\Q");
		}
		sb.append(s.substring(current, s.length()));
		sb.append("\\E");
		return sb.toString();
	}

}
