/**
 * 
 */
package org.eclipse.dltk.ui.text.blocks;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Represents a condition that refines the context where a block's word can be matched.
 * 
 * @author Andrey Tarantsov
 */
public class Recognition {
	
	private final String regexPrefix;
	private final String regexSuffix;

	public Recognition(String regexPrefix, String regexSuffix) {
		this.regexPrefix = regexPrefix;
		this.regexSuffix = regexSuffix;
	}
	
	public static final Recognition ANYWHERE = new Recognition("", "");

	public static final Recognition WORD_BOUNDARY = new Recognition("\\b", "\\b");
	
	public boolean canMatchAt(IDocument document, int offset) throws BadLocationException {
		return true;
	}
	
	public String decorateRegularExploression(String string) {
		return regexPrefix + string + regexSuffix;
	}
	
}