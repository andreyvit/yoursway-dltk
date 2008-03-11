package org.eclipse.dltk.python.internal.ui.templates;

import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.python.internal.ui.text.PythonTextTools;
import org.eclipse.dltk.python.internal.ui.text.SimplePythonSourceViewerConfiguration;
import org.eclipse.dltk.python.ui.text.IPythonPartitions;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;

public class PythonCodeTemplatePreferencePage extends ScriptTemplatePreferencePage {
	
	public PythonCodeTemplatePreferencePage() {
		setPreferenceStore(PythonUI.getDefault().getPreferenceStore());
		setTemplateStore(PythonTemplateAccess.getInstance().getTemplateStore());
		setContextTypeRegistry(PythonTemplateAccess.getInstance().getContextTypeRegistry());
	}
	
	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration() {
		IPreferenceStore store = PythonUI.getDefault().getPreferenceStore();
		PythonTextTools textTools = PythonUI.getDefault().getTextTools();
		return new SimplePythonSourceViewerConfiguration(textTools.getColorManager(),store,null,IPythonPartitions.PYTHON_PARTITIONING,false);
	}

	protected void setDocumentParticioner(IDocument document) {
		PythonTextTools textTools = PythonUI.getDefault().getTextTools();
		textTools.setupDocumentPartitioner(document, IPythonPartitions.PYTHON_PARTITIONING);
	}

}
