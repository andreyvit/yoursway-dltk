/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.text.completion;

import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

/**
 * Ruby completion processor
 */
public class RubyCompletionProcessor extends ScriptCompletionProcessor {

	public RubyCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getNatureId()
	 */
	protected String getNatureId() {
		return RubyNature.NATURE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getProposalLabelProvider()
	 */
	protected CompletionProposalLabelProvider getProposalLabelProvider() {
		return new RubyCompletionProposalLabelProvider();
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ContentAssistProcessor#getPreferenceStore()
	 */
	protected IPreferenceStore getPreferenceStore() {
		return RubyUI.getDefault().getPreferenceStore();
	}

}
