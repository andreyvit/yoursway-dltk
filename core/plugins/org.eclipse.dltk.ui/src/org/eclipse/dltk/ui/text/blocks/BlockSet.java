package org.eclipse.dltk.ui.text.blocks;

import java.util.HashMap;
import java.util.Map;

public abstract class BlockSet implements IBlockSet {

	protected Map keywordsToBlockSets;

	protected Map blockSetsToIntersections = new HashMap();

	public void setKeywordsToBlockSets(Map keywordsToBlockSets) {
		this.keywordsToBlockSets = keywordsToBlockSets;
	}

	public IBlockSet narrowByKeyword(Keyword keyword) {
		IBlockSet result = (IBlockSet) keywordsToBlockSets.get(keyword);
		if (result == null)
			return IBlockSet.EMPTY;
		else
			return result;
	}
	
	public void putIntersection(IBlockSet with, IBlockSet intersection) {
		blockSetsToIntersections.put(with, intersection);
	}

	public IBlockSet intersect(IBlockSet peer) {
		IBlockSet result = (IBlockSet) blockSetsToIntersections.get(peer);
		if (result == null)
			return IBlockSet.EMPTY;
		else
			return result;
	}

}
