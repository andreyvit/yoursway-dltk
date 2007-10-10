package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;

public class XOTclInstProc extends TclMixinElement {
	public int getType() {
		return ELEMENT_XOTCL_INSTPROC;
	}
	public String toString() {
		return "xotclinstproc";
	}
	protected boolean isValidModelElement(IModelElement element) {
		return element.getElementType() == IModelElement.METHOD;
	}
}
