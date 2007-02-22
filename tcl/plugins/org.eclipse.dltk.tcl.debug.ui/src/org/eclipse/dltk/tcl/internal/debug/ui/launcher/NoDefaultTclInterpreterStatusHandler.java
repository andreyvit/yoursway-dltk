package org.eclipse.dltk.tcl.internal.debug.ui.launcher;

import org.eclipse.dltk.internal.debug.ui.launcher.AbstractNoInterpreterStatusHandler;
import org.eclipse.dltk.tcl.internal.debug.ui.interpreters.TclInterpreterPreferencePage;
import org.eclipse.jface.preference.IPreferencePage;

/**
 * Prompts the user to setup interpreters
 */
public class NoDefaultTclInterpreterStatusHandler extends
		AbstractNoInterpreterStatusHandler {

	protected void showInterpreterPreferencePage() {
		IPreferencePage page = new TclInterpreterPreferencePage();
		showPrefPage(TclInterpreterPreferencePage.PAGE_ID, page);
	}

}
