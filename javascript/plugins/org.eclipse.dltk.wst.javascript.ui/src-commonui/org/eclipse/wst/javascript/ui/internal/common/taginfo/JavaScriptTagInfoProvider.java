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
package org.eclipse.wst.javascript.ui.internal.common.taginfo;


import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;
import org.eclipse.wst.xml.core.internal.contentmodel.annotation.AnnotationMap;

/**
 * Provides tag information documentation for javascript code
 * @see ITagInfoProvider
 */
public class JavaScriptTagInfoProvider {
	static public final String JSAnnotationURI = "data/jsref.xml"; //$NON-NLS-1$

	static protected final char chEndBracket = ']';
	static protected final char chEndBrace = ')';
	static protected final char chBeginBracket = '[';
	static protected final char chBeginBrace = '(';
	static protected final char chPeriod = '.';

	static protected AnnotationMap fAnnotationMap = null;
	protected String fErrorMessage = null;

	/**
	 * Constructor for JavaScriptTagInfoProvider.
	 */
	public JavaScriptTagInfoProvider() {
		super();
	}

	/**
	 * Set the reason why we could not find any tag info
	 */
	public void setErrorMessage(String errorMessage) {
		fErrorMessage = errorMessage;
	}

	/*
	 * @see HoverHelpInfo#getErrorMessage()
	 */
	public String getErrorMessage() {
		return fErrorMessage;
	}

	/**
	 * Gets the annotationMap associated with javascript
	 * @return Returns an AnnotationMap
	 */
	public AnnotationMap getAnnotationMap() {
		if (fAnnotationMap == null) {
			fAnnotationMap = new AnnotationMap();
			try {
				fAnnotationMap.load(JSAnnotationURI, JSEditorPlugin.ID);
			}
			catch (Exception e) {
				// could not load annotation map
			}
		}
		return fAnnotationMap;
	}

	/**
	 * Returns documentation associated with the javacript code
	 * Searches for end segment first, them moves up till cant find a better
	 * definition
	 * ** this way is faster since we already know how annotation file looks but the other method
	 * ** actually works correctly because if the addition info is for
	 * ** layer[].clip, and there is no definition for clip, then i will never find the definition
	 * ** for layer[].clip.
	 * @param spec String javascript code to search for
	 * 
	 * @return String documentation associated with spec
	 */
	public String getTagInfo(String spec) {
		if ((spec == null) || (spec.length() == 0)) {
			return null; //nothing to find
		}

		String result = null;
		String prevResult = null;
		int pathLength = spec.length();
		int currPos = pathLength;

		do {
			try {
				currPos = getParentPos(spec, currPos);
				String currString = spec.substring(currPos);
				prevResult = result;
				result = getAnnotationMap().getProperty(currString, "tagInfo"); //$NON-NLS-1$
				currPos = currPos - 2; // skip over '.', '(', '{'
			}
			catch (StringIndexOutOfBoundsException e) {
			}
		}
		while ((result != null) && (currPos > 0));

		if ((prevResult == null) && (result != null))
			return result; // prevResult didnt get set before exiting
		else
			return prevResult;
	}

	/**
	 * Give the previous position of either of the two characters starting at the given index of the given string
	 * @param estring the string to search
	 * @param sidx index at which to start the search
	 * @param ch1 one of the characters to search for
	 * @param ch2 one of the characters to search for
	 */
	protected int strPreviousPos(String estring, int sidx, char ch1, char ch2) {
		int idx1;
		int idx11 = estring.lastIndexOf(ch1, sidx);
		int idx12 = estring.lastIndexOf(ch2, sidx);
		if (idx11 == -1) {
			idx1 = idx12;
		}
		else if (idx12 == -1) {
			idx1 = idx11;
		}
		else {
			idx1 = (idx11 > idx12) ? idx11 : idx12;
		}
		return idx1;
	}

