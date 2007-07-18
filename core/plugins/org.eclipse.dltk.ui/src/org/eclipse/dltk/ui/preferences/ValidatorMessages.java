package org.eclipse.dltk.ui.preferences;

import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.osgi.util.NLS;

public class ValidatorMessages {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ui.preferences.ValidatorMessages";//$NON-NLS-1$

	static {
		NLS.initializeMessages(BUNDLE_NAME, ValidatorMessages.class);
	}

	private ValidatorMessages() {
		// Do not instantiate
	}

	// Path validator
	public static String FilePathIsEmpty;
	public static String FilePathNotExists;
	public static String FilePathIsInvalid;
	
	// Positive number validator
	public static String PositiveNumberIsEmpty;
	
	public static String PositiveNumberIsInvalid;
	
	// Port validator
	public static String PortIsEmpty;
	public static String PortShouldBeInRange;
}
