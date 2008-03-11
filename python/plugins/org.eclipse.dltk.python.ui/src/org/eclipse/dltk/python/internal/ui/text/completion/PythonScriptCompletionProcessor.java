package org.eclipse.dltk.python.internal.ui.text.completion;

import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

public class PythonScriptCompletionProcessor extends ScriptCompletionProcessor {

	public PythonScriptCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	protected ContentAssistInvocationContext createContext(ITextViewer viewer,
			int offset) {
		return new ScriptContentAssistInvocationContext(viewer, offset,
				fEditor, PythonNature.NATURE_ID) {
			protected CompletionProposalLabelProvider createLabelProvider() {
				return new PythonCompletionProposalLabelProvider();
			}
		};
	}

	protected IPreferenceStore getPreferenceStore() {
		return PythonUI.getDefault().getPreferenceStore();
	}
//TODO context information validator
}
