/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;

/**
 * Helper class for match pairs of characters.
 */
public final class RubyPairMatcher extends DefaultCharacterPairMatcher {

	private int fBlockAnchor;

	/**
	 * Stores the source version state.
	 * 
	 * @since 3.1
	 */
	public RubyPairMatcher() {
		super("{}[]()".toCharArray(), IRubyPartitions.RUBY_PARTITIONING); //$NON-NLS-1$
	}

	/* @see ICharacterPairMatcher#match(IDocument, int) */
	public IRegion match(IDocument document, int offset) {
		try {
			return performMatch(document, offset);
		} catch (BadLocationException ble) {
			return null;
		}
	}

	/*
	 * Performs the actual work of matching for #match(IDocument, int).
	 */
	private IRegion performMatch(IDocument document, int offset)
			throws BadLocationException {
		if (offset < 0 || document == null)
			return null;
		final IRegion region = super.match(document, offset);
		if (region != null)
			return region;
		
		RubyHeuristicScanner scanner = new RubyHeuristicScanner(document);
		IRegion word = scanner.findWordAt(offset);
		if (word == null)
			return null;
		
		int start = word.getOffset();
		int end = start + word.getLength();
		if (scanner.isBlockBeginning(start, end)) {
			start = scanner.getPosition();
			end = scanner.findBlockEndingOffset(end);
			if (end != RubyHeuristicScanner.NOT_FOUND) {
				clear();
				fBlockAnchor = LEFT;
				return new Region(start, end - start);	
			}
			
		} else if (scanner.isBlockEnding(start, end)) {
			end = scanner.getPosition();
			start = scanner.findBlockBeginningOffset(start);
			if (start != RubyHeuristicScanner.NOT_FOUND) {
				clear();
				fBlockAnchor = RIGHT;
				return new Region(start, end - start);	
			}
		}
		
		return null;
	}

	public int getAnchor() {
		int superAnchor = super.getAnchor();
		if (superAnchor < 0)
			return fBlockAnchor;
		else
			return superAnchor;
	}

	public void clear() {
		super.clear();
		fBlockAnchor = -1;
	}
}
