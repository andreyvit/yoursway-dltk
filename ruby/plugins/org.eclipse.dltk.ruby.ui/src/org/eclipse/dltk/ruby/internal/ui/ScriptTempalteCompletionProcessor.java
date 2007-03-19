package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public abstract class ScriptTempalteCompletionProcessor extends
		TemplateCompletionProcessor {

	private ScriptContentAssistInvocationContext context;

	public ScriptTempalteCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		if (context == null) {
			throw new IllegalArgumentException();
		}

		this.context = context;
	}

	protected ScriptContentAssistInvocationContext getContext() {
		return this.context;
	}
}
