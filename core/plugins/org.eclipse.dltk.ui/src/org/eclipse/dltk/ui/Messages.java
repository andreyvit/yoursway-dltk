package org.eclipse.dltk.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.messages"; //$NON-NLS-1$
	public static String DLTKExecuteExtensionHelper_natureAttributeMustBeSpecifiedAndCirrect;
	public static String PluginImagesHelper_imageRegistryAlreadyDefined;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
