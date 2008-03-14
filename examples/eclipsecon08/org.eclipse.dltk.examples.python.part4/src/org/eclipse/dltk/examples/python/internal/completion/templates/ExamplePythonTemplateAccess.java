package org.eclipse.dltk.examples.python.internal.completion.templates;

import org.eclipse.dltk.examples.python.internal.ExamplePythonUI;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;

public class ExamplePythonTemplateAccess extends ScriptTemplateAccess {

	private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.dtlk.example.python.Templates";

	private static ExamplePythonTemplateAccess instance;
	
	public static ExamplePythonTemplateAccess getInstance() {
		if (instance == null) {
			instance = new ExamplePythonTemplateAccess();
		}
		
		return instance;
	}
	
	protected String getContextTypeId() {
		return ExamplePythonUniversalTemplateContextType.CONTEXT_TYPE_ID;
	}
	protected String getCustomTemplatesKey() {
		return CUSTOM_TEMPLATES_KEY;
	}

	protected IPreferenceStore getPreferenceStore() {
		return ExamplePythonUI.getDefault().getPreferenceStore();
	}
}
