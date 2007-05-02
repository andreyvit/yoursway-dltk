/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

public final class TclCheckerConstants {
	private TclCheckerConstants() {
	}

	public static final String PREF_PATH = "tclchecker.path";

	public static final String PREF_MODE = "tclchecker.mode";

	public static final int MODE_ERRORS = 0;

	public static final int MODE_ERRORS_AND_USAGE_WARNINGS = 1;

	public static final int MODE_ALL = 2;
	
	public static final int MODE_DEFAULT = MODE_ALL; 
}
