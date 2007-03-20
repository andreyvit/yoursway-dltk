package org.eclipse.dltk.tcl.internal.ui.text;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.tcl.internal.ui.text.completion.TclContentAssistPreference;
import org.eclipse.dltk.tcl.internal.ui.text.completion.TclScriptCompletionProcessor;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.SingleTokenScriptScanner;
import org.eclipse.dltk.ui.text.completion.ContentAssistProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;

public class TclSourceViewerConfiguration extends
		ScriptSourceViewerConfiguration {

	private TclTextTools fTextTools;

	private TclCodeScanner fCodeScanner;
	private AbstractScriptScanner fStringScanner;
	private AbstractScriptScanner fCommentScanner;

	public TclSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return TclPartitions.TCL_PARTITION_TYPES;
	}

	public String[] getIndentPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		// TODO: what's a shit this method returns?
		return new String[] { "\t", "    " }; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String[] getDefaultPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		return new String[] { "#", "" }; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public int getTabWidth(ISourceViewer sourceViewer) {
		if (fPreferenceStore == null)
			return super.getTabWidth(sourceViewer);

		return fPreferenceStore
				.getInt(CodeFormatterConstants.FORMATTER_TAB_SIZE);
	}

	protected void initializeScanners() {
		Assert.isTrue(isNewSetup());
		
		// Creating scanners 
		fCodeScanner = new TclCodeScanner(getColorManager(), fPreferenceStore);
		
		fStringScanner = new TclStringScanner(getColorManager(),
				fPreferenceStore);
		
		fCommentScanner = new SingleTokenScriptScanner(getColorManager(),
				fPreferenceStore, TclColorConstants.TCL_SINGLE_LINE_COMMENT);
	}

	/**
	 * @return <code>true</code> iff the new setup without text tools is in
	 *         use.
	 */
	private boolean isNewSetup() {
		return fTextTools == null;
	}

	protected RuleBasedScanner getStringScanner() {
		return fStringScanner;
	}
	
	protected RuleBasedScanner getCommentScanner() {
		return fCommentScanner;
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		reconciler
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				this.fCodeScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getStringScanner());
		reconciler.setDamager(dr, TclPartitions.TCL_STRING);
		reconciler.setRepairer(dr, TclPartitions.TCL_STRING);

		dr = new DefaultDamagerRepairer(getCommentScanner());
		reconciler.setDamager(dr, TclPartitions.TCL_COMMENT);
		reconciler.setRepairer(dr, TclPartitions.TCL_COMMENT);

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
	 * @see TclSourceViewerConfiguration#TclSourceViewerConfiguration(IColorManager,
	 *      IPreferenceStore, ITextEditor, String)
	 */
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		Assert.isTrue(isNewSetup());

		if (fCodeScanner.affectsBehavior(event)) {
			fCodeScanner.adaptToPreferenceChange(event);
		}

		if (fStringScanner.affectsBehavior(event)) {
			fStringScanner.adaptToPreferenceChange(event);
		}

		if (fCommentScanner.affectsBehavior(event)) {
			fCommentScanner.adaptToPreferenceChange(event);
		}
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
				|| fStringScanner.affectsBehavior(event)
				|| fCommentScanner.affectsBehavior(event);
	}

	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		// return super.getAutoEditStrategies(sourceViewer, contentType);
		String partitioning = getConfiguredDocumentPartitioning(sourceViewer);
		return new TclAutoEditStrategy[] { new TclAutoEditStrategy(
				fPreferenceStore, partitioning) };
	}
	
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

		if (getEditor() != null) {

			ContentAssistant assistant = new ContentAssistant();
			assistant
					.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));

			assistant
					.setRestoreCompletionProposalSize(getSettings("completion_proposal_size")); //$NON-NLS-1$

			// IDocument.DEFAULT_CONTENT_TYPE
			IContentAssistProcessor scriptProcessor = new TclScriptCompletionProcessor(
					getEditor(), assistant, IDocument.DEFAULT_CONTENT_TYPE);
			assistant.setContentAssistProcessor(scriptProcessor,
					IDocument.DEFAULT_CONTENT_TYPE);

			// TclPartitions.TCL_COMMENT
			ContentAssistProcessor singleLineProcessor = new TclScriptCompletionProcessor(
					getEditor(), assistant, TclPartitions.TCL_COMMENT);
			assistant.setContentAssistProcessor(singleLineProcessor,
					TclPartitions.TCL_COMMENT);

			// TclPartitions.TCL_STRING
			ContentAssistProcessor stringProcessor = new TclScriptCompletionProcessor(
					getEditor(), assistant, TclPartitions.TCL_STRING);
			assistant.setContentAssistProcessor(stringProcessor,
					TclPartitions.TCL_STRING);

			// Configuration
			TclContentAssistPreference.getDefault().configure(assistant,
					fPreferenceStore);

			assistant
					.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
			
			assistant
					.setInformationControlCreator(getInformationControlCreator(sourceViewer));

			return assistant;
		}

		return null;
	}

	protected IInformationControlCreator getOutlinePresenterControlCreator(
			ISourceViewer sourceViewer, final String commandId) {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				int shellStyle = SWT.RESIZE;
				int treeStyle = SWT.V_SCROLL | SWT.H_SCROLL;
				return new TclOutlineInformationControl(parent, shellStyle,
						treeStyle, commandId);
			}
		};
	}
}
