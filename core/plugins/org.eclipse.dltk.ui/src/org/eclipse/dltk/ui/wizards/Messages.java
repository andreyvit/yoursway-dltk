package org.eclipse.dltk.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.wizards.messages"; //$NON-NLS-1$
	public static String GenericDLTKProjectWizard_createNewDltkProject;
	public static String GenericDLTKProjectWizard_natureMustBeSpecified;
	public static String GenericDLTKProjectWizard_newDltkProject;
	public static String NewSourceModulePage_file;
	public static String NewSourceModulePage_fileAlreadyExists;
	public static String NewSourceModulePage_noFoldersAvailable;
	public static String NewSourceModulePage_pathCannotBeEmpty;
	public static String NewSourceModulePage_selectScriptFolder;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
