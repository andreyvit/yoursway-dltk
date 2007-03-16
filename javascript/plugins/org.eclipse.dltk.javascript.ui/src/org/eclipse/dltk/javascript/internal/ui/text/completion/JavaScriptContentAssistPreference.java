package org.eclipse.dltk.javascript.internal.ui.text.completion;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class JavaScriptContentAssistPreference extends ContentAssistPreference {
	static JavaScriptContentAssistPreference sDefault;
	protected ScriptTextTools getTextTools() {
		return JavaScriptUI.getDefault().getTextTools();
	}

	public static ContentAssistPreference getDefault() {
		if( sDefault == null ) {
			sDefault = new JavaScriptContentAssistPreference();
		}
		return sDefault;
	}
}
