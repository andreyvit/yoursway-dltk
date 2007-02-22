/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.documentation;

import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.corext.documentation.SingleCharReader;

/**
 * Reads documentation comment from a Tcl comment. Skips #-character on begin of
 * line
 */
public class TclCommentReader extends SingleCharReader {

	private IBuffer fBuffer;

	private int fCurrPos;
	private int fStartPos;
	private int fEndPos;

	private boolean fWasNewLine;

	public TclCommentReader(IBuffer buf, int start, int end) {
		fBuffer = buf;
		fStartPos = start;
		fEndPos = end;

		reset();
	}

	/**
	 * @see java.io.Reader#read()
	 */
	public int read() {
		if (fCurrPos < fEndPos) {
			char ch;
			if (fWasNewLine) {
				do {
					ch = fBuffer.getChar(fCurrPos++);
				} while (fCurrPos < fEndPos && Character.isWhitespace(ch));
				if (ch == '#') {
					if (fCurrPos < fEndPos) {
						do {
							ch = fBuffer.getChar(fCurrPos++);
						} while (ch == '#');
					} else {
						return -1;
					}
				}
			} else {
				ch = fBuffer.getChar(fCurrPos++);
			}
			fWasNewLine = (ch == '\n' || ch == '\r');

			return ch;
		}
		return -1;
	}

	/**
	 * @see java.io.Reader#close()
	 */
	public void close() {
		fBuffer = null;
	}

	/**
	 * @see java.io.Reader#reset()
	 */
	public void reset() {
		fCurrPos = fStartPos;
		fWasNewLine = true;
	}

	/**
	 * Returns the offset of the last read character in the passed buffer.
	 */
	public int getOffset() {
		return fCurrPos;
	}
}
