/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.blocks;

public interface IBlockSet {
	
	public static final Block[] NO_BLOCKS = new Block[0];
	
	public static final IBlockSet EMPTY = new IBlockSet() {

		public Block[] getBlocks() {
			return NO_BLOCKS;
		}

		public boolean isEmpty() {
			return true;
		}

		public IBlockSet narrowByKeyword(Keyword keyword) {
			return this;
		}

		public IBlockSet intersect(IBlockSet peer) {
			return this;
		}

	};
	
	boolean isEmpty();
	
	Block[] getBlocks();

	IBlockSet narrowByKeyword(Keyword keyword);
	
	IBlockSet intersect(IBlockSet peer);
	
}
