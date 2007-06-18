/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.internal.ui;

import org.eclipse.osgi.util.NLS;

public class ValidatorMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.validators.internal.ui.ValidatorMessages";//$NON-NLS-1$
	public static String addValidatorDialog_ValidatorEnvironmentName;
	public static String ValidatorPreferencePage_1;
	public static String ValidatorPreferencePage_2;
	public static String InstalledValidatorBlock_15;
	public static String InstalledValidatorBlock_0;
	public static String InstalledValidatorBlock_1;
	public static String InstalledValidatorBlock_2;
	public static String InstalledValidatorBlock_3;
	public static String InstalledValidatorBlock_4;
	public static String InstalledValidatorBlock_16;
	public static String InstalledValidatorBlock_5;
	public static String InstalledValidatorBlock_8;
	public static String InstalledValidatorBlock_7;
	public static String addValidatorDialog_enterName;
	public static String addValidatorDialog_duplicateName;
	public static String addValidatorDialog_ValidatorEnvironmentType;
	public static String ValidatorUpdater_0;
	
	private ValidatorMessages() {
	// dont instatiate
	}
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, ValidatorMessages.class);
	}
}
