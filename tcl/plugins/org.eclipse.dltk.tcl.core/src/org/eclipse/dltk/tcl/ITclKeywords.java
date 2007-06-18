package org.eclipse.dltk.tcl;

public interface ITclKeywords {
	public static final int MODULE = 0;

	public static final int NAMESPACE = 1;

	public static final int FUNCTION = 2;

	public static final int EXEC_EXPRESSION = 3;
	
	public static final int METHOD = 4;
	
	public static final int CLASS = 5;
	
	String[] getKeywords();
	String[] getKeywords(int type);
}
