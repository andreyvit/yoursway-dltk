/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorPart;

/**
 * Script completion processor.
 */
public abstract class ScriptCompletionProcessor extends ContentAssistProcessor {
	
	private final static String VISIBILITY = DLTKCore.CODEASSIST_VISIBILITY_CHECK;
	
	private final static String ENABLED = "enabled"; //$NON-NLS-1$

	private final static String DISABLED = "disabled"; //$NON-NLS-1$

	private IContextInformationValidator fValidator;

	protected final IEditorPart fEditor;

	public ScriptCompletionProcessor(IEditorPart editor,
			ContentAssistant assistant, String partition) {
		super(assistant, partition);
		fEditor = editor;
	}

	/**
	 * Tells this processor to restrict its proposal to those element visible in
	 * the actual invocation context.
	 * 
	 * @param restrict
	 *            <code>true</code> if proposals should be restricted
	 */
	public void restrictProposalsToVisibility(boolean restrict) {
		Hashtable options = DLTKCore.getOptions();
		Object value = options.get(VISIBILITY);
		if (value instanceof String) {
			String newValue = restrict ? ENABLED : DISABLED;
			if (!newValue.equals(value)) {
				options.put(VISIBILITY, newValue);
				DLTKCore.setOptions(options);
			}
		}
	}

	/**
	 * Tells this processor to restrict is proposals to those starting with
	 * matching cases.
	 * 
	 * @param restrict
	 *            <code>true</code> if proposals should be restricted
	 */
	public void restrictProposalsToMatchingCases(boolean restrict) {
		// not yet supported
	}

	/*
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
	 */
	public IContextInformationValidator getContextInformationValidator() {
		if (fValidator == null) {
			fValidator= new ScriptParameterListValidator();
		}
		return fValidator;
	}

	protected List filterAndSortProposals(List proposals,
			IProgressMonitor monitor, ContentAssistInvocationContext context) {
		ProposalSorterRegistry.getDefault().getCurrentSorter().sortProposals(
				context, proposals);
		return proposals;
	}

	protected abstract String getNatureId();

	protected abstract CompletionProposalLabelProvider getProposalLabelProvider();

	protected ContentAssistInvocationContext createContext(ITextViewer viewer,
			int offset) {
		return new ScriptContentAssistInvocationContext(viewer, offset,
				fEditor, getNatureId()) {
			protected CompletionProposalLabelProvider createLabelProvider() {
				return getProposalLabelProvider();
			}
		};
	}

	protected static class ScriptParameterListValidator implements IContextInformationValidator,
			IContextInformationPresenter {

		private int initialOffset;

		public void install(IContextInformation info, ITextViewer viewer,
				int offset) {
			initialOffset = offset;
		}

		public boolean isContextInformationValid(int offset) {
			return Math.abs(offset - initialOffset) < 5;
		}

		public boolean updatePresentation(int documentPosition,
				TextPresentation presentation) {
			return false;
		}
	}

}
