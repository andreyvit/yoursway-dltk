package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.ScriptElementLabels;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;

public class RubyUILanguageToolkit implements IDLTKUILanguageToolkit {
	private static ScriptElementLabels sInstance = new ScriptElementLabels() {
	};
	
	private static RubyUILanguageToolkit sToolkit = null;
	
	public static IDLTKUILanguageToolkit getInstance() {
		if( sToolkit == null ) {
			sToolkit = new RubyUILanguageToolkit();
		}
		return sToolkit;
	}

	public ScriptElementLabels getScriptElementLabels() {
		return sInstance;
	}

	public IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}

	public IDLTKLanguageToolkit getCoreToolkit() {
		return RubyLanguageToolkit.getDefault();
	}
	public IDialogSettings getDialogSettings() {
		return RubyUI.getDefault().getDialogSettings();
	}
}
