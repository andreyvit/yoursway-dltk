package org.eclipse.dltk.ruby.internal.ui.text.completion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.ruby.internal.ui.RubyTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.CompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class RubyTypeCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	public RubyTypeCompletionProposalComputer() {
	}

	public List computeContextInformation(
			ContentAssistInvocationContext context, IProgressMonitor monitor) {
			System.out
			.println("RubyTypeCompletionProposalComputer.computeContextInformation()");
		
		// if (context instanceof ScriptContentAssistInvocationContext) {
		// ScriptContentAssistInvocationContext scriptContext=
		// (ScriptContentAssistInvocationContext) context;
		//			
		// int contextInformationPosition=
		// guessContextInformationPosition(scriptContext);
		// List result= addContextInformations(scriptContext,
		// contextInformationPosition, monitor);
		// return result;
		// }
		// return Collections.EMPTY_LIST;

		List types = computeCompletionProposals(context, monitor);
		if (DLTKCore.DEBUG) {
			System.out.println("!!! Proposals: " + types.size());
		}
		Iterator iter = types.iterator();

		List list = new ArrayList();
		while (iter.hasNext()) {
			IScriptCompletionProposal proposal = (IScriptCompletionProposal) iter
					.next();
			IContextInformation contextInformation = proposal
					.getContextInformation();
			if (contextInformation == null) {
				continue;
			}
			if (DLTKCore.DEBUG) {
				System.out.println("Proposal: " + proposal + ", info: "
						+ contextInformation.getInformationDisplayString());
			}
			list.add(contextInformation);
		}
		return list;
	}
	
	public List computeCompletionProposals(
			ContentAssistInvocationContext context, IProgressMonitor monitor) {	
		List proposals = super.computeCompletionProposals(context, monitor);
		
		//Add code template completion proposals, just for test
		
		
		return proposals;
	}

	protected CompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context) {
		return new RubyCompletionProposalCollector(context.getSourceModule());
	}
	
	protected TemplateCompletionProcessor createTemplateProposalComputer() {
		return new RubyTemplateCompletionProcessor();
	}
}
