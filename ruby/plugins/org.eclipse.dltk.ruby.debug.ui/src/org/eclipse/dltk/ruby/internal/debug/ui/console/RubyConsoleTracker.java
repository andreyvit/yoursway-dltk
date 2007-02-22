package org.eclipse.dltk.ruby.internal.debug.ui.console;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListenerDelegate;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

/**
 * Provides links for stack traces
 */
public class RubyConsoleTracker implements IPatternMatchListenerDelegate {
	
	/**
	 * The console associated with this line tracker 
	 */
	private TextConsole fConsole;

    /* (non-Javadoc)
     * @see org.eclipse.ui.console.IPatternMatchListenerDelegate#connect(org.eclipse.ui.console.IConsole)
     */
    public void connect(TextConsole console) {
	    fConsole = console;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.console.IPatternMatchListenerDelegate#disconnect()
     */
    public void disconnect() {
        fConsole = null;
    }
    
    protected TextConsole getConsole() {
        return fConsole;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.console.IPatternMatchListenerDelegate#matchFound(org.eclipse.ui.console.PatternMatchEvent)
     */
    public void matchFound(PatternMatchEvent event) {
        try {
            int offset = event.getOffset();
            int length = event.getLength();
            IHyperlink link = new RubyFileHyperlink(fConsole);
            fConsole.addHyperlink(link, offset, length);   
        } catch (BadLocationException e) {
        }
    }

}