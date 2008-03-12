package org.eclipse.dltk.ruby.internal.parsers.jruby;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.dltk.ruby.internal.parsers.jruby.messages"; //$NON-NLS-1$
	public static String RubyASTBuildVisitor_jrubyNodeHasntBeenConvertedIntoAnyDltkAstNode;
	public static String RubyASTBuildVisitor_unknownArgumentType;
	public static String RubyASTBuildVisitor_unpairedHash;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
