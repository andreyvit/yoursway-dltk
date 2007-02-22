package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.completion.CompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptNoTypeCompletionProposalComputer;

public class TclNoTypeCompletionProposalComputer extends ScriptNoTypeCompletionProposalComputer {

	protected CompletionProposalCollector internalCreateCollector(ScriptContentAssistInvocationContext context) {
//		if (TclUI.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.CODEASSIST_FILL_ARGUMENT_NAMES))
//			return new ExperimentalResultCollector(context);
//		else
		return new TclCompletionProposalCollector(context.getSourceModule());
	}
}
