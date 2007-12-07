package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.tcl.core.TclLanguageToolkit;

public class XOTclLanguageToolkit extends TclLanguageToolkit {
	private static final String[] EXTENSIONS = new String[] {"tcl", "exp", "xotcl", "test"};
	static {
		sInstance = new XOTclLanguageToolkit();
	}
	public String[] getLanguageFileExtensions() {
		return EXTENSIONS;
	}
}
