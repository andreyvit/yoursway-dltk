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
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultLineTracker;
import org.eclipse.jface.text.ILineTracker;
import org.eclipse.jface.text.IRegion;

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
	public static String[] convertIntoLines(String input) {
		try {
			ILineTracker tracker= new DefaultLineTracker();
			tracker.set(input);
			int size= tracker.getNumberOfLines();
			String result[]= new String[size];
			for (int i= 0; i < size; i++) {
				IRegion region= tracker.getLineInformation(i);
				int offset= region.getOffset();
				result[i]= input.substring(offset, offset + region.getLength());
			}
			return result;
		} catch (BadLocationException e) {
			return null;
		}
	}
	public static String concatenate(String[] lines, String delimiter) {
		StringBuffer buffer= new StringBuffer();
		for (int i= 0; i < lines.length; i++) {
			if (i > 0)
				buffer.append(delimiter);
			buffer.append(lines[i]);
		}
		return buffer.toString();
	}
}
