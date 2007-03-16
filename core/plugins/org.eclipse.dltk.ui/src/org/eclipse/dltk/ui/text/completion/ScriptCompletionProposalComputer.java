/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.ui.text.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationExtension;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

/**
 * Computes Script completion proposals and context infos.
 */
public abstract class ScriptCompletionProposalComputer implements
		IScriptCompletionProposalComputer {

	private static final class ContextInformationWrapper implements
			IContextInformation, IContextInformationExtension {

		private final IContextInformation fContextInformation;
		private int fPosition;

		public ContextInformationWrapper(IContextInformation contextInformation) {
			fContextInformation = contextInformation;
		}

		public String getContextDisplayString() {
			return fContextInformation.getContextDisplayString();
		}

		public Image getImage() {
			return fContextInformation.getImage();
		}

		public String getInformationDisplayString() {
			return fContextInformation.getInformationDisplayString();
		}

		public int getContextInformationPosition() {
			return fPosition;
		}

		public void setContextInformationPosition(int position) {
			fPosition = position;
		}

		public boolean equals(Object object) {
			if (object instanceof ContextInformationWrapper)
				return fContextInformation
						.equals(((ContextInformationWrapper) object).fContextInformation);
			else
				return fContextInformation.equals(object);
		}
	}

	private String fErrorMessage;

	private void handleCodeCompletionException(ModelException e,
			ScriptContentAssistInvocationContext context) {
		ISourceModule module = context.getSourceModule();
		Shell shell = context.getViewer().getTextWidget().getShell();
		if (e.isDoesNotExist()
				&& !module.getScriptProject().isOnBuildpath(module)) {
			IPreferenceStore store = DLTKUIPlugin.getDefault()
					.getPreferenceStore();
			boolean value = store
					.getBoolean(PreferenceConstants.NOTIFICATION_NOT_ON_BUILDPATH_MESSAGE);
			if (!value) {
				MessageDialog
						.openInformation(
								shell,
								ScriptTextMessages.CompletionProcessor_error_notOnBuildPath_title,
								ScriptTextMessages.CompletionProcessor_error_notOnBuildPath_message);
			}
			store.setValue(
					PreferenceConstants.NOTIFICATION_NOT_ON_BUILDPATH_MESSAGE,
					true);
		} else
			ErrorDialog
					.openError(
							shell,
							ScriptTextMessages.CompletionProcessor_error_accessing_title,
							ScriptTextMessages.CompletionProcessor_error_accessing_message,
							e.getStatus());
	}

	private List internalComputeCompletionProposals(int offset,
			ScriptContentAssistInvocationContext context,
			IProgressMonitor monitor) {

		// Source module getting
		ISourceModule sourceModule = context.getSourceModule();
		if (sourceModule == null) {
			return Collections.EMPTY_LIST;
		}

		// Collector creating and configuration
		CompletionProposalCollector collector = createCollector(context);
		collector.setInvocationContext(context);
		Point selection = context.getViewer().getSelectedRange();
		if (selection.y > 0) {
			collector.setReplacementLength(selection.y);
		}
		

		// Fillig collector with proposals
		try {
			IModelElement element = sourceModule.getElementAt(offset);
			if (element != null){
				System.out.println("========= Model element: " + element.getClass());
			}
			
			sourceModule.codeComplete(offset, collector);
		} catch (ModelException e) {
			handleCodeCompletionException(e, context);
		}

		ICompletionProposal[] proposals = collector
				.getScriptCompletionProposals();

		// Test template proposals
		TemplateCompletionProcessor templateProcessor = createTemplateProposalComputer();
		if (false && templateProcessor != null) {
			ICompletionProposal[] tempalteProposals = templateProcessor
					.computeCompletionProposals(context.getViewer(), offset);
			System.out.println("========= Template proposals: "
					+ tempalteProposals.length);
			proposals = tempalteProposals;
		}

		// Checking proposals
		if (proposals.length == 0) {
			String error = collector.getErrorMessage();
			if (error.length() > 0) {
				fErrorMessage = error;
			}

			return Collections.EMPTY_LIST;
		}

		return Arrays.asList(proposals);
	}

	public ScriptCompletionProposalComputer() {
	}

	private List addContextInformations(
			ScriptContentAssistInvocationContext context, int offset,
			IProgressMonitor monitor) {
		List proposals = internalComputeCompletionProposals(offset, context,
				monitor);
		List result = new ArrayList(proposals.size());

		for (Iterator it = proposals.iterator(); it.hasNext();) {
			ICompletionProposal proposal = (ICompletionProposal) it.next();
			IContextInformation contextInformation = proposal
					.getContextInformation();
			if (contextInformation != null) {
				ContextInformationWrapper wrapper = new ContextInformationWrapper(
						contextInformation);
				wrapper.setContextInformationPosition(offset);
				result.add(wrapper);
			}
		}
		return result;
	}

	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalComputer#computeContextInformation(org.eclipse.jface.text.contentassist.TextContentAssistInvocationContext,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	/*
	 * public List computeContextInformation(ContentAssistInvocationContext
	 * context, IProgressMonitor monitor) { if (context instanceof
	 * ScriptContentAssistInvocationContext) {
	 * ScriptContentAssistInvocationContext scriptContext=
	 * (ScriptContentAssistInvocationContext) context;
	 * 
	 * int contextInformationPosition=
	 * guessContextInformationPosition(scriptContext); List result=
	 * addContextInformations(scriptContext, contextInformationPosition,
	 * monitor); return result; } return Collections.EMPTY_LIST; }
	 */

	public List computeContextInformation(
			ContentAssistInvocationContext context, IProgressMonitor monitor) {//
		System.out
				.println("TclTypeCompletionProposalComputer.computeContextInformation()");
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
		System.out.println("!!! Proposals: " + types.size());
		Iterator iter = types.iterator();

		List list = new ArrayList();
		while (iter.hasNext()) {
			IScriptCompletionProposal proposal = (IScriptCompletionProposal) iter
					.next();
			System.out.println("Proposal: " + proposal + ", info: "
					+ proposal.getContextInformation());
			System.out.println(proposal.getClass());
			list.add(proposal.getContextInformation());
		}
		return list;
	}

	public List computeCompletionProposals(
			ContentAssistInvocationContext context, IProgressMonitor monitor) {
		
		if (context instanceof ScriptContentAssistInvocationContext) {
			ScriptContentAssistInvocationContext scriptContext = (ScriptContentAssistInvocationContext) context;

			return internalComputeCompletionProposals(context
					.getInvocationOffset(), scriptContext, monitor);
		}

		return Collections.EMPTY_LIST;
	}

	public String getErrorMessage() {
		return fErrorMessage;
	}

	public void sessionStarted() {
	}

	public void sessionEnded() {
		fErrorMessage = null;
	}

	protected TemplateCompletionProcessor createTemplateProposalComputer() {
		return null;
	}

	protected abstract CompletionProposalCollector createCollector(
			ScriptContentAssistInvocationContext context);
}
