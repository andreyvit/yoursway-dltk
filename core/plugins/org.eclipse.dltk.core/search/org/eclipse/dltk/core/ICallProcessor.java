package org.eclipse.dltk.core;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.search.IDLTKSearchScope;

public interface ICallProcessor {
	Map process(final IModelElement parent, IModelElement member,
			IDLTKSearchScope scope, IProgressMonitor monitor);
}
