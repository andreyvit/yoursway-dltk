/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.wst.javascript.ui.internal.common.autoedit.AutoEditStrategyForTabs;
import org.eclipse.wst.javascript.ui.internal.common.contentassist.JavaScriptContentAssistProcessor;
import org.eclipse.wst.javascript.ui.internal.common.preferences.JSCommonUIPreferenceNames;
import org.eclipse.wst.javascript.ui.internal.common.taginfo.JavaScriptInformationPresenter;
import org.eclipse.wst.javascript.ui.internal.common.taginfo.JavaScriptTagInfoHoverProcessor;
import org.eclipse.wst.sse.ui.internal.preferences.EditorPreferenceNames;
import org.eclipse.wst.sse.ui.internal.reconcile.DocumentRegionProcessor;
import org.eclipse.wst.sse.ui.internal.util.EditorUtility;


public class JSSourceViewerConfiguration extends TextSourceViewerConfiguration {
	private ITextHover fTextHover = null;
	private IContentAssistant fContentAssistant = null;
	private InformationPresenter fInformationPresenter = null;
	private IReconciler fReconciler = null;

	public JSSourceViewerConfiguration() {
		super();
	}

	public JSSourceViewerConfiguration(IPreferenceStore store) {
		super(store);
		
	}

	public String[] getIndentPrefixes(ISourceViewer sourceViewer, String contentType) {
		Vector vector = new Vector();		
		// prefix[0] is either '\t' or ' ' x tabWidth, depending on preference
		int indentationWidth = fPreferenceStore.getInt(JSCommonUIPreferenceNames.INDENTATION_SIZE);
		String indentCharPref = fPreferenceStore.getString(JSCommonUIPreferenceNames.INDENTATION_CHAR);
		boolean useSpaces = JSCommonUIPreferenceNames.SPACE.equals(indentCharPref);

		for (int i = 0; i <= indentationWidth; i++) {
			StringBuffer prefix = new StringBuffer();
			boolean appendTab = false;

			if (useSpaces) {
				for (int j = 0; j + i < indentationWidth; j++)
					prefix.append(' ');

				if (i != 0)
					appendTab = true;
			}
			else {
				for (int j = 0; j < i; j++)
					prefix.append(' ');

				if (i != indentationWidth)
					appendTab = true;
			}

			if (appendTab) {
				prefix.append('\t');
				vector.add(prefix.toString());
				// remove the tab so that indentation - tab is also an indent
				// prefix
				prefix.deleteCharAt(prefix.length() - 1);
			}
			vector.add(prefix.toString());
		}

		vector.add(""); //$NON-NLS-1$

		return (String[]) vector.toArray(new String[vector.size()]);
	}

	/**
	 * Get color for the preference key. Assumes fPreferenceStore is not null.
	 * 
	 * @param key
	 * @return Color for preference key or null if none found
	 */
	private Color getColor(String key) {
		RGB rgb = PreferenceConverter.getColor(fPreferenceStore, key);
		return EditorUtility.getColor(rgb);
	}

