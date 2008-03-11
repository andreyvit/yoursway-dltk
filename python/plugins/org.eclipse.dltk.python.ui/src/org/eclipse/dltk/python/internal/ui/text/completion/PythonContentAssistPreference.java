package org.eclipse.dltk.python.internal.ui.text.completion;

import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class PythonContentAssistPreference extends ContentAssistPreference {
	static PythonContentAssistPreference sDefault;
	protected ScriptTextTools getTextTools() {
		return PythonUI.getDefault().getTextTools();
	}

	public static ContentAssistPreference getDefault() {
		if( sDefault == null ) {
			sDefault = new PythonContentAssistPreference();
		}
		return sDefault;
	}
}
