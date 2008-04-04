/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.validators.internal.ui.ValidatorsUI;
import org.eclipse.ui.console.IPatternMatchListener;

public class ValidatorConsoleTrackerManager {

	private final static String EXTPOINT = ValidatorsUI.PLUGIN_ID
			+ ".validatorConsoleTracker"; //$NON-NLS-1$

	private static List listeners;

	private static void initialize() throws CoreException {
		if (listeners != null) {
			return;
		}

		listeners = new ArrayList(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTPOINT);

		for (int i = 0; i < cfg.length; i++) {
			IPatternMatchListener listener = (IPatternMatchListener) cfg[i]
					.createExecutableExtension("class"); //$NON-NLS-1$
			listeners.add(listener);
		}
	}
	public static IPatternMatchListener[] getListeners() {
		try {
			initialize();
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		return (IPatternMatchListener[])listeners.toArray(new IPatternMatchListener[listeners.size()]);
	}
}