	/**
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(ISourceViewer)
	 */
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		if (fContentAssistant == null) {
			// Ensure that only one assistant is ever returned. Creating a
			// second assistant
			// that is added to a viewer can cause odd key-eating by the wrong
			// one.
			ContentAssistant contentAssistant = new ContentAssistant();
			contentAssistant.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
			IContentAssistProcessor contentAssistantProcessor = new JavaScriptContentAssistProcessor();
			contentAssistant.setContentAssistProcessor(contentAssistantProcessor, IDocument.DEFAULT_CONTENT_TYPE);
			contentAssistant.enableAutoActivation(true);
			contentAssistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
			contentAssistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
			contentAssistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
			
			// set content assist preferences
			if (fPreferenceStore != null) {
				int delay = fPreferenceStore.getInt(EditorPreferenceNames.CODEASSIST_AUTOACTIVATION_DELAY);
				contentAssistant.setAutoActivationDelay(delay);

				Color color = getColor(EditorPreferenceNames.CODEASSIST_PROPOSALS_BACKGROUND);
				contentAssistant.setProposalSelectorBackground(color);

				color = getColor(EditorPreferenceNames.CODEASSIST_PROPOSALS_FOREGROUND);
				contentAssistant.setProposalSelectorForeground(color);

				color = getColor(EditorPreferenceNames.CODEASSIST_PARAMETERS_BACKGROUND);
				contentAssistant.setContextInformationPopupBackground(color);
				contentAssistant.setContextSelectorBackground(color);

				color = getColor(EditorPreferenceNames.CODEASSIST_PARAMETERS_FOREGROUND);
				contentAssistant.setContextInformationPopupForeground(color);
				contentAssistant.setContextSelectorForeground(color);
			}
			
			fContentAssistant = contentAssistant;
		}
		return fContentAssistant;
	}


	public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				int style = true ? SWT.NONE : (SWT.V_SCROLL | SWT.H_SCROLL);
				return new DefaultInformationControl(parent, style, new HTMLTextPresenter(true));
			}
		};
	}

	/*
	 * @see SourceViewerConfiguration#getTextHover(ISourceViewer, String, int)
	 */
	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType, int stateMask) {
		if (fTextHover == null) {
			fTextHover = new JavaScriptTagInfoHoverProcessor();
		}
		return fTextHover;
	}

	/*
	 * @see SourceViewerConfiguration#getTextHover(ISourceViewer, String)
	 */
	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
		return getTextHover(sourceViewer, contentType, ITextViewerExtension2.DEFAULT_HOVER_STATE_MASK);
	}

	/**
	 * Returns the information presenter control creator. The creator is a
	 * factory creating the presenter controls for the given source viewer.
	 * This implementation always returns a creator for
	 * <code>DefaultInformationControl</code> instances. (Copied from
	 * JavaSourceViewerConfiguration)
	 * 
	 * @param sourceViewer
	 *            the source viewer to be configured by this configuration
	 * @return an information control creator
	 */
	private IInformationControlCreator getInformationPresenterControlCreator(ISourceViewer sourceViewer) {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				int shellStyle = SWT.RESIZE;
				int style = SWT.V_SCROLL | SWT.H_SCROLL;
				return new DefaultInformationControl(parent, shellStyle, style, new HTMLTextPresenter(false));
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getInformationPresenter(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IInformationPresenter getInformationPresenter(ISourceViewer sourceViewer) {
		if (fInformationPresenter == null) {
			fInformationPresenter = new JavaScriptInformationPresenter(getInformationPresenterControlCreator(sourceViewer));

			fInformationPresenter.setSizeConstraints(60, 10, true, true);
			fInformationPresenter.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		}
		return fInformationPresenter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAutoEditStrategies(org.eclipse.jface.text.source.ISourceViewer,
	 *      java.lang.String)
	 */
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		List allStrategies = new ArrayList(0);

		IAutoEditStrategy[] superStrategies = super.getAutoEditStrategies(sourceViewer, contentType);
		for (int i = 0; i < superStrategies.length; i++) {
			allStrategies.add(superStrategies[i]);
		}

		// be sure this is last, so it can modify any results form previous
		// commands that might on on same partiion type.
		// add auto edit strategy that handles when tab key is pressed
		allStrategies.add(new AutoEditStrategyForTabs());

		return (IAutoEditStrategy[]) allStrategies.toArray(new IAutoEditStrategy[allStrategies.size()]);
	}

	/*
	 * NOTE: this is a workaround for:
	 * 
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=115531
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=114631
	 * 
	 * until we can get IContentType from IDocument somehow, we need to
	 * override this method.
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		boolean reconcilingEnabled = fPreferenceStore.getBoolean(EditorPreferenceNames.EVALUATE_TEMPORARY_PROBLEMS);
		if (sourceViewer == null || !reconcilingEnabled)
			return null;

		/*
		 * Only create reconciler if sourceviewer is present
		 */
		if (fReconciler == null && sourceViewer != null) {
			fReconciler = new DocumentRegionProcessor() {
				protected String getContentType(IDocument doc) {
					return "org.eclipse.wst.javascript.core.javascriptsource"; //$NON-NLS-1$
				}

			};
		}
		return fReconciler;
	}
}
