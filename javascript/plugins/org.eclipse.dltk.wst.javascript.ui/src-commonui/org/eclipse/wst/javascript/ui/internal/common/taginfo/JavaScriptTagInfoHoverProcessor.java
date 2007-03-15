/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common.taginfo;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.wst.javascript.ui.internal.common.Logger;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.ui.internal.StructuredTextViewer;
import org.eclipse.wst.sse.ui.internal.taginfo.AbstractHoverProcessor;

/**
 * Provides hover help documentation for javascript code
 * 
 */
public class JavaScriptTagInfoHoverProcessor extends AbstractHoverProcessor {
	static protected final char chEndBracket = ']';
	static protected final char chEndBrace = ')';
	static protected final char chBeginBracket = '[';
	static protected final char chBeginBrace = '(';
	static protected final char chPeriod = '.';


	protected JavaScriptTagInfoProvider tagInfoProvider = null;

	/**
	 * Constructor for JSHoverHelpProcessor
	 */
	public JavaScriptTagInfoHoverProcessor() {
	}

	/**
	 * Return the flatnode at the current offset
	 */
	protected IStructuredDocumentRegion getStructuredDocumentRegion(StructuredTextViewer viewer, int offset) {
		IStructuredDocumentRegion flatNode = null;

		if ((viewer != null) && (viewer.getDocument() != null))
			flatNode = ((IStructuredDocument) viewer.getDocument()).getRegionAtCharacterOffset(offset);

		return flatNode;
	}

	/**
	 * Returns the region to hover the text over based on the offset.
	 * 
	 * @param textViewer
	 * @param offset
	 * 
	 * @return IRegion region to hover over if offset is within tag name,
	 *         attribute name, or attribute value and if offset is not over
	 *         invalid whitespace. otherwise, returns <code>null</code>
	 * 
	 * @see org.eclipse.jface.text.ITextHover#getHoverRegion(ITextViewer, int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		if (textViewer instanceof StructuredTextViewer) {
			StructuredTextViewer viewer = (StructuredTextViewer) textViewer;
			IStructuredDocumentRegion flatNode = getStructuredDocumentRegion(viewer, offset);
			ITextRegion region = null;

			if (flatNode != null) {
				region = flatNode.getRegionAtCharacterOffset(offset);
			}

			// if no structuredDocumentRegion or in region's trailing
			// whitespace, no hover region
			if ((region == null) || (offset > flatNode.getTextEndOffset(region)))
				return null;
		}

		try {
			// check if we are at whitespace before or after line
			IRegion line = textViewer.getDocument().getLineInformationOfOffset(offset);
			if ((offset > (line.getOffset())) && (offset < (line.getOffset() + line.getLength()))) {
				org.eclipse.jface.text.Region codeRegion = getSegmentRegion(textViewer, offset);
				if (codeRegion == null)
					codeRegion = new org.eclipse.jface.text.Region(offset, 0);
				return codeRegion;
			}
		}
		catch (BadLocationException e) {
			Logger.logException(e);
		}
		return null;
	}


	/**
	 * Returns the documentation associated with the hoverRegion passed in
	 * 
	 * @param ITextViewer
	 *            viewer
	 * @param IRegion
	 *            hoverRegion
	 * 
	 * @return String documentation for hoverRegion or null if there is none
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
	public String getHoverInfo(ITextViewer viewer, IRegion hoverRegion) {
		if ((hoverRegion == null) || (viewer == null))
			return null;

		int documentOffset = hoverRegion.getOffset();
		String displayText = null;

		displayText = computeHoverHelp(viewer, documentOffset);

		return displayText;
	}

	/**
	 * Retrieves documentation to display in the hover help popup.
	 * 
	 * @return String any documentation information to display
	 *         <code>null</code> if there is nothing to display.
	 * 
	 */
	public String computeHoverHelp(ITextViewer textViewer, int documentPosition) {

		String result = null;
		IStructuredDocumentRegion scriptNode = null;
		ITextRegion scriptRegion = null;
		org.eclipse.jface.text.Region segmentRegion = null;
		String segment = null;

		if ((textViewer == null) || (textViewer.getDocument() == null))
			return null;

		// get only the region/block that contains the keyword identified by
		// documentPosition
		if (textViewer instanceof StructuredTextViewer) {
			scriptNode = getStructuredDocumentRegion((StructuredTextViewer) textViewer, documentPosition);
			if (scriptNode != null) {
				scriptRegion = scriptNode.getRegionAtCharacterOffset(documentPosition);
			}
		}
		if (scriptRegion == null)
			segmentRegion = getSegmentRegion(textViewer, documentPosition);
		else
			segmentRegion = getSegmentRegion(textViewer, documentPosition, scriptNode.getStartOffset(scriptRegion), scriptNode.getEndOffset(scriptRegion));

		segment = getSegmentString(textViewer, segmentRegion);

		result = getJSTagInfo(segment);
		return result;
	}

