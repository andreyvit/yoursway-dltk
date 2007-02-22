package org.eclipse.dltk.console.ui.internal;

import org.eclipse.osgi.util.NLS;

public class ScriptConsoleMessages extends NLS  {
		private static final String BUNDLE_NAME = "org.eclipse.dltk.console.ui.internal.ScriptConsoleMessages"; //$NON-NLS-1$

		public static String SaveSessionAction;

		public static String SaveSessionTooltip;

		public static String TerminateConsoleAction;
		
		public static String TerminateConsoleTooltip;

		static {
			NLS.initializeMessages(BUNDLE_NAME, ScriptConsoleMessages.class);
		}
}
