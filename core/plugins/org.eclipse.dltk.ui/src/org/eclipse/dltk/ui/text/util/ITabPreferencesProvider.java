package org.eclipse.dltk.ui.text.util;

/**
 * Provides information about the preferred indentation options.
 * 
 * @author Andrey Tarantsov
 */
public interface ITabPreferencesProvider {
	
	public int getIndentSize();
	
	public int getTabSize();
	
	public TabStyle getTabStyle();
	
	public String getIndent();
	
	public String getIndentByVirtualSize(int size);

	public String getIndent(int count);

}
