/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;


public interface IDLTKProject extends IModelElement, IOpenable, IParent {

	/**
	 * Returns the <code>IProject</code> on which this <code>IScriptProject</code>
	 * was created. This is handle-only method.
	 * 
	 * @return the <code>IProject</code> on which this <code>IScriptProject</code>
	 * was created
	 */
	IProject getProject();
	
	/**
	 * Returns all of the  project fragments contained in this
	 * project, identified on this project's resolved buildpath. The result
	 * does not include project fragments in other projects referenced
	 * on this project's buildpath.
	 *
	 * <p>NOTE: This is equivalent to <code>getChildren()</code>.
	 *
	 * @return all of the  project fragments contained in this
	 * project, identified on this project's resolved buildpath
	 * @exception ModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	IProjectFragment[] getProjectFragments() throws ModelException;
	
	/**
	 * Helper method for returning one option value only. Equivalent to <code>(String)this.getOptions(inheritDLTKCoreOptions).get(optionName)</code>
	 * Note that it may answer <code>null</code> if this option does not exist, or if there is no custom value for it.
	 * <p>
	 * For a complete description of the configurable options, see <code>DLTKCore#getDefaultOptions</code>.
	 * </p>
	 * 
	 * @param optionName the name of an option
	 * @param inheritDLTKCoreOptions - boolean indicating whether DLTKCore options should be inherited as well
	 * @return the String value of a given option
	 * @see DLTKCore#getDefaultOptions()
	 */
	String getOption(String optionName, boolean inheritDLTKCoreOptions);
	
	/**
	 * Returns the table of the current custom options for this project. Projects remember their custom options,
	 * in other words, only the options different from the the DLTKCore global options for the workspace.
	 * A boolean argument allows to directly merge the project options with global ones from <code>DLTKCore</code>.
	 * <p>
	 * For a complete description of the configurable options, see <code>DLTKCore#getDefaultOptions</code>.
	 * </p>
	 * 
	 * @param inheritDLTKCoreOptions - boolean indicating whether DLTKCore options should be inherited as well
	 * @return table of current settings of all options 
	 *   (key type: <code>String</code>; value type: <code>String</code>)
	 * @see DLTKCore#getDefaultOptions()
	 *
	 */
	Map getOptions(boolean inheritDLTKCoreOptions);
	/**
	 * Helper method for setting one option value only. Equivalent to <code>Map options = this.getOptions(false); map.put(optionName, optionValue); this.setOptions(map)</code>
	 * <p>
	 * For a complete description of the configurable options, see <code>DLTKCore#getDefaultOptions</code>.
	 * </p>
	 * 
	 * @param optionName the name of an option
	 * @param optionValue the value of the option to set
	 * @see DLTKCore#getDefaultOptions()
	 *
	 */
	void setOption(String optionName, String optionValue);

	/**
	 * Sets the project custom options. All and only the options explicitly included in the given table 
	 * are remembered; all previous option settings are forgotten, including ones not explicitly
	 * mentioned.
	 * <p>
	 * For a complete description of the configurable options, see <code>DLTKCore#getDefaultOptions</code>.
	 * </p>
	 * 
	 * @param newOptions the new options (key type: <code>String</code>; value type: <code>String</code>),
	 *   or <code>null</code> to flush all custom options (clients will automatically get the global DLTKCore options).
	 * @see DLTKCore#getDefaultOptions()
	 *
	 */
	void setOptions(Map newOptions);

	
	/**
	 * Returns a project fragment for the given resource, which
	 * must either be a folder representing the root of source folders,
	 * or <code>.zip</code> file.
	 * This is a handle-only method.  The underlying resource may or may not exist. 
	 * 
	 * @param resource the given resource
	 * @return a project fragment for the given resource, which
	 * must either be a folder representing the top of source folders,
	 * or a <code>.zip</code> file
	 */
	IProjectFragment getProjectFragment(IResource resource);
	
