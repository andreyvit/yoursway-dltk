package org.eclipse.dltk.tcl.internal.parsers.raw;

public interface ISubstitution {
	
	
	/**
	 * Reads substitution and initializes object from it. 
	 * @param scanner
	 * @return false if subs. doesn't fit
	 */
	public boolean readMe (CodeScanner scanner) throws TclParseException;
	
}
