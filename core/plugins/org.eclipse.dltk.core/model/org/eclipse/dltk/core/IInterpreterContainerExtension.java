package org.eclipse.dltk.core;

import java.util.List;

public interface IInterpreterContainerExtension {
	/**
	 * This method could modify set of entries.
	 */
	void processEntres(IScriptProject project, List entries);
}