	/**
	 * Returns a package fragment root for the ZIP at the specified file system path.
	 * This is a handle-only method.  The underlying <code>java.io.File</code>
	 * may or may not exist. No resource is associated with this local ZIP
	 * package fragment root.
	 * 
	 * @param zipPath the jars's file system path
	 * @return a package fragment root for the JAR at the specified file system path
	 */
	IProjectFragment getProjectFragment(String zipPath);

	/**
	 * Sets the buildpath of this project using a list of buildpath entries. 
	 * 
	 * Setting the buildpath to <code>null</code> specifies a default buildpath
	 * (the project root). Setting the buildpath to an empty array specifies an
	 * empty buildpath.
	 * <p>
	 * If a cycle is detected while setting this buildpath, an error marker will be added
	 * to the project closing the cycle.
	 * To avoid this problem, use <code>hasBuildpathCycle(IBuildpathEntry[] entries)</code>
	 * before setting the buildpath.
	 * <p>
	 * This operation acquires a lock on the workspace's root.
	 *
	 * @param entries a list of buildpath entries
	 * @param monitor the given progress monitor
	 * @exception ModelException if the buildpath could not be set. Reasons include:
	 * <ul>
	 * <li> This model element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> The buildpath is being modified during resource change event notification (CORE_EXCEPTION)
	 * <li> The buildpath failed the validation check
	 * </ul>
	 * @see IBuildpathEntry
	 */
	void setRawBuildpath(IBuildpathEntry[] entries, IProgressMonitor monitor)
		throws ModelException;
	
	/**
	 * Returns true if this is valid DLTK Project.
	 *
	 */
	public boolean isValid();
	
	/**
	 * Decodes the buildpath entry that has been encoded in the given string
	 * in the context of this project.
	 * Returns null if the encoded entry is malformed.
	 * 
	 * @param encodedEntry the encoded buildpath entry
	 * @return the decoded buildpath entry, or <code>null</code> if unable to decode it
	 *
	 */
	IBuildpathEntry decodeBuildpathEntry(String encodedEntry);
	
	/**
	 * Encodes the given buildpath entry into a string in the context of this project.
	 * 
	 * @param buildpathEntry the buildpath entry to encode
	 * @return the encoded buildpath entry
	 *
	 */
	public String encodeBuildpathEntry(IBuildpathEntry buildpathEntry);

	IBuildpathEntry[] getRawBuildpath() throws ModelException;
	
	/**
	 * This is a helper method returning the resolved buildpath for the project
	 * as a list of simple (non-variable, non-container) buildpath entries.
	 * All buildpath variable and buildpath container entries in the project's
	 * raw buildpath will be replaced by the simple buildpath entries they
	 * resolve to.
	 * <p>
	 * The resulting resolved buildpath is accurate for the given point in time.
	 * If the project's raw buildpath is later modified, or if buildpath
	 * variables are changed, the resolved buildpath can become out of date.
	 * Because of this, hanging on resolved buildpath is not recommended.
	 * </p>
	 * 
	 * @param ignoreUnresolvedEntry indicates how to handle unresolvable
	 * variables and containers; <code>true</code> indicates that missing
	 * variables and unresolvable buildpath containers should be silently
	 * ignored, and that the resulting list should consist only of the
	 * entries that could be successfully resolved; <code>false</code> indicates
	 * that a <code>ModelException</code> should be thrown for the first
	 * unresolved variable or container
	 * @return the resolved buildpath for the project as a list of simple 
	 * buildpath entries, where all buildpath variable and container entries
	 * have been resolved and substituted with their final target entries
	 * @exception ModelException in one of the corresponding situation:
	 * <ul>
	 *    <li>this element does not exist</li>
	 *    <li>an exception occurs while accessing its corresponding resource</li>
	 *    <li>a buildpath variable or buildpath container was not resolvable
	 *    and <code>ignoreUnresolvedEntry</code> is <code>false</code>.</li>
	 * </ul>
	 * @see IBuildpathEntry
	 */
	IBuildpathEntry[] getResolvedBuildpath(boolean ignoreUnresolvedEntry)
	     throws ModelException;
	
	
	/**
	 * Returns the <code>IModelElement</code> corresponding to the given
	 * buildpath-relative path, or <code>null</code> if no such 
	 * <code>IModelElement</code> is found. The result is one of an
	 * <code>ISourceModule</code>, <code>IClassFile</code>, or
	 * <code>IScriptFolder</code>. 
	 * <p>
	 * When looking for a package fragment, there might be several potential
	 * matches; only one of them is returned.
	 *
	 * <p>For example, the path "java/lang/Object.java", would result in the
	 * <code>ISourceModule</code> corresponding to
	 * "java.lang.Object". The path "java/lang" would result in the
	 * <code>IScriptFolder</code> for "java.lang".
	 * @param path the given buildpath-relative path
	 * @exception ModelException if the given path is <code>null</code>
	 *  or absolute
	 * @return the <code>IModelElement</code> corresponding to the given
	 * buildpath-relative path, or <code>null</code> if no such 
	 * <code>IModelElement</code> is found
	 */
	IModelElement findElement(IPath path) throws ModelException;
	
