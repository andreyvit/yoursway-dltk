package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.ui.editor.messages"; //$NON-NLS-1$
	public static String SourceModuleDocumentProvider_saveAsTargetOpenInEditor;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
