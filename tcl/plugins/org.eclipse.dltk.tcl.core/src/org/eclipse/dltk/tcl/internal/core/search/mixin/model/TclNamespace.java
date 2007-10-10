package org.eclipse.dltk.tcl.internal.core.search.mixin.model;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;

public class TclNamespace extends TclMixinElement {
	public int getType() {
		return ELEMENT_NAMESPACE;
	}

	public String toString() {
		return "namespace";
	}

	protected boolean isValidModelElement(IModelElement element) {
		if (!(element.getElementType() == IModelElement.TYPE)) {
			return false;
		}
		IType type = (IType) element;
		try {
			if ((type.getFlags() & Modifiers.AccNameSpace) == 0) {
				return true;
			}
		} catch (ModelException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
