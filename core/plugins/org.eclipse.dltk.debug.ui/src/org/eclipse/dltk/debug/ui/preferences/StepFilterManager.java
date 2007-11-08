package org.eclipse.dltk.debug.ui.preferences;

import org.eclipse.dltk.internal.debug.ui.ScriptDebugOptionsManager;
import org.eclipse.jface.preference.IPreferenceStore;

public class StepFilterManager {
	public static Filter[] getActiveFilters(IPreferenceStore store) {
		Filter[] filters = null;
		String[] activefilters;
		activefilters = ScriptDebugOptionsManager
				.parseList(store
						.getString(IDLTKDebugPreferenceConstants.PREF_ACTIVE_FILTERS_LIST));
		filters = new Filter[activefilters.length];
		for (int i = 0; i < activefilters.length; i++) {
			String[] split = activefilters[i].split(":");
			if( split.length == 1 ) {
				filters[i] = new Filter(split[0], true, 0);
			}
			else {
				filters[i] = new Filter(split[0], true, (new Integer(split[1])).intValue());
			}
		}

		return filters;
	}
}
