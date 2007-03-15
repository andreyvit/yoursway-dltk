package org.eclipse.dltk.javascript.scriptdoc;

import org.eclipse.osgi.util.NLS;


final class JavaDocMessages extends NLS {

	private static final String BUNDLE_NAME= JavaDocMessages.class.getName();

	private JavaDocMessages() {
		// Do not instantiate
	}

	public static String CompletionEvaluator_default_package;
	public static String JavaDoc2HTMLTextReader_parameters_section;
	public static String JavaDoc2HTMLTextReader_returns_section;
	public static String JavaDoc2HTMLTextReader_throws_section;
	public static String JavaDoc2HTMLTextReader_author_section;
	public static String JavaDoc2HTMLTextReader_see_section;
	public static String JavaDoc2HTMLTextReader_since_section;

	static {
		NLS.initializeMessages(BUNDLE_NAME, JavaDocMessages.class);
	}
}