	/**
	 * Returns the <code>IModelElement</code> corresponding to the given
	 * buildpath-relative path, or <code>null</code> if no such 
	 * <code>IModelElement</code> is found. The result is one of an
	 * <code>ISourceModule</code>, <code>IClassFile</code>, or
	 * <code>IScriptFolder</code>. If it is an <code>ISourceModule</code>,
	 * its owner is the given owner.
	 * <p>
	 * When looking for a package fragment, there might be several potential
	 * matches; only one of them is returned.
	 *
	 * <p>For example, the path "java/lang/Object.java", would result in the
	 * <code>ISourceModule</code> corresponding to
	 * "java.lang.Object". The path "java/lang" would result in the
	 * <code>IScriptFolder</code> for "java.lang".
	 * @param path the given buildpath-relative path
	 * @param owner the owner of the returned compilation unit, ignored if it is
	 *   not a compilation unit.
	 * @exception ModelException if the given path is <code>null</code>
	 *  or absolute
	 * @return the <code>IModelElement</code> corresponding to the given
	 * buildpath-relative path, or <code>null</code> if no such 
	 * <code>IModelElement</code> is found
	 *
	 */
	IModelElement findElement(IPath path, WorkingCopyOwner owner) throws ModelException;

	/**
	 * Returns the first existing package fragment on this project's buildpath
	 * whose path matches the given (absolute) path, or <code>null</code> if none
	 * exist.
	 * The path can be:
	 * 	- internal to the workbench: "/Project/src"
	 *  - external to the workbench: "c:/jdk/classes.zip/java/lang"
	 * @param path the given absolute path
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first existing package fragment on this project's buildpath
	 * whose path matches the given (absolute) path, or <code>null</code> if none
	 * exist
	 */
	IScriptFolder findScriptFolder(IPath path) throws ModelException;

