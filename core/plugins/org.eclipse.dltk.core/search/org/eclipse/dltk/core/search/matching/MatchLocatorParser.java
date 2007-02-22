package org.eclipse.dltk.core.search.matching;

import org.eclipse.dltk.core.search.IMatchLocatorParser;
import org.eclipse.dltk.internal.core.search.matching.MatchingNodeSet;

public abstract class MatchLocatorParser implements IMatchLocatorParser {
	private MatchLocator matchLocator;
	private PatternLocator patternLocator;
	
	private MatchingNodeSet nodeSet;	
		
	public void setNodeSet(MatchingNodeSet nodeSet) {
		this.nodeSet = nodeSet;
	}
	
	protected MatchingNodeSet getNodeSet(){
		return nodeSet;
	}
		
	protected MatchLocatorParser(MatchLocator locator) {
		this.matchLocator = locator;
		this.patternLocator = locator.patternLocator;
	}
	
	protected MatchLocator getMatchLocator(){
		return matchLocator;
	}
	
	protected PatternLocator getPatternLocator(){
		return patternLocator; 
	}	
}
