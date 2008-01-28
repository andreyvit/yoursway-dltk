/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.templates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.IWorkbenchPartOrientation;

public abstract class ScriptTempalteCompletionProcessor extends
		TemplateCompletionProcessor {

	private static final class ProposalComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			return ((TemplateProposal) o2).getRelevance()
					- ((TemplateProposal) o1).getRelevance();
		}
	}

	private static final Comparator comaparator = new ProposalComparator();

	private ScriptContentAssistInvocationContext context;

	public ScriptTempalteCompletionProcessor(
			ScriptContentAssistInvocationContext context) {
		if (context == null) {
			throw new IllegalArgumentException();
		}

		this.context = context;
	}

	protected ScriptContentAssistInvocationContext getContext() {
		return this.context;
	}

	// TODO: add more customizations
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {

		ITextSelection selection = (ITextSelection) viewer
				.getSelectionProvider().getSelection();

		// adjust offset to end of normalized selection
		if (selection.getOffset() == offset)
			offset = selection.getOffset() + selection.getLength();

		String prefix = extractPrefix(viewer, offset);
		Region region = new Region(offset - prefix.length(), prefix.length());
		TemplateContext context = createContext(viewer, region);
		if (context == null)
			return new ICompletionProposal[0];

		// name of the selection variables {line, word}_selection
		context.setVariable("selection", selection.getText()); //$NON-NLS-1$

		Template[] templates = getTemplates(context.getContextType().getId());

		List matches = new ArrayList();
		for (int i = 0; i < templates.length; i++) {
			Template template = templates[i];
			try {
				context.getContextType().validate(template.getPattern());
			} catch (TemplateException e) {
				continue;
			}

			// Addes check of startsWith
			if (template.getName().startsWith(prefix)
					&& template.matches(prefix, context.getContextType()
							.getId()))
				matches.add(createProposal(template, context, (IRegion) region,
						getRelevance(template, prefix)));
		}

		Collections.sort(matches, comaparator);

		return (ICompletionProposal[]) matches
				.toArray(new ICompletionProposal[matches.size()]);
	}
	
	protected TemplateContext createContext(ITextViewer viewer, IRegion region) {
		TemplateContextType contextType = getContextType(viewer, region);
		if (contextType instanceof ScriptTemplateContextType) {
			IDocument document = viewer.getDocument();

			return ((ScriptTemplateContextType) contextType).createContext(
					document, region.getOffset(), region.getLength(),
					getContext().getSourceModule());
		} else {
			return super.createContext(viewer, region);
		}
	}

	protected ICompletionProposal createProposal(Template template,
			TemplateContext context, IRegion region, int relevance) {	
		TemplateProposal proposal = new ScriptTemplateProposal(template, context, region, getImage(template), relevance);
		proposal.setInformationControlCreator(getInformationControlCreator());
		return proposal;
	}
		
	private IInformationControlCreator getInformationControlCreator() {
		int orientation;
		ScriptEditor editor= getScriptEditor();
		if (editor instanceof IWorkbenchPartOrientation)
			orientation= ((IWorkbenchPartOrientation)editor).getOrientation();
		else
			orientation= SWT.LEFT_TO_RIGHT;
		return new TemplateInformationControlCreator(orientation, editor.getLanguageToolkit());
	}
	
	/**
	 * Returns the currently active java editor, or <code>null</code> if it
	 * cannot be determined.
	 *
	 * @return  the currently active java editor, or <code>null</code>
	 */
	private ScriptEditor getScriptEditor() {
		IEditorPart part= DLTKUIPlugin.getActivePage().getActiveEditor();
		if (part instanceof ScriptEditor)
			return (ScriptEditor) part;
		else
			return null;
	}
}
