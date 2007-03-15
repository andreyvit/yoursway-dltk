/**
 * 
 */
package org.eclipse.dltk.javascript.internal.ui.text;

import org.eclipse.jface.text.rules.IWordDetector;

class JavascriptDecoratorDetector implements IWordDetector{

    /**
     * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
     */
    public boolean isWordStart(char c) {
        return c == '@';
    }

    /**
     * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
     */
    public boolean isWordPart(char c) {
		return c != '\n' && c != '\r' && c != '(';
    }
    
}
