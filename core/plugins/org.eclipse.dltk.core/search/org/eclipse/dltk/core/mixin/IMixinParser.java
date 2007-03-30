package org.eclipse.dltk.core.mixin;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceModuleInfoCache;


public interface IMixinParser {
	/**
	 * Module could be null if signature are false.
	 * If signature are set to true, then module could not be null and user are 
	 * able to use search. If not, user could not use search.
	 * 
	 * @param contents
	 * @param signature
	 * @param module
	 */
	public void parserSourceModule(char[] contents, boolean signature, ISourceModule module, ISourceModuleInfoCache.ISourceModuleInfo info);
	void setRequirestor( IMixinRequestor requestor );
}
