package org.eclipse.dltk.ast;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ast.messages"; //$NON-NLS-1$
	public static String ASTNode_nodeDoesntSupportDebugPrinting;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
