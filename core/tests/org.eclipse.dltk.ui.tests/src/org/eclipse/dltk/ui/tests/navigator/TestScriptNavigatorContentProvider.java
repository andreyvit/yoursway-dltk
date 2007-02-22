package org.eclipse.dltk.ui.tests.navigator;

import org.eclipse.dltk.internal.ui.navigator.ScriptNavigatorContentProvider;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;


public class TestScriptNavigatorContentProvider extends ScriptNavigatorContentProvider {

	protected IPreferenceStore getPreferenceStore() {
		return DLTKUIPlugin.getDefault().getPreferenceStore();
	}
}
