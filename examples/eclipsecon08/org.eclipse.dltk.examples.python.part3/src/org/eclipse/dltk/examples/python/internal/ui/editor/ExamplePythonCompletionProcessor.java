/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.dltk.examples.internal.python.core.ExamplePythonNature;
import org.eclipse.dltk.examples.internal.python.core.PythonCorePlugin;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

/**
 * Tcl completion processor
 */
public class ExamplePythonCompletionProcessor extends ScriptCompletionProcessor {

	public ExamplePythonCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getNatureId()
	 */
	protected String getNatureId() {
		return ExamplePythonNature.PYTHON_NATURE;
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getProposalLabelProvider()
	 */
	protected CompletionProposalLabelProvider getProposalLabelProvider() {
		return new CompletionProposalLabelProvider();
	}

	/*
	 * @see org.eclipse.dltk.ui.text.completion.ContentAssistProcessor#getPreferenceStore()
	 */
	protected IPreferenceStore getPreferenceStore() {
		return PythonCorePlugin.getDefault().getPreferenceStore();
	}
}
