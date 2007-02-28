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
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationExtension;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;


/**
 * Computes Script completion proposals and context infos.
 */
public abstract class ScriptCompletionProposalComputer implements IScriptCompletionProposalComputer {

	private static final class ContextInformationWrapper implements IContextInformation, IContextInformationExtension {

		private final IContextInformation fContextInformation;
		private int fPosition;

		public ContextInformationWrapper(IContextInformation contextInformation) {
			fContextInformation= contextInformation;
		}

		/*
		 * @see IContextInformation#getContextDisplayString()
		 */
		public String getContextDisplayString() {
			return fContextInformation.getContextDisplayString();
		}

			/*
		 * @see IContextInformation#getImage()
		 */
		public Image getImage() {
			return fContextInformation.getImage();
		}

		/*
		 * @see IContextInformation#getInformationDisplayString()
		 */
		public String getInformationDisplayString() {
			return fContextInformation.getInformationDisplayString();
		}

		/*
		 * @see IContextInformationExtension#getContextInformationPosition()
		 */
		public int getContextInformationPosition() {
			return fPosition;
		}

		public void setContextInformationPosition(int position) {
			fPosition= position;
		}

		/*
		 * @see org.eclipse.jface.text.contentassist.IContextInformation#equals(java.lang.Object)
		 */
		public boolean equals(Object object) {
			if (object instanceof ContextInformationWrapper)
				return fContextInformation.equals(((ContextInformationWrapper) object).fContextInformation);
			else
				return fContextInformation.equals(object);
		}
	}
	
	private String fErrorMessage;
	
	public ScriptCompletionProposalComputer() {
	}

	protected int guessContextInformationPosition(ContentAssistInvocationContext context) {
		int contextPosition= context.getInvocationOffset();

	//	IDocument document= context.getDocument();

		// Add look like method call.

		return contextPosition;
	}

	private List addContextInformations(ScriptContentAssistInvocationContext context, int offset, IProgressMonitor monitor) {
		List proposals= internalComputeCompletionProposals(offset, context, monitor);
		List result= new ArrayList(proposals.size());

		for (Iterator it= proposals.iterator(); it.hasNext();) {
			ICompletionProposal proposal= (ICompletionProposal) it.next();
			IContextInformation contextInformation= proposal.getContextInformation();
			if (contextInformation != null) {
				ContextInformationWrapper wrapper= new ContextInformationWrapper(contextInformation);
				wrapper.setContextInformationPosition(offset);
				result.add(wrapper);
			}
		}
		return result;
	}

	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalComputer#computeContextInformation(org.eclipse.jface.text.contentassist.TextContentAssistInvocationContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	/*public List computeContextInformation(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		if (context instanceof ScriptContentAssistInvocationContext) {
			ScriptContentAssistInvocationContext scriptContext= (ScriptContentAssistInvocationContext) context;
			
