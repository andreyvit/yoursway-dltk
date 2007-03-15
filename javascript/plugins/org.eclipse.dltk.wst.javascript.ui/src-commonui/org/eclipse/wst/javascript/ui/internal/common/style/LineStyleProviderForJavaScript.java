/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common.style;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.wst.javascript.core.internal.jsparser.lexer.ILexer;
import org.eclipse.wst.javascript.core.internal.jsparser.lexer.LexerException;
import org.eclipse.wst.javascript.core.internal.jsparser.node.EOF;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TBlank;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TCommenttok;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TErrorChar;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TStringLiteral;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TUnterminatedComment;
import org.eclipse.wst.javascript.core.internal.jsparser.node.TUnterminatedStringLiteral;
import org.eclipse.wst.javascript.core.internal.jsparser.node.Token;
import org.eclipse.wst.javascript.ui.internal.common.Debug;
import org.eclipse.wst.javascript.ui.internal.common.JavaScriptColorPreferences;
import org.eclipse.wst.javascript.ui.internal.common.LexerCacheForJavaScript;
import org.eclipse.wst.javascript.ui.internal.common.Logger;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.ui.internal.provisional.style.Highlighter;
import org.eclipse.wst.sse.ui.internal.provisional.style.LineStyleProvider;

public class LineStyleProviderForJavaScript implements LineStyleProvider, IDocumentListener {
	private static java.util.HashSet boldKeywords = new java.util.HashSet();
	public static String[] keywords = null; // global public to share with the

	private java.util.Hashtable htForNode2 = new java.util.Hashtable();
	private IStructuredDocument fDocument;
	private Highlighter fHighlighter;
	private PropertyChangeListener fPreferenceListener = new PropertyChangeListener();
	private boolean fIsInitialized = false;

	class nodedata {
		protected LexerCacheForJavaScript pcParseCache = null;
		protected ArrayList cachedStyles = new ArrayList(100);
		protected int offCachedStylesRegion = 0;
		protected String strOldNodeValue = null;
		// protected org.w3c.dom.Node targetNode;

	}

	private class PropertyChangeListener implements IPropertyChangeListener {
		public void propertyChange(PropertyChangeEvent event) {
			handlePropertyChange(event);
		}
	}

	static {
		keywords = new String[]{"continue", //$NON-NLS-1$
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
					"}" //$NON-NLS-1$
		};
		for (int i = 0; i < keywords.length; i++) {
			boldKeywords.add(keywords[i]);
		}
		 boldKeywords.add(")");
		 boldKeywords.add("(");
		 boldKeywords.add(".");
	}

	// todo: typing an unterminated quotation marks and removing them is slow
	// todo: add bold to keywords after bold spacing is fixed to match unbold
	// todo: make sure that the line line drawing bug gets fixed. The one that
	// is sensitive to a StyleRange ending at the beginning of the line. It
	// does bite us occasionally.
	// todo: it would be nice to have fixed pitch fonts in code and
	// proportional one's in comments
	// todo: the script and comment areas would look nicer if the background
	// extended infinitely to the right.
	// todo: it would be nice to spot syntax errors and color them
	// differently.
	// todo: nice to trigger on space and "(".
	// todo: David has expressed a desire to use something other than SableCC
	// for parsing since we're the only one's using SableCC. OTOH, we don't
	// like recursive descent parsers. Then again, we are only lex'ing at this
	// point.
	// todo: don't keep strOldNodeValue. Use the value within the pcParseCache
	public LineStyleProviderForJavaScript() {
		super();
	}

	void handlePropertyChange(PropertyChangeEvent event) {
		// clear out all cached styles so they can be reconfigured
		java.util.Enumeration values = htForNode2.elements();
		while (values.hasMoreElements()) {
			nodedata nd = (nodedata) values.nextElement();
			nd.cachedStyles.clear();
		}

		// force a full update of the text viewer
		fHighlighter.refreshDisplay();
	}

	public void init(IStructuredDocument structuredDocument, Highlighter highlighter) {
		// TODO need a better place/way to add document listeners, instead of
		// each init!
		IStructuredDocument currentDocument = fDocument;
		if (currentDocument != null && currentDocument != structuredDocument) {
			currentDocument.removeDocumentListener(this);
		}
		fDocument = structuredDocument;
		fDocument.addDocumentListener(this);

		fHighlighter = highlighter;

		if (fIsInitialized)
			return;
		JavaScriptColorPreferences.addPropertyChangeListener(fPreferenceListener);
		fIsInitialized = true;
	}

