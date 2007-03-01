/**
 * 
 */
package org.eclipse.dltk.ruby.typeinference;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.dltk.core.IModelElement;

class UniqueNamesList extends ArrayList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4866432224140371654L;
	
	HashSet names = new HashSet ();
	
	public boolean add(Object elem) {
		if (elem instanceof IModelElement) {
			IModelElement modelElement = (IModelElement) elem;
			if (names.contains(modelElement.getElementName()))
					return false;
			names.add(modelElement.getElementName());
		}
		return super.add(elem);
	}

	public void clear() {			
		super.clear();
		names.clear();
	}

	public boolean contains(Object elem) {
		if (elem instanceof IModelElement) {
			IModelElement modelElement = (IModelElement) elem;
			return names.contains(modelElement.getElementName());
		}
		return super.contains(elem);
	}

}