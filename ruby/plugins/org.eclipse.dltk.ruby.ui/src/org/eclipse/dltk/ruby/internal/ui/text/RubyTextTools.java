package org.eclipse.dltk.ruby.internal.ui.text;

import org.eclipse.dltk.ruby.ui.text.IRubyPartitions;
import org.eclipse.dltk.ui.text.DLTKSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.TextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;

public class RubyTextTools extends TextTools {
	
	private IPartitionTokenScanner fPartitionScanner;
	
	private final static String[] LEGAL_CONTENT_TYPES = new String[] {
		IRubyPartitions.RUBY_STRING,
		IRubyPartitions.RUBY_COMMENT,
		IRubyPartitions.RUBY_DOC
	};

	public RubyTextTools(boolean autoDisposeOnDisplayDispose) {
		super(IRubyPartitions.RUBY_PARTITIONING, LEGAL_CONTENT_TYPES, autoDisposeOnDisplayDispose);
		fPartitionScanner = new RubyPartitionScanner();
	}

	
	public DLTKSourceViewerConfiguration createSourceViewerConfiguraton(
			IPreferenceStore preferenceStore, ITextEditor editor, String partitioning) {
		return new RubySourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
	}

	
	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}

}
