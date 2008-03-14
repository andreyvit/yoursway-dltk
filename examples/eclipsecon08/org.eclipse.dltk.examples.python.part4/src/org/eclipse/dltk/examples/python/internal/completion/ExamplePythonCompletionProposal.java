package org.eclipse.dltk.examples.python.internal.completion;

import org.eclipse.dltk.examples.internal.python.core.PythonCorePlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

public class ExamplePythonCompletionProposal extends ScriptCompletionProposal {
	public ExamplePythonCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance);
	}
	public ExamplePythonCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance, boolean isInDoc) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance, isInDoc);
	}
	protected boolean isSmartTrigger(char trigger) {
		if (trigger == '.') {
			return true;
		}
		return false;
	}
	protected boolean insertCompletion() {
		IPreferenceStore preference = PythonCorePlugin.getDefault()
				.getPreferenceStore();
		return preference
				.getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
	}
}
