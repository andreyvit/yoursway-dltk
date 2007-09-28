package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;


public class XOTclClass extends TclMixinElement {
	private String namespaceKey;
	public int getType() {
		return ELEMENT_XOTCL_CLASS;
	}
	public String toString() {
		return "xotclclass";
	}
	public void setNamespace(String namespacePrefix) {
		this.namespaceKey = namespacePrefix;
	}
}
