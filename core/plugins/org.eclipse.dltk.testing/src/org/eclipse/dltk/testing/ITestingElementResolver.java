package org.eclipse.dltk.testing;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;

public interface ITestingElementResolver {
	ISourceRange resolveRange(IScriptProject project,
			ILaunchConfiguration config, String name, ISourceModule module, IModelElement element, String method);

	IModelElement resolveElement(IScriptProject project, ILaunchConfiguration config,
			ISourceModule module, String name);
}
