/**
 * 
 */
package org.eclipse.dltk.ui.text.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Platform;

/**
 * Represents a set of blocks and aggregates certain information about them.
 * 
 * @author Andrey Tarantsov
 */
public class BlocksConfiguration {
	
	private static final boolean DEBUG_BLOCKS_INTERSECTION = Boolean.valueOf(
		    Platform.getDebugOption("org.eclipse.dltk.ui/blocks/debugIntersection")).booleanValue();
	
	private Pattern beginMiddleEndPattern;

	private Pattern middleEndAlignedPattern;
	
	private Map singleBlockSets = new HashMap();
	
	private Map multiBlockSets = new HashMap();
	
	private Map wordsToKeywords = new HashMap();
	
	private IBlockSet rootBlockSet;
	
	public BlocksConfiguration(Block[] blocks) {
		MultiMap allNames = new MultiHashMapWithHashSet();
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			block.install(allNames);
		}
		
		for (Iterator iter = allNames.keySet().iterator(); iter.hasNext();) {
			Keyword keyword = (Keyword) iter.next();
			wordsToKeywords.put(keyword.getText(), keyword);
		}
		
		Set rootBlocks = new HashSet();
		rootBlocks.addAll(Arrays.asList(blocks));
		rootBlockSet = createBlockSet(rootBlocks);

		int lastBlockSetsCount = 0;
		int currentBlockSetsCount = singleBlockSets.size() + multiBlockSets.size();
		int index = 0;
		while (currentBlockSetsCount > lastBlockSetsCount) {
			Collection all = new ArrayList(currentBlockSetsCount);
			all.addAll(singleBlockSets.values());
			all.addAll(multiBlockSets.values());
			
			for (Iterator iter1 = all.iterator(); iter1.hasNext();) {
				BlockSet leftBlockSet = (BlockSet) iter1.next();
				Set leftBlocks = new HashSet();
				leftBlocks.addAll(Arrays.asList(leftBlockSet.getBlocks()));
				if (DEBUG_BLOCKS_INTERSECTION)
					System.out.println("Intersecting " + leftBlockSet);
				
				for (Iterator iter2 = all.iterator(); iter2.hasNext();) {
					BlockSet rightBlockSet = (BlockSet) iter2.next();
					Set rightBlocks = new HashSet();
					if (DEBUG_BLOCKS_INTERSECTION)
						System.out.print("   with " + rightBlockSet + "    #" + (index++));
					
					rightBlocks.addAll(Arrays.asList(rightBlockSet.getBlocks()));
					rightBlocks.retainAll(leftBlocks);
					if (!rightBlocks.isEmpty()) {
						IBlockSet blockSet = createBlockSet(rightBlocks);
						if (DEBUG_BLOCKS_INTERSECTION)
							System.out.println(" = " + blockSet);
						leftBlockSet.putIntersection(rightBlockSet, blockSet);
					} else {
						if (DEBUG_BLOCKS_INTERSECTION)
							System.out.println(" = ø");
					}
				}
			}
			
			lastBlockSetsCount = currentBlockSetsCount;
			currentBlockSetsCount = singleBlockSets.size() + multiBlockSets.size();
		}
		
		beginMiddleEndPattern = createBeginMiddleEndPattern(blocks);
		middleEndAlignedPattern = createMiddleEndAlignedPattern(blocks);
	}

	private IBlockSet createBlockSet(Set blocks) {
		if (blocks.isEmpty())
			return IBlockSet.EMPTY;
		BlockSet blockSet;
		if (blocks.size() == 1) {
			Block block = (Block) blocks.iterator().next();
			SingleBlockSet result = (SingleBlockSet) singleBlockSets.get(block);
			if (result != null)
				return result;
			result = new SingleBlockSet(block);
			singleBlockSets.put(block, result);
			blockSet = result;
		} else {
			AmbiguousBlockSet result = (AmbiguousBlockSet) multiBlockSets.get(blocks);
			if (result != null)
				return result;
			Block[] array = new Block[blocks.size()];
			blocks.toArray(array);
			result = new AmbiguousBlockSet(array);
			multiBlockSets.put(blocks, result);
			blockSet = result;
		}
		
		Map narrowings = new HashMap();
		for (Iterator iter = wordsToKeywords.values().iterator(); iter.hasNext();) {
			Keyword incomingKeyword = (Keyword) iter.next();
//			if (incomingKeyword.getText().equals("end"))
//				System.out.println();
			Set narrowBlocks = new HashSet();
			for (Iterator iterator = blocks.iterator(); iterator.hasNext();) {
				Block block = (Block) iterator.next();
				if (blockAllowsKeyword(block, incomingKeyword))
					narrowBlocks.add(block);
			}
			if (!narrowBlocks.isEmpty())
				narrowings.put(incomingKeyword, createBlockSet(narrowBlocks));
		}
		blockSet.setKeywordsToBlockSets(narrowings);
		
		return blockSet;
	}

	public static boolean blockAllowsKeyword(Block block, Keyword keyword) {
		if (block.getBeginning() == keyword || block.getEnding() == keyword)
			return true;
		Keyword[] middleKeywords = block.getMiddleKeywords();
		for (int i = 0; i < middleKeywords.length; i++)
			if (middleKeywords[i] == keyword)
				return true;
		return false;
	}

	private static Pattern createBeginMiddleEndPattern(Block[] blocks) {
		Set components = new HashSet();
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			components.add(block.getBeginningRegularExpression());
			components.addAll(block.getMiddleRegularExpressions());
			components.add(block.getEndingRegularExpression());
		}
		String regexp = join(components);
		return Pattern.compile(regexp);
	}
	
	private static Pattern createMiddleEndAlignedPattern(Block[] blocks) {
		Set components = new HashSet();
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			components.addAll(block.getMiddleRegularExpressions());
			components.add(block.getEndingRegularExpression());
		}
		String regexp = "^(?:" + join(components) + ")";
		return Pattern.compile(regexp);
	}

	private static String join(Set components) {
		StringBuffer regex = new StringBuffer();
		regex.append("(?:");
		boolean first = true;
		for (Iterator iter = components.iterator(); iter.hasNext();) {
			String component = (String) iter.next();
			if (first)
				first = false;
			else 
				regex.append("|");
			regex.append(component);
		}
		regex.append(")");
		String regexp = regex.toString();
		return regexp;
	}
	
	public Pattern getBeginMiddleEndPattern() {
		return beginMiddleEndPattern;
	}
	
	public Pattern getMiddleEndAlignedPattern() {
		return middleEndAlignedPattern;
	}

	public IBlockSet getRootBlockSet() {
		return rootBlockSet;
	}
	
	public Keyword getKeyword(String word) {
		return (Keyword) wordsToKeywords.get(word);
	}
	
}