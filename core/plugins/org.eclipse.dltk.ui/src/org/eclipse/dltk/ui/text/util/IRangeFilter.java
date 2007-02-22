package org.eclipse.dltk.ui.text.util;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Tests whether given ranges satisfy an arbitrary criteria.
 * 
 * @author Andrey Tarantsov
 */
public interface IRangeFilter {
	
	boolean allowRange(IDocument document, int start, int length) throws BadLocationException;

}
