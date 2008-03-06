/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.ruby.activestatedebugger.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.debug.ui.preferences.ExternalDebuggingEngineOptionsBlock;
import org.eclipse.dltk.ruby.activestatedebugger.RubyActiveStateDebuggerConstants;
import org.eclipse.dltk.ruby.activestatedebugger.RubyActiveStateDebuggerPlugin;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class RubyActiveStateDebuggerPreferencePage extends
		AbstractConfigurationBlockPropertyAndPreferencePage {

	static PreferenceKey ENGINE_PATH = new PreferenceKey(
			RubyActiveStateDebuggerPlugin.PLUGIN_ID,
			RubyActiveStateDebuggerConstants.DEBUGGING_ENGINE_PATH_KEY);

	static PreferenceKey ENABLE_LOGGING = new PreferenceKey(
			RubyActiveStateDebuggerPlugin.PLUGIN_ID,
			RubyActiveStateDebuggerConstants.ENABLE_LOGGING);

	static PreferenceKey LOG_FILE_PATH = new PreferenceKey(
			RubyActiveStateDebuggerPlugin.PLUGIN_ID,
			RubyActiveStateDebuggerConstants.LOG_FILE_PATH);

	static PreferenceKey LOG_FILE_NAME = new PreferenceKey(
			RubyActiveStateDebuggerPlugin.PLUGIN_ID,
			RubyActiveStateDebuggerConstants.LOG_FILE_NAME);

	private static final String PREFERENCE_PAGE_ID = "org.eclipse.dltk.ruby.preferences.debug.engines.activestatedebugger";
	private static final String PROPERTY_PAGE_ID = "org.eclipse.dltk.ruby.propertyPage.debug.engines.activestatedebugger";

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#createOptionsBlock(org.eclipse.dltk.ui.util.IStatusChangeListener,
	 *      org.eclipse.core.resources.IProject,
	 *      org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
	 */
	protected AbstractOptionsBlock createOptionsBlock(
			IStatusChangeListener newStatusChangedListener, IProject project,
			IWorkbenchPreferenceContainer container) {
		return new ExternalDebuggingEngineOptionsBlock(
				newStatusChangedListener, project, new PreferenceKey[] {
						ENGINE_PATH, ENABLE_LOGGING, LOG_FILE_PATH,
						LOG_FILE_NAME }, container) {

			protected void createLoggingBlock(Composite composite) {
				/*
				 * remove once the python active state debug runner supports
				 * setting up the log file
				 */
			}

			protected PreferenceKey getDebuggingEnginePathKey() {
				return ENGINE_PATH;
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
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getProjectHelpId()
	 */
	protected String getProjectHelpId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setDescription()
	 */
	protected void setDescription() {
		setDescription(PreferenceMessages.PreferencesDescription);
	}

	/*
	 * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
	 */
	protected void setPreferenceStore() {
		setPreferenceStore(RubyActiveStateDebuggerPlugin.getDefault()
				.getPreferenceStore());
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
	 */
	protected String getPreferencePageId() {
		return PREFERENCE_PAGE_ID;
	}

	/*
	 * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
	 */
	protected String getPropertyPageId() {
		return PROPERTY_PAGE_ID;
	}
}
