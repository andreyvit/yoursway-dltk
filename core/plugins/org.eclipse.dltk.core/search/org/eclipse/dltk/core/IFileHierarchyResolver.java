package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This class is extension, which is used in fileHierarchyResolver extension point.
 */
public interface IFileHierarchyResolver {

	/**
	 * Gathers information on all files, which are referenced by the given file either directly or through other files.
	 * @param file Source module to resolve file hierarchy information for
	 * @param monitor Progress monitor
	 * @return file hierarchy information
	 */
	public IFileHierarchyInfo resolveUp(ISourceModule file, IProgressMonitor monitor);

	/**
	 * Gathers information on all files that reference given file either directly or through other files.
	 * @param file Source module to resolve file hierarchy information for
	 * @param monitor Progress monitor
	 * @return file hierarchy information
	 */
	public IFileHierarchyInfo resolveDown(ISourceModule file, IProgressMonitor monitor);
}