	/**
	 * Returns the existing package fragment root on this project's buildpath
	 * whose path matches the given (absolute) path, or <code>null</code> if
	 * one does not exist.
	 * The path can be:
	 *	- internal to the workbench: "/Compiler/src"
	 *	- external to the workbench: "c:/jdk/classes.zip"
	 * @param path the given absolute path
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the existing package fragment root on this project's buildpath
	 * whose path matches the given (absolute) path, or <code>null</code> if
	 * one does not exist
	 */
	IProjectFragment findProjectFragment(IPath path)
		throws ModelException;
	/**
	 * Returns the existing package fragment roots identified by the given entry.
	 * Note that a buildpath entry that refers to another project may
	 * have more than one root (if that project has more than on root
	 * containing source), and buildpath entries within the current
	 * project identify a single root.
	 * <p>
	 * If the buildpath entry denotes a variable, it will be resolved and return
	 * the roots of the target entry (empty if not resolvable).
	 * <p>
	 * If the buildpath entry denotes a container, it will be resolved and return
	 * the roots corresponding to the set of container entries (empty if not resolvable).
	 * 
	 * @param entry the given entry
	 * @return the existing package fragment roots identified by the given entry
	 * @see IBuildpathContainer
	 *
	 */
	IProjectFragment[] findProjectFragments(IBuildpathEntry entry);
	/**
	 * Returns the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found.
	 * The fully qualified name is a dot-separated name. For example,
	 * a class B defined as a member type of a class A in package x.y should have a 
	 * the fully qualified name "x.y.A.B".
	 * 
	 * Note that in order to be found, a type name (or its toplevel enclosing
	 * type name) must match its corresponding compilation unit name. As a 
	 * consequence, secondary types cannot be found using this functionality.
	 * To find secondary types use {@link #findType(String, IProgressMonitor)} instead.
	 * 
	 * @param fullyQualifiedName the given fully qualified name
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found
	 * @see IType#getFullyQualifiedName(char)
	 *
	 */
	IType findType(String fullyQualifiedName) throws ModelException;
	/**
	 * Same functionality as {@link #findType(String)} but also look for secondary
	 * types if given name does not match a compilation unit name.
	 * 
	 * @param fullyQualifiedName the given fully qualified name
	 * @param progressMonitor the progress monitor to report progress to,
	 * 	or <code>null</code> if no progress monitor is provided
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found
	 * @see IType#getFullyQualifiedName(char)
	 *
	 */
	IType findType(String fullyQualifiedName, IProgressMonitor progressMonitor) throws ModelException;
	/**
	 * Returns the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found.
	 * The fully qualified name is a dot-separated name. For example,
	 * a class B defined as a member type of a class A in package x.y should have a 
	 * the fully qualified name "x.y.A.B".
	 * If the returned type is part of a compilation unit, its owner is the given
	 * owner.
	 * 
	 * Note that in order to be found, a type name (or its toplevel enclosing
	 * type name) must match its corresponding compilation unit name. As a 
	 * consequence, secondary types cannot be found using this functionality.
	 * To find secondary types use {@link #findType(String, WorkingCopyOwner, IProgressMonitor)}
	 * instead.
	 * 
	 * @param fullyQualifiedName the given fully qualified name
	 * @param owner the owner of the returned type's compilation unit
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found
	 * @see IType#getFullyQualifiedName(char)
	 *
	 */
	IType findType(String fullyQualifiedName, WorkingCopyOwner owner) throws ModelException;
	/**
	 * Same functionality as {@link #findType(String, WorkingCopyOwner)}
	 * but also look for secondary types if given name does not match
	 * a compilation unit name.
	 * 
	 * @param fullyQualifiedName the given fully qualified name
	 * @param owner the owner of the returned type's compilation unit
	 * @param progressMonitor the progress monitor to report progress to,
	 * 	or <code>null</code> if no progress monitor is provided
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found
	 * @see IType#getFullyQualifiedName(char)
	 *
	 */
	IType findType(String fullyQualifiedName, WorkingCopyOwner owner, IProgressMonitor progressMonitor) throws ModelException;
	/**
	 * Returns the first type found following this project's buildpath 
	 * with the given package name and type qualified name
	 * or <code>null</code> if none is found.
	 * The package name is a dot-separated name.
	 * The type qualified name is also a dot-separated name. For example,
	 * a class B defined as a member type of a class A should have the 
	 * type qualified name "A.B".
	 * 
	 * Note that in order to be found, a type name (or its toplevel enclosing
	 * type name) must match its corresponding compilation unit name. As a 
	 * consequence, secondary types cannot be found using this functionality.
	 * To find secondary types use {@link #findType(String, String, IProgressMonitor)}
	 * instead.
	 * 
	 * @param packageName the given package name
	 * @param typeQualifiedName the given type qualified name
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given package name and type qualified name
	 * or <code>null</code> if none is found
	 * @see IType#getTypeQualifiedName(char)
	 *
	 */
	IType findType(String packageName, String typeQualifiedName) throws ModelException;
	/**
	 * Same functionality as {@link #findType(String, String)} but also look for
	 * secondary types if given name does not match a compilation unit name.
	 * 
	 * @param packageName the given package name
	 * @param typeQualifiedName the given type qualified name
	 * @param progressMonitor the progress monitor to report progress to,
	 * 	or <code>null</code> if no progress monitor is provided
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found
	 * @see IType#getFullyQualifiedName(char)
	 *
	 */
	IType findType(String packageName, String typeQualifiedName, IProgressMonitor progressMonitor) throws ModelException;
	/**
	 * Returns the first type found following this project's buildpath 
	 * with the given package name and type qualified name
	 * or <code>null</code> if none is found.
	 * The package name is a dot-separated name.
	 * The type qualified name is also a dot-separated name. For example,
	 * a class B defined as a member type of a class A should have the 
	 * type qualified name "A.B".
	 * If the returned type is part of a compilation unit, its owner is the given
	 * owner.
	 * 
	 * Note that in order to be found, a type name (or its toplevel enclosing
	 * type name) must match its corresponding compilation unit name. As a 
	 * consequence, secondary types cannot be found using this functionality.
	 * To find secondary types use {@link #findType(String, String, WorkingCopyOwner, IProgressMonitor)}
	 * instead.
	 * 
	 * @param packageName the given package name
	 * @param typeQualifiedName the given type qualified name
	 * @param owner the owner of the returned type's compilation unit
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given package name and type qualified name
	 * or <code>null</code> if none is found
	 * @see IType#getTypeQualifiedName(char)
	 *
	 */
	IType findType(String packageName, String typeQualifiedName, WorkingCopyOwner owner) throws ModelException;
	/**
	 * Same functionality as {@link #findType(String, String, WorkingCopyOwner)}
	 * but also look for secondary types if given name does not match a compilation unit name.
	 * 
	 * @param packageName the given package name
	 * @param typeQualifiedName the given type qualified name
	 * @param owner the owner of the returned type's compilation unit
	 * @param progressMonitor the progress monitor to report progress to,
	 * 	or <code>null</code> if no progress monitor is provided
	 * @exception ModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return the first type found following this project's buildpath 
	 * with the given fully qualified name or <code>null</code> if none is found
	 * @see IType#getFullyQualifiedName(char)
	 *
	 */
	IType findType(String packageName, String typeQualifiedName, WorkingCopyOwner owner, IProgressMonitor progressMonitor) throws ModelException;
	/**
	 * Returns the raw buildpath for the project as defined by its
	 * <code>.buildpath</code> file from disk, or <code>null</code>
	 * if unable to read the file. 
	 * <p>
	 * This buildpath may differ from the in-memory buildpath returned by
	 * <code>getRawBuildpath</code>, in case the automatic reconciliation
	 * mechanism has not been performed yet. Usually, any change to the
	 * <code>.buildpath</code> file is automatically noticed and reconciled at
	 * the next resource change notification event. However, if the file is
	 * modified within an operation, where this change needs to be taken into
	 * account before the operation ends, then the buildpath from disk can be
	 * read using this method, and further assigned to the project using
	 * <code>setRawBuildpath(...)</code>. 
	 * </p>
	 * <p>
	 * Buildpath variable and buildpath container entries can be resolved using
	 * the helper method <code>getResolvedBuildpath</code>; buildpath variable
	 * entries also can be resolved individually using 
	 * <code>DLTKCore#getBuildpathVariable</code>).
	 * </p>
	 * <p>
	 * Note that no check is performed whether the project has the script nature
	 * set, allowing an existing <code>.buildpath</code> file to be considered
	 * independantly (unlike <code>getRawBuildpath</code> which requires the
	 * script nature to be associated with the project). 
	 * </p>
	 * <p>
	 * In order to manually force a project buildpath refresh, one can simply
	 * assign the project buildpath using the result of this method, as follows:
	 * <code>proj.setRawBuildpath(proj.readRawBuildpath(), proj.readOutputLocation(), monitor)</code>
	 * (note that the <code>readRawBuildpath/readOutputLocation</code> methods
	 * could return <code>null</code>).
	 * </p>
	 * 
	 * @return the raw buildpath from disk for the project, as a list of
	 * buildpath entries
	 * @see #getRawBuildpath()
	 * @see IBuildpathEntry
	 *
	 */
	IBuildpathEntry[] readRawBuildpath();
	
