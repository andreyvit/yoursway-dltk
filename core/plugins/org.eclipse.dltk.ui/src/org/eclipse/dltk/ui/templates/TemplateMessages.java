package org.eclipse.dltk.ui.templates;

import org.eclipse.osgi.util.NLS;

public final class TemplateMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.templates.messages"; //$NON-NLS-1$

	static {
		NLS.initializeMessages(BUNDLE_NAME, TemplateMessages.class);
	}
	
	public static String Variable_File_Description;
	public static String Variable_Language_Description;
	
	public static String Validation_SeveralCursorPositions;
}
