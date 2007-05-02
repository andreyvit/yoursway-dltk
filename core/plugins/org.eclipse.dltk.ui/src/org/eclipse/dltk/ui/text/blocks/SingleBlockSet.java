/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.blocks;

public class SingleBlockSet extends BlockSet {
	
	private Block[] blocks;
	
	public SingleBlockSet(Block block) {
		blocks = new Block[] {block};
	}

	public Block[] getBlocks() {
		return blocks;
	}

	public boolean isEmpty() {
		return false;
	}
	
	public String toString() {
		return blocks[0].toString();
	}

}
