package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.swt.graphics.Image;


public class TclScriptCompletionProposal extends ScriptCompletionProposal {

	public TclScriptCompletionProposal(String replacementString, int replacementOffset, int replacementLength, Image image, String displayString, int relevance) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance);		
	}

	public TclScriptCompletionProposal(String replacementString, int replacementOffset, int replacementLength, Image image, String displayString, int relevance, boolean isInDoc) {
		super(replacementString, replacementOffset, replacementLength, image,
				displayString, relevance, isInDoc);
	}

	protected boolean isSmartTrigger(char trigger) {
		if( trigger == '$') {
			return true;
		}
		return false;
	}
}
