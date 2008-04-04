package org.eclipse.dltk.examples.python.internal.completion;

import org.eclipse.dltk.examples.python.internal.completion.templates.ExamplePythonTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class ExamplePythonCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	public ExamplePythonCompletionProposalComputer() {
	}

	protected ScriptCompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context) {
		return new ExamplePythonCompletionProposalCollector(context.getSourceModule());
	}

	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new ExamplePythonTemplateCompletionProcessor(context);
	}
}
