/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.nls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.text.edits.TextEdit;



public class NLSUtil {

	//no instances
	private NLSUtil() {
	}
	/**
	 * Returns null if an error occurred.
	 * closes the stream 
	 */
	public static String readString(InputStream is) {
		if (is == null)
			return null;
		BufferedReader reader= null;
		try {
			StringBuffer buffer= new StringBuffer();
			char[] part= new char[2048];
			int read= 0;
			reader= new BufferedReader(new InputStreamReader(is, "8859_1")); //$NON-NLS-1$

			while ((read= reader.read(part)) != -1)
				buffer.append(part, 0, read);

			return buffer.toString();

		} catch (IOException ex) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
				}
			}
		}
		return null;
	}
	/**
	 * Creates and returns an NLS tag edit for a string that is at the specified position in 
	 * a compilation unit. Returns <code>null</code> if the string is already NLSed 
	 * or the edit could not be created for some other reason.
	 * @throws CoreException 
	 */
	public static TextEdit createNLSEdit(ISourceModule cu, int position) throws CoreException {
		return null;
	}
	
	/**
	 * Creates and returns NLS tag edits for strings that are at the specified positions in 
	 * a compilation unit. Returns <code>null</code> if all the strings are already NLSed 
	 * or the edits could not be created for some other reason.
	 * @throws CoreException 
	 */
	public static TextEdit[] createNLSEdits(ISourceModule cu, int[] positions) throws CoreException {		
		return null;
	}
}
