package org.eclipse.dltk.ui.preferences;

import org.eclipse.dltk.ast.parser.SourceParserManager;
import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.jface.preference.PreferencePage;

public abstract class AbstractSourceParserConfigurationBlock extends
		ContributedExtensionConfigurationBlock {

	public AbstractSourceParserConfigurationBlock(OverlayPreferenceStore store,
			PreferencePage page) {
		super(store, page);
	}

	protected DLTKContributionExtensionManager getExtensionManager() {
		return SourceParserManager.getInstance();
	}

	protected String getSelectorGroupLabel() {
		return PreferencesMessages.SourceParsers_groupLabel;
	}

	protected String getSelectorNameLabel() {
		return PreferencesMessages.SourceParsers_nameLabel;
	}
}
