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
	public static String ProjectCreationWizard_title;
	public static String ProjectCreationWizardFirstPage_title;
	public static String ProjectCreationWizardFirstPage_description;

	// File creation wizard
	public static String FileCreationWizard_title;

	static {
		NLS.initializeMessages(BUNDLE_NAME, RubyWizardMessages.class);
	}
}
