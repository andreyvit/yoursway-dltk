package org.eclipse.dltk.core.mixin;

import org.eclipse.dltk.core.ISourceModule;


public interface IMixinParser {
	/**
	 * Module could be null if signature are false.
	 * If signature are set to true, then module could not be null.
	 * @param contents
	 * @param signature
	 * @param module
	 */
	public void parserSourceModule(char[] contents, boolean signature, ISourceModule module);
	void setRequirestor( IMixinRequestor requestor );
}