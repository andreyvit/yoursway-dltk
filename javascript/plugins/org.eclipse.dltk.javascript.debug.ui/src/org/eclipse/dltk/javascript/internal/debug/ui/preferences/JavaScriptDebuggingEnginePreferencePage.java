package org.eclipse.dltk.javascript.internal.debug.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingEngineOptionsBlock;
import org.eclipse.dltk.javascript.core.JavaScriptNature;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugConstants;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugPlugin;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class JavaScriptDebuggingEnginePreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

	private static PreferenceKey DEBUGGING_ENGINE = new PreferenceKey(
			JavaScriptDebugPlugin.PLUGIN_ID,
			JavaScriptDebugConstants.DEBUGGING_ENGINE_ID_KEY);

	private static final String PREFERENCE_PAGE_ID = "org.eclipse.dltk.javascript.preferences.debug.engines";
	private static final String PROPERTY_PAGE_ID = "org.eclipse.dltk.javascript.propertyPage.debug.engines";
	
	protected AbstractOptionsBlock createOptionsBlock(
			IStatusChangeListener newStatusChangedListener, IProject project,
			IWorkbenchPreferenceContainer container) {
		return new AbstractDebuggingEngineOptionsBlock(
				newStatusChangedListener, project, getKeys(), container) {

			protected String getNatureId() {
				return JavaScriptNature.NATURE_ID;
			}

			protected PreferenceKey getSavedContributionKey() {
				return DEBUGGING_ENGINE;
			}
		};
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
	 */
	protected String getHelpId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
	 */
	protected String getPreferencePageId() {
		return PREFERENCE_PAGE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getProjectHelpId()
	 */
	protected String getProjectHelpId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
	 */
	protected String getPropertyPageId() {
		return PROPERTY_PAGE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setDescription()
	 */
	protected void setDescription() {
		setDescription(JavaScriptDebugPreferenceMessages.JavaScriptDebugEnginePreferencePage_description);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
	 */
	protected void setPreferenceStore() {
		setPreferenceStore(new PreferencesAdapter(JavaScriptDebugPlugin.getDefault()
				.getPluginPreferences()));
	}

	private PreferenceKey[] getKeys() {
		return new PreferenceKey[] { DEBUGGING_ENGINE };
	}
}
