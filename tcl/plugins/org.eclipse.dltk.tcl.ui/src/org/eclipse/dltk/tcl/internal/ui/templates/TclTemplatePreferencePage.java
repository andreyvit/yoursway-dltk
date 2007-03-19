package org.eclipse.dltk.tcl.internal.ui.templates;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.text.SimpleTclSourceViewerConfiguration;
import org.eclipse.dltk.tcl.internal.ui.text.TclTextTools;
import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class TclTemplatePreferencePage extends ScriptTemplatePreferencePage
		implements IWorkbenchPreferencePage {
	public TclTemplatePreferencePage() {
		setPreferenceStore(TclUI.getDefault().getPreferenceStore());
		
		setTemplateStore(TclTemplateAccess.getInstance().getTemplateStore());
		setContextTypeRegistry(TclTemplateAccess.getInstance().getContextTypeRegistry());
	}

	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration(IDocument document) {
		IPreferenceStore store = TclUI.getDefault().getPreferenceStore();

		TclTextTools textTools = TclUI.getDefault().getTextTools();
		textTools.setupDocumentPartitioner(document,
				TclPartitions.TCL_PARTITIONING);

		return new SimpleTclSourceViewerConfiguration(
				textTools.getColorManager(), store, null,
				TclPartitions.TCL_PARTITIONING, false);
		
	}
}
