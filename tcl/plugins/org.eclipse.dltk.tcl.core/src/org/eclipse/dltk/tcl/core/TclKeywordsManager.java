/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.DLTKCore;

public final class TclKeywordsManager {
	private static final String EXTENSION_POINT = "org.eclipse.dltk.tcl.core.tclkeywords";
	private static final String CLASS = "class";
	private static String[][] all = new String[ITclKeywords.END_INDEX][];
	private static boolean initialized = false;

	private static void initialize() {
		if( initialized) {
			return;
		}
		initialized = true;
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT);
		for (int i = 0; i < ITclKeywords.END_INDEX; i++) {
			all[i] = new String[0];
		}
		for (int i = 0; i < cfg.length; i++) {
			if (cfg[i].getName().equals("keywords")) {
				try {
					ITclKeywords keywords = (ITclKeywords) cfg[i]
							.createExecutableExtension(CLASS);
					if (keywords != null) {
						for (int q = 0; q < ITclKeywords.END_INDEX; ++q) {
							String[] kw2 = keywords.getKeywords(q);
							all[q] = TclKeywords.append(all[q], kw2);
						}
					}
				} catch (CoreException e) {
					if (DLTKCore.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static String[] getKeywords() {
		initialize();
		return all[ITclKeywords.ALL];
	}

	public static String[] getKeywords(int type) {
		initialize();
		if (type >= 0 && type < all.length) {
			return all[type];
		}
		return null;
	}
}
