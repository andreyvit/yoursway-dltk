package org.eclipse.dltk.python.internal.ui.text.completion;

import org.eclipse.dltk.python.internal.ui.templates.PythonTempalteCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class PythonCompletionProposalComputer extends ScriptCompletionProposalComputer {
	public PythonCompletionProposalComputer() {
	}

	protected ScriptCompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
		return new PythonCompletionProposalCollector(context.getSourceModule());
	}

	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new PythonTempalteCompletionProcessor(context);
	}
}
