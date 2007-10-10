package org.eclipse.dltk.tcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;

public class TclProc extends TclMixinElement {
	public int getType() {
		return ELEMENT_PROC;
	}
	public String toString() {
		return "proc";
	}
	protected boolean isValidModelElement(IModelElement element) {
		return element.getElementType() == IModelElement.METHOD;
	}
}
