package org.eclipse.dltk.internal.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.core.messages"; //$NON-NLS-1$
	public static String Model_invalidResourceForTheProject;
	public static String ModelOperation_operationCancelled;
	public static String Openable_completionRequesterCannotBeNull;
	public static String UserLibraryBuildpathContainerInitializer_dltkLanguageToolkitIsNull;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
