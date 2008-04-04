/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.ui.text.completion;

import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

/**
 * JavaScript completion processor
 */
public class JavaScriptCompletionProcessor extends ScriptCompletionProcessor {
	
	public JavaScriptCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getNatureId()
	 */
	protected String getNatureId() {
		return JavaScriptNature.NATURE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getProposalLabelProvider()
	 */
	protected CompletionProposalLabelProvider getProposalLabelProvider() {
		return new JavaScriptCompletionProposalLabelProvider();
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ContentAssistProcessor#getPreferenceStore()
	 */
	protected IPreferenceStore getPreferenceStore() {
		return JavaScriptUI.getDefault().getPreferenceStore();
	}

	
}
