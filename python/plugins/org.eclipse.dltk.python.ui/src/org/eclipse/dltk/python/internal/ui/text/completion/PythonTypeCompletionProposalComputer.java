package org.eclipse.dltk.python.internal.ui.text.completion;

import org.eclipse.dltk.python.internal.ui.templates.PythonTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class PythonTypeCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new PythonTemplateCompletionProcessor(context);
	}

}
