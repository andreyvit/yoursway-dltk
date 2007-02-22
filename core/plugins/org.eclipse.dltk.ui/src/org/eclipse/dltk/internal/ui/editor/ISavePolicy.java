package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.dltk.core.ISourceModule;


public interface ISavePolicy {

	/**
	 *
	 */
	void preSave(ISourceModule unit);

	/**
	 * Returns the compilation unit in which the argument
	 * has been changed. If the argument is not changed, the
	 * returned result is <code>null</code>.
	 */
	ISourceModule postSave(ISourceModule unit);
}

