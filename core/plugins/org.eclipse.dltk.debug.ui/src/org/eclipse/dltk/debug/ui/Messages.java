package org.eclipse.dltk.debug.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.debug.ui.messages"; //$NON-NLS-1$
	public static String DebugConsoleManager_debugConsole;
	public static String DLTKDebugUIPlugin_internalError;
	public static String ScriptDebuggerConsoleToFileHyperlink_error;
	public static String ScriptDebugModelPresentation_breakpointText;
	public static String ScriptDebugModelPresentation_breakpointText2;
	public static String ScriptDebugModelPresentation_breakpointText3;
	public static String ScriptDebugModelPresentation_breakpointText4;
	public static String ScriptDebugModelPresentation_breakpointText5;
	public static String ScriptDebugModelPresentation_debugTargetText;
	public static String ScriptDebugModelPresentation_expressionText;
	public static String ScriptDebugModelPresentation_stackFrameText;
	public static String ScriptDebugModelPresentation_stackFrameText2;
	public static String ScriptDebugModelPresentation_threadText;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
