package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;

public class XOTclProc extends TclMixinElement {
	public int getType() {
		return ELEMENT_XOTCL_PROC;
	}
	public String toString() {
		return "xotclproc";
	}
}
