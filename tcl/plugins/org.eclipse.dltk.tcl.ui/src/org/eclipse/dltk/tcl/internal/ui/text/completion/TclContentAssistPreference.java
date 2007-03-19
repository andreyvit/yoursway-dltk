package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class TclContentAssistPreference extends ContentAssistPreference {
	static TclContentAssistPreference sDefault;
	protected ScriptTextTools getTextTools() {
		return TclUI.getDefault().getTextTools();
	}

	public static ContentAssistPreference getDefault() {
		if( sDefault == null ) {
			sDefault = new TclContentAssistPreference();
		}
		return sDefault;
	}
}
