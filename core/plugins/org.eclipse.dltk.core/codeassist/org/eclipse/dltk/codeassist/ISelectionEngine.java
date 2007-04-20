package org.eclipse.dltk.codeassist;

import java.util.Map;

import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.core.SearchableEnvironment;

public interface ISelectionEngine {
	IModelElement[] select(ISourceModule module, int offset, int i);

	void setEnvironment(SearchableEnvironment environment);

	void setOptions(Map options);
}
