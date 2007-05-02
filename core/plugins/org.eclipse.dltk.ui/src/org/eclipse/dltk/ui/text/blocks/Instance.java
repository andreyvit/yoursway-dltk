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

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Represents a block beginning which has not yet been matched with a corresponding
 * block ending.
 * 
 * @author Andrey Tarantsov
 */
public class Instance {
	
	public static abstract class JoinResult {
		
		public static final int UNHANDLED_ID = 0;
		public static final int HANDLED_ID = 1;
		public static final int HANDLED_PLEASE_DISCARD_ID = 3;
		public static final int STOP_SEARCH_ID = 2;
		
		private JoinResult() {
		}
		
		public abstract int getId();
		
		public static final JoinResult UNHANDLED = new JoinResult() {
			public int getId() {
				return UNHANDLED_ID;
			}
		};
		
		public static final JoinResult HANDLED = new JoinResult() {
			public int getId() {
				return HANDLED_ID;
			}
		};
		
		public static final JoinResult HANDLED_PLEASE_DISCARD = new JoinResult() {
			public int getId() {
				return HANDLED_PLEASE_DISCARD_ID;
			}
		};
		
		public static final JoinResult STOP_SEARCH = new JoinResult() {
			public int getId() {
				return STOP_SEARCH_ID;
			}
		};
		
	}
	
	private int begOffset = -1, midOffset = -1, endOffset = -1;
	
	private int begLength = -1, midLength = -1, endLength = -1;
	
	private IBlockSet blockSet;
	
	public Instance(IBlockSet rootBlockSet, Keyword keyword, int offset) {
		this.blockSet = rootBlockSet;
		if (tryJoinAsRightPeer(keyword, offset) != JoinResult.HANDLED)
			throw new AssertionError("Keyword matching problems (internal error)");
	}
	
	public JoinResult tryJoinAsRightPeer(Instance instance, boolean allowOverridingMiddle) {
		boolean begKnown = begOffset >= 0;
		boolean midKnown = midOffset >= 0;
		boolean endKnown = endOffset >= 0;
		
		boolean begJoin = !begKnown && instance.begOffset >= 0;
		boolean midJoin = instance.midOffset >= 0;
		boolean endJoin = !endKnown && instance.endOffset >= 0;
		
		boolean begPossible = begJoin && (midKnown || endKnown);
		boolean midPossible = midJoin && (!midKnown || allowOverridingMiddle);
		boolean endPossible = endJoin && (midKnown || begKnown);
		if (!begPossible && !midPossible && !endPossible)
			return JoinResult.UNHANDLED;
		
		IBlockSet newBlockSet = blockSet.intersect(instance.getBlockSet());
		if (newBlockSet.isEmpty())
			return JoinResult.UNHANDLED;
		
		if (begPossible) {
			begOffset = instance.begOffset;
			begLength = instance.begLength;
		}
		if (midPossible) {
			midOffset = instance.midOffset;
			midLength = instance.midLength;
		}
		if (endPossible) {
			endOffset = instance.endOffset;
			endLength = instance.endLength;
		}
		
		if (isFullyStuffed())
			return JoinResult.HANDLED_PLEASE_DISCARD;
		else
			return JoinResult.HANDLED;
	}
	
	public JoinResult tryJoinAsRightPeer(Keyword keyword, int offset) {
		switch(keyword.getRole().id()) {
		case KeywordRole.ID_BEGINNING:
			if (begOffset >= 0)
				return JoinResult.UNHANDLED;
			break;
		case KeywordRole.ID_ENDING:
			if (endOffset >= 0)
				return JoinResult.UNHANDLED;
			break;
		}
		IBlockSet newBlockSet = blockSet.narrowByKeyword(keyword);
		if (newBlockSet.isEmpty())
			return JoinResult.UNHANDLED;
		assignOffset(keyword.getRole(), offset, keyword.getText().length());
		if (isFullyStuffed())
			return JoinResult.HANDLED_PLEASE_DISCARD;
		else
			return JoinResult.HANDLED;
	}
	
	public boolean isFullyStuffed() {
		return begOffset >= 0 && endOffset >= 0;
	}
	
	private void assignOffset(KeywordRole role, int offset, int length) {
		switch(role.id()) {
		case KeywordRole.ID_BEGINNING:
			begOffset = offset;
			begLength = length;
			break;
		case KeywordRole.ID_MIDDLE:
			midOffset = offset;
			midLength = length;
			break;
		case KeywordRole.ID_ENDING:
			endOffset = offset;
			endLength = length;
			break;
		default:
			throw new AssertionError();
		}
	}
	
	public int getOffset(KeywordRole role) {
		switch(role.id()) {
		case KeywordRole.ID_BEGINNING:
			return begOffset;
		case KeywordRole.ID_MIDDLE:
			return midOffset;
		case KeywordRole.ID_ENDING:
			return endOffset;
		default:
			throw new AssertionError();
		}
	}
	
	public int getLength(KeywordRole role) {
		switch(role.id()) {
		case KeywordRole.ID_BEGINNING:
			return begLength;
		case KeywordRole.ID_MIDDLE:
			return midLength;
		case KeywordRole.ID_ENDING:
			return endLength;
		default:
			throw new AssertionError();
		}
	}

	public int getBeginningLength() {
		return begLength;
	}

	public int getBeginningOffset() {
		return begOffset;
	}

	public IBlockSet getBlockSet() {
		return blockSet;
	}

	public int getEndingLength() {
		return endLength;
	}

	public int getEndingOffset() {
		return endOffset;
	}

	public int getMiddleLength() {
		return midLength;
	}

	public int getMiddleOffset() {
		return midOffset;
	}

	public void addToDebugString(StringBuffer r, IDocument document) {
		try {
			if (begOffset >= 0)
				r.append(document.get(begOffset, begLength));
			if (midOffset >= 0) {
				r.append(':');
				r.append(document.get(midOffset, midLength));
			}
			if (endOffset >= 0) {
				r.append('/');
				r.append(document.get(endOffset, endLength));
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}