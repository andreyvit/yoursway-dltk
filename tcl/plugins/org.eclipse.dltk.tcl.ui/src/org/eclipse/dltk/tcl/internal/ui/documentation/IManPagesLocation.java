package org.eclipse.dltk.tcl.internal.ui.documentation;

import java.io.Reader;

public interface IManPagesLocation {
	
	/**
	 * Should find inside location for an information about keyword
	 * @param keyword
	 * @return Reader with html code
	 */
	public Reader getHtmlInfo (String keyword);
	
}
