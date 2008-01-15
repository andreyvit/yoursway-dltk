package org.eclipse.dltk.itcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;

public class IncrTclClassInstance extends TclMixinElement implements
		IIncrTclMixinConstants {
	public int getType() {
		return ELEMENT_INCRTCL_CLASS_INSTANCE;
	}

	public String toString() {
		return "xotcl_class_instance";
	}

	protected boolean isValidModelElement(IModelElement element) {
		return element.getElementType() == IModelElement.FIELD;
	}
}
