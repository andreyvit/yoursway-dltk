package org.eclipse.dltk.tcl.internal.tclchecker;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class TclCheckerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public TclCheckerPreferenceInitializer() {
	}

	public void initializeDefaultPreferences() {
		IPreferenceStore store = TclCheckerPlugin.getDefault()
				.getPreferenceStore();
		store.setDefault(TclCheckerConstants.PREF_MODE,
				TclCheckerConstants.MODE_DEFAULT);

		List problems = TclCheckerProblemDescription.getProblemIdentifiers();
		Iterator it = problems.iterator();
		while (it.hasNext()) {
			store.setDefault((String) it.next(), false);
		}
	}
}
