package org.eclipse.dltk.compiler.problem;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.compiler.problem.messages"; //$NON-NLS-1$
	public static String DLTKProblemReporter_factoryCannotBeNull;
	public static String DLTKProblemReporter_resourceCannotBeNull;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
