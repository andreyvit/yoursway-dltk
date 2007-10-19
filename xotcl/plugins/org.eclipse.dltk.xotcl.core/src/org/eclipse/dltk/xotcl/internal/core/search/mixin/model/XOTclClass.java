package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;
import org.eclipse.dltk.xotcl.core.IXOTclModifiers;


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
	protected boolean isValidModelElement(IModelElement element) {
		if (!(element.getElementType() == IModelElement.TYPE)) {
			return false;
		}
		IType type = (IType) element;
		try {
			if ((type.getFlags() & IXOTclModifiers.AccXOTcl) != 0) {
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