	public Object[] getForeignResources() throws ModelException;
	
	/**
	 * Returns whether the given element is on the buildpath of this project, 
	 * that is, referenced from a buildpath entry and not explicitly excluded
	 * using an exclusion pattern. 
	 * 
	 * @param element the given element
	 * @return <code>true</code> if the given element is on the buildpath of
	 * this project, <code>false</code> otherwise
	 * @see IBuildpathEntry#getInclusionPatterns()
	 * @see IBuildpathEntry#getExclusionPatterns()
	 *
	 */
	boolean isOnBuildpath(IModelElement element);
	/**
	 * Returns whether the given resource is on the buildpath of this project,
	 * that is, referenced from a buildpath entry and not explicitly excluded
	 * using an exclusion pattern.
	 * 
	 * @param resource the given resource
	 * @return <code>true</code> if the given resource is on the buildpath of
	 * this project, <code>false</code> otherwise
	 * @see IBuildpathEntry#getInclusionPatterns()
	 * @see IBuildpathEntry#getExclusionPatterns()
	 *
	 */
	boolean isOnBuildpath(IResource resource);
	
	
	/**
	 * Creates and returns a type hierarchy for all types in the given
	 * region, considering subtypes within that region.
	 *
	 * @param monitor the given progress monitor
	 * @param region the given region
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @exception IllegalArgumentException if region is <code>null</code>
	 * @return a type hierarchy for all types in the given
	 * region, considering subtypes within that region
	 */
	ITypeHierarchy newTypeHierarchy(IRegion region, IProgressMonitor monitor)
		throws ModelException;

