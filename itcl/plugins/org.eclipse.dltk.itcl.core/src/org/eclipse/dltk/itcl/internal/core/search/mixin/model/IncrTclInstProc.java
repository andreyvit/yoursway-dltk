package org.eclipse.dltk.itcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;

public class IncrTclInstProc extends TclMixinElement implements IIncrTclMixinConstants {
	public int getType() {
		return ELEMENT_INCRTCL_INSTPROC;
	}
	public String toString() {
		return "xotclinstproc";
	}
	protected boolean isValidModelElement(IModelElement element) {
		return element.getElementType() == IModelElement.METHOD;
	}
}
