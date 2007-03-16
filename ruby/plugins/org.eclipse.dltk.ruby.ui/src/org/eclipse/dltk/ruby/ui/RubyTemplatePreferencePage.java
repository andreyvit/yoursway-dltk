package org.eclipse.dltk.ruby.ui;

import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;

public class RubyTemplatePreferencePage extends TemplatePreferencePage
		implements IWorkbenchPreferencePage {
	public RubyTemplatePreferencePage() {
		 setPreferenceStore(RubyUI.getDefault().getPreferenceStore());
		 setTemplateStore(RubyUI.getDefault().getTemplateStore());
		 setContextTypeRegistry(RubyUI.getDefault().getContextTypeRegistry());
	}

	protected boolean isShowFormatterSetting() {
		return true;
	}

	public boolean performOk() {
		boolean ok = super.performOk();

		// PydevPlugin.getDefault().savePluginPreferences();

		return ok;
	}
}
