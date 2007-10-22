package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;

public class XOTclClassInstance extends TclMixinElement implements
		IXOTclMixinConstants {
	public int getType() {
		return ELEMENT_XOTCL_CLASS_INSTANCE;
	}

	public String toString() {
		return "xotcl_class_instance";
	}

	protected boolean isValidModelElement(IModelElement element) {
		return element.getElementType() == IModelElement.FIELD;
	}
}
