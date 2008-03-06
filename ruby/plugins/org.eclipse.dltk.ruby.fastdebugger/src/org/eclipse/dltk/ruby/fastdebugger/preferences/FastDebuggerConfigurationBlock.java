package org.eclipse.dltk.ruby.fastdebugger.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.ui.preferences.DebuggingEngineConfigOptionsBlock;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerPlugin;
import org.eclipse.dltk.ruby.fastdebugger.FastDebuggerConstants;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class FastDebuggerConfigurationBlock extends DebuggingEngineConfigOptionsBlock {

	private static PreferenceKey ENABLE_LOGGING = new PreferenceKey(
			FastDebuggerPlugin.PLUGIN_ID,
			FastDebuggerConstants.ENABLE_LOGGING);

	private static PreferenceKey LOG_FILE_PATH = new PreferenceKey(
			FastDebuggerPlugin.PLUGIN_ID,
			FastDebuggerConstants.LOG_FILE_PATH);

	private static PreferenceKey LOG_FILE_NAME = new PreferenceKey(
			FastDebuggerPlugin.PLUGIN_ID,
			FastDebuggerConstants.LOG_FILE_NAME);

	public FastDebuggerConfigurationBlock(IStatusChangeListener context,
			IProject project, IWorkbenchPreferenceContainer container) {
		super(context, project, getKeys(), container);
	}

	private static PreferenceKey[] getKeys() {
		return new PreferenceKey[] { ENABLE_LOGGING, LOG_FILE_PATH,
				LOG_FILE_NAME };
	}

	protected void createEngineBlock(Composite composite) {
		// no engine preferences, yet...
	}

	protected PreferenceKey getEnableLoggingPreferenceKey() {
		return ENABLE_LOGGING;
	}

	protected PreferenceKey getLogFileNamePreferenceKey() {
		return LOG_FILE_NAME;
	}

	protected PreferenceKey getLogFilePathPreferenceKey() {
		return LOG_FILE_PATH;
	}
}
