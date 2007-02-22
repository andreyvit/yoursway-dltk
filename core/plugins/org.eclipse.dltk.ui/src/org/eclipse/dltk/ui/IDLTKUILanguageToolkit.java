package org.eclipse.dltk.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;

public interface IDLTKUILanguageToolkit {
	ScriptElementLabels getScriptElementLabels();

	IPreferenceStore getPreferenceStore();

	IDLTKLanguageToolkit getCoreToolkit();

	IDialogSettings getDialogSettings();
}
