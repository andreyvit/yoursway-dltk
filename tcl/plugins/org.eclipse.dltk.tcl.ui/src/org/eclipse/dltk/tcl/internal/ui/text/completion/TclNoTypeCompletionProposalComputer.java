package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

public class TclNoTypeCompletionProposalComputer extends ScriptCompletionProposalComputer {

	protected ScriptCompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
		ScriptCompletionProposalCollector collector =	new TclCompletionProposalCollector(context.getSourceModule());
		
		collector.setIgnored(CompletionProposal.ANNOTATION_ATTRIBUTE_REF, false);
		collector.setIgnored(CompletionProposal.FIELD_REF, false);
		collector.setIgnored(CompletionProposal.KEYWORD, false);
		collector.setIgnored(CompletionProposal.PACKAGE_REF, false);
		collector.setIgnored(CompletionProposal.LABEL_REF, false);
		collector.setIgnored(CompletionProposal.LOCAL_VARIABLE_REF, false);
		collector.setIgnored(CompletionProposal.METHOD_DECLARATION, false);
		collector.setIgnored(CompletionProposal.METHOD_NAME_REFERENCE, false);
		collector.setIgnored(CompletionProposal.METHOD_REF, false);
		collector.setIgnored(CompletionProposal.POTENTIAL_METHOD_DECLARATION, false);
		collector.setIgnored(CompletionProposal.VARIABLE_DECLARATION, false);
			
		collector.setIgnored(CompletionProposal.TYPE_REF, true);
		
		return collector;
	}
}
