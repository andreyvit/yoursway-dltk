package org.eclipse.dltk.tcl.internal.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.ICallHierarchyFactory;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.ICalleeProcessor;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.search.IDLTKSearchScope;
import org.eclipse.dltk.tcl.internal.core.search.TclCallProcessor;

public class TclCallHierarchyFactory implements ICallHierarchyFactory {

	public ICalleeProcessor createCalleeProcessor(IMethod method, IProgressMonitor monitor, IDLTKSearchScope scope) {
		return new TclCalleeProcessor( method, monitor, scope );
	}
	public ICallProcessor createCallProcessor() {
		return new TclCallProcessor();
	}
}
