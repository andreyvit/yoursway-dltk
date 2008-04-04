package org.eclipse.dltk.examples.python.internal.completion.templates;

import org.eclipse.dltk.examples.python.internal.ExamplePythonUI;
import org.eclipse.dltk.examples.python.internal.ui.editor.ExamplePythonTextTools;
import org.eclipse.dltk.examples.python.internal.ui.editor.ExampleSimplePythonSourceViewerConfiguration;
import org.eclipse.dltk.examples.python.internal.ui.editor.IExamplePythonPartitions;
import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.text.IDocument;

public class ExamplePythonCodeTemplatesPreferencePage extends
		ScriptTemplatePreferencePage {
	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		return new ExampleSimplePythonSourceViewerConfiguration(getTextTools()
				.getColorManager(), getPreferenceStore(), null,
				IExamplePythonPartitions.PYTHON_PARTITIONING, false);
	}
	protected void setDocumentParticioner(IDocument document) {
		getTextTools().setupDocumentPartitioner(document,
				IExamplePythonPartitions.PYTHON_PARTITIONING);
	}
	protected void setPreferenceStore() {
		setPreferenceStore(ExamplePythonUI.getDefault().getPreferenceStore());
	}
	protected ScriptTemplateAccess getTemplateAccess() {
		return ExamplePythonTemplateAccess.getInstance();
	}
	private ExamplePythonTextTools getTextTools() {
		return ExamplePythonUI.getDefault().getTextTools();
	}
}
