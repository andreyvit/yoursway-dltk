package org.eclipse.dltk.python.internal.ui.text.completion;

import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

public class PythonCompletionProposal extends ScriptCompletionProposal {

	public PythonCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance);
	}

	public PythonCompletionProposal(String replacementString,
			int replacementOffset, int replacementLength, Image image,
			String displayString, int relevance, boolean isInDoc) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance, isInDoc);
	}

	protected boolean isSmartTrigger(char trigger) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected boolean insertCompletion() {
		IPreferenceStore preference = PythonUI.getDefault().getPreferenceStore();
		return preference.getBoolean(PreferenceConstants.CODEASSIST_INSERT_COMPLETION);
	}

}
