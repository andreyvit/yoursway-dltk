package org.eclipse.dltk.typeinference;

public interface IContainedNodeElementInternal extends IContainedNodeElement {
	
	public static final int LOCATED_BEFORE = -2;
	
	public static final int LOCATED_INTERSECTS = -1;
	
	public static final int LOCATED_EQUAL = 0;
	
	public static final int LOCATED_AFTER = 2;
	
	public static final int LOCATED_OUTSIDE = 3;
	
	public static final int LOCATED_INSIDE = 4;
	
	int compareOffset(int start, int end);
	
}
