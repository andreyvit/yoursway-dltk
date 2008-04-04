package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.ui.actions.messages"; //$NON-NLS-1$
	public static String PopupScriptDisplayAction_moveToDisplayView;
	public static String ScriptDisplayAction_displayResult;
	public static String ScriptEvaluationAction_anExceptionOccurred;
	public static String ScriptEvaluationAction_anExceptionOccurred2;
	public static String ScriptEvaluationAction_cannotGetStackFrame;
	public static String ScriptEvaluationAction_errorEvaluating;
	public static String ScriptEvaluationAction_errorMessage;
	public static String ScriptEvaluationAction_threadIsNotSuspended;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
