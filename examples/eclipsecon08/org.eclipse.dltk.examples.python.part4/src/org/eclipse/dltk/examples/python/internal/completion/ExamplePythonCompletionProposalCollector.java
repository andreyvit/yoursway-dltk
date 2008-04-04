package org.eclipse.dltk.examples.python.internal.completion;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptOverrideCompletionProposal;
import org.eclipse.swt.graphics.Image;

public class ExamplePythonCompletionProposalCollector extends
		ScriptCompletionProposalCollector {

	protected final static char[] VAR_TRIGGER = new char[] { '\t', ' ', '=',
			';', '.' };

	protected char[] getVarTrigger() {
		return VAR_TRIGGER;
	}

	public ExamplePythonCompletionProposalCollector(ISourceModule module) {
		super(module);
	}

	// Specific proposals creation. May be use factory?
	protected ScriptCompletionProposal createScriptCompletionProposal(
			String completion, int replaceStart, int length, Image image,
			String displayString, int i) {
		return new ExamplePythonCompletionProposal(completion, replaceStart, length,
				image, displayString, i);
	}

	protected ScriptCompletionProposal createScriptCompletionProposal(
			String completion, int replaceStart, int length, Image image,
			String displayString, int i, boolean isInDoc) {
		return new ExamplePythonCompletionProposal(completion, replaceStart, length,
				image, displayString, i, isInDoc);
	}

	protected ScriptCompletionProposal createOverrideCompletionProposal(
			IScriptProject scriptProject, ISourceModule compilationUnit,
			String name, String[] paramTypes, int start, int length,
			String displayName, String completionProposal) {
		return new ScriptOverrideCompletionProposal(scriptProject, compilationUnit,
				name, paramTypes, start, length, displayName,
				completionProposal);
	}
}
