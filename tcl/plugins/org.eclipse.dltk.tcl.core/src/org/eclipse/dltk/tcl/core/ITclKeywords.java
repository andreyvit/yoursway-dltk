package org.eclipse.dltk.tcl.core;

public interface ITclKeywords {
	public static final int ALL = 0;
	public static final int MODULE = 1;

	public static final int NAMESPACE = 2;

	public static final int FUNCTION = 3;

	public static final int EXEC_EXPRESSION = 4;
	
	public static final int METHOD = 5;
	
	public static final int CLASS = 6;
	
	public static final int START_INDEX= 1;
	public static final int END_INDEX = 7;
	
	String[] getKeywords();
	String[] getKeywords(int type);
}
