/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.text.completion;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.ui.text.completion.CompletionProposalLabelProvider;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorPart;


public class TclScriptCompletionProcessor extends ScriptCompletionProcessor {
	public TclScriptCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(editor, assistant, partition);
	}

	protected IPreferenceStore getPreferenceStore() {
		return TclUI.getDefault().getPreferenceStore();
	}

	protected ContentAssistInvocationContext createContext(ITextViewer viewer,
			int offset) {
		return new ScriptContentAssistInvocationContext(viewer, offset,
				fEditor, TclNature.NATURE_ID) {
			protected CompletionProposalLabelProvider createLabelProvider() {
				return new TclCompletionProposalLabelProvider();
			}
		};
	}

	protected static class Validator implements IContextInformationValidator,
			IContextInformationPresenter {

		private int initialOffset;
		
		public boolean isContextInformationValid(int offset) {
			return Math.abs(offset - initialOffset) < 5;
		}

		public void install(IContextInformation info, ITextViewer viewer,
				int offset) {			
			initialOffset = offset;
		}

		public boolean updatePresentation(int documentPosition,
				TextPresentation presentation) {
			return false;
		}
	}

	private IContextInformationValidator validator;

	public IContextInformationValidator getContextInformationValidator() {
		if (validator == null) {
			validator = new Validator();
		}
		return validator;
	}
}
