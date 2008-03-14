package org.eclipse.dltk.examples.python.internal.completion.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

public class ExamplePythonTemplateCompletionProcessor extends
		ScriptTemplateCompletionProcessor {

	private static char[] IGNORE = new char[] {'.'};
	
	public ExamplePythonTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);
	}

	protected String getContextTypeId() {
		return ExamplePythonUniversalTemplateContextType.CONTEXT_TYPE_ID;
	}

	protected char[] getIgnore() {
		return IGNORE;
	}
	protected ScriptTemplateAccess getTemplateAccess() {
		return ExamplePythonTemplateAccess.getInstance();
	}
}
