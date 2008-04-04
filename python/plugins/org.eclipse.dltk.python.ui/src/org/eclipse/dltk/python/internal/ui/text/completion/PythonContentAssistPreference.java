package org.eclipse.dltk.python.internal.ui.text.completion;

import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class PythonContentAssistPreference extends ContentAssistPreference {

	private static PythonContentAssistPreference instance;
	
	public static ContentAssistPreference getDefault() {
		if (instance == null) {
			instance = new PythonContentAssistPreference();
		}
		
		return instance;
	}
				
	/*
	 * @see org.eclipse.dltk.ui.text.completion.ContentAssistPreference#getTextTools()
	 */
	protected ScriptTextTools getTextTools() {
		return PythonUI.getDefault().getTextTools();
	}

}