			int contextInformationPosition= guessContextInformationPosition(scriptContext);
			List result= addContextInformations(scriptContext, contextInformationPosition, monitor);
			return result;
		}
		return Collections.EMPTY_LIST;
	}*/
	
	public List computeContextInformation(ContentAssistInvocationContext context, IProgressMonitor monitor) {//
		System.out
				.println("TclTypeCompletionProposalComputer.computeContextInformation()");
//		if (context instanceof ScriptContentAssistInvocationContext) {
//			ScriptContentAssistInvocationContext scriptContext= (ScriptContentAssistInvocationContext) context;
//			
//			int contextInformationPosition= guessContextInformationPosition(scriptContext);
//			List result= addContextInformations(scriptContext, contextInformationPosition, monitor);
//			return result;
//		}
//		return Collections.EMPTY_LIST;
						
			List types= computeCompletionProposals(context, monitor);
			System.out.println("!!! Proposals: " + types.size());
			Iterator iter = types.iterator();
			
			List list = new ArrayList();			
			while(iter.hasNext()){
				IScriptCompletionProposal proposal = (IScriptCompletionProposal)iter.next();
				System.out.println("Proposal: " + proposal + ", info: " + proposal.getContextInformation());
				System.out.println(proposal.getClass());
				list.add(proposal.getContextInformation());
			}
			return list;
	}

	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalComputer#computeCompletionProposals(org.eclipse.jface.text.contentassist.TextContentAssistInvocationContext, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public List computeCompletionProposals(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		if (context instanceof ScriptContentAssistInvocationContext) {
			ScriptContentAssistInvocationContext scriptContext= (ScriptContentAssistInvocationContext) context;
			return internalComputeCompletionProposals(context.getInvocationOffset(), scriptContext, monitor);
		}
		return Collections.EMPTY_LIST;
	}

	private List internalComputeCompletionProposals(int offset, ScriptContentAssistInvocationContext context, IProgressMonitor monitor) {
		ISourceModule unit= context.getSourceModule();
		if (unit == null)
			return Collections.EMPTY_LIST;
		
		ITextViewer viewer= context.getViewer();
		
		CompletionProposalCollector collector= createCollector(context);
		collector.setInvocationContext(context);

		try {
			Point selection= viewer.getSelectedRange();
			if (selection.y > 0)
				collector.setReplacementLength(selection.y);
			
				unit.codeComplete(offset, collector);
		} catch (ModelException x) {
			Shell shell= viewer.getTextWidget().getShell();
			if (x.isDoesNotExist() && !unit.getScriptProject().isOnBuildpath(unit)) {
				IPreferenceStore store = DLTKUIPlugin.getDefault().getPreferenceStore();
				boolean value = store.getBoolean(PreferenceConstants.NOTIFICATION_NOT_ON_BUILDPATH_MESSAGE);
				if( !value ) {
					MessageDialog.openInformation(shell, ScriptTextMessages.CompletionProcessor_error_notOnBuildPath_title, ScriptTextMessages.CompletionProcessor_error_notOnBuildPath_message);
				}
				store.setValue(PreferenceConstants.NOTIFICATION_NOT_ON_BUILDPATH_MESSAGE, true);
			}
			else
				ErrorDialog.openError(shell, ScriptTextMessages.CompletionProcessor_error_accessing_title, ScriptTextMessages.CompletionProcessor_error_accessing_message, x.getStatus());
		}

		ICompletionProposal[] javaProposals= collector.getScriptCompletionProposals();
		//int contextInformationOffset= guessContextInformationPosition(context);
//		if (contextInformationOffset != offset) {
//			for (int i= 0; i < javaProposals.length; i++) {
//				if (javaProposals[i] instanceof JavaMethodCompletionProposal) {
//					JavaMethodCompletionProposal jmcp= (JavaMethodCompletionProposal) javaProposals[i];
//					jmcp.setContextInformationPosition(contextInformationOffset);
//				}
//			}
//		}
		if (DLTKCore.DEBUG) {
			System.err.println("$TODO:Completion: Add Method completion proposal support.");
		}
		
		List proposals= new ArrayList(Arrays.asList(javaProposals));
		if (proposals.size() == 0) {
			String error= collector.getErrorMessage();
			if (error.length() > 0)
				fErrorMessage= error;
		}
		return proposals;
	}

	/**
	 * Creates the collector used to get proposals from core.
	 */
	protected abstract CompletionProposalCollector internalCreateCollector(ScriptContentAssistInvocationContext context);
	
	protected CompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
		return internalCreateCollector(context);
	}
//	protected CompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
//		if (PreferenceConstants.getPreferenceStore().getBoolean(PreferenceConstants.CODEASSIST_FILL_ARGUMENT_NAMES))
//			return new ExperimentalResultCollector(context);
//		else
//			return new CompletionProposalCollector(context.getSourceModule());
//	}

	/*
	 * @see org.eclipse.jface.text.contentassist.ICompletionProposalComputer#getErrorMessage()
	 */
	public String getErrorMessage() {
		return fErrorMessage;
	}

	public void sessionStarted() {
	}
	
	public void sessionEnded() {
		fErrorMessage= null;
	}
}
