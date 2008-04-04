/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.text;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.jface.text.information.InformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

public class PythonSourceViewerConfiguration extends
		ScriptSourceViewerConfiguration {

	private PythonTextTools fTextTools;
	private PythonCodeScanner fCodeScanner;
	private AbstractScriptScanner fStringScanner;
	private AbstractScriptScanner fCommentScanner;

	public PythonSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return IPythonPartitions.PYTHON_PARITION_TYPES;
	}

	public String[] getIndentPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		StringBuffer tab = new StringBuffer();
		for (int i = 0; i < fPreferenceStore.getInt(CodeFormatterConstants.FORMATTER_TAB_SIZE); i++)
			tab.append(' ');
		return new String[] { "\t", tab.toString() };
	}

	/*
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getTabWidth(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public int getTabWidth(ISourceViewer sourceViewer) {
		if (fPreferenceStore == null)
			return super.getTabWidth(sourceViewer);
		return fPreferenceStore
				.getInt(CodeFormatterConstants.FORMATTER_TAB_SIZE);
	}

	protected void initializeScanners() {
		Assert.isTrue(isNewSetup());
		fCodeScanner = new PythonCodeScanner(getColorManager(),
				fPreferenceStore);
		fStringScanner = new PythonStringScanner(getColorManager(),
				fPreferenceStore);
		fCommentScanner = new SingleTokenScriptScanner(getColorManager(),
				fPreferenceStore,
				PythonColorConstants.PYTHON_SINGLE_LINE_COMMENT);
	}

	/**
	 * @return <code>true</code> if the new setup without text tools is in
	 *         use.
	 */
	private boolean isNewSetup() {
		return fTextTools == null;
	}

	/**
	 * Returns the Python string scanner for this configuration.
	 * 
	 * @return the Python string scanner
	 */
	protected RuleBasedScanner getStringScanner() {
		return fStringScanner;
	}

	/**
	 * Returns the Python comment scanner for this configuration.
	 * 
	 * @return the Python comment scanner
	 */
	protected RuleBasedScanner getCommentScanner() {
		return fCommentScanner;
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new ScriptPresentationReconciler();
		reconciler
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				this.fCodeScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getStringScanner());
		reconciler.setDamager(dr, IPythonPartitions.PYTHON_STRING);
		reconciler.setRepairer(dr, IPythonPartitions.PYTHON_STRING);

		dr = new DefaultDamagerRepairer(getCommentScanner());
		reconciler.setDamager(dr, IPythonPartitions.PYTHON_COMMENT);
		reconciler.setRepairer(dr, IPythonPartitions.PYTHON_COMMENT);

		return reconciler;
	}

	/**
	 * Adapts the behavior of the contained components to the change encoded in
	 * the given event.
	 * <p>
	 * Clients are not allowed to call this method if the old setup with text
	 * tools is in use.
	 * </p>
	 * 
	 * @param event
	 *            the event to which to adapt
	 * @see PythonSourceViewerConfiguration#ScriptSourceViewerConfiguration(IColorManager,
	 *      IPreferenceStore, ITextEditor, String)
	 */
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		Assert.isTrue(isNewSetup());
		if (fCodeScanner.affectsBehavior(event))
			fCodeScanner.adaptToPreferenceChange(event);
		if (fStringScanner.affectsBehavior(event))
			fStringScanner.adaptToPreferenceChange(event);
	}

	/**
	 * Determines whether the preference change encoded by the given event
	 * changes the behavior of one of its contained components.
	 * 
	 * @param event
	 *            the event to be investigated
	 * @return <code>true</code> if event causes a behavioral change
	 * 
	 */
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return fCodeScanner.affectsBehavior(event)
				|| fStringScanner.affectsBehavior(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAutoEditStrategies(org.eclipse.jface.text.source.ISourceViewer,
	 *      java.lang.String)
	 */
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		// TODO: check contentType. think, do we really need it? :)
		String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
		return new IAutoEditStrategy[] { new PythonAutoEditStrategy(
				fPreferenceStore, partitioning) };
	}

	protected void initializeQuickOutlineContexts(
			InformationPresenter presenter, IInformationProvider provider) {
		presenter.setInformationProvider(provider,
				IPythonPartitions.PYTHON_COMMENT);
		presenter.setInformationProvider(provider,
				IPythonPartitions.PYTHON_STRING);
	}

	protected ContentAssistPreference getContentAssistPreference() {
		return PythonContentAssistPreference.getDefault();
	}

	protected void alterContentAssistant(ContentAssistant assistant) {
		PythonCompletionProcessor processor = new PythonCompletionProcessor(
				getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setContentAssistProcessor(processor,
				IDocument.DEFAULT_CONTENT_TYPE);
	}
	public IInformationPresenter getOutlinePresenter(ScriptSourceViewer viewer,
			boolean doCodeResolve) {
		return null;
	}
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		if (getEditor() != null) {

			ContentAssistant assistant = new ContentAssistant();
			assistant
					.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

			assistant
					.setRestoreCompletionProposalSize(getSettings("completion_proposal_size")); //$NON-NLS-1$

			// IDocument.DEFAULT_CONTENT_TYPE
			IContentAssistProcessor scriptProcessor = new PythonScriptCompletionProcessor(
					getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
			assistant.setContentAssistProcessor(scriptProcessor,
					IDocument.DEFAULT_CONTENT_TYPE);

			// IPythonPartitions.PYTHON_COMMENT
			ContentAssistProcessor singleLineProcessor = new PythonScriptCompletionProcessor(
					getEditor(), assistant, IPythonPartitions.PYTHON_COMMENT);
			assistant.setContentAssistProcessor(singleLineProcessor,
					IPythonPartitions.PYTHON_COMMENT);

			// TclPartitions.PYTHON_STRING
			ContentAssistProcessor stringProcessor = new PythonScriptCompletionProcessor(
					getEditor(), assistant, IPythonPartitions.PYTHON_STRING);
			assistant.setContentAssistProcessor(stringProcessor,
					IPythonPartitions.PYTHON_STRING);

			// Configuration
			PythonContentAssistPreference.getDefault().configure(assistant, fPreferenceStore);
			assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
			assistant.setInformationControlCreator(getInformationControlCreator(sourceViewer));
			return assistant;
		}
		return null;
	}
}
