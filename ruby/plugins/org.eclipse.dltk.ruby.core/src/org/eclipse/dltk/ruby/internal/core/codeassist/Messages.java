package org.eclipse.dltk.ruby.internal.core.codeassist;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.core.codeassist.messages"; //$NON-NLS-1$
	public static String RubyCompletionEngine_failedToInitializeRubyCompletionEngine;
	public static String RubyCompletionEngine_pleaseWaitUntilBuildingIsReady;
	public static String RubyCompletionEngine_pleaseWaitWhileDltkRubyBeingInitialized;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
