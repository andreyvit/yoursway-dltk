package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

public class TclProc extends TclMixinElement {
	public int getType() {
		return ELEMENT_PROC;
	}
	public String toString() {
		return "proc";
	}
}
