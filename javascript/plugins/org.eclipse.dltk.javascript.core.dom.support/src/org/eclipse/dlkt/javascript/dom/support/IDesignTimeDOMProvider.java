package org.eclipse.dlkt.javascript.dom.support;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.dltk.core.ISourceModule;
import org.mozilla.javascript.ScriptableObject;

public interface IDesignTimeDOMProvider extends IExecutableExtension {

	/**
	 * 
	 * @param module
	 * @return true if this provider works for given source module
	 */
	public boolean canResolve(ISourceModule module);

	/**
	 * 
	 * @param module
	 * @return top level DOM object for given module
	 */
	public ScriptableObject resolveTopLevelScope(ISourceModule module);

	/**
	 * 
	 * @param module
	 * @return set of classes wich are defined for given module
	 */
	public Class[] resolveHostObjectClasses(ISourceModule module);
}
