package org.eclipse.dltk.ruby.internal.ui.templates;

import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.dltk.ruby.internal.ui.preferences.SimpleRubySourceViewerConfiguration;
import org.eclipse.dltk.ruby.internal.ui.text.RubyPartitions;
import org.eclipse.dltk.ruby.internal.ui.text.RubyTextTools;
import org.eclipse.dltk.ui.templates.ScriptTemplatePreferencePage;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RubyTemplatePreferencePage extends ScriptTemplatePreferencePage
		implements IWorkbenchPreferencePage {
	public RubyTemplatePreferencePage() {
		setPreferenceStore(RubyUI.getDefault().getPreferenceStore());

		setTemplateStore(RubyTemplateAccess.getInstance().getTemplateStore());
		setContextTypeRegistry(RubyTemplateAccess.getInstance()
				.getContextTypeRegistry());
	}

	protected ScriptSourceViewerConfiguration createSourceViewerConfiguration(
			IDocument document) {
		IPreferenceStore store = RubyUI.getDefault().getPreferenceStore();

		RubyTextTools textTools = RubyUI.getDefault().getTextTools();
		textTools.setupDocumentPartitioner(document,
				RubyPartitions.RUBY_PARTITIONING);

		return new SimpleRubySourceViewerConfiguration(textTools
				.getColorManager(), store, null,
				RubyPartitions.RUBY_PARTITIONING, false);

	}
}
