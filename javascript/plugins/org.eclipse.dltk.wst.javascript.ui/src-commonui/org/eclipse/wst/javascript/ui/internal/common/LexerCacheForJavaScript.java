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
package org.eclipse.wst.javascript.ui.internal.common;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.Vector;

import org.eclipse.wst.javascript.core.internal.jsparser.lexer.ILexer;
import org.eclipse.wst.javascript.core.internal.jsparser.lexer.LexerException;
import org.eclipse.wst.javascript.core.internal.jsparser.node.EOF;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TBlank;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TIdentifier;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;
import org.eclipse.wst.javascript.core.internal.langlexer.ITokenCache;
import org.eclipse.wst.javascript.core.internal.langlexer.LexerFactory;

/**
 * This is a common class for parsing (actually lexical analyzing) a node.
 * This class also caches results from parses and expects to be told about
 * modifications to the nodes that might affect the validity of the cache.
 *
 * Creation date: (7/6/2001 5:41:19 PM)
 * @author: Jason Crawford n David Williams
 */
public class LexerCacheForJavaScript implements ITokenCache {

	private static Hashtable htCaches = new Hashtable();
	private String strOldNodeValue = null;
	private String strOldCleanedNodeValue = null;
	private boolean lastCachedTokenBroken = false;
	private static java.util.HashSet hsReservedKeywords = new java.util.HashSet();

	public static final int CHANGED_NONE = -1;
	public static final int CHANGED_ALL = -2;
	public static final int CHANGED_ALL_FORWARD = -3;

	int lenFuncTreeUnchangedAtFront = 987654321;
	int lenFuncTreeUnchangedAtBack = 987654321;
	int lenBufForOldFNV = 0;
	Vector vecOldFNV = null;

	static {
		String[] keywords = new String[]{"continue", //$NON-NLS-1$
					"return", //$NON-NLS-1$
					"break", //$NON-NLS-1$
					"throw", //$NON-NLS-1$
					"else", //$NON-NLS-1$
					"new", //$NON-NLS-1$
					"var", //$NON-NLS-1$
					"case", //$NON-NLS-1$
					"finally", //$NON-NLS-1$
					"void", //$NON-NLS-1$
					"catch", //$NON-NLS-1$
					"for", //$NON-NLS-1$
					"switch", //$NON-NLS-1$
					"while", //$NON-NLS-1$
					"function", //$NON-NLS-1$
					"this", //$NON-NLS-1$
					"with", //$NON-NLS-1$
					"default", //$NON-NLS-1$
					"if", //$NON-NLS-1$
					"delete", //$NON-NLS-1$
					"in", //$NON-NLS-1$
					"try", //$NON-NLS-1$
					"do", //$NON-NLS-1$
					"instanceof", //$NON-NLS-1$
					"typeof", //$NON-NLS-1$
					"null", //$NON-NLS-1$
					"true", //$NON-NLS-1$
					"false", //$NON-NLS-1$
					"{", //$NON-NLS-1$
					"}", //$NON-NLS-1$
					")", //$NON-NLS-1$
					"(", //$NON-NLS-1$
					"."//$NON-NLS-1$
		};
		for (int i = 0; i < keywords.length; i++) {
			hsReservedKeywords.add(keywords[i]);
		}
	}
	private final java.util.TreeSet tsTokCache = new java.util.TreeSet(new java.util.Comparator() {
		public boolean equals(Object obj) {
			return (this == obj);
		}

		public int compare(Object obj1, Object obj2) {
			int so1 = ((Token) obj1).getLPOffset();
			int so2 = ((Token) obj2).getLPOffset();
			if (so1 == so2) {
				return 0;
			}
			return (so1 < so2) ? -1 : 1;
		}
	});

	/**
	 * NodeParserForJavaScript constructor comment.
	 */
	private LexerCacheForJavaScript() {
		super();
	}

	public void add(Token tk) {
		tsTokCache.add(tk);
	}

