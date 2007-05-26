/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public final class RubyWizardMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.ui.wizards.RubyWizardMessages";//$NON-NLS-1$	

	private RubyWizardMessages() {
	}

	// Project creation wizard
	public static String NewProjectWizard_title;
	public static String NewProjectFirstPage_title;
	public static String NewProjectFirstPage_description;

	// File
	public static String NewFileWizard_title;
	public static String NewFilePage_title;
	public static String NewFilePage_description;
	
	// Class	
	public static String NewClassWizard_title;
	public static String NewClassPage_title;
	public static String NewClassPage_description;
	
	// Pages
	public static String NewModuleWizard_title;
	public static String NewModulePage_title;
	public static String NewModulePage_description;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, RubyWizardMessages.class);
	}
}
