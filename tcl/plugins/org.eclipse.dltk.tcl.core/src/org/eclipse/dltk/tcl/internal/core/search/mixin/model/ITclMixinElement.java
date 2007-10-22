package org.eclipse.dltk.tcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;

public interface ITclMixinElement {
	//tcl
	public final static int ELEMENT_NAMESPACE = 0;
	public final static int ELEMENT_FIELD = 1;
	public final static int ELEMENT_PROC = 2;
	
	/**
	 * Returns type of this mixin element. 
	 * @return
	 */
	int getType();
	
	/**
	 * Initializes content of this tcl element.
	 * So after it it is possible to call getModelElement and so on.
	 */
	void initialize(IMixinElement element, ISourceModule module, TclMixinModel model);
	
	IModelElement getModelElement();
	String getKey();
	String getName();
}
