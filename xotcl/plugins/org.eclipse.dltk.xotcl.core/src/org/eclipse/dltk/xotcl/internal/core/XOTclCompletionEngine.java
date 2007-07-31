package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.tcl.internal.core.codeassist.TclCompletionEngine;
public class XOTclCompletionEngine extends TclCompletionEngine {
	public XOTclCompletionEngine() {
		this.parser = new XOTclCompletionParser();
	}
}
