package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.tcl.core.TclLanguageToolkit;

public class XOTclLanguageToolkit extends TclLanguageToolkit {
	static {
		sInstance = new XOTclLanguageToolkit();
	}
	public String[] getLanguageFileExtensions() {
		return new String[] {"tcl", "exp", "xotcl", "test"};
	}
}
