/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class TclKeywordsManager {
	private static final String PRIORITY = "priority";
	private static final String EXTENSION_POINT = "org.eclipse.dltk.tcl.core.tclkeywords";
	private static final String CLASS = "class";
	private static ITclKeywords sKeywords = null;
	private static int sPriority = -1;
	private TclKeywordsManager(){
	}
	private static void initialize() {
		if( sKeywords != null ) {
			return;
		}
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
		.getConfigurationElementsFor(EXTENSION_POINT);
		for (int i = 0; i < cfg.length; i++) {
			if( cfg[i].getName().equals("keywords")) {
				int priority = getPriority(cfg[i]);
				if( sPriority ==  -1 || sPriority < priority ) {
					try {
						sKeywords = (ITclKeywords) cfg[i].createExecutableExtension(CLASS);
						sPriority = priority;
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if( sKeywords == null ) {
			sKeywords = new TclKeywords();
		}
	}
	private static int getPriority(IConfigurationElement config) {
		String priority = config.getAttribute(PRIORITY);
		if (priority == null) {
			return 0;
		}
		try {
			int parseInt = Integer.parseInt(priority);
			return parseInt;
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static String[] getKeywords() {
		initialize();
		return sKeywords.getKeywords();
	}

	public static String[] getKeywords(int type) {
		initialize();
		return sKeywords.getKeywords(type);
	}
}
