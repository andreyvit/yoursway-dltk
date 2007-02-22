package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IProjectFragment extends IParent, IModelElement, IOpenable {

	
	/**	
	 * <p>
	 * The name of default script folder (value: the empty 
	 * string, <code>""</code>).
	 * </p>
 	*/
	public static final String DEFAULT_SCRIPT_FOLDER_NAME = ""; //$NON-NLS-1$	

	public static final String DEFAULT_PACKAGE_ROOT = "";
	
	/**
	 * Update model flag constant (bit mask value 1) indicating that the operation
	 * is to not copy/move/delete the package fragment root resource.

	 */
	int NO_RESOURCE_MODIFICATION = 1;
	/**
	 * Update model flag constant (bit mask value 2) indicating that the operation
	 * is to update the buildpath of the originating project.
	 *
	 */
	int ORIGINATING_PROJECT_BUILDPATH = 2;
	/**
	 * Update model flag constant (bit mask value 4) indicating that the operation
	 * is to update the buildpath of all referring projects except the originating project.
	 *
	 */
	int OTHER_REFERRING_PROJECTS_BUILDPATH = 4;
	/**
	 * Update model flag constant (bit mask value 8) indicating that the operation
	 * is to update the buildpath of the destination project.
	 *
	 */
	int DESTINATION_PROJECT_BUILDPATH = 8;	
	/**
	 * Update model flag constant (bit mask value 16) indicating that the operation
	 * is to replace the resource and the destination project's buildpath entry.
	 *
	 */
	int REPLACE = 16;	
	
	/**
	 * Kind constant for a source project fragment. Indicates this root
	 * may contains source files.
	 */
	int K_SOURCE = 1;	

	int K_STATIC = 2;
	int K_BINARY = 3;
	
	int getKind() throws ModelException;
	
	/**
	 * Returns the script folder with the given path.
	 * An empty path indicates the root folder.
	 * This is a handle-only operation.  The folder
	 * may or may not exist.
	 * 
	 * @param path of the given folder
	 * @return the script folder with the given path
	 */
	IScriptFolder getScriptFolder(IPath path);
	/**
	 * Returns the script folder with the given path.
	 * An empty path indicates the root folder.
	 * This is a handle-only operation.  The folder
	 * may or may not exist.
	 * 
	 * @param path of the given folder
	 * @return the script folder with the given path
	 */
	IScriptFolder getScriptFolder(String path);
	
	public Object[] getForeignResources() throws ModelException;
	
	/**
	 * Returns the first raw buildpath entry that corresponds to this package
	 * fragment root.
	 * A raw buildpath entry corresponds to a package fragment root if once resolved
	 * this entry's path is equal to the root's path. 
	 * 
	 * @exception ModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource.
	 * @return the first raw buildpath entry that corresponds to this package fragment root
	 *
	 */
	IBuildpathEntry getRawBuildpathEntry() throws ModelException;
	
	/**
	 * Returns whether this package fragment root's underlying
	 * resource is a binary archive (a zip file).
	 * <p>
	 * This is a handle-only method.
	 * </p>
	 * 
	 * @return true if this package fragment root's underlying resource is a binary archive, false otherwise
	 */
	public boolean isArchive();
	
	/**
	 * Returns whether this package fragment root is external
	 * to the workbench (that is, a local file), and has no
	 * underlying resource.
	 * <p>
	 * This is a handle-only method.
	 * </p>
	 * 
	 * @return true if this package fragment root is external
	 * to the workbench (that is, a local file), and has no
	 * underlying resource, false otherwise
	 */
	boolean isExternal();
	
	/**
	 * Creates and returns a package fragment in this root with the 
	 * given dot-separated package name.  An empty string specifies the default package. 
	 * This has the side effect of creating all package
	 * fragments that are a prefix of the new package fragment which
	 * do not exist yet. If the package fragment already exists, this
	 * has no effect.
	 *
	 * For a description of the <code>force</code> flag, see <code>IFolder.create</code>.
	 *
	 * @param name the given dot-separated package name
	 * @param force a flag controlling how to deal with resources that
	 *    are not in sync with the local file system
	 * @param monitor the given progress monitor
	 * @exception ModelException if the element could not be created. Reasons include:
	 * <ul>
	 * <li> This script element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> A <code>CoreException</code> occurred while creating an underlying resource
	 * <li> This package fragment root is read only (READ_ONLY)
	 * <li> The name is not a valid package name (INVALID_NAME)
	 * </ul>
	 * @return a package fragment in this root with the given dot-separated package name
	 * @see org.eclipse.core.resources.IFolder#create(boolean, boolean, IProgressMonitor)
	 */
	IScriptFolder createScriptFolder(
		String name,
		boolean force,
		IProgressMonitor monitor)
		throws ModelException;
	
	/**
	 * Deletes the resource of this package fragment root as specified by
	 * <code>IResource.delete(int, IProgressMonitor)</code> but excluding nested
	 * source folders.
	 * <p>
	 * If <code>NO_RESOURCE_MODIFICATION</code> is specified in 
	 * <code>updateModelFlags</code> or if this package fragment root is external, 
	 * this operation doesn't delete the resource. <code>updateResourceFlags</code> 
	 * is then ignored.
	 * </p><p>
	 * If <code>ORIGINATING_PROJECT_CLASSPATH</code> is specified in 
	 * <code>updateModelFlags</code>, update the raw buildpath of this package 
	 * fragment root's project by removing the corresponding buildpath entry.
	 * </p><p>
	 * If <code>OTHER_REFERRING_PROJECTS_CLASSPATH</code> is specified in 
	 * <code>updateModelFlags</code>, update the raw buildpaths of all other Script
	 * projects referring to this root's resource by removing the corresponding buildpath 
	 * entries.
	 * </p><p>
	 * If no flags is specified in <code>updateModelFlags</code> (using 
	 * <code>IResource.NONE</code>), the default behavior applies: the
	 * resource is deleted (if this package fragment root is not external) and no
	 * buildpaths are updated.
	 * </p>
	 * 
	 * @param updateResourceFlags bit-wise or of update resource flag constants
	 *   (<code>IResource.FORCE</code> and <code>IResource.KEEP_HISTORY</code>)
	 * @param updateModelFlags bit-wise or of update resource flag constants
	 *   (<code>ORIGINATING_PROJECT_CLASSPATH</code>,
	 *   <code>OTHER_REFERRING_PROJECTS_CLASSPATH</code> and 
	 *   <code>NO_RESOURCE_MODIFICATION</code>)
	 * @param monitor a progress monitor
	 * 
	 * @exception ScriptModelException if this root could not be deleted. Reasons
	 * include:
	 * <ul>
	 * <li> This root does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> A <code>CoreException</code> occurred while deleting the resource 
	 * or updating a buildpath
	 * </li>
	 * </ul>
	 * @see org.eclipse.core.resources.IResource#delete(boolean, IProgressMonitor)
	 *
	 */
	void delete(int updateResourceFlags, int updateModelFlags, IProgressMonitor monitor) throws ModelException;
	/**
	 * Copies the resource of this package fragment root to the destination path
	 * as specified by <code>IResource.copy(IPath, int, IProgressMonitor)</code>
	 * but excluding nested source folders.
	 * <p>
	 * If <code>NO_RESOURCE_MODIFICATION</code> is specified in 
	 * <code>updateModelFlags</code> or if this package fragment root is external, 
	 * this operation doesn't copy the resource. <code>updateResourceFlags</code> 
	 * is then ignored.
	 * </p><p>
	 * If <code>DESTINATION_PROJECT_CLASSPATH</code> is specified in 
	 * <code>updateModelFlags</code>, updates the buildpath of the 
	 * destination's project (if it is a Script project). If a non-<code>null</code> 
	 * sibling is specified, a copy of this root's buildpath entry is inserted before the 
	 * sibling on the destination project's raw buildpath. If <code>null</code> is 
	 * specified, the buildpath entry is added at the end of the raw buildpath.
	 * </p><p>
	 * If <code>REPLACE</code> is specified in <code>updateModelFlags</code>,
	 * overwrites the resource at the destination path if any.
	 * If the same buildpath entry already exists on the destination project's raw
	 * buildpath, then the sibling is ignored and the new buildpath entry replaces the 
	 * existing one.
	 * </p><p>
	 * If no flags is specified in <code>updateModelFlags</code> (using 
	 * <code>IResource.NONE</code>), the default behavior applies: the
	 * resource is copied (if this package fragment root is not external) and the
	 * buildpath is not updated.
	 * </p>
	 * 
	 * @param destination the destination path
	 * @param updateResourceFlags bit-wise or of update resource flag constants
	 *   (<code>IResource.FORCE</code> and <code>IResource.SHALLOW</code>)
	 * @param updateModelFlags bit-wise or of update resource flag constants
	 *   (<code>DESTINATION_PROJECT_CLASSPATH</code> and 
	 *   <code>NO_RESOURCE_MODIFICATION</code>)
	 * @param sibling the buildpath entry before which a copy of the buildpath
	 * entry should be inserted or <code>null</code> if the buildpath entry should
	 * be inserted at the end
	 * @param monitor a progress monitor
	 * 
	 * @exception ScriptModelException if this root could not be copied. Reasons
	 * include:
	 * <ul>
	 * <li> This root does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> A <code>CoreException</code> occurred while copying the
	 * resource or updating a buildpath</li>
	 * <li>
	 * The destination is not inside an existing project and <code>updateModelFlags</code>
	 * has been specified as <code>DESTINATION_PROJECT_CLASSPATH</code> 
	 * (INVALID_DESTINATION)</li>
	 * <li> The sibling is not a buildpath entry on the destination project's
	 * raw buildpath (INVALID_SIBLING)</li>
	 * <li> The same buildpath entry already exists on the destination project's
	 * buildpath (NAME_COLLISION) and <code>updateModelFlags</code>
	 * has not been specified as <code>REPLACE</code></li>
	 * </ul>
	 * @see org.eclipse.core.resources.IResource#copy(IPath, boolean, IProgressMonitor)
	 *
	 */
	void copy(IPath destination, int updateResourceFlags, int updateModelFlags, IBuildpathEntry sibling, IProgressMonitor monitor) throws ModelException;
	/**
	 * Moves the resource of this package fragment root to the destination path
	 * as specified by <code>IResource.move(IPath,int,IProgressMonitor)</code>
	 * but excluding nested source folders.
	 * <p>
	 * If <code>NO_RESOURCE_MODIFICATION</code> is specified in 
	 * <code>updateModelFlags</code> or if this package fragment root is external, 
	 * this operation doesn't move the resource. <code>updateResourceFlags</code> 
	 * is then ignored.
	 * </p><p>
	 * If <code>DESTINATION_PROJECT_CLASSPATH</code> is specified in 
	 * <code>updateModelFlags</code>, updates the buildpath of the 
	 * destination's project (if it is a Script project). If a non-<code>null</code> 
	 * sibling is specified, a copy of this root's buildpath entry is inserted before the 
	 * sibling on the destination project's raw buildpath. If <code>null</code> is 
	 * specified, the buildpath entry is added at the end of the raw buildpath.
	 * </p><p>
	 * If <code>ORIGINATING_PROJECT_CLASSPATH</code> is specified in 
	 * <code>updateModelFlags</code>, update the raw buildpath of this package 
	 * fragment root's project by removing the corresponding buildpath entry.
	 * </p><p>
	 * If <code>OTHER_REFERRING_PROJECTS_CLASSPATH</code> is specified in 
	 * <code>updateModelFlags</code>, update the raw buildpath of all other Script
	 * projects referring to this root's resource by removing the corresponding buildpath 
	 * entries.
	 * </p><p>
	 * If <code>REPLACE</code> is specified in <code>updateModelFlags</code>,
	 * overwrites the resource at the destination path if any.
	 * If the same buildpath entry already exists on the destination project's raw
	 * buildpath, then the sibling is ignored and the new buildpath entry replaces the 
	 * existing one.
	 * </p><p>
	 * If no flags is specified in <code>updateModelFlags</code> (using 
	 * <code>IResource.NONE</code>), the default behavior applies: the
	 * resource is moved (if this package fragment root is not external) and no
	 * buildpaths are updated.
	 * </p>
	 * 
	 * @param destination the destination path
	 * @param updateResourceFlags bit-wise or of update flag constants
	 * (<code>IResource.FORCE</code>, <code>IResource.KEEP_HISTORY</code> 
	 * and <code>IResource.SHALLOW</code>)
	 * @param updateModelFlags bit-wise or of update resource flag constants
	 *   (<code>DESTINATION_PROJECT_CLASSPATH</code>,
	 *   <code>ORIGINATING_PROJECT_CLASSPATH</code>,
	 *   <code>OTHER_REFERRING_PROJECTS_CLASSPATH</code> and 
	 *   <code>NO_RESOURCE_MODIFICATION</code>)
	 * @param sibling the buildpath entry before which a copy of the buildpath
	 * entry should be inserted or <code>null</code> if the buildpath entry should
	 * be inserted at the end
	 * @param monitor a progress monitor
	 * 
	 * @exception ScriptModelException if this root could not be moved. Reasons
	 * include:
	 * <ul>
	 * <li> This root does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> A <code>CoreException</code> occurred while copying the
	 * resource or updating a buildpath</li>
	 * <li>
	 * The destination is not inside an existing project and <code>updateModelFlags</code>
	 * has been specified as <code>DESTINATION_PROJECT_CLASSPATH</code> 
	 * (INVALID_DESTINATION)</li>
	 * <li> The sibling is not a buildpath entry on the destination project's
	 * raw buildpath (INVALID_SIBLING)</li>
	 * <li> The same buildpath entry already exists on the destination project's
	 * buildpath (NAME_COLLISION) and <code>updateModelFlags</code>
	 * has not been specified as <code>REPLACE</code></li>
	 * </ul>
	 * @see org.eclipse.core.resources.IResource#move(IPath, boolean, IProgressMonitor)
	 *
	 */
	void move(IPath destination, int updateResourceFlags, int updateModelFlags, IBuildpathEntry sibling, IProgressMonitor monitor) throws ModelException;
	
}