	private nodedata ndDoc = null;

	private nodedata getNodeData(IStructuredDocumentRegion fnode) {
		if (fnode == null) {
			if (ndDoc == null) {
				ndDoc = new nodedata();
			}
			return ndDoc;
		}
		nodedata nd = (nodedata) htForNode2.get(fnode);
		if (nd == null) {
			nd = new nodedata();
			htForNode2.put(fnode, nd);
			// todo: jlc030328: bug: we probably have a memory leak if an
			// fnode goes away in the dom, but we still have a reference to it
			// in this hashtable.
		}
		return nd;
	}

	protected ILexer getParseStartPoint(IStructuredDocumentRegion fnode, ITextRegion region, int offStart, boolean startHere) {
		int iNodeOffset = 0;
		// IStructuredDocumentRegion fnode = null;
		// // if (targetNode instanceof TextImpl) {
		// // TextImpl ti = (TextImpl)targetNode;
		// if (getDocument() instanceof IStructuredDocument) {
		// IStructuredDocument structuredDocument = getDocument();
		// fnode = structuredDocument.getNodeAtCharacterOffset(offStart);
		// // fnode = ti.getFirstStructuredDocumentRegion();
		if (fnode != null) {
			// System.out.println( "old/new is "+iNodeOffset+"
			// "+fnode.getStartOffset() );
			iNodeOffset = fnode.getStartOffset();
		}
		// }
		nodedata nd = getNodeData(fnode);
		if (nd.pcParseCache == null) {
			// todo: we don't like this. We really want to use "this" as our
			// key. But we also really want to use something that we can use
			// with the syntax completion code... but we have no data
			// structures in common until a modification is done.
			// pcParseCache = LexerCacheForJavaScript.getCache(targetNode,
			// strOldNodeValue);
			if (fnode != null) {
				nd.strOldNodeValue = fnode.getText();
				// pcParseCache = LexerCacheForJavaScript.getCache(targetNode,
				// strOldNodeValue);
				nd.pcParseCache = LexerCacheForJavaScript.getCache(fnode, nd.strOldNodeValue);
				nd.pcParseCache.notifyChange(nd.strOldNodeValue, -1, 0, 0);
			}
			else {
				// pcParseCache = LexerCacheForJavaScript.getCache(targetNode,
				// strOldNodeValue==null ? "":strOldNodeValue ); //$NON-NLS-1$
				nd.pcParseCache = LexerCacheForJavaScript.getCache(fnode, nd.strOldNodeValue == null ? "" : nd.strOldNodeValue); //$NON-NLS-1$
			}
		}
		return nd.pcParseCache.getParser(offStart - iNodeOffset, startHere);
	}

	public void documentAboutToBeChanged(DocumentEvent event) {
		// do nothing
	}

	public void documentChanged(DocumentEvent event) {

		Enumeration keys = htForNode2.keys();
		while (keys.hasMoreElements()) {
			IStructuredDocumentRegion fnode = (IStructuredDocumentRegion) keys.nextElement();
			int idxNodeStart = fnode.getStart();
			int idxNodeEnd = fnode.getEnd();
			int idxChgEnd = event.fOffset + event.fLength;
			if (event.fOffset <= idxNodeStart && idxChgEnd >= idxNodeEnd) {
				// fnode deleted
				LexerCacheForJavaScript.release(fnode);
				htForNode2.remove(fnode);
			}
			else if (event.fOffset < idxNodeEnd && idxChgEnd > idxNodeStart) {
				nodeChanged(fnode, event.fOffset - idxNodeStart, event.fLength, event.fText);
			}

		}

	}

