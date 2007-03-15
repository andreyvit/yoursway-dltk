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
package org.eclipse.wst.javascript.ui.internal.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension5;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
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
import org.eclipse.wst.javascript.ui.internal.common.JavaScriptColorPreferences;
import org.eclipse.wst.javascript.ui.internal.common.LexerCacheForJavaScript;
import org.eclipse.wst.javascript.ui.internal.common.TextRange;
import org.eclipse.wst.javascript.ui.internal.common.style.LineStyleProviderForJavaScript;


public class JSLineStyleListener implements LineStyleListener, IDocumentListener {
	private class PropertyChangeListener implements IPropertyChangeListener {
		/* (non-Javadoc)
		 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent event) {
			// have to do it this way so others can override the method
			handlePropertyChange(event);
		}
	}
	
	private ISourceViewer fSourceViewer = null;
	protected ArrayList cachedStyles = new ArrayList(100);
	protected int offCachedStylesRegion = 0;

	protected String strOldNodeValue = null;
	protected LexerCacheForJavaScript pcParseCache = null;
	private IDocument fDocument = null;
	private static java.util.HashSet boldKeywords = new java.util.HashSet();
	private PropertyChangeListener fPreferenceListener = new PropertyChangeListener();
//	private LexerCacheForJavaScript fLexerCache = null;

	static {
		for (int i = 0; i < LineStyleProviderForJavaScript.keywords.length; i++) {
			boldKeywords.add(LineStyleProviderForJavaScript.keywords[i]);
		}

	}

	/* 
	 * done: js outline view doesn't appear initially.  Appears after the file is mod'ed
	 * done: no icons in js outline view
	 * done: after changing colors in pref dialog, the js file is not forced to repaint.
	 * todo: look in to javadoc help.
	 * todo: test syntax completion.
	 * todo: let syntax completion support vars (not just functions) also.
	 * todo: cache outline view info better (if needed)
	 * todo: on 02/02/28 the performance of typing is bad on Jason's machine.  Check in to it.
	 */

	public JSLineStyleListener(IDocument document, ISourceViewer sourceViewer) {
		fDocument = document; //setDocument(document);
		if (fDocument != null) {
			//fLexerCache =
			// this method has side effect, even though we don't use returned value
			LexerCacheForJavaScript.getCache(fDocument, fDocument.get());
			fDocument.addDocumentListener(this);
			
			JavaScriptColorPreferences.addPropertyChangeListener(fPreferenceListener);
		}
		if (sourceViewer != null) {
			setSourceViewer(sourceViewer);
		}
	}

	//protected void setDocument(IDocument document) {
	//	fDocument = document;
	//}
	public IDocument getDocument() {
		return fDocument;
	}

	/**
	 * 
	 */
	public void documentAboutToBeChanged(DocumentEvent event) {
	}

