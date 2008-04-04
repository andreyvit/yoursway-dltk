package org.eclipse.dltk.validators.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.validators.core.messages"; //$NON-NLS-1$
	public static String ValidatorRuntime_error;
	public static String ValidatorRuntime_exceptionOccurred;
	public static String ValidatorRuntime_for;
	public static String ValidatorRuntime_runningValidators;
	public static String ValidatorRuntime_validationCouldNotBePerformed;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
