/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.dltk.launching.LaunchingMessages;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;

public class NoDefaultInterpreterStatusHandler extends
		AbstractOpenPreferencePageStatusHandler {
	protected String getTitle() {
		return LaunchingMessages.NoDefaultInterpreterStatusHandler_title;
	}

	protected String getMessage() {
		return LaunchingMessages.NoDefaultInterpreterStatusHandler_message;
	}

	protected String getPreferencePageId(IDLTKUILanguageToolkit toolkit) {
		return toolkit.getInterpreterPreferencePage();
	}
}