	public void nodeChanged(IStructuredDocumentRegion fnode, int idxInsertPos, int iReplacedLen, String strInserted) {
		nodedata nd = getNodeData(fnode);
		try {
			if (fnode != null) {
				LexerCacheForJavaScript lx = LexerCacheForJavaScript.getCache(fnode, ""); //$NON-NLS-1$
				if (nd.pcParseCache == null) {
					// do nothing
				}
				else {
					if (nd.pcParseCache != lx) {
						Logger.log(Logger.WARNING_DEBUG, "Unreliable way to find the node, and memory leak (hopefully small) will result, unless only showing part of editor (then everything is fine)"); //$NON-NLS-1$

						// Note: As a temporary fix we can put soft references
						// in the LCFJS hash table so that
						// otherwise unused structures are tossed out.
					}
				}
				nd.pcParseCache = lx;
			}
			if (strInserted != null) {
				String nv = null;
				nv = nd.strOldNodeValue.substring(0, idxInsertPos) + strInserted + nd.strOldNodeValue.substring(idxInsertPos + iReplacedLen);
				if (nv != null) {
					if (nd.strOldNodeValue == null) {
						nd.pcParseCache.notifyChange(nv, 0, 0, nv.length());
						nd.cachedStyles.clear();
					}
					else {
						int idx = 0;
						// todo: ask that david give me more info so that I
						// don't have to search for the change.
						if (false) {
							int imax = (nv.length() < nd.strOldNodeValue.length()) ? nv.length() : nd.strOldNodeValue.length();
							while (idx < imax) {
								if (nv.charAt(idx) != nd.strOldNodeValue.charAt(idx))
									break;
								idx++;
							}
						}
						else {
							idx = idxInsertPos;
						}

						// note: we might need special code if changing of one
						// character might
						// affect earlier parsing for example changing "+ " to
						// "++" changes
						// the previous token too. For good measure let's move
						// it back a few
						// characters. If we discover we need to move back
						// more than that,
						// we can code that up after we learn about it.
						idx -= 2;
						if (idx < 0)
							idx = 0;

						// note: idx now is the index to mark the earliest
						// spot where there
						// might be a visible character change or token
						// change. In the
						// case of a token, it doesn't necessarily have to be
						// at the
						// beginning of a token. The reason is for example
						// that the token
						// is likely to be broken and possibly even split at
						// the change point.

						nd.pcParseCache.notifyChange(nv, idx, nd.strOldNodeValue.length() - idx, nv.length() - idx);
						if (nd.cachedStyles.size() > 0) {
							int csize = nd.cachedStyles.size();
							int offIdx = idx + nd.offCachedStylesRegion;
							StyleRange srOne = (StyleRange) (nd.cachedStyles.get(0));
							if (srOne.start > offIdx) {
								nd.cachedStyles.clear();
							}
							else {
								while ((csize--) > 0) {
									srOne = (StyleRange) nd.cachedStyles.get(csize);
									if ((srOne.start + srOne.length) >= offIdx) {
										nd.cachedStyles.remove(srOne);
									}
									else {
										break;
									}
								}
							}
						}
					}
					nd.strOldNodeValue = nv;
				}
			}
		}
		catch (Exception exc) {
			Logger.log(Logger.WARNING_DEBUG, "Exception in notifyChanged() of LineStyleProviderForJavaScript", exc); //$NON-NLS-1$
		}
	}

	/**
	 */
	public boolean prepareRegions(ITypedRegion currentRegion, int offStart, int length, Collection holdResults) {
		IStructuredDocument structuredDocument = fDocument;
		IStructuredDocumentRegion fnode = structuredDocument.getRegionAtCharacterOffset(offStart);

		Vector vecTempResults = new Vector();
		boolean retval;
		int offStart2 = offStart;
		int length2 = length;
		{
			if (offStart2 < currentRegion.getOffset()) {
				int diff = currentRegion.getOffset() - offStart2;
				offStart2 += diff;
				length2 -= diff;
			}
			if (offStart2 + length2 - 1 > getEndOffset(currentRegion)) {
				length2 = getEndOffset(currentRegion) - offStart2 + 1;
			}
			retval = prepareRegions2(fnode, currentRegion, offStart2, length2, vecTempResults);
		}
		// now we'll have to trim the ends off because the
		// caller of this doesn't want it to begin before
		// the requested region or extend beyond.
		// I know for a fact that only the first and
		// last elements might extend outside the region.
		int veclen = vecTempResults.size();
		// if (veclen>0) {
		// StyleRange sr0 = (StyleRange)vecTempResults.elementAt(0);
		// sr0 = returnPrunedStyle(offStart2, length2, sr0);
		// vecTempResults.removeElementAt(0);
		// vecTempResults.insertElementAt(sr0,0);
		// StyleRange srE = (StyleRange)vecTempResults.elementAt(veclen-1);
		// srE = returnPrunedStyle(offStart2, length2, srE);
		// vecTempResults.removeElementAt(veclen-1);
		// vecTempResults.insertElementAt(srE,veclen-1);
		// }
		// holdResults.addAll(vecTempResults);
		for (int i = 0; i < veclen; i++) {
			holdResults.add(returnPrunedStyle(offStart2, length2, (StyleRange) vecTempResults.get(i)));
		}
		return retval;
	}

