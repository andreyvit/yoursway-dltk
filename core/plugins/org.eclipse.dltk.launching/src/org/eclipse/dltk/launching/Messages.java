package org.eclipse.dltk.launching;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.launching.messages"; //$NON-NLS-1$
	public static String EnvironmentVariable_variableNameAndValueMustNotBeEmpty;
	public static String InterpreterConfig_interpreterArgumentCannotBeNull;
	public static String InterpreterConfig_scriptArgumentCannotBeNull;
	public static String InterpreterConfig_scriptFileCannotBeNull;
	public static String InterpreterConfig_workingDirectoryCannotBeNull;
	public static String InterpreterSearcher_foundSearching;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
