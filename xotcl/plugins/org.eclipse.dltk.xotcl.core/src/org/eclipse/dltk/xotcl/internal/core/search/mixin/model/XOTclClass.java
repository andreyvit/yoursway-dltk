package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

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
