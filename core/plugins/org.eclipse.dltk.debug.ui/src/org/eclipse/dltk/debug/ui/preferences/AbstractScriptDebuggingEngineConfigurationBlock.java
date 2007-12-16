package org.eclipse.dltk.debug.ui.preferences;

import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.ui.preferences.ContributedExtensionConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 * @deprecated use AbstractDebuggingOptionsBlock instead
 */
public abstract class AbstractScriptDebuggingEngineConfigurationBlock extends
		ContributedExtensionConfigurationBlock {

	public AbstractScriptDebuggingEngineConfigurationBlock(
			OverlayPreferenceStore store, PreferencePage preferencePage) {
		super(store, preferencePage);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionConfigurationBlock#addPreferenceLink(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
	 */
	protected void addPreferenceLink(Composite composite,
			IWorkbenchPreferenceContainer container) {

		// link to the general debugging preferences page
		PreferenceLinkArea area = new PreferenceLinkArea(composite, SWT.NONE,
				ScriptDebugPreferencePage.PAGE_ID,
				ScriptDebugPreferencesMessages.LinkToGeneralPreferences,
				container, null);

		area.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false));
	}

	protected DLTKContributionExtensionManager getExtensionManager() {
		return DebuggingEngineManager.getInstance();
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionConfigurationBlock#getSelectorGroupLabel()
	 */
	protected String getSelectorGroupLabel() {
		return ScriptDebugPreferencesMessages.DebuggingEngine;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionConfigurationBlock#getSelectorNameLabel()
	 */
	protected String getSelectorNameLabel() {
		return ScriptDebugPreferencesMessages.NameLabel;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.ContributedExtensionConfigurationBlock#getSelectorDescLabel()
	 */
	protected String getSelectorDescLabel() {
		return ScriptDebugPreferencesMessages.DescriptionLabel;
	}

	protected void addDescriptionPreferenceLink(Composite composite, String prefPageId,
			IWorkbenchPreferenceContainer container) {

		PreferenceLinkArea area = new PreferenceLinkArea(
				composite,
				SWT.NONE,
				prefPageId,
				ScriptDebugPreferencesMessages.LinkToDebuggingEnginePreferences,
				container, null);

		area.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, false, false));
	}

}
