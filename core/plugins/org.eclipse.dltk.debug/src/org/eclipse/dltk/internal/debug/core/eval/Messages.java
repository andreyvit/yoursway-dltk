package org.eclipse.dltk.internal.debug.core.eval;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.core.eval.messages"; //$NON-NLS-1$
	public static String ScriptEvaluationEngine_cantEvaluate;
	public static String ScriptEvaluationEngine_evaluationOf;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
