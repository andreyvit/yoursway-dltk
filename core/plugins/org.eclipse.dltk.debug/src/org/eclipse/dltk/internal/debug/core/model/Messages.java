package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.debug.core.model.messages"; //$NON-NLS-1$
	public static String HotCodeReplaceManager_hotCodeReplaceProviderForNotFound;
	public static String ScriptStackFrame_classVariables;
	public static String ScriptStackFrame_globalVariables;
	public static String ScriptStackFrame_stackFrame;
	public static String ScriptStackFrame_unableToLoadVariables;
	public static String ScriptValue_detailFormatterRequiredToContainIdentifier;
	public static String ScriptValue_unableToLoadChildrenOf;
	public static String ScriptVariable_cantAssignVariable;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
