package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.examples.internal.python.core.ExamplePythonLanguageToolkit;
import org.eclipse.dltk.examples.python.internal.ExamplePythonUI;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.ui.IEditorInput;

public class ExamplePythonEditor extends ScriptEditor {

	public static final String EDITOR_ID = "org.eclipse.dltk.examples.python.part3.ui.editor";

	public static final String EDITOR_CONTEXT = "#PythonEditorContext";

	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
	}

	public String getEditorId() {
		return EDITOR_ID;
	}

	protected IPreferenceStore getScriptPreferenceStore() {
		return ExamplePythonUI.getDefault().getPreferenceStore();
	}

	public IDLTKLanguageToolkit getLanguageToolkit() {
		return ExamplePythonLanguageToolkit.getDefault();
	}

	public ScriptTextTools getTextTools() {
		return ExamplePythonUI.getDefault().getTextTools();
	}

	protected void connectPartitioningToElement(IEditorInput input,
			IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension
					.getDocumentPartitioner(IExamplePythonPartitions.PYTHON_PARTITIONING) == null) {
				ExamplePythonDocumentSetupParticipant participant = new ExamplePythonDocumentSetupParticipant();
				participant.setup(document);
			}
		}
	}
}
