package org.eclipse.dltk.internal.core.mixin;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.internal.core.mixin.messages"; //$NON-NLS-1$
	public static String MixinBuilder_buildingRuntimeModelFor;
	public static String MixinBuilder_buildingRuntimeModelFor2;
	public static String MixinBuilder_savingIndexFor;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
