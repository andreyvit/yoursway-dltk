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
