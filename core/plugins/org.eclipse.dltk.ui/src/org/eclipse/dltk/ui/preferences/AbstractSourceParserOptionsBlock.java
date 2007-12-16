package org.eclipse.dltk.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ast.parser.SourceParserManager;
import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class AbstractSourceParserOptionsBlock extends
		ContributedExtensionOptionsBlock {

	public AbstractSourceParserOptionsBlock(IStatusChangeListener context,
			IProject project, PreferenceKey[] allKeys,
			IWorkbenchPreferenceContainer container) {
		super(context, project, allKeys, container);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionOptionsBlock#getExtensionManager()
	 */
	protected DLTKContributionExtensionManager getExtensionManager() {
		return SourceParserManager.getInstance();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionOptionsBlock#getSelectorGroupLabel()
	 */
	protected String getSelectorGroupLabel() {
		return PreferencesMessages.SourceParsers_groupLabel;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionOptionsBlock#getSelectorNameLabel()
	 */
	protected String getSelectorNameLabel() {
		return PreferencesMessages.SourceParsers_nameLabel;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionOptionsBlock#getPreferenceLinkMessage()
	 */
	protected String getPreferenceLinkMessage() {
		return PreferencesMessages.SourceParsers_LinkToPreferences;
	}

}