	/**
	 * @param ITextViewer
	 *            textViewer - assumes this is not null
	 * 
	 * @return String the substring in textViewer's doc associated with
	 *         codeRegion
	 */
	protected String getSegmentString(ITextViewer textViewer, org.eclipse.jface.text.Region codeRegion) {
		String fullPath = null;

		if (codeRegion == null)
			return null;
		try {
			// get the substring from the document associated with the region
			fullPath = textViewer.getDocument().get(codeRegion.getOffset(), codeRegion.getLength());
		}
		catch (org.eclipse.jface.text.BadLocationException ble) {
			return null;
		}

		return fullPath;
	}

	/**
	 * Returns a region containing the segment of code containing docPos. for
	 * example: document.lay*er where * is the docPos, would return a region
	 * representing layer
	 */
	public org.eclipse.jface.text.Region getSegmentRegion(ITextViewer viewer, int docPos) {
		if (viewer == null)
			return null;

		org.eclipse.jface.text.IDocument doc = viewer.getDocument();
		return getSegmentRegion(viewer, docPos, 0, doc.getLength());
	}

	/**
	 * Returns a region containing the segment of code containing docPos for
	 * example: document.lay*er where * is the docPos, would return a region
	 * representing layer
	 * 
	 * @param int
	 *            blockBegin - the min doc position use in computing
	 * @param int
	 *            blockEnd - the max doc position to use in computing
	 */
	public org.eclipse.jface.text.Region getSegmentRegion(ITextViewer viewer, int docPos, int blockBegin, int blockEnd) {
		org.eclipse.jface.text.IDocument doc = viewer.getDocument();

		int begin = -1;
		int end = -1;

		// check if we are within our parameters
		if ((docPos < blockBegin) || (docPos > blockEnd) || (blockBegin > blockEnd))
			return null;

		int iPos = docPos;
		char ch;
		try {
			// future_TODO: add a limit so that we don't scan backwards a huge
			// amount of time
			while (iPos >= blockBegin) {
				if (iPos < (docPos - 100)) {
					// don't scan backwards a lot. If it's long, just give up.
					return null;
				}
				ch = doc.getChar(iPos);
				if (!Character.isLetterOrDigit(ch))
					break;
				--iPos;
			}
			begin = iPos;
			iPos = docPos;

			// future_TODO: add a limit so that we don't scan forwards a huge
			// amount of time
			while (iPos <= blockEnd) {
				if (iPos > (docPos + 100)) {
					// don't scan forwards a lot. If it's long, just give up.
					return null;
				}
				ch = doc.getChar(iPos);
				if (!Character.isLetterOrDigit(ch))
					break;
				++iPos;
			}
			end = iPos;

		}
		catch (org.eclipse.jface.text.BadLocationException ble) {
			return null;
		}

		if (begin == docPos && end == docPos)
			return new org.eclipse.jface.text.Region(docPos, 0);
		else if (begin == docPos)
			return new org.eclipse.jface.text.Region(begin, end - begin);
		else
			return new org.eclipse.jface.text.Region(begin + 1, end - begin - 1);
	}

	/**
	 * Gets the default JSTagInfo provider adapter
	 */
	protected JavaScriptTagInfoProvider getTagInfoProvider() {
		if (tagInfoProvider == null)
			tagInfoProvider = new JavaScriptTagInfoProvider();

		return tagInfoProvider;
	}

	/**
	 * Return the tag info associated with fullPath
	 */
	protected String getJSTagInfo(String fullPath) {
		String result = null;

		result = getTagInfoProvider().getTagInfo(fullPath);

		return result;
	}

}
