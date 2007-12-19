package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class ToggleShowClassVariablesActionDelegate implements
		IViewActionDelegate {

	protected Preferences getPrefs() {
		return DLTKDebugPlugin.getDefault().getPluginPreferences();
	}

	public void init(IViewPart view) {

	}

	public void run(IAction action) {
		boolean value = getPrefs().getBoolean(
				DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS);
		getPrefs().setValue(
				DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS, !value);
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
