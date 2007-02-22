package org.eclipse.dltk.tcl.internal.ui.navigation;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.tcl.internal.ui.navigation.ElementsView.ElementList;

public class ElementListAdapterFactory implements IAdapterFactory {
	private static Class[] PROPERTIES = new Class[] { IModelElement.class };

	public Class[] getAdapterList() {
		return PROPERTIES;
	}

	public Object getAdapter(Object element, Class key) {
		if (IModelElement.class.equals(key)) {
			if (element instanceof ElementList) {
				return ((ElementList) element).getFirstElement();
			}
		}
		return null;
	}
}
