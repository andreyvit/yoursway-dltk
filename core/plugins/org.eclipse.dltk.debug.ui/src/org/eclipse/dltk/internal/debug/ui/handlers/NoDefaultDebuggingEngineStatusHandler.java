/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.ui.DLTKUILanguageManager;

public class NoDefaultDebuggingEngineStatusHandler extends
		AbstractOpenPreferencePageStatusHandler {

	protected String getPreferencePageId(Object source) {
		if (source instanceof AbstractScriptLaunchConfigurationDelegate) {
			AbstractScriptLaunchConfigurationDelegate delegate = (AbstractScriptLaunchConfigurationDelegate) source;
			return DLTKUILanguageManager.getLanguageToolkit(
					delegate.getLanguageId()).getDebugPreferencePage();
		}

		return null;
	}

	protected String getQuestion() {
		return HandlerMessages.NoDefaultDebuggingEngineQuestion;
	}

	protected String getTitle() {
		return HandlerMessages.NoDefaultDebuggingEngineTitle;
	}
}
