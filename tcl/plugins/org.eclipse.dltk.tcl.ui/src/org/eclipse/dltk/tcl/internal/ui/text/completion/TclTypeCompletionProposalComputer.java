/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.text.completion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.CompletionProposal;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.text.completion.CompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ContentAssistInvocationContext;
import org.eclipse.dltk.ui.text.completion.IScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;


/**
 * 
	 *
 */
public class TclTypeCompletionProposalComputer extends ScriptCompletionProposalComputer {

	protected CompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context) {
		CompletionProposalCollector collector = super.createCollector(context);
		collector.setIgnored(CompletionProposal.ANNOTATION_ATTRIBUTE_REF, true);
		collector.setIgnored(CompletionProposal.FIELD_REF, true);
		collector.setIgnored(CompletionProposal.KEYWORD, true);
		collector.setIgnored(CompletionProposal.LABEL_REF, true);
		collector.setIgnored(CompletionProposal.LOCAL_VARIABLE_REF, true);
		collector.setIgnored(CompletionProposal.METHOD_DECLARATION, true);
		collector.setIgnored(CompletionProposal.METHOD_NAME_REFERENCE, true);
		collector.setIgnored(CompletionProposal.METHOD_REF, true);
		collector.setIgnored(CompletionProposal.POTENTIAL_METHOD_DECLARATION, true);
		collector.setIgnored(CompletionProposal.VARIABLE_DECLARATION, true);

		// collector.setIgnored(CompletionProposal.JAVADOC_BLOCK_TAG, true);
		// collector.setIgnored(CompletionProposal.JAVADOC_FIELD_REF, true);
		// collector.setIgnored(CompletionProposal.JAVADOC_INLINE_TAG, true);
		// collector.setIgnored(CompletionProposal.JAVADOC_METHOD_REF, true);
		// collector.setIgnored(CompletionProposal.JAVADOC_PARAM_REF, true);
		// collector.setIgnored(CompletionProposal.JAVADOC_TYPE_REF, true);
		// collector.setIgnored(CompletionProposal.JAVADOC_VALUE_REF, true);
		//		
		collector.setIgnored(CompletionProposal.TYPE_REF, false);
		return collector;
	}

	public List computeContextInformation(ContentAssistInvocationContext context, IProgressMonitor monitor) {//
		System.out.println("Offset: " + context.getInvocationOffset());

		if (DLTKCore.DEBUG) {
			System.out.println("TclTypeCompletionProposalComputer.computeContextInformation()");
		}
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
			IScriptCompletionProposal proposal = (IScriptCompletionProposal) iter.next();
			if (DLTKCore.DEBUG) {
				System.out.println("Proposal: " + proposal + ", info: " + proposal.getContextInformation().getInformationDisplayString());
			}
			list.add(proposal.getContextInformation());
		}
		return list;
	}

	public List computeCompletionProposals(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		List types = super.computeCompletionProposals(context, monitor);
		if (context instanceof ScriptContentAssistInvocationContext) {
			ScriptContentAssistInvocationContext scriptContext = (ScriptContentAssistInvocationContext) context;
			try {
				if (types.size() > 0 && context.computeIdentifierPrefix().length() == 0) {
					IType expectedType = scriptContext.getExpectedType();
					if (expectedType != null) {
						// empty prefix completion - insert LRU types if known
						LazyTclTypeCompletionProposal typeProposal = (LazyTclTypeCompletionProposal) types.get(0);
						List history = DLTKUIPlugin.getDefault().getContentAssistHistory().getHistory(expectedType.getFullyQualifiedName())
								.getTypes();

						int relevance = typeProposal.getRelevance() - history.size() - 1;
						for (Iterator it = history.iterator(); it.hasNext();) {
							String type = (String) it.next();
							if (type.equals(((IType) typeProposal.getModelElement()).getFullyQualifiedName()))
								continue;

							IScriptCompletionProposal proposal = createTypeProposal(relevance, type, scriptContext);

							if (proposal != null)
								types.add(proposal);
							relevance++;
						}
					}
				}
			} catch (BadLocationException x) {
				// log & ignore
				DLTKUIPlugin.log(x);
			} catch (ModelException x) {
				// log & ignore
				DLTKUIPlugin.log(x);
			}
		}

		return types;
	}

	private IScriptCompletionProposal createTypeProposal(int relevance, String fullyQualifiedType, ScriptContentAssistInvocationContext context)
			throws ModelException {
		IType type = context.getSourceModule().getScriptProject().findType(fullyQualifiedType);
		if (type == null)
			return null;

		CompletionProposal proposal = CompletionProposal.create(CompletionProposal.TYPE_REF, context.getInvocationOffset());
		proposal.setCompletion(type.getElementName().toCharArray());
		proposal.setDeclarationSignature(type.getScriptFolder().getElementName().toCharArray());
		proposal.setFlags(type.getFlags());
		proposal.setRelevance(relevance);
		proposal.setReplaceRange(context.getInvocationOffset(), context.getInvocationOffset());

		return new LazyTclTypeCompletionProposal(proposal, context);
	}

	protected CompletionProposalCollector internalCreateCollector(ScriptContentAssistInvocationContext context) {
		// if
		// (TclUI.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.CODEASSIST_FILL_ARGUMENT_NAMES))
		// return new ExperimentalResultCollector(context);
		// else
		return new TclCompletionProposalCollector(context.getSourceModule());
	}
}
