package org.eclipse.dltk.itcl.internal.core;

import org.eclipse.dltk.tcl.core.ITclKeywords;

public class IncrTclKeywords implements ITclKeywords {
	private static String[] XOTclKeywords = { "class", "itcl", "body", "code",
			"configbody", "delete", "ensemble", "scope", "part", "constructor",
			"destructor", "common", "public", "protected", "private", "method" };

	public String[] getKeywords() {
		return XOTclKeywords;
	}

	public String[] getKeywords(int type) {
		return XOTclKeywords;
	}
}
