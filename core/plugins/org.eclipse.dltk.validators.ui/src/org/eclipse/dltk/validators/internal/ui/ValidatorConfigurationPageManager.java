/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.validators.ui.ValidatorConfigurationPage;

public class ValidatorConfigurationPageManager {

	private final static String LANGUAGE_EXTPOINT = ValidatorsUI.PLUGIN_ID
			+ ".validatorConfigPage"; //$NON-NLS-1$

	private final static String ID_ATTR = "id"; //$NON-NLS-1$

	private static Map toolkits;

	private static void initialize() {
		if (toolkits != null) {
			return;
		}

		toolkits = new HashMap(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(LANGUAGE_EXTPOINT);

		for (int i = 0; i < cfg.length; i++) {
			String id = cfg[i].getAttribute(ID_ATTR);
			if (toolkits.get(id) != null)
				System.err.println("TODO log redeclaration"); //$NON-NLS-1$
			toolkits.put(id, cfg[i]);
		}
	}

	public static ValidatorConfigurationPage getConfigurationPage(String Id)
			throws CoreException {
		initialize();

		Object ext = toolkits.get(Id);

		if (ext != null) {
			if (ext instanceof ValidatorConfigurationPage)
				return (ValidatorConfigurationPage) ext;

			IConfigurationElement cfg = (IConfigurationElement) ext;
			ValidatorConfigurationPage toolkit = (ValidatorConfigurationPage) cfg
					.createExecutableExtension("class"); //$NON-NLS-1$
			toolkits.put(Id, toolkit);
			return toolkit;
		}
		return null;
	}
}
