package org.eclipse.dltk.internal.debug.core.model.operations;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.core.model.operations.messages"; //$NON-NLS-1$
	public static String DbgpResumeOperation_resumeOperation;
	public static String DbgpStepIntoOperation_stepIntoOperation;
	public static String DbgpStepOverOperation_stepOverOperation;
	public static String DbgpStepReturnOperation_stepReturnOperation;
	public static String DbgpSuspendOperation_suspendOperation;
	public static String DbgpTerminateOperation_terminateOperation;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
