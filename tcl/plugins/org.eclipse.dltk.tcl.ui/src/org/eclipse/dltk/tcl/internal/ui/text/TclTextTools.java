package org.eclipse.dltk.tcl.internal.ui.text;

import org.eclipse.dltk.tcl.ui.text.TclPartitions;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;


public class TclTextTools extends ScriptTextTools {
	
	private IPartitionTokenScanner fPartitionScanner;
	
	private final static String[] LEGAL_CONTENT_TYPES = new String[] {
		TclPartitions.TCL_STRING,
		TclPartitions.TCL_COMMENT
	};

	public TclTextTools(boolean autoDisposeOnDisplayDispose) {
		super(TclPartitions.TCL_PARTITIONING, LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
		fPartitionScanner = new TclPartitionScanner();
	}

	
	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor, String partitioning) {
		return new TclSourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
	}

	
	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}

}
