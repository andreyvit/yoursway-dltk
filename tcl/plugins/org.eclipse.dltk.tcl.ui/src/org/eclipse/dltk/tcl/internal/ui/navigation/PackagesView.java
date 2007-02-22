package org.eclipse.dltk.tcl.internal.ui.navigation;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IPackageDeclaration;

public class PackagesView extends ElementsView {
	public String getElementName(Object element) {		
		if( element instanceof IPackageDeclaration ) {
			return ((IPackageDeclaration)element).getElementName();
		}
		return null;
	}

	public String getJobTitle() {
		return "Packages view search...";
	}

	public boolean isElement(IModelElement e) {
		return e instanceof IPackageDeclaration;
	}	
	
	public boolean needProcessChildren(IModelElement e) {
		return true;
	}
	protected String getPreferencesId() {		
		return "PackagesView_";
	}	
}
