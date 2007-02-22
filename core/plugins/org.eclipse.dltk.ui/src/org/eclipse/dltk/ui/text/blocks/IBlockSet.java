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
