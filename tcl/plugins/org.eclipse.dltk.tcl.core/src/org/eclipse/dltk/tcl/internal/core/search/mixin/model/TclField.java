package org.eclipse.dltk.tcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;

public class TclField extends TclMixinElement {
	public int getType() {
		return ELEMENT_FIELD;
	}

	public String toString() {
		return "field";
	}

	protected boolean isValidModelElement(IModelElement element) {
		return element.getElementType() == IModelElement.FIELD;
	}
}
