package org.eclipse.dltk.itcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.itcl.internal.core.IIncrTclModifiers;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.TclMixinElement;


public class IncrTclClass extends TclMixinElement implements IIncrTclMixinConstants {
	private String namespaceKey;
	public int getType() {
		return ELEMENT_INCRTCL_CLASS;
	}
	public String toString() {
		return "incrtclclass";
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
			if ((type.getFlags() & IIncrTclModifiers.AccIncrTcl) != 0) {
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
