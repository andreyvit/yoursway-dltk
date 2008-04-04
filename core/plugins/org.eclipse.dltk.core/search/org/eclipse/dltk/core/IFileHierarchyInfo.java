package org.eclipse.dltk.core;

/**
 * This class contains cached information about file hierarchy
 */
public interface IFileHierarchyInfo {

	/**
	 * Checks whether the given file exists in the file hierarchy.
	 */
	public boolean exists(ISourceModule file);
}
