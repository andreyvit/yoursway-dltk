package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;

public class ExamplePythonTextTools extends ScriptTextTools {

	private final static String[] LEGAL_CONTENT_TYPES = new String[] {
			IExamplePythonPartitions.PYTHON_STRING,
			IExamplePythonPartitions.PYTHON_COMMENT };

	private IPartitionTokenScanner fPartitionScanner;

	public ExamplePythonTextTools(boolean autoDisposeOnDisplayDispose) {
		super(IExamplePythonPartitions.PYTHON_PARTITIONING,
				LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
		fPartitionScanner = new ExamplePythonPartitionScanner();
	}

	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor,
			String partitioning) {
		return new ExamplePythonSourceViewerConfiguration(getColorManager(),
				preferenceStore, editor, partitioning);
	}
	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}
}
