package org.eclipse.dltk.ui.templates;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlinkPresenter;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class CodeTemplateSourceViewerConfigurationAdapter extends
		SourceViewerConfiguration {
	private SourceViewerConfiguration fConfig;
	private IContentAssistProcessor fContentAssistProcessor;

	public CodeTemplateSourceViewerConfigurationAdapter(
			SourceViewerConfiguration config,
			IContentAssistProcessor contentAssistProcessor) {
		fConfig = config;
		fContentAssistProcessor = contentAssistProcessor;
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new ContentAssistant();
		assistant.enableAutoActivation(true);
		assistant.enableAutoInsert(true);
		assistant.setContentAssistProcessor(fContentAssistProcessor,
				IDocument.DEFAULT_CONTENT_TYPE);
		return assistant;
	}

	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return fConfig.getAnnotationHover(sourceViewer);
	}

	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		return fConfig.getAutoEditStrategies(sourceViewer, contentType);
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return fConfig.getConfiguredContentTypes(sourceViewer);
	}

	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
		return fConfig.getConfiguredDocumentPartitioning(sourceViewer);
	}

	public int[] getConfiguredTextHoverStateMasks(ISourceViewer sourceViewer,
			String contentType) {
		return fConfig.getConfiguredTextHoverStateMasks(sourceViewer,
				contentType);
	}

	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		return fConfig.getContentFormatter(sourceViewer);
	}

	public String[] getDefaultPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		return fConfig.getDefaultPrefixes(sourceViewer, contentType);
	}

	public ITextDoubleClickStrategy getDoubleClickStrategy(
			ISourceViewer sourceViewer, String contentType) {
		return fConfig.getDoubleClickStrategy(sourceViewer, contentType);
	}

	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		return fConfig.getHyperlinkDetectors(sourceViewer);
	}

	public IHyperlinkPresenter getHyperlinkPresenter(ISourceViewer sourceViewer) {
		return fConfig.getHyperlinkPresenter(sourceViewer);
	}

	public int getHyperlinkStateMask(ISourceViewer sourceViewer) {
		return fConfig.getHyperlinkStateMask(sourceViewer);
	}

	public String[] getIndentPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		return fConfig.getIndentPrefixes(sourceViewer, contentType);
	}

	public IInformationControlCreator getInformationControlCreator(
			ISourceViewer sourceViewer) {
		return fConfig.getInformationControlCreator(sourceViewer);
	}

	public IInformationPresenter getInformationPresenter(
			ISourceViewer sourceViewer) {
		return fConfig.getInformationPresenter(sourceViewer);
	}

	public IAnnotationHover getOverviewRulerAnnotationHover(
			ISourceViewer sourceViewer) {
		return fConfig.getOverviewRulerAnnotationHover(sourceViewer);
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		return fConfig.getPresentationReconciler(sourceViewer);
	}

	public IQuickAssistAssistant getQuickAssistAssistant(
			ISourceViewer sourceViewer) {
		return fConfig.getQuickAssistAssistant(sourceViewer);
	}

	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		return fConfig.getReconciler(sourceViewer);
	}

	public int getTabWidth(ISourceViewer sourceViewer) {
		return fConfig.getTabWidth(sourceViewer);
	}

	public ITextHover getTextHover(ISourceViewer sourceViewer,
			String contentType, int stateMask) {
		return fConfig.getTextHover(sourceViewer, contentType, stateMask);
	}

	public ITextHover getTextHover(ISourceViewer sourceViewer,
			String contentType) {
		return fConfig.getTextHover(sourceViewer, contentType);
	}

	public IUndoManager getUndoManager(ISourceViewer sourceViewer) {
		return fConfig.getUndoManager(sourceViewer);
	}

}
