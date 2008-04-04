package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.dltk.examples.internal.python.core.ExamplePythonNature;
import org.eclipse.dltk.examples.internal.python.core.PythonCorePlugin;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

public class ExamplePythonCompletionProcessor extends ScriptCompletionProcessor {
	public ExamplePythonCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	protected String getNatureId() {
		return ExamplePythonNature.PYTHON_NATURE;
	}

	protected CompletionProposalLabelProvider getProposalLabelProvider() {
		return new CompletionProposalLabelProvider();
	}
	protected IPreferenceStore getPreferenceStore() {
		return PythonCorePlugin.getDefault().getPreferenceStore();
	}
}
