/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.completion;

import org.eclipse.dltk.ruby.internal.ui.templates.RubyTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

public class RubyTypeCompletionProposalComputer extends
		ScriptCompletionProposalComputer {

	public RubyTypeCompletionProposalComputer() {
	}

	protected ScriptCompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context) {
		return new RubyCompletionProposalCollector(context.getSourceModule());
	}

	protected TemplateCompletionProcessor createTemplateProposalComputer(
			ScriptContentAssistInvocationContext context) {
		return new RubyTemplateCompletionProcessor(context);
	}
}