	/**
	 * gets the position of the parent starting from endPos
	 * @param str String full javascript code
	 * @param endPos int position of string to start searching from
	 * 
	 * @return int position where parent starts from
	 */
	protected int getParentPos(String str, int endPos) {
		if ((str == null) || (str.length() == 0) || (endPos > str.length())) {
			return -1;
		}

		int idx1 = str.lastIndexOf(chPeriod, endPos);
		int idx2 = endPos;
		int idx3 = idx2;
		// future_TODO: enhance this to not be thrown off by quoted chars like '(' or "]" within the area we're scanning
		do {
			idx2 = strPreviousPos(str, idx3, chEndBrace, chEndBracket);
			idx3 = strPreviousPos(str, idx2, chBeginBrace, chBeginBracket);
		}
		// if we encounter '[]' or '()', we are not done yet
		while ((idx2 != -1) && (idx3 != -1) && ((idx2 - 1) == idx3));

		int idx4 = (idx2 == -1) ? idx3 : (idx3 == -1) ? idx2 : (idx2 > idx3) ? idx2 : idx3;

		// which occurs first?  . or []/()?
		int pos = (idx1 == -1) ? idx4 : (idx4 == -1) ? idx1 : (idx1 > idx4) ? idx1 : idx4;

		++pos; // skip over '.''{''('
		return pos;
	}

	/**
	 * @see TagInfoProviderAdapter#release()
	 */
	public void release() {
		fAnnotationMap = null;
	}

	////	//	 The 3 methods below are the alternative way to get the tagInfo by searching from beginning segment.
	////	//	 These methods are commented out rather than delete because the current way to get tagInfo
	////	//	 may not be the best way.
	////
	////	/**
	////	 * Returns documentation associated with the javascript code
	////	 * searches for top layer first, then removes top parent layer and tries again till
	////	 * taginfo is found
	////	 * 
	////	 * @param String spec javascript code to search for
	////	 * @return String taginfo associated with spec
	////	 */
	////	public String getTagInfo(String spec) {
	////		String result = null;
	////		String strFCName = spec;
	////
	////		while ((strFCName != null) && (result == null)) {
	////			result = getAnnotationMap().getProperty(strFCName, "tagInfo"); //$NON-NLS-1$
	////			strFCName = removeAParent(strFCName);
	////		}
	////
	////		return result;
	////	}
	////
	////	/**
	////	 * give the next position of either of the two characters starting at the given index of the given string
	////	 * @param estring the string to search
	////	 * @param sidx index at which to start the search
	////	 * @param ch1 one of the characters to search for
	////	 * @param ch2 one of the characters to search for
	////	 */
	////	protected int strNextPos(String estring, int sidx, char ch1, char ch2) {
	////		int idx1;
	////		int idx11 = estring.indexOf(ch1, sidx);
	////		int idx12 = estring.indexOf(ch2, sidx);
	////		if (idx11 == -1) {
	////			idx1 = idx12;
	////		}
	////		else if (idx12 == -1) {
	////			idx1 = idx11;
	////		}
	////		else {
	////			idx1 = (idx11 < idx12) ? idx11 : idx12;
	////		}
	////		return idx1;
	////	}
	////
	////	/**
	////	 * Removes topmost layer/parent of javascript code str
	////	 * @param str String
	////	 * @return String
	////	 */
	////	protected String removeAParent(String str) {
	////		if ((str == null) || (str.length() == 0)) {
	////			return null;
	////		}
	////
	////		int idx1 = str.indexOf(chPeriod, 0);
	////		// todo: enhance this to not be thrown off by quoted chars like '(' or "]" within the area we're scanning
	////		int idx2 = strNextPos(str, 0, chBeginBrace, chBeginBracket);
	////		int idx3 = strNextPos(str, idx2, chEndBrace, chEndBracket);
	////		int idx4 = (idx2 == -1) ? idx3 : (idx3 == -1) ? idx2 : (idx2 < idx3) ? idx2 : idx3;
	////
	////		// which occurs first?  . or []/()?
	////		int pos = (idx1 == -1) ? idx4 : (idx4 == -1) ? idx1 : (idx1 < idx4) ? idx1 : idx4;
	////
	////		if (pos == -1)
	////			return null;
	////		else
	////			return str.substring(pos + 1);
	////	}
}
