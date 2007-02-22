package org.eclipse.dltk.ruby.internal.ui.text.completion;

import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.text.TextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class RubyContentAssistPreference extends ContentAssistPreference {
	static RubyContentAssistPreference sDefault;
	protected TextTools getTextTools() {
		return RubyUI.getDefault().getTextTools();
	}

	public static ContentAssistPreference getDefault() {
		if( sDefault == null ) {
			sDefault = new RubyContentAssistPreference();
		}
		return sDefault;
	}
}