	/**
	 * 
	 */
	public void documentChanged(DocumentEvent event) {
		try {
			if (pcParseCache == null) {
				// hmm.  Is this ever called?
				//                Logger.logError("JSLSL: docChanged called before we have a cache\n"); //$NON-NLS-1$
			}
			String nv = fDocument.get();
			if (nv != null) {
				if (strOldNodeValue == null) {
					pcParseCache.notifyChange(nv, 0, 0, nv.length());
					cachedStyles.clear();
					redrawRegion(0, LexerCacheForJavaScript.CHANGED_ALL);
				}
				else {
					int idx = event.getOffset();
					// note: idx now is the index to mark the earliest spot where there 
					//    might be a visible character change or token change.  In the
					//    case of a token, it doesn't necessarily have to be at the
					//    beginning of a token.  The reason is for example that the token
					//    is likely to be broken and possibly even split at the change point.

					TextRange trRightmostParseChange = pcParseCache.notifyChange(nv, idx, event.getLength(), (event.getText() != null ? event.getText().length() : 0));

					// note: we might need special code if changing of one character might
					//    affect earlier parsing for example changing  "+ " to "++" changes
					//    the previous token too.  For good measure let's move it back a few
					//    characters.  If we discover we need to move back more than that,
					//    we can code that up after we learn about it.
					//idx -= 2;

					if (cachedStyles.size() > 0) {
						int csize = cachedStyles.size();
						int offIdx = idx + offCachedStylesRegion;
						StyleRange srOne = (StyleRange) (cachedStyles.get(0));
						if (srOne.start > offIdx) {
							cachedStyles.clear();
						}
						else {
							while ((csize--) > 0) {
								srOne = (StyleRange) cachedStyles.get(csize);
								if ((srOne.start + srOne.length) >= offIdx) {
									cachedStyles.remove(srOne);
								}
								else {
									break;
								}
							}
						}
					}
					if (trRightmostParseChange.right > 0) {
						int idxEOL = nv.indexOf('\n', trRightmostParseChange.offStart);
						if (idxEOL > trRightmostParseChange.right) {
							trRightmostParseChange.right = idxEOL;
						}
						// defect 219198 - Highlighting wrong for end bracket without trailing whitespace
						else if (idxEOL < 0) {
							trRightmostParseChange.right = nv.length();
						}
					}
					redrawRegion(trRightmostParseChange.offStart, trRightmostParseChange.right);
				}
				this.strOldNodeValue = nv;
			}

		}
		catch (Exception exc) {
			Logger.logException("Exception in notifyChanged() of LineStyleProviderForJava", exc); //$NON-NLS-1$
		}
	}

	/**
	 * @see LineStyleListener#lineGetStyle(LineStyleEvent)
	 */
	public void lineGetStyle(LineStyleEvent event) {
		String lineText = event.lineText;
		int offset = event.lineOffset;

		event.styles = getStyleRangeArray(offset, lineText);
	}

	protected StyleRange[] getStyleRangeArray(int offset, String text) {
		Vector styleRangeVector = new Vector();
		prepareRegions(offset, text.length(), styleRangeVector);
		StyleRange[] styleRangeArray = new StyleRange[styleRangeVector.size()];
		styleRangeVector.copyInto(styleRangeArray);

		return styleRangeArray;
	}

	/**
	 */
	private void prepareRegions(int offStart, int length, Collection holdResults) {
		//Date dt0 = new Date();
		Vector vecTempResults = new Vector();
		prepareRegions2(offStart, length, vecTempResults /*holdResults*/
		);
		// now we'll have to trim the ends off because the 
		//    caller of this doesn't want it to begin before
		//    the requested region or extend beyond.
		//    I know for a fact that only the first and
		//    last elements might extend outside the region.
		int veclen = vecTempResults.size();
		if (veclen > 0) {
			StyleRange sr0 = (StyleRange) vecTempResults.elementAt(0);
			sr0 = returnPrunedStyle(offStart, length, sr0);
			vecTempResults.removeElementAt(0);
			vecTempResults.insertElementAt(sr0, 0);
			StyleRange srE = (StyleRange) vecTempResults.elementAt(veclen - 1);
			srE = returnPrunedStyle(offStart, length, srE);
			vecTempResults.removeElementAt(veclen - 1);
			vecTempResults.insertElementAt(srE, veclen - 1);
		}
		// The following line of code commented by defect 238935.
		//holdResults.addAll(vecTempResults);
		//
		// defect 238935
		// add to holdResults only if style length > 0
		//
		// This is just a work around for a bidi problem
		// in StyledText. The problem has been fixed in
		// Eclipse 2.1 stream. See Bugzilla defect 31448.
		for (int i = 0; i < vecTempResults.size(); i++) {
			StyleRange styleRange = (StyleRange) vecTempResults.elementAt(i);
			if (styleRange.length > 0)
				holdResults.add(styleRange);
		}
		//Date dt1 = new Date(); 
		//int lgTime = (int)(dt1.getTime() - dt0.getTime());
		//System.out.println( "\ntime="+lgTime+"   "+offStart+"   "+length );
	}

