package org.eclipse.dltk.codeassist;

import org.eclipse.dltk.compiler.env.ISourceModule;

public interface ICompletionEngine {
	void complete(ISourceModule module, int position, int i);
}
