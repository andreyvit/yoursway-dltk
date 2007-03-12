package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.ui.text.completion.LazyScriptTypeCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class LazyTclTypeCompletionProposal extends
		LazyScriptTypeCompletionProposal {
	protected static final char[] TYPE_TRIGGERS = new char[] { '.', '\t', '[',
			'(', ' ' };
	protected static final char[] DOC_TYPE_TRIGGERS = new char[] { '#', '}',
			' ', '.' };

	public LazyTclTypeCompletionProposal(CompletionProposal proposal,
			ScriptContentAssistInvocationContext context) {
		super(proposal, context);
	}

	protected char[] getDocTriggers() {
		return DOC_TYPE_TRIGGERS;
	}

	protected char[] getTypeTriggers() {
		return TYPE_TRIGGERS;
	}

	protected void handleSmartTrigger(IDocument document, char trigger,
			int referenceOffset) throws BadLocationException {
		// TODO Auto-generated method stub
	}

	protected boolean isSmartTrigger(char trigger) {
		if (trigger == '$') {
			return true;
		}

		return false;
	}
}