	private void prepareRegions2(int offStart, int length, Collection holdResults) {
		boolean startHere = false;
		int offMax = offStart + length;
		int offStart2 = offStart;
		TextAttribute previousAttr = null;
		//System.out.println( "**" );

		// figure out at what file offset the node starts.
		int offNode = 0; // bad guess, override below

		try {
			{
				//cachedStyles.clear();  // debug performance
				// perf: see about implementing cachedStyles as an array to avoid all this casting to StyleRange
				int cacheSize = cachedStyles.size();
				if (cacheSize > 0) {
					//if ( true /*cacheSize > 0*/ ) { /*System.out.println( "debug no js style cache" );*/ } else {
					if (offCachedStylesRegion != offNode) {
						int delta = offNode - offCachedStylesRegion;
						for (int sidx = 0; sidx < cacheSize; sidx++) {
							StyleRange sr = (StyleRange) cachedStyles.get(sidx);
							sr.start += delta;
						}
					}
					offCachedStylesRegion = offNode;

					StyleRange srLastCached = (StyleRange) cachedStyles.get(cacheSize - 1);
					if ((((StyleRange) cachedStyles.get(0)).start <= offStart) && (offStart < srLastCached.start + srLastCached.length)) {
						int ii = 0;
						StyleRange srOne = null;
						{
							int iihigh = cacheSize - 1;
							int iilow = 0;
							while (true) {
								ii = (iihigh + iilow) / 2;
								srOne = (StyleRange) cachedStyles.get(ii);
								if (srOne.start > offStart) {
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
							//System.out.println(srOne);
							holdResults.add(returnPrunedStyle(offStart, length, srOne));
							if (++ii >= cacheSize)
								break;
							srOne = (StyleRange) cachedStyles.get(ii);
						}
						if (srOne.start + srOne.length >= offMax) {
							// we're done
							return;
						}
						else {
							offStart2 = srOne.start + srOne.length;
							// We don't want the cache to get too full.  It eats memory... and this
							//    seems like the best guess at where we can release some since we know
							//    that we've past the end of the cache and probably are going to go well
							//    as more line requests come in.
							if (cacheSize > 150)
								cachedStyles.clear();
							startHere = true; // we know the parse can begin exactly at offStart2 and doesn't need to back up to the last cached.
						}
					}
					else {
						// the first part of what we need to draw isn't in the cache so just
						// expunge the cache and start from scratch
						if ((cacheSize > 150) // || (offStart<srLastCached.start)
									|| (((StyleRange) cachedStyles.get(0)).start > offStart)) {
							// it's before the cache or the cache is too big to grow more
							cachedStyles.clear();
						}
						else {
							// if we're close to the end of the cache, like perhaps just a
							//   CRLF away, just start there.
							if ((srLastCached.start + srLastCached.length + 2) < offStart) {
								// still too short, just go ahead and start from scratch
								cachedStyles.clear();
							}
							else {
								// start where the cache left off.  It's not a major problem to throw a style or two extra up front
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
				ILexer lexer2 = getParseStartPoint(offStart2, startHere);

				// debug
				if (lexer2 == null)
					return;

				Token tkThis = lexer2.next();

				// iterate through tokens until on screen and off and a bit more for caching
				while (tkThis.getLPOffset() < (offsMax + 200)) {
					String strTkThis = tkThis.toString();
					int offsTkThis = tkThis.getLPOffset();
					int offsTkThisEnd = offsTkThis + strTkThis.length();
					TextAttribute attr = null;
					if (offsTkThisEnd <= offsReq) {
						// skip.  This doesn't extend onto the screen
					}
					else {
						attr = JavaScriptColorPreferences.taDefault;
						if (tkThis instanceof TStringLiteral) {
							attr = JavaScriptColorPreferences.taStringLit;
						}
						else if (tkThis instanceof TBlank) {
							// just use whatever we last used if the background color is the same.  We can get away with this because blanks have no foreground pixels, so as long as the backgrounds are the same it's equiv.
							// defect 215738 - below line commented out because of null pointer exception-replaced with big if-then below it
							//							if ((styleRange!=null) && styleRange.background.equals(attr.getBackground()) && (attr.getStyle()==styleRange.fontStyle)) attr = previousAttr;
							if ((styleRange != null) && ((styleRange.background == attr.getBackground()) || ((styleRange.background != null) && (attr.getBackground() != null) && styleRange.background.equals(attr.getBackground()))) && (attr.getStyle() == styleRange.fontStyle))
								attr = previousAttr;
							// when colors are only selected from a set of constants replace big if-then above with line below
							//							if ((styleRange!=null) && (styleRange.background == attr.getBackground()) && (attr.getStyle()==styleRange.fontStyle)) attr = previousAttr;    
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
						else if (tkThis instanceof TErrorChar) { // like a backslash character 
							attr = JavaScriptColorPreferences.taUnfComment;
						}
						else if (boldKeywords.contains(strTkThis)) {
							attr = JavaScriptColorPreferences.taKeyword;
						}
						// if this region's attr is the same as previous one, then just adjust the previous style range
						// instead of creating a new instance of one
						// note: to use 'equals' in this case is important, since sometimes
						// different instances of attributes are associated with a region, even the
						// the attribute has the same values.  OTOH, we can use == if we use
						// constants in all code that could fill previousAttr and attr.
						if ((styleRange != null) && (previousAttr != null) && (previousAttr.equals(attr))) {
							styleRange.length += (strTkThis.length());
						}
						else {
							// perf: createStyleRange could be inlined here and optimized out.  For now though it provides a central place to monitor all StyleRanges built.
							//styleRange = createStyleRange(currentRegion, attr, offNode + offsTkThis, strTkThis.length());
							styleRange = new StyleRange(offNode + offsTkThis, strTkThis.length(), attr.getForeground(), attr.getBackground(), attr.getStyle());
							if (offsTkThis < offsMax)
								holdResults.add(styleRange);
							cachedStyles.add(styleRange);
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
			Logger.logException(exc);
		}
		catch (LexerException exc) {
			// some difficulty parsing.  It's not clear what we should do in this case generically.  It's hard enough to decide in specific cases like unterminated quotations.
			Logger.logException(exc);
		}
		catch (Exception exc) {
			Logger.logException("Exception in JavaScript prepareRegions()", exc); //$NON-NLS-1$
		}
		return;
	}

	protected ILexer getParseStartPoint(int offStart, boolean startHere) {
		if (pcParseCache == null) {
			pcParseCache = LexerCacheForJavaScript.getCache(fDocument, fDocument.get());
			if (fDocument != null) {
				strOldNodeValue = fDocument.get();
				pcParseCache.notifyChange(strOldNodeValue, -1, 0, 0);
			}
		}
		return pcParseCache.getParser(offStart, startHere);
	}

	//protected boolean isKeyword(String text) {
	//	for (int i = 0; i < LineStyleProviderForJavaScript.keywords.length; i++)
	//		if (LineStyleProviderForJavaScript.keywords[i].equals(text))
	//			return true;
	//	return false;
	//}

	private void handlePropertyChange(PropertyChangeEvent event) {
		// need to check if event.getProperty is one I am concerned with before doing
		// the below steps
		if (event != null) {
			String prefKey = event.getProperty();
			// check if preference changed is a style preference
			if (JavaScriptColorPreferences.isJavaScriptColorPreference(prefKey)) {
				cachedStyles.clear();

				ISourceViewer sourceViewer = getSourceViewer();
				if (sourceViewer != null) {
					StyledText textWidget = sourceViewer.getTextWidget();
					if (textWidget != null) {
						textWidget.redraw();
					}
				}
			}
		} else {
			// this is around for old deprecated preferencesChanged() method
			// TODO remove when preferencesChanged() is removed
			cachedStyles.clear();
	
			ISourceViewer sourceViewer = getSourceViewer();
			if (sourceViewer != null) {
				StyledText textWidget = sourceViewer.getTextWidget();
				if (textWidget != null) {
					textWidget.redraw();
				}
			}
		}
	}

	/**
	 * returnPrunedStyle just takes the given style and throws it at the end of the given colleciton.  If the
	 *  style falls outside the specified offsets, then a copy of the provided style is created, trimmed and
	 *  appended to the collection instead.
	 */
	public static StyleRange returnPrunedStyle(int offRegStart, int lenReg, StyleRange srIn) {
		int intSRStart = srIn.start;
		int intSREnd = srIn.start + srIn.length;

		// assert: we assume that if we are called *some* part of srIn is in the specified region.  If that were not the case there probably isn't a meaningful value we could return besides possibly null
		if (intSRStart < offRegStart) {
			intSRStart = offRegStart;
		}
		if (intSREnd > (offRegStart + lenReg)) {
			intSREnd = offRegStart + lenReg;
		}
		if ((intSRStart == srIn.start) && (intSREnd == (srIn.start + srIn.length))) {
			// no changes made so just return
			return srIn;
		}
		StyleRange retval = (StyleRange) srIn.clone();
		retval.start = intSRStart;
		retval.length = (intSREnd - intSRStart);
		return retval;
	}

	protected void setSourceViewer(ISourceViewer sourceViewer) {
		fSourceViewer = sourceViewer;
	}

	protected ISourceViewer getSourceViewer() {
		return fSourceViewer;
	}

	protected void redrawRegion(int start, int idxEnd) {
		int difflen = 0;
		if (idxEnd == LexerCacheForJavaScript.CHANGED_NONE) {
			return;
		}
		else if (idxEnd >= 0) {
			difflen = idxEnd - start;
		}
		else {
			// TODO this needs to be improved to handle projection documents better
			// because there can be several visible regions, not just one
			IRegion visibleRegion = null;
			ITextViewer viewer = getSourceViewer();
			if (viewer instanceof ITextViewerExtension5) {
				ITextViewerExtension5 extension = (ITextViewerExtension5)viewer;
				visibleRegion = extension.getModelCoverage();
			} else {
				visibleRegion = getSourceViewer().getVisibleRegion();	
			}
			if (idxEnd == LexerCacheForJavaScript.CHANGED_ALL) {
				start = visibleRegion.getOffset();
				difflen = visibleRegion.getLength();
			}
			else if (idxEnd == LexerCacheForJavaScript.CHANGED_ALL_FORWARD) {
				int start2 = visibleRegion.getOffset();
				difflen = start2 + visibleRegion.getLength() - start;
			}
			else {
				// shouldn't reach here.
				return;
			}
		}
		// make extra sure not trying to redraw region that does not exist yet (bug 92962)
		int end = start + difflen;
		int actualEnd = getSourceViewer().getTextWidget().getCharCount();
		if (end > actualEnd) {
			difflen = actualEnd - start;
		}
		getSourceViewer().getTextWidget().redrawRange(start, difflen, true);

		// temp workaround until StyledText.redrawRange(int start, int length, boolean clearBackground) is fixed.
		try {
			int lastLineStart = getDocument().getLineOffset(getDocument().getLineOfOffset(start + difflen));
			int length = start + difflen - lastLineStart;
			getSourceViewer().getTextWidget().redrawRange(lastLineStart, length, true);
		}
		catch (BadLocationException exception) {
			// skip redrawing last line
		}
	}

	public void dispose() {
		JavaScriptColorPreferences.removePropertyChangeListener(fPreferenceListener);

		if (fDocument != null) {
			fDocument.removeDocumentListener(this);
			LexerCacheForJavaScript.release(fDocument);
		}
	}
}
