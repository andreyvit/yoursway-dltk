package org.eclipse.dltk.core.mixin;

import org.eclipse.dltk.core.ISourceModule;

public interface IMixinElement {
	IMixinElement[] getChildren();
	String getKey();
	
	/**
	 * Return sepecified object or List of objects if it is non one.
	 * @param module
	 * @return
	 */
	Object getObject(ISourceModule module);
	IMixinElement getParent();
	ISourceModule[] getSourceModules();
}
