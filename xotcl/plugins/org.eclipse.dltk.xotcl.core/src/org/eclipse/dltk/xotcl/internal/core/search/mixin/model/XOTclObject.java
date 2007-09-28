package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;

public class XOTclObject extends TclMixinElement {
	public int getType() {
		return ELEMENT_XOTCL_OBJECT;
	}
	public String toString() {
		return "xotclobject";
	}
}
