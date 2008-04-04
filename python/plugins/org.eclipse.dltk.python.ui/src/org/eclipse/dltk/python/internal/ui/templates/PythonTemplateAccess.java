package org.eclipse.dltk.python.internal.ui.templates;

import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Provides access to the Python template store.
 */
public class PythonTemplateAccess extends ScriptTemplateAccess {

	private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.dtlk.python.Templates";

	private static PythonTemplateAccess instance;
	
	public static PythonTemplateAccess getInstance() {
		if (instance == null) {
			instance = new PythonTemplateAccess();
		}
		
		return instance;
	}
	
	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getContextTypeId()
	 */
	protected String getContextTypeId() {
		return PythonUniversalTemplateContextType.CONTEXT_TYPE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getCustomTemplatesKey()
	 */
	protected String getCustomTemplatesKey() {
		return CUSTOM_TEMPLATES_KEY;
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplateAccess#getPreferenceStore()
	 */
	protected IPreferenceStore getPreferenceStore() {
		return PythonUI.getDefault().getPreferenceStore();
	}

}
