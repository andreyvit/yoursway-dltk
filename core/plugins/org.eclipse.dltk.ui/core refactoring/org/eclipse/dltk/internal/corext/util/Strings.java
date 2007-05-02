/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.util;

import org.eclipse.jface.action.LegacyActionTools;

/**
 * Helper class to provide String manipulation functions not available in standard JDK.
 */
public class Strings {
	
	private Strings(){}
	
	public static boolean startsWithIgnoreCase(String text, String prefix) {
		int textLength= text.length();
		int prefixLength= prefix.length();
		if (textLength < prefixLength)
			return false;
		for (int i= prefixLength - 1; i >= 0; i--) {
			if (Character.toLowerCase(prefix.charAt(i)) != Character.toLowerCase(text.charAt(i)))
				return false;
		}
		return true;
	}
	public static boolean isLowerCase(char ch) {
		return Character.toLowerCase(ch) == ch;
	}
	public static String removeMnemonicIndicator(String string) {
		return LegacyActionTools.removeMnemonics(string);
	}
}
