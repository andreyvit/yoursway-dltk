/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.blocks;


public class AmbiguousBlockSet extends BlockSet {
	
	private Block[] blocks;
	
	public AmbiguousBlockSet(Block[] blocks) {
		this.blocks = blocks;
	}
	
	public Block[] getBlocks() {
		return blocks;
	}

	public boolean isEmpty() {
		return false;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			if (i > 0) 
				buf.append(' ');
			buf.append(block.toString());
		}
		return buf.toString();
	}
	
}
