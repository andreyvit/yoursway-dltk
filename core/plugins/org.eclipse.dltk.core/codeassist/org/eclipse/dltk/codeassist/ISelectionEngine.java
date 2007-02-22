package org.eclipse.dltk.codeassist;

import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.IModelElement;

public interface ISelectionEngine {
	IModelElement[] select(ISourceModule module, int offset, int i);
}
