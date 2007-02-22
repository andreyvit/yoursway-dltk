package org.eclipse.dltk.ruby.internal.debug.ui.launcher;


import org.eclipse.dltk.internal.debug.ui.launcher.AbstractNoInterpreterStatusHandler;
import org.eclipse.dltk.ruby.internal.debug.ui.interpreters.RubyInterpreterPreferencePage;
import org.eclipse.jface.preference.IPreferencePage;


/**
 * Prompts the user to setup interpreters
 */
public class NoDefaultRubyInterpreterStatusHandler extends AbstractNoInterpreterStatusHandler
{
    //~ Methods

    protected void showInterpreterPreferencePage()
    {
        IPreferencePage page = new RubyInterpreterPreferencePage();
        showPrefPage("org.eclipse.dltk.ruby.debug.ui.interpreters.RubyInterpreterPreferencePage",
            page); // $NON-NLS-1$
    }

}
