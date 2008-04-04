package org.eclipse.dltk.python.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

/**
 * Python template completion processor
 */
public class PythonTemplateCompletionProcessor extends
		ScriptTemplateCompletionProcessor {

	private static char[] IGNORE = new char[] {'.'};
	
	public PythonTemplateCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		super(context);
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getContextTypeId()
	 */
	protected String getContextTypeId() {
		return PythonUniversalTemplateContextType.CONTEXT_TYPE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getIgnore()
	 */
	protected char[] getIgnore() {
		return IGNORE;
	}
	
	/*
	 * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getTemplateAccess()
	 */
	protected ScriptTemplateAccess getTemplateAccess() {
		return PythonTemplateAccess.getInstance();
	}
}
