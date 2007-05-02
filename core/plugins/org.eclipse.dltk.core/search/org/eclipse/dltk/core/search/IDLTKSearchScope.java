/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core.search;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;


/**
 * An <code>IJavaSearchScope</code> defines where search result should be
 * found by a <code>SearchEngine</code>. Clients must pass an instance of
 * this interface to the <code>search(...)</code> methods. Such an instance
 * can be created using the following factory methods on
 * <code>SearchEngine</code>: <code>createHierarchyScope(IType)</code>,
 * <code>createJavaSearchScope(IResource[])</code>,
 * <code>createWorkspaceScope()</code>, or clients may choose to implement
 * this interface.
 */
public interface IDLTKSearchScope {
	/**
	 * This constant defines the separator of the resourcePath string of the
	 * <code>encloses(String)</code> method. If present in the string, it
	 * separates the path to the archive file from the path to the .class file in
	 * the archive.
	 */
	String FILE_ENTRY_SEPARATOR = "|"; //$NON-NLS-1$

	/**
	 * Include type constant (bit mask) indicating that source folders should be
	 * considered in the search scope.
	 * 
	 *
	 */
	int SOURCES = 1;

	/**
	 * Include type constant (bit mask) indicating that application libraries
	 * should be considered in the search scope.
	 * 
	 *
	 */
	int APPLICATION_LIBRARIES = 2;

	/**
	 * Include type constant (bit mask) indicating that system libraries should
	 * be considered in the search scope.
	 * 
	 *
	 */
	int SYSTEM_LIBRARIES = 4;

	/**
	 * Include type constant (bit mask) indicating that referenced projects
	 * should be considered in the search scope.
	 * 
	 *
	 */
	int REFERENCED_PROJECTS = 8;

	/**
	 * Checks whether the resource at the given path is enclosed by this scope.
	 * 
	 * @param resourcePath
	 *            if the resource is contained in a archive file, the path is
	 *            composed of 2 paths separated by
	 *            <code>ARCHIVE_FILE_ENTRY_SEPARATOR</code>: the first path is
	 *            the full OS path to the archive (if it is an external archive), or the
	 *            workspace relative <code>IPath</code> to the archive (if it is
	 *            an internal archive), the second path is the path to the resource
	 *            inside the archive.
	 * @return whether the resource is enclosed by this scope
	 */
	public boolean encloses(String resourcePath);

	/**
	 * Checks whether this scope encloses the given element.
	 * 
	 * @param element
	 *            the given element
	 * @return <code>true</code> if the element is in this scope
	 */
	public boolean encloses(IModelElement element);

	/**
	 * Returns the paths to the enclosing projects and archives for this search
	 * scope.
	 * <ul>
	 * <li> If the path is a project path, this is the full path of the project
	 * (see <code>IResource.getFullPath()</code>). For example, /MyProject
	 * </li>
	 * <li> If the path is a archive path and this archive is internal to the workspace,
	 * this is the full path of the archive file (see
	 * <code>IResource.getFullPath()</code>). For example,
	 * /MyProject/mylib.jar </li>
	 * <li> If the path is a archive path and this archive is external to the workspace,
	 * this is the full OS path to the archive file on the file system. For example,
	 * d:\libs\mylib.jar </li>
	 * </ul>
	 * 
	 * @return an array of paths to the enclosing projects and archives.
	 */
	IPath[] enclosingProjectsAndZips();
		
	IDLTKLanguageToolkit getLanguageToolkit();
}