	/**
	 * Creates and returns a type hierarchy for all types in the given
	 * region, considering subtypes within that region and considering types in the 
	 * working copies with the given owner. 
	 * In other words, the owner's working copies will take 
	 * precedence over their original compilation units in the workspace.
	 * <p>
	 * Note that if a working copy is empty, it will be as if the original compilation
	 * unit had been deleted.
	 * <p>
	 *
	 * @param monitor the given progress monitor
	 * @param region the given region
	 * @param owner the owner of working copies that take precedence over their original compilation units
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @exception IllegalArgumentException if region is <code>null</code>
	 * @return a type hierarchy for all types in the given
	 * region, considering subtypes within that region
	 * @since 3.0
	 */
	ITypeHierarchy newTypeHierarchy(IRegion region, WorkingCopyOwner owner, IProgressMonitor monitor)
		throws ModelException;

	/**
	 * Creates and returns a type hierarchy for the given type considering
	 * subtypes in the specified region.
	 * 
	 * @param type the given type
	 * @param region the given region
	 * @param monitor the given monitor
	 * 
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 *
	 * @exception IllegalArgumentException if type or region is <code>null</code>
	 * @return a type hierarchy for the given type considering
	 * subtypes in the specified region
	 */
	ITypeHierarchy newTypeHierarchy(
		IType type,
		IRegion region,
		IProgressMonitor monitor)
		throws ModelException;

	/**
	 * Creates and returns a type hierarchy for the given type considering
	 * subtypes in the specified region and considering types in the 
	 * working copies with the given owner. 
	 * In other words, the owner's working copies will take 
	 * precedence over their original compilation units in the workspace.
	 * <p>
	 * Note that if a working copy is empty, it will be as if the original compilation
	 * unit had been deleted.
	 * <p>
	 * 
	 * @param type the given type
	 * @param region the given region
	 * @param monitor the given monitor
	 * @param owner the owner of working copies that take precedence over their original compilation units
	 * 
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 *
	 * @exception IllegalArgumentException if type or region is <code>null</code>
	 * @return a type hierarchy for the given type considering
	 * subtypes in the specified region
	 * @since 3.0
	 */
	ITypeHierarchy newTypeHierarchy(
		IType type,
		IRegion region,
		WorkingCopyOwner owner,
		IProgressMonitor monitor)
		throws ModelException;
}