	static final String[] javakeykeywords = {"{", "}", "function", "var"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	public String[] getKeyKeywordArray() {
		return javakeykeywords;
	}

	public static LexerCacheForJavaScript getCache(Object key, String strInit) {
		// todo: look into using java.util.SoftReference
		Object val = htCaches.get(key);
		if (val == null) {
			// todo: technically we should sync this to make it thread-safe
			LexerCacheForJavaScript lc = new LexerCacheForJavaScript();
			lc.notifyChange(strInit, 0, 0, 0);//$NON-NLS-1$
			htCaches.put(key, lc);
			return lc;
		}
		else {
			return (LexerCacheForJavaScript) val;
		}
	}

	public ILexer getParser(int offStart, boolean startHere) {
		//System.out.println( "LCFJS:getParser( offStart=="+offStart+", startHere="+startHere );
		String txt = strOldCleanedNodeValue; // region.getText();
		PushbackReader pr = new PushbackReader(new StringReader(txt), 600);
		int offRegion = 0; // region.getStartOffset();
		int offRequestSOff = offStart - offRegion;
		String strJavaScript = "javascript"; //$NON-NLS-1$
		if (startHere) {
			// todo: not sure we should trust the caller.  We don't want gaps.  At least the function finder doesn't. Evaluate a design change here.
			//return new CachingJSLexer(this, pr, offRequestSOff);
			return LexerFactory.createPeekable(pr, offRequestSOff, strJavaScript, this);
		}
		Token tokSplitter = new TBlank("", 0, 0, offRequestSOff + 1, false); //+1 so that it picks up the one at our offset also//$NON-NLS-1$
		java.util.SortedSet ss = tsTokCache.headSet(tokSplitter);
		Token tslast = null;
		try {
			tslast = (Token) ss.last();
		}
		catch (Exception exc) {
			// nothing cached start at beginning of region
			//return new CachingJSLexer(this, pr);
			return LexerFactory.createPeekable(pr, /*offset:*/0, strJavaScript, this);
		}
		//boolean isBroke = false;
		if ((lastCachedTokenBroken) && (tsTokCache.last() == tslast)) {
			// start at the beginning of that broken token
			//return new CachingJSLexer(this, pr, tslast.getLPOffset());
			return LexerFactory.createPeekable(pr, /*offset:*/tslast.getLPOffset(), strJavaScript, this);
		}
		if (tslast.getEndLPOffset() >= offRequestSOff) {
			// todo: see if > is better than >= above.
			// that found token covers the first character to color.  Have the lexer return it first and continue
			//return new CachingJSLexer(this, pr, tslast);
			return LexerFactory.createPeekable(pr, /*offset:*/tslast, strJavaScript, this);
		}
		if (ss.size() == tsTokCache.size()) {
			// the cache probably didn't reach our starting point so we don't 
			// know anything about the starting point so should start after the last
			// point we cached
			//return new CachingJSLexer(this, pr, tslast.getEndLPOffset());
			return LexerFactory.createPeekable(pr, /*offset:*/tslast.getEndLPOffset(), strJavaScript, this);
		}
		// We are in the middle of a region that has been parsed. And
		// between (not at any) parsed tokens.  Let's just
		// start on the right side of the last parsed token that preceeds this
		// start point.
		// todo: in the future it might be a good idea to cache the token at the point that we were
		//            requested to start at.  It might save a bit of parsing. 
		//return new CachingJSLexer(this, pr, tslast.getEndLPOffset());
		return LexerFactory.createPeekable(pr, /*offset:*/tslast.getEndLPOffset(), strJavaScript, this);
	}

	/**
	 do not call this method unless you're in contact with the author.  It is likely
	 to change since it's semantics don't match those of potential callers.  A more
	 efficient version is also possible.
	 */
	public TextRange notifyChange(String strNew, int posChange, int lenOld, int lenNew) {
		//System.out.println( "\nLCFJS:notifyChange strNew, "+posChange+", "+lenOld+" "+lenNew );
		if (lenNew < 0)
			throw new RuntimeException(JSCommonUIMessages.internal_error_1); //$NON-NLS-1$
		//if (lenOld<0) throw new RuntimeException( "internal error: can't pass a negative lenNew to LCFJS.notifyChange" );
		if (strNew == null)
			throw new RuntimeException(JSCommonUIMessages.internal_error_2); //$NON-NLS-1$
		if (strNew == strOldNodeValue)
			return new TextRange(0, CHANGED_NONE); // no change
		String nv = strNew;

		int idxLeftMostParseChange = 0;
		try {
			if (strOldNodeValue == null) {
				tsTokCache.clear();
				lenFuncTreeUnchangedAtFront = lenFuncTreeUnchangedAtBack = strNew.length() + 1;
				this.strOldNodeValue = nv;
				this.strOldCleanedNodeValue = cleanString(nv);
				return new TextRange(0, CHANGED_ALL); // all change
			}
			if (strNew.length() != (strOldCleanedNodeValue.length() + lenNew - lenOld)) {
				// something is amiss, we might have already found
				//     out about the change.  For now, be cautious
				//     and just figure out what the situation is.
				//     (11/15/02) We already know about one such situation
				//     where a modified file is renamed.  Without this 
				//     reevaluation, we get in an infinite loop later in
				//     this method.  In that case we construct a new
				//     cacheentry, initialize it as a side effect of 
				//     something else, and then a document change event
				//     comes in that is supposed to tell us the content
				//     of the file... which we've already figured out.
				posChange = -1;
				// todo: because the caller wants to know what changed
				//     since the last time and we are invariably prone
				//     to say nothing changed because we already know 
				//     about the change, we might need to change some
				//     code later in this method to conservatively say
				//     that everything changed.  This is certainly 
				//     something to be avoided though because it 
				//     undermines our attempts to optimize repaints.
				//     What we really need to do is avoid being told
				//     twice about changes.
			}
			int idxE1 = strOldNodeValue.length(), idxE2 = nv.length();
			int idx = 0;
			if (posChange < 0) {
				lenOld = lenNew = -1; // recalc
				// the caller wants us to figure out if there was a change.
				// note: having us do this is probably the most reliable way
				//       of doing this since multiple entities can share this object
				//       and won't know what the other entities told it.  In practice
				//       this probably isn't a problem because they probably all
				//       update this at the same time with the same values.   This
				//       being the case, I prefer that the code that actually modified
				//       the string tell us what changed since they have the most
				//       insight into what happened and don't have to start from
				//       scratch to figure it out.
				int imax = (nv.length() < strOldNodeValue.length()) ? nv.length() : strOldNodeValue.length();
				while (idx < imax) {
					if (nv.charAt(idx) != strOldNodeValue.charAt(idx))
						break;
					idx++;
				}
				posChange = idx;
			}
			else {
				idx = posChange;
			}
			if (lenOld < 0) {
				// we are supposed to calculate it
				if (idx == nv.length()) {
					// strings are equal or end was deleted to form nv.
					idxE1 = strOldNodeValue.length();
					idxE2 = idx;
					lenNew = 0;
					lenOld = idxE1 - idx;
				}
				else if (idx == strOldNodeValue.length()) {
					// strings nv is formed by adding something to end of old value
					idxE2 = nv.length();
					idxE1 = idx;
					lenOld = 0;
					lenNew = idxE2 - idx;
				}
				else {
					// we didn't reach the end so there is at least one character at the end of each string to compare
					idxE1 = strOldNodeValue.length() - 1;
					idxE2 = nv.length() - 1;
					while ((idxE1 >= idx) && (idxE2 >= idx)) {
						if (nv.charAt(idxE2) != strOldNodeValue.charAt(idxE1))
							break;
						idxE2--;
						idxE1--;
					}
					idxE2++;
					idxE1++;
					lenOld = idxE1 - idx;
					lenNew = idxE2 - idx;
				}
			}
			else {
				idxE1 = posChange + lenOld;
				idxE2 = posChange + lenNew;
			}
			// note: we now know that the number of characters at the
			//    end of the buffer that match.


			{
				// calculate a bit of cache maintainance info about function/var positions
				int idxT3 = strNew.length() - (posChange + lenNew);
				if (posChange < lenFuncTreeUnchangedAtFront)
					lenFuncTreeUnchangedAtFront = posChange;
				if (idxT3 < lenFuncTreeUnchangedAtBack)
					lenFuncTreeUnchangedAtBack = idxT3;
			}

			Token tkFirstAfter = null;
			Token tkLastBefore = null;
			if (!tsTokCache.isEmpty()) {
				Token tk1Splitter = new TBlank("", 0, 0, idx, false); //+1 so that it picks up the one at our offset also//$NON-NLS-1$
				java.util.SortedSet ss1 = tsTokCache.headSet(tk1Splitter);
				Token tk2Splitter = new TBlank("", 0, 0, idxE1 + 1, false); //+1 so that it picks up the one at our offset also//$NON-NLS-1$
				java.util.SortedSet ss3 = tsTokCache.tailSet(tk2Splitter);
				SortedSet ss2 = tsTokCache.subSet(tk1Splitter, tk2Splitter);
				ss2.clear();
				if (!ss1.isEmpty()) {
					tkLastBefore = (Token) ss1.last();
					if (tkLastBefore.getEndLPOffset() >= idx)
						tsTokCache.remove(tkLastBefore);
				}
				if (!ss3.isEmpty()) {
					tkFirstAfter = (Token) ss3.first();
				}

				{ // todo: this code can can be moved down further once we know 
					// ...that we won't throw away these tokens.
					Iterator iter = ss3.iterator();
					int diff = idxE2 - idxE1;
					while (iter.hasNext()) {
						Token tk = (Token) iter.next();
						tk.setLPOffset(tk.getLPOffset() + diff);
					}
				}
			}

			this.strOldNodeValue = nv;
			this.strOldCleanedNodeValue = cleanString(nv);
			// This is a bit dubious.  I've just set these two 
			//    values so that I can do a getParser() below, but
			//    it's only because I have intimate knowledge of 
			//    this implementation that I can know what to 
			//    manipulate to get the right thing to happen.

			if (tkFirstAfter != null) {
				ILexer lexr = null;
				if (tkLastBefore == null) {
					idxLeftMostParseChange = 0;
					//lexr = getParser( idx, false);
				}
				else {
					if (tkLastBefore.getEndLPOffset() < idx) {
						idxLeftMostParseChange = tkLastBefore.getEndLPOffset();
						//lexr = getParser( idx, false);
					}
					else {
						// the token is potentially bad so restart at the beginning of it.
						idxLeftMostParseChange = tkLastBefore.getLPOffset();
						//lexr = getParser( tkLastBefore.getLPOffset(), true);
					}
				}
				lexr = getParser(idxLeftMostParseChange, true);

				int offTkFirstAfter = tkFirstAfter.getLPOffset();
				Token tk3 = null;
				while ((tk3 = lexr.next()) != null) {
					if (tk3.getLPOffset() == offTkFirstAfter) {
						// we filled in the gap and now
						return new TextRange(idxLeftMostParseChange, tk3.getLPOffset());
					}
					else if (tk3.getLPOffset() > offTkFirstAfter) {
						// we parsed through the gap and apparently there is
						//   some signficant change there that means our tokens
						//   don't line up now.
						break;
					}
				}
			}


			// note: we might need special code if changing of one character might
			//    affect earlier parsing for example changing  "+ " to "++" changes
			//    the previous token too.  For good measure let's move it back a few
			//    characters.  If we discover we need to move back more than that,
			//    we can code that up after we learn about it.
			idx -= 2;

			// note: idx now is the index to mark the earliest spot where there 
			//    might be a visible character change or token change.  In the
			//    case of a token, it doesn't necessarily have to be at the
			//    beginning of a token.

			if (tsTokCache.size() > 0) {
				int dsize = tsTokCache.size();
				Token tk = (Token) tsTokCache.first();
				if (tk.getLPOffset() > idx) {
					tsTokCache.clear();
					lastCachedTokenBroken = false;
				}
				else {
					while ((dsize--) > 0) {
						tk = (Token) tsTokCache.last();
						if (tk.getLPOffset() > idx) {
							tsTokCache.remove(tk);
							lastCachedTokenBroken = false;
						}
						else {
							if (tk.getEndLPOffset() > idx) {
								lastCachedTokenBroken = true;
							}
							break;
						}
					}
				}
			}
		}
		catch (Exception exc) {
			//exc.printStackTrace();
			Logger.logException("Exception in notifyChanged() on LexerCacheForJavaScript", exc); //$NON-NLS-1$
		}
		return new TextRange(idxLeftMostParseChange, CHANGED_ALL_FORWARD); // all forward
	}


	static private String cleanString(String nv) {
		// let's convert <% %> blocks to something benign
		StringBuffer sbCleaned = new StringBuffer();
		int idx = 0;
		int idx1;
		while ((idx1 = nv.indexOf("<%", idx)) >= 0) {//$NON-NLS-1$
			// todo: test if all this code works if there are \r\n in the expression

			// 240500 - fix infinite loop caused by incorrect index
			//int idx2 = nv.indexOf( "%>", idx+2 );//$NON-NLS-1$
			int idx2 = nv.indexOf("%>", idx1 + 2);//$NON-NLS-1$

			if (idx2 < 0)
				break;
			idx2 += 2;
			sbCleaned.append(nv.substring(idx, idx1));
			int delta = idx2 - idx1;
			while (delta-- > 0)
				sbCleaned.append('a');
			idx = idx2;
		}
		sbCleaned.append(nv.substring(idx));
		return sbCleaned.toString();
	}


	public void parseForFunctionNVarNames(HashSet hsNames) {
		List v0 = parseForFunctionsNVariables();
		for (int i = 0; i < v0.size(); i++) {
			JSContentElementImpl coe = (JSContentElementImpl) v0.get(i);
			if (coe.getType() == JSContentElementConstants.JS_FUNCTION) {
				hsNames.add(coe.getName() + "()"); //$NON-NLS-1$
			}
			else if (coe.getType() == JSContentElementConstants.JS_VARIABLE) {
				hsNames.add(coe.getName());
			}
		}
	}

	/**
	 This method will quickly try to get the name of all the functions defined in the
	 block of code it is responsible for.  At some point we may alter this code to
	 heuristically not devote much time to finding this.  That might mean that subsequent
	 calls to this routine will reveal more or fewer functions than previous calls, so 
	 don't call this code if you want a clearly defined and accurate result.  
	 */
	public Vector parseForFunctionsNVariables() {
		final int levelMax = 3; // 0== top level only, 
		if (strOldNodeValue == null)
			return null;

		if (vecOldFNV == null) {
			vecOldFNV = new Vector();
			lenFuncTreeUnchangedAtBack = 0;
			lenFuncTreeUnchangedAtFront = 0;
		}
		
		if ((lenFuncTreeUnchangedAtFront + lenFuncTreeUnchangedAtBack) > strOldNodeValue.length()) {
			return vecOldFNV;
			// note: we are returning our cached value.  I hope the callers don't modify that vector;
			//    perhaps we should return a clone of it instead?
		}

		Vector retval = new Vector();
		try {

			{
				int offStart = strOldNodeValue.length();
//				int length = 0;
				//if (offStart>-2) { return;} // debug performance, see what happens when our code does nothing
//				int offMax = offStart + length;
				int offStart2 = offStart;
				ILexer lexer2 = getParser(offStart2, /*startHere:*/false);
				Token tkThis = lexer2.next();

				// iterate through tokens until we've lexed everything.  A side effect
				//       of this is that the cache will have a record of all
				//       the instances of "function" and "var".
				//
				while (true /*tkThis.getLPOffset() < (offsMax + 100)*/) {
					tkThis = lexer2.next();
					if (tkThis instanceof EOF)
						break;
				}
				// todo: consider halting this early for large files.  You might miss a function or two, but for the current purposes this shouldn't matter.
			}

			JSContentElementImpl coeOld = null;
			int idxOldBeg = -500;
			int idxRestartAt = 0;
			Enumeration elements = null;
			{
				elements = vecOldFNV.elements();
				if (elements.hasMoreElements()) {
					do {
						coeOld = (JSContentElementImpl) elements.nextElement();
						//idxOldBeg = (coeOld.getJavaDocOffset()<0) ? coeOld.getOffset() : coeOld.getJavaDocOffset();
						idxOldBeg = coeOld.getOffset();
						if (idxOldBeg <= lenFuncTreeUnchangedAtFront) {
							if ((coeOld.getOffset() + coeOld.getLength()) < lenFuncTreeUnchangedAtFront) {
								retval.add(coeOld);
								idxRestartAt = coeOld.getOffset() + coeOld.getLength();
							}
							else {
								idxRestartAt = coeOld.getOffset();
								break;
							}
						}
						else {
							break;
						}
					}
					while (elements.hasMoreElements());
				}
				if (coeOld != null) {
					// skip any cached info inside the gap.
					while ((lenBufForOldFNV - idxOldBeg) >= lenFuncTreeUnchangedAtBack) {
						if (!elements.hasMoreElements()) {
							coeOld = null;
							break;
						}
						coeOld = (JSContentElementImpl) elements.nextElement();
						//idxOldBeg = (coeOld.getJavaDocOffset()<0) ? coeOld.getOffset() : coeOld.getJavaDocOffset();
						idxOldBeg = coeOld.getOffset();
					}
				}
			}
			// coeOld is the top level record in the cache that is beyond the
			//   last change in the file.  Enum is set up to continue past it.
			//   If it's null, there were no more records beyond the mod poitn.
			// idxRestartAt is the point in the file where the structure is still
			//   correct.  It will either be right at the beginning of a top level
			//   element or right after one.

			// we clone the following before deriving an iterator so that the parser's modification
			//     of the cache doesn't throw off the iterator.
			Iterator iter = ((java.util.TreeSet) (tsTokCache.clone())).iterator();
			Token tkFunc = null;
			Token tkLastJavaDoc = null;
			int idxEndLastJavaDoc = -500;
			// todo: this loop can be made faster using the tail functionality.  But it's rarely slow anyway.
			while (iter.hasNext()) {
				// skipping past tokens in the gap.
				Token tk0Func = (Token) iter.next();
				if (tk0Func.getText().startsWith("/**")) { //$NON-NLS-1$
					tkLastJavaDoc = tk0Func;
					idxEndLastJavaDoc = tk0Func.getEndLPOffset();
				}
				if (tk0Func.getEndLPOffset() > idxRestartAt) {
					tkFunc = tk0Func;
					break;
				}
			}
			// tkFunc is the first cached token after the last valid structured element.  It will tend to be a var or function keyword, but it can be just about anything
			JSContentElementImpl coeLastFunction = null;
			Hashtable coeLastFunctionBraceLevels = new Hashtable();
			// todo: add code to pick up javadoc comments too.
			int iBraceLevel = 0;
			while (tkFunc != null) {
				String tkText = tkFunc.getText();
				if ((iBraceLevel <= levelMax) && (tkText.equals("function") //$NON-NLS-1$
							|| tkText.equals("var"))) {//$NON-NLS-1$
					if ((iBraceLevel == 0) && (coeOld != null) && ((lenBufForOldFNV - coeOld.getOffset()) == (strOldNodeValue.length() - tkFunc.getLPOffset()))) {
						int shift = strOldNodeValue.length() - lenBufForOldFNV;
						// reassign the javadoc at the front
						if (idxEndLastJavaDoc > (tkFunc.getLPOffset() - 8)) {
							// actually set the offset wrong because we'll correct it below
							coeOld.setJavaDocOffset(tkLastJavaDoc.getLPOffset() - shift);
							coeOld.setJavaDocString(tkLastJavaDoc.getText());
						}
						else {
							coeOld.setJavaDocOffset(-500);
							coeOld.setJavaDocString(null);
						}
						while (true) {
							// work goes here
							coeOld.setOffset(coeOld.getOffset() + shift);
							coeOld.setNameOffset(coeOld.getNameOffset() + shift);
							coeOld.setJavaDocOffset(coeOld.getJavaDocOffset() + shift);
							retval.add(coeOld);
							shiftChildren(coeOld, shift);
							if (!elements.hasMoreElements())
								break;
							coeOld = (JSContentElementImpl) elements.nextElement();
						}
						break;
					}
					// the cache contains a sparse set of tokens.  We create a new parser her to access a complete list of tokens near where we've found the VAR or FUNCTION keyword.
					ILexer lexer2 = getParser(tkFunc.getEndLPOffset(), true);
					while (true) {
						Token tkThis = lexer2.next();
						String strTkThis = tkThis.getText();
						if (tkThis instanceof TBlank) {
							// continue parsing for the function name
						}
						else if ((tkThis instanceof TIdentifier) && (!hsReservedKeywords.contains(strTkThis))) {
							if (true) {
								JSContentElementImpl coe = new JSContentElementImpl();
								if (idxEndLastJavaDoc > (tkFunc.getLPOffset() - 8)) {
									coe.setJavaDocOffset(tkLastJavaDoc.getLPOffset());
									coe.setJavaDocString(tkLastJavaDoc.getText());
								}
								coe.setName(strTkThis);
								coe.setNameOffset(tkThis.getLPOffset());
								coe.setOffset(tkFunc.getLPOffset());
								if (tkText.equals("function")) { //$NON-NLS-1$
									coe.setLength(strOldNodeValue.length() + 1 - tkFunc.getLPOffset()); // extends to the eof until we learn otherwise
									coe.setType(JSContentElementConstants.JS_FUNCTION);
									if (coeLastFunction != null) {
										coe.setParent(coeLastFunction);
										coeLastFunction.addChild(coe);
									}
									else {
										retval.add(coe);
									}
									coeLastFunction = coe;
								}
								else {
									coe.setLength(tkThis.getEndLPOffset() - tkFunc.getLPOffset());
									coe.setType(JSContentElementConstants.JS_VARIABLE);
									if (coeLastFunction != null) {
										coe.setParent(coeLastFunction);
										coeLastFunction.addChild(coe);
									}
									else {
										retval.add(coe);
									}
								}
							}
							break;
						}
						else {
							break;
						}
					}
				}
				else if ((iBraceLevel <= levelMax) && tkText.startsWith("/**")) { //$NON-NLS-1$
					tkLastJavaDoc = tkFunc;
					idxEndLastJavaDoc = tkFunc.getEndLPOffset();
				}
				else if (tkText.equals("{")) {//$NON-NLS-1$
					iBraceLevel++;

					if (coeLastFunction != null && !coeLastFunctionBraceLevels.containsKey(coeLastFunction))
						coeLastFunctionBraceLevels.put(coeLastFunction, new Integer(iBraceLevel));
				}
				else if (tkText.equals("}")) {//$NON-NLS-1$
					// probably the end of a function.
					if (coeLastFunction != null && coeLastFunctionBraceLevels.containsKey(coeLastFunction) && ((Integer) coeLastFunctionBraceLevels.get(coeLastFunction)).intValue() == iBraceLevel) {
						// assume that's the function the braces belong to
						coeLastFunctionBraceLevels.remove(coeLastFunction);
						coeLastFunction.setLength(1 + tkFunc.getLPOffset() - coeLastFunction.getOffset());
						coeLastFunction = (JSContentElementImpl) coeLastFunction.getParent();
					}

					iBraceLevel--;
				}
				if (!iter.hasNext())
					break;
				tkFunc = (Token) iter.next();
			} // endwhile

			vecOldFNV = retval;
			lenBufForOldFNV = strOldNodeValue.length();
			lenFuncTreeUnchangedAtBack = lenFuncTreeUnchangedAtFront = 987654321;
		}
		catch (IOException exc) {
			Logger.logException(exc);
		}
		catch (LexerException exc) {
			// some difficulty parsing.  It's not clear what we should do in this case generically.  It's hard enough to decide in specific cases like unterminated quotations.
			Logger.logException(exc);
		}
		catch (Exception exc) {
			//exc.printStackTrace();
			Logger.logException(exc);
		}
		return retval;
	}

	public static void shiftChildren(JSContentElementImpl coeIn, int shift) {
		if (coeIn.hasChildren2()) {
			Enumeration elements = coeIn.getChildren2().elements();
			while (elements.hasMoreElements()) {
				JSContentElementImpl coe = (JSContentElementImpl) elements.nextElement();
				coe.setOffset(coe.getOffset() + shift);
				coe.setNameOffset(coe.getNameOffset() + shift);
				shiftChildren(coe, shift);
			}
		}
	}

	public void remove(Token tk) {
		tsTokCache.remove(tk);
	}

	public static void release(Object key) {
		if (key != null) {
			htCaches.remove(key);
		}
	}

	public static void releaseAll() {

		htCaches.clear();

	}

}
