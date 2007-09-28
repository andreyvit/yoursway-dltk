package org.eclipse.dltk.tcl.internal.core.search.mixin.model;

public class TclNamespace extends TclMixinElement {
	public int getType() {
		return ELEMENT_NAMESPACE;
	}
	public String toString() {
		return "namespace";
	}
}
