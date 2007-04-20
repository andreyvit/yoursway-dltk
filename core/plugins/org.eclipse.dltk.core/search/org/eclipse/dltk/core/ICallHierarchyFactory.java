package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.search.IDLTKSearchScope;

public interface ICallHierarchyFactory {
	ICalleeProcessor createCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope); //to ext point
	ICallProcessor createCallProcessor(); // to ext point
}
