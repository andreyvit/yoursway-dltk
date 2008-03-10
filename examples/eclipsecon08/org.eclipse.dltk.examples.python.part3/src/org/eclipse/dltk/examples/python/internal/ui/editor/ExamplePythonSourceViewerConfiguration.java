package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.dltk.examples.python.internal.ui.editor.text.ExamplePythonCodeScanner;
import org.eclipse.dltk.examples.python.internal.ui.editor.text.IExamplePythonColorConstants;
import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.SingleTokenScriptScanner;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.information.IInformationPresenter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

public class ExamplePythonSourceViewerConfiguration extends
		ScriptSourceViewerConfiguration {
	private AbstractScriptScanner fCodeScanner;
	private AbstractScriptScanner fStringScanner;
	private AbstractScriptScanner fCommentScanner;

	public ExamplePythonSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		return new IAutoEditStrategy[] { new DefaultIndentLineAutoEditStrategy() };
	}

	public String[] getIndentPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		return new String[] { "\t", "        " };
	}

	protected ContentAssistPreference getContentAssistPreference() {
		return ExamplePythonContentAssistPreference.getDefault();
	}

	public IInformationPresenter getOutlinePresenter(ScriptSourceViewer viewer,
			boolean doCodeResolve) {
		return null;
	}

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return IExamplePythonPartitions.PYTHON_PARITION_TYPES;
	}

	protected void initializeScanners() {
		this.fCodeScanner = new ExamplePythonCodeScanner(
				this.getColorManager(), this.fPreferenceStore);
		this.fStringScanner = new SingleTokenScriptScanner(this
				.getColorManager(), this.fPreferenceStore,
				IExamplePythonColorConstants.PYTHON_STRING);
		this.fCommentScanner = new SingleTokenScriptScanner(this
				.getColorManager(), this.fPreferenceStore,
				IExamplePythonColorConstants.PYTHON_COMMENT);
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new ScriptPresentationReconciler();
		reconciler.setDocumentPartitioning(this
				.getConfiguredDocumentPartitioning(sourceViewer));

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				this.fCodeScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(this.fStringScanner);
		reconciler.setDamager(dr, IExamplePythonPartitions.PYTHON_STRING);
		reconciler.setRepairer(dr, IExamplePythonPartitions.PYTHON_STRING);

		dr = new DefaultDamagerRepairer(this.fCommentScanner);
		reconciler.setDamager(dr, IExamplePythonPartitions.PYTHON_COMMENT);
		reconciler.setRepairer(dr, IExamplePythonPartitions.PYTHON_COMMENT);

		return reconciler;
	}

	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (this.fCodeScanner.affectsBehavior(event)) {
			this.fCodeScanner.adaptToPreferenceChange(event);
		}
		if (this.fStringScanner.affectsBehavior(event)) {
			this.fStringScanner.adaptToPreferenceChange(event);
		}
	}

	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return this.fCodeScanner.affectsBehavior(event)
				|| this.fStringScanner.affectsBehavior(event);
	}
}
