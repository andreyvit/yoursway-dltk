/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.tcl.internal.core.codeassist.ITclCompletionProposalTypes;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.templates.TclTemplateCompletionProcessor;
import org.eclipse.dltk.tcl.ui.TclPreferenceConstants;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class TclTypeCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	protected ScriptCompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context) {
		ScriptCompletionProposalCollector collector = new TclCompletionProposalCollector(
				context.getSourceModule());

		collector.setIgnored(CompletionProposal.FIELD_REF, true);
		collector.setIgnored(CompletionProposal.KEYWORD, true);
		collector.setIgnored(CompletionProposal.LABEL_REF, true);
		collector.setIgnored(CompletionProposal.LOCAL_VARIABLE_REF, true);
		collector.setIgnored(CompletionProposal.METHOD_DECLARATION, true);
		collector.setIgnored(CompletionProposal.METHOD_NAME_REFERENCE, true);
		collector.setIgnored(CompletionProposal.METHOD_REF, true);
		collector.setIgnored(CompletionProposal.POTENTIAL_METHOD_DECLARATION,
				true);
		collector.setIgnored(CompletionProposal.VARIABLE_DECLARATION, true);

		collector.setIgnored(CompletionProposal.TYPE_REF, false);

		IPreferenceStore preferenceStore = TclUI.getDefault()
				.getPreferenceStore();
		collector
				.setIgnored(
						ITclCompletionProposalTypes.FILTER_INTERNAL_API,
						preferenceStore
								.getBoolean(TclPreferenceConstants.CODEASSIST_FILTER_INTERNAL_API));

		return collector;
	}

	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new TclTemplateCompletionProcessor(context);
	}
}
