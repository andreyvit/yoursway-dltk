/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.ui.text.blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.dltk.ui.text.blocks.Instance.JoinResult;
import org.eclipse.dltk.ui.text.util.CollectionUtils;
import org.eclipse.dltk.ui.text.util.IRangeFilter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Represents a series of not-yet-closed and not-yet-opened blocks which is
 * collected and modified while traversing through the document.
 * 
 * The whole system currently assumes that block beginnings identify the block
 * in an unique way, while block endings may be ambiguous.
 * 
 * Middle words handling is mostly not implemented yet.
 * 
 * @author Andrey Tarantsov
 */
public class Balance {
	
	public interface Listener {
		
		void instanceMatched(Instance instance);
		
	}

	private final ArrayList active = new ArrayList();

	private final BlocksConfiguration blocks;

	public Balance(BlocksConfiguration blocks) {
		this.blocks = blocks;
	}
	
	public Instance.JoinResult process(IDocument document, String match, int offset, Listener listener) {
		Keyword keyword = blocks.getKeyword(match);
		if (keyword == null) 
			return JoinResult.UNHANDLED;
		try {
			if (!keyword.getRecognition().canMatchAt(document, offset))
				return JoinResult.UNHANDLED;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		for (int i = active.size() - 1; i >= 0; i--) {
			Instance instance = (Instance) active.get(i);
			JoinResult result = instance.tryJoinAsRightPeer(keyword, offset);
			switch(result.getId()) {
			case JoinResult.STOP_SEARCH_ID:
				return JoinResult.STOP_SEARCH;
			case JoinResult.HANDLED_PLEASE_DISCARD_ID:
				CollectionUtils.removeElementsFromIndexToEnd(active, i);
				// fall-through
			case JoinResult.HANDLED_ID:
				if (listener != null)
					listener.instanceMatched(instance);
				return JoinResult.HANDLED;
			case JoinResult.UNHANDLED_ID:
				continue;
			default:
				throw new AssertionError("Unreachable code");
			}
		}
		IBlockSet blockSet = blocks.getRootBlockSet().narrowByKeyword(keyword);
		Instance instance = new Instance(blockSet, keyword, offset);
		active.add(instance);
		return JoinResult.HANDLED;
	}
	
	public Instance.JoinResult process(Instance peer, Listener listener) {
		for (int i = active.size() - 1; i >= 0; i--) {
			Instance instance = (Instance) active.get(i);
			JoinResult result = instance.tryJoinAsRightPeer(peer, false);
			switch(result.getId()) {
			case JoinResult.STOP_SEARCH_ID:
				return JoinResult.STOP_SEARCH;
			case JoinResult.HANDLED_PLEASE_DISCARD_ID:
				CollectionUtils.removeElementsFromIndexToEnd(active, i);
				// fall-through
			case JoinResult.HANDLED_ID:
				if (listener != null)
					listener.instanceMatched(instance);
				return JoinResult.HANDLED;
			case JoinResult.UNHANDLED_ID:
				continue;
			default:
				throw new AssertionError("Unreachable code");
			}
		}
		active.add(peer);
		return JoinResult.HANDLED;
	}

	public void addAll(Balance successor, Listener listener) {
		for (int i = 0; i < successor.active.size(); i++) {
			Instance peer = (Instance) successor.active.get(i);
			process(peer, listener);
		}
		successor.active.clear();
	}

	public boolean isAnythingOpen() {
		for (int i = active.size() - 1; i >= 0; i--) {
			Instance instance = (Instance) active.get(i);
			if (instance.getBeginningOffset() >= 0 || instance.getMiddleOffset() >= 0)
				return true;
		}
		return false; 
	}

	public String toDebugString(IDocument document) {
		StringBuffer r = new StringBuffer();
		for (Iterator iter = active.iterator(); iter.hasNext();) {
			Instance item = (Instance) iter.next();
			if (r.length() > 0)
				r.append(' ');
			item.addToDebugString(r, document);
		}
		return r.toString();
	}

	/**
	 * Matches blocks within the given area of the document. Traverses the range
	 * forward.
	 * 
	 * @param document
	 *            the document to match blocks in
	 * @param startOffset
	 *            the start of the area to match blocks in
	 * @param length
	 *            the length of the area to match blocks in
	 * @param blocks
	 *            the blocks to match (only the words from within these blocks
	 *            would be found and matched)
	 * @param rangeFilter
	 *            the filter which is consulted to determine if a given block
	 *            can be matched at the given offset
	 * @return
	 * @throws BadLocationException
	 */
	public static Balance calculateBalance(IDocument document, int startOffset, int length,
			BlocksConfiguration blocks, IRangeFilter rangeFilter, Listener listener) throws BadLocationException {
		Balance balance = new Balance(blocks);
		String data = document.get(startOffset, length);
		Pattern regularExpression = blocks.getBeginMiddleEndPattern();
		Matcher matcher = regularExpression.matcher(data);
		for (boolean found = matcher.find(); found; found = matcher.find()) {
			String match = matcher.group();
			int start = startOffset + matcher.start();
			if (!rangeFilter.allowRange(document, start, match.length()))
				continue;
			balance.process(document, match, start, listener);
		}
		return balance;
	}

}