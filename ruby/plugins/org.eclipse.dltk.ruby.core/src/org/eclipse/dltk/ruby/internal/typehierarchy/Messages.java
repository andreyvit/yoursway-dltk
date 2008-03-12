package org.eclipse.dltk.ruby.internal.typehierarchy;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.typehierarchy.messages"; //$NON-NLS-1$
	public static String RubyTypeHierarchyEngine_collectingTypes;
	public static String RubyTypeHierarchyEngine_searchingTypes;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
