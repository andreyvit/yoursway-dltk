package org.eclipse.dltk.codeassist;

import java.util.Map;

import org.eclipse.dltk.compiler.env.ISourceModule;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.ISearchableEnvironment;

public interface ICompletionEngine {
	void complete(ISourceModule module, int position, int i);

	void setEnvironment(ISearchableEnvironment environment);

	void setRequestor(CompletionRequestor requestor);

	void setOptions(Map options);

	void setProject(IDLTKProject project);
}
