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
