package org.eclipse.dltk.core.mixin;

import org.eclipse.dltk.core.ISourceModule;

public interface IMixinElement {
	/**
	 * Return all children elements.
	 * If not builded then builded.
	 * @return children elements.
	 */
	IMixinElement[] getChildren();
	
	/**
	 * Return key value.
	 * @return key value
	 */
	String getKey();
	
	/**
	 * Return sepecified objects.
	 * If not builded then builded.
	 * @param module
	 * @return
	 */
	Object[] getObjects(ISourceModule module);
	
	/**
	 * Returns all not equal objects.
	 * If not builded then builded.
	 * @return
	 */
	Object[] getAllObjects();
	
	/**
	 * Return parent mixin. Parent mixin aren't builded. 
	 * @return
	 */
	IMixinElement getParent();
	
	/**
	 * If not builded then builded.
	 * @return
	 */
	ISourceModule[] getSourceModules();
}
