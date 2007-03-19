package org.eclipse.dltk.ruby.internal.ui.text.completion;

import org.eclipse.dltk.ruby.internal.ui.RubyTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.CompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class RubyTypeCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	public RubyTypeCompletionProposalComputer() {
	}

	protected CompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context) {
		return new RubyCompletionProposalCollector(context.getSourceModule());
	}

	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new RubyTemplateCompletionProcessor(context);
	}
}
