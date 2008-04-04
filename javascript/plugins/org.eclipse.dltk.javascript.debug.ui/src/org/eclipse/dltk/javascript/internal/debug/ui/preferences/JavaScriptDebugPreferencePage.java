package org.eclipse.dltk.javascript.internal.debug.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingOptionsBlock;
import org.eclipse.dltk.javascript.internal.debug.JavaScriptDebugPlugin;
import org.eclipse.dltk.ui.PreferencesAdapter;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class JavaScriptDebugPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage {

    private static PreferenceKey BREAK_ON_FIRST_LINE =
        new PreferenceKey(JavaScriptDebugPlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);

    private static PreferenceKey ENABLE_DBGP_LOGGING =
        new PreferenceKey(JavaScriptDebugPlugin.PLUGIN_ID,
            DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
	
	private static String PREFERENCE_PAGE_ID = "org.eclipse.dltk.javascript.preferences.debug";
	private static String PROPERTY_PAGE_ID = "org.eclipse.dltk.javascript.propertyPage.debug";
    
	protected AbstractOptionsBlock createOptionsBlock(
			IStatusChangeListener newStatusChangedListener, IProject project,
			IWorkbenchPreferenceContainer container) {
		return new AbstractDebuggingOptionsBlock(newStatusChangedListener,
				project, getKeys(), container) {

			protected PreferenceKey getBreakOnFirstLineKey() {
				return BREAK_ON_FIRST_LINE;
			}

			protected PreferenceKey getDbgpLoggingEnabledKey() {
				return ENABLE_DBGP_LOGGING;
			}
		};
	}
	
	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
	 */
	protected String getHelpId() {
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
		setDescription(JavaScriptDebugPreferenceMessages.JavaScriptDebugPreferencePage_description);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
	 */
	protected void setPreferenceStore() {
		setPreferenceStore(new PreferencesAdapter(JavaScriptDebugPlugin.getDefault().getPluginPreferences()));
	}
	
	private PreferenceKey[] getKeys() {
		return new PreferenceKey[] { BREAK_ON_FIRST_LINE, ENABLE_DBGP_LOGGING };
	}
}