	/**
	 * @param currentRegion
	 * @return
	 */
	private int getEndOffset(ITypedRegion currentRegion) {

		return currentRegion.getOffset() + currentRegion.getLength() - 1;
	}

	/**
	 */
	public boolean prepareRegions2(IStructuredDocumentRegion fnode, ITypedRegion currentRegion, int offStart, int length, Collection holdResults) {
		if (offStart > getEndOffset(currentRegion))
			return false; // comes after this flatnode
		if (offStart + length <= currentRegion.getOffset())
			return false; // comes before this flatnode

		// if (offStart>-2) { return;} // debug performance, see what happens
		// when our code does nothing
		// holdResults = new Vector(); // debug performance, see how it
		// performs if we do all our work but not pass the result to the
		// caller
		if (Debug.jsDebugSyntaxColoring) {
			int xxif = offStart - currentRegion.getOffset();
			// good things this is only called during debug!
			String tt = fDocument.getText().substring(xxif, xxif + length);
			// System.out.println( "pr: ("+offStart+","+length+") "+tt );
			if (tt.indexOf("xbove ") != -1) { //$NON-NLS-1$
				Logger.trace("jsDebugSyntaxColoring", "tt.indexOf xbove != -1"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (tt.indexOf("s var") != -1) { //$NON-NLS-1$
				Logger.trace("jsDebugSyntaxColoring", "tt.indexOf s var != -1"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		boolean startHere = false;
		int offMax = offStart + length;
		int offStart2 = offStart;
		TextAttribute previousAttr = null;
		// System.out.println( "**" );

		// figure out at what file offset the node starts.
		int offNode = 0; // bad guess, override below
		if (fDocument != null) {
			if (fnode != null) {
				offNode = fnode.getStartOffset();
			}
		}
		nodedata nd = getNodeData(fnode);
		try {
			{
				// cachedStyles.clear(); // debug performance
				// TODO: see about implementing cachedStyles as an array to
				// avoid all this casting to StyleRange
				int cacheSize = nd.cachedStyles.size();
				if (cacheSize > 0) {
					// if ( false /*cacheSize > 0*/ ) { System.out.println(
					// "debug no js style cache" ); } else {
					if (nd.offCachedStylesRegion != offNode) {
						int delta = offNode - nd.offCachedStylesRegion;
						for (int sidx = 0; sidx < cacheSize; sidx++) {
							StyleRange sr = (StyleRange) nd.cachedStyles.get(sidx);
							sr.start += delta;
						}
					}
					nd.offCachedStylesRegion = offNode;

					StyleRange srLastCached = (StyleRange) nd.cachedStyles.get(cacheSize - 1);
					if ((((StyleRange) nd.cachedStyles.get(0)).start <= offStart) && (offStart < srLastCached.start + srLastCached.length)) {
						int ii = 0;
						StyleRange srOne = null;
						{
							int iihigh = cacheSize - 1;
							int iilow = 0;
							while (true) {
								ii = (iihigh + iilow) / 2;
								srOne = (StyleRange) nd.cachedStyles.get(ii);
								if (srOne.start > offStart) {
									if (iihigh == ii) {
										if (Debug.jsDebugSyntaxColoring)
											Logger.log(Logger.ERROR, "someone corrupted the js style range cache. Infinite loop aborted."); //$NON-NLS-1$
										return false;
									} //$NON-NLS-1$
									iihigh = ii;
								}
								else if (srOne.start + srOne.length <= offStart) {
									iilow = ii + 1;
								}
								else {
									break;
								}
							}
						}
						while (srOne.start < offMax) {
							// System.out.println(srOne);
							holdResults.add(returnPrunedStyle(offStart, length, srOne));
							if (++ii >= cacheSize)
								break;
							srOne = (StyleRange) nd.cachedStyles.get(ii);
						}
						if (srOne.start + srOne.length >= offMax) {
							// we're done
							return true;
						}
						offStart2 = srOne.start + srOne.length;
						// We don't want the cache to get too full. It
						// eats memory... and this
						// seems like the best guess at where we can
						// release some since we know
						// that we've past the end of the cache and
						// probably are going to go well
						// as more line requests come in.
						if (cacheSize > 150)
							nd.cachedStyles.clear();
						startHere = true; // we know the parse can begin
						// exactly at offStart2 and
						// doesn't need to back up to
						// the last cached.
					}
					else {
						// the first part of what we need to draw isn't in the
						// cache so just
						// expunge the cache and start from scratch
						if ((cacheSize > 150) // ||
									// (offStart<srLastCached.start)
									|| (((StyleRange) nd.cachedStyles.get(0)).start > offStart)) {
							// it's before the cache or the cache is too big
							// to grow more
							nd.cachedStyles.clear();
						}
						else {
							// if we're close to the end of the cache, like
							// perhaps just a
							// CRLF away, just start there.
							if ((srLastCached.start + srLastCached.length + 2) < offStart) {
								// still too short, just go ahead and start
								// from scratch
								nd.cachedStyles.clear();
							}
							else {
								// start where the cache left off. It's not a
								// major problem to throw a style or two extra
								// up front
								offStart2 = srLastCached.start + srLastCached.length;
								startHere = true;
							}
						}
					}
				}
			}
			{
				StyleRange styleRange = null;
				int offsReq = offStart2 - offNode;
				int offsMax = offMax - offNode;
				ILexer lexer2 = getParseStartPoint(fnode, null, offStart2, startHere);

				// debug
				if (lexer2 == null)
					return true;

				Token tkThis = lexer2.next();

				// iterate through tokens until on screen and off and a bit
				// more for caching
				while (tkThis.getLPOffset() < (offsMax + 200)) {
					String strTkThis = tkThis.toString();
					int offsTkThis = tkThis.getLPOffset();
					int offsTkThisEnd = offsTkThis + strTkThis.length();
					TextAttribute attr = null;
					if (offsTkThisEnd <= offsReq) {
						// skip. This doesn't extend onto the screen
					}
					else {
						attr = JavaScriptColorPreferences.taDefault;
						if (tkThis instanceof TStringLiteral) {
							attr = JavaScriptColorPreferences.taStringLit;
						}
						else if (tkThis instanceof TBlank) {
							// just use whatever we last used if the
							// background color is the same. We can get away
							// with this because blanks have no foreground
							// pixels, so as long as the backgrounds are the
							// same it's equiv.
							// defect 215738 - below line commented out
							// because of null pointer exception-replaced with
							// big if-then below it
							// if ((styleRange!=null) &&
							// styleRange.background.equals(attr.getBackground())
							// && (attr.getStyle()==styleRange.fontStyle))
							// attr = previousAttr;
							if ((styleRange != null) && ((styleRange.background == attr.getBackground()) || ((styleRange.background != null) && (attr.getBackground() != null) && styleRange.background.equals(attr.getBackground()))) && (attr.getStyle() == styleRange.fontStyle))
								attr = previousAttr;
							// when colors are only selected from a set of
							// constants replace big if-then above with line
							// below
							// if ((styleRange!=null) &&
							// (styleRange.background == attr.getBackground())
							// && (attr.getStyle()==styleRange.fontStyle))
							// attr = previousAttr;
						}
						else if (tkThis instanceof TCommenttok) {
							attr = JavaScriptColorPreferences.taComment;
						}
						else if (tkThis instanceof TUnterminatedComment) {
							attr = JavaScriptColorPreferences.taUnfComment;
						}
						else if (tkThis instanceof TUnterminatedStringLiteral) {
							attr = JavaScriptColorPreferences.taUnfComment;
						}
						else if (tkThis instanceof TErrorChar) { // like a
							// backslash
							// character
							attr = JavaScriptColorPreferences.taUnfComment;
						}
						else if (boldKeywords.contains(strTkThis)) {
							attr = JavaScriptColorPreferences.taKeyword;
						}
						// if this region's attr is the same as previous one,
						// then just adjust the previous style range
						// instead of creating a new instance of one
						// note: to use 'equals' in this case is important,
						// since sometimes
						// different instances of attributes are associated
						// with a region, even the
						// the attribute has the same values. OTOH, we can use
						// == if we use
						// constants in all code that could fill previousAttr
						// and attr.
						if (Debug.jsDebugSyntaxColoring && (previousAttr != null) && (previousAttr.equals(attr)) && (previousAttr != attr)) {
							// perf: if we never reach here we should be able
							// to use == below as an optimization.
							throw new RuntimeException("assert failed: we didn't use unique constants for highligh colors"); //$NON-NLS-1$
						}
						if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr))) {
							styleRange.length += (strTkThis.length());
						}
						else {
							// perf: createStyleRange could be inlined here
							// and optimized out. For now though it provides a
							// central place to monitor all StyleRanges built.
							// styleRange = createStyleRange(currentRegion,
							// attr, offNode + offsTkThis,
							// strTkThis.length());
							styleRange = new StyleRange(offNode + offsTkThis, strTkThis.length(), attr.getForeground(), attr.getBackground(), attr.getStyle());
							if (offsTkThis < offsMax)
								holdResults.add(styleRange);
							nd.cachedStyles.add(styleRange);
							previousAttr = attr;
						}
					}
					tkThis = lexer2.next();
					if (tkThis instanceof EOF)
						break;
				} // endwhile
			}
		}
		catch (IOException exc) {
			Logger.log(Logger.WARNING_DEBUG, "Exception in JavaScript prepareRegions()", exc); //$NON-NLS-1$
		}
		catch (LexerException exc) {
			// some difficulty parsing. It's not clear what we should do in
			// this case generically. It's hard enough to decide in specific
			// cases like unterminated quotations.
			Logger.log(Logger.WARNING_DEBUG, "Exception in JavaScript prepareRegions()", exc); //$NON-NLS-1$
		}
		catch (Exception exc) {
			Logger.log(Logger.WARNING_DEBUG, "Exception in JavaScript prepareRegions()", exc); //$NON-NLS-1$
		}
		return true;
	}

	/**
	 * returnPrunedStyle just takes the given style and throws it at the end
	 * of the given colleciton. If the style falls outside the specified
	 * offsets, then a copy of the provided style is created, trimmed and
	 * appended to the collection instead.
	 */
	public static StyleRange returnPrunedStyle(int offRegStart, int lenReg, StyleRange srIn) {
		int intSRStart = srIn.start;
		int intSREnd = srIn.start + srIn.length;

		// assert: we assume that if we are called *some* part of srIn is in
		// the specified region. If that were not the case there probably
		// isn't a meaningful value we could return besides possibly null
		if (intSRStart < offRegStart) {
			intSRStart = offRegStart;
		}
		if (intSREnd > (offRegStart + lenReg)) {
			intSREnd = offRegStart + lenReg;
		}
		if ((intSRStart == srIn.start) && (intSREnd == (srIn.start + srIn.length))) {
			// no changes made so just return
			return (StyleRange) srIn.clone();
		}
		StyleRange retval = (StyleRange) srIn.clone();
		retval.start = intSRStart;
		retval.length = (intSREnd - intSRStart);
		return retval;
	}

	/**
	 * returnPrunedStyle behaves the same as returnPrunedStyle except that in
	 * the case that the value is flush with the end of the acceptable region,
	 * we create a duplicate that we return. The reason the caller might want
	 * this is if it extends the range of styles and doesn't want to check if
	 * he is extending it beyond the end of the acceptable region. (Details
	 * omitted.) In this case, he gets a second copy so that he can decide
	 * which copy gets extended.
	 */
	public static StyleRange returnPrunedStyleWithEndDup(int offRegStart, int lenReg, StyleRange srIn) {
		StyleRange sr2 = returnPrunedStyle(offRegStart, lenReg, srIn);
		int intSREnd = srIn.start + srIn.length;
		if ((srIn == sr2) && (intSREnd == (offRegStart + lenReg))) {
			return (StyleRange) sr2.clone();
		}
		return sr2;
	}

	public void release() {
		releaseNodesInLexerCache();

		if (fDocument != null) {
			fDocument.removeDocumentListener(this);
		}

		JavaScriptColorPreferences.removePropertyChangeListener(fPreferenceListener);
		fIsInitialized = false;
	}

	protected void releaseNodesInLexerCache() {
		Enumeration keys = htForNode2.keys();
		while (keys.hasMoreElements()) {
			IStructuredDocumentRegion fnode = (IStructuredDocumentRegion) keys.nextElement();
			LexerCacheForJavaScript.release(fnode);
		}
	}
}
