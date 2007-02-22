package org.eclipse.dltk.tcl.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class TclWizardMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.dltk.tcl.internal.ui.wizards.TclWizardMessages";//$NON-NLS-1$

	private TclWizardMessages() {
	}

	public static String ProjectCreationWizard_title;
	
	public static String ProjectCreationWizardFirstPage_title;
	public static String ProjectCreationWizardFirstPage_description;


	static {
		NLS.initializeMessages(BUNDLE_NAME, TclWizardMessages.class);
	}
}
