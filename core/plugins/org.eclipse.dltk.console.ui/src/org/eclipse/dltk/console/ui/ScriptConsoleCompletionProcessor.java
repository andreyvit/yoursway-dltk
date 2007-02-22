package org.eclipse.dltk.console.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.console.IScriptConsoleShell;
import org.eclipse.dltk.console.ScriptConsoleCompletionProposal;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;


public abstract class ScriptConsoleCompletionProcessor implements
		IContentAssistProcessor {
	protected static interface IProposalDecorator {
		String formatProposal(ScriptConsoleCompletionProposal c);

		Image getImage(ScriptConsoleCompletionProposal c);
	}

	private static IProposalDecorator defaultDecorator;

	protected IProposalDecorator getDefaultDecorator() {
		if (defaultDecorator == null) {
			defaultDecorator = new IProposalDecorator() {
				public String formatProposal(ScriptConsoleCompletionProposal c) {
					return c.getDisplay();
				}

				public Image getImage(ScriptConsoleCompletionProposal c) {
					return null;
				}
			};
		}

		return defaultDecorator;
	}

	private IScriptConsoleShell interpreterShell;

	public ScriptConsoleCompletionProcessor(
			IScriptConsoleShell interpreterShell) {
		this.interpreterShell = interpreterShell;
	}

	protected IScriptConsoleShell getInterpreterShell() {
		return this.interpreterShell;
	}

	protected List createProposalsFromString(List list, int offset,
			IProposalDecorator decorator) {
		
		if (decorator == null){
			decorator = getDefaultDecorator();
		}

		List result = new ArrayList();

		Iterator it = list.iterator();
		while (it.hasNext()) {
			ScriptConsoleCompletionProposal c = (ScriptConsoleCompletionProposal) it.next();

			CompletionProposal proposal = new CompletionProposal(c.getInsert(), // replacementString
					offset, // replacementOffset
					0, // replacementLength
					c.getInsert().length(), // cursorPosition
					decorator.getImage(c), // image
					decorator.formatProposal(c), // displayString
					null, // contextInformation
					null); // additionalContextInformation

			result.add(proposal);
		}

		Collections.sort(result, new Comparator() {
			public int compare(Object o1, Object o2) {
				CompletionProposal p1 = (CompletionProposal) o1;
				CompletionProposal p2 = (CompletionProposal) o2;
				return p1.getDisplayString().compareTo(p2.getDisplayString());
			}
		});

		return result;
	}

	protected abstract ICompletionProposal[] computeCompletionProposalsImpl(
			IScriptConsoleViewer viewer, int offset);

	protected abstract IContextInformation[] computeContextInformationImpl(
			ITextViewer viewer, int offset);

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		return computeCompletionProposalsImpl((IScriptConsoleViewer) viewer,
				offset);
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return computeContextInformationImpl((IScriptConsoleViewer) viewer,
				offset);
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}
}
