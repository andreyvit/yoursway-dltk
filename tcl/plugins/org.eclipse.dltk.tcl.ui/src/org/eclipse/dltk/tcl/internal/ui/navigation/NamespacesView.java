package org.eclipse.dltk.tcl.internal.ui.navigation;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;

public class NamespacesView extends ElementsView {

	public String getElementName(Object element) {		
		if( element instanceof IType ) {
			String buffer = "";			
			IType type = (IType)element;
			while( type != null ) {
				String str = getOriginalElementText(type);
				if( str.startsWith("::")) {
					buffer = str + buffer;
				}
				else {
					buffer = "::" + str + buffer;
				}
				
				IModelElement parent = type.getParent();
				if( parent instanceof IType ) {
					type = (IType)parent;
				}
				else {
					type = null;
				}
			}
			String from = ((IType)element).getScriptFolder().getElementName();
			if( from.length() > 0 ) {				
				return buffer + " (" + from + ")";
			}
			return buffer;
		}
		return null;
	}

	public String getJobTitle() {
		return "Namespaces view search...";
	}

	public boolean isElement(IModelElement e) {
		return e instanceof IType;
	}

	public boolean needProcessChildren(IModelElement e) {
		if( e instanceof IMethod ) {
			return false;
		}
		return true;
	}	
	protected String getPreferencesId() {		
		return "NamespacesView_";
	}	
}
