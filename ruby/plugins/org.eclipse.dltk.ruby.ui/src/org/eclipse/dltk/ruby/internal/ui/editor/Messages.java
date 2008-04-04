package org.eclipse.dltk.ruby.internal.ui.editor;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.ui.editor.messages"; //$NON-NLS-1$
	public static String RubyEditor_matchingBracketIsOutsideSelectedElement;
	public static String RubyEditor_nobracketSelected;
	public static String RubyEditor_noMatchingBracketFound;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
