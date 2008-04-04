package org.eclipse.dltk.examples.python.internal.ui;

import org.eclipse.dltk.examples.internal.python.ui.ExamplePythonUILanguageToolkit;
import org.eclipse.dltk.examples.python.internal.ui.editor.ExampleSimplePythonSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;

public class ExamplePythonUILanguageToolkit2 extends
		ExamplePythonUILanguageToolkit {
	public ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		return new ExampleSimplePythonSourceViewerConfiguration(getTextTools()
				.getColorManager(), getPreferenceStore(), null,
				getPartitioningId(), false);
	}
}
