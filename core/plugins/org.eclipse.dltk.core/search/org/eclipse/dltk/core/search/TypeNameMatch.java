/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core.search;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IType;

/**
 * A match collected while searching for {@link SearchEngine} all type names
 * methods using a {@link TypeNameRequestor requestor}.
 * <p>
 * User can get type from this match using {@link #getType()} method.
 * </p>
 * <p>
 * This class is intented to be overridden by clients.
 * </p>
 * 
 * @see TypeNameMatchRequestor
 * @see SearchEngine#searchAllTypeNames(char[], int, char[], int, int,
 *      IScriptSearchScope, TypeNameMatchRequestor, int,
 *      org.eclipse.core.runtime.IProgressMonitor)
 * @see SearchEngine#searchAllTypeNames(char[][], char[][], IScriptSearchScope,
 *      TypeNameMatchRequestor, int, org.eclipse.core.runtime.IProgressMonitor)
 */
public abstract class TypeNameMatch {

	/**
	 * Returns the matched type fully qualified name using '.' character as
	 * separator (e.g. package name + '.' enclosing type names + '.' simple
	 * name).
	 * 
	 * @see #getType()
	 * @see IType#getFullyQualifiedName(char)
	 * 
	 * @throws NullPointerException
	 *             if matched type is <code> null</code>
	 * @return Fully qualified type name of the type
	 */
	public String getFullyQualifiedName() {
		return getType().getFullyQualifiedName();
	}

	/**
	 * Returns the modifiers of the matched type.
	 * <p>
	 * This is a handle-only method as neither Script Model nor classpath
	 * initialization is done while calling this method.
	 * 
	 * @return the type modifiers
	 */
	public abstract int getModifiers();

	/**
	 * Returns the project fragment of the stored type. Project fragment cannot
	 * be null and <strong>does</strong> exist.
	 * 
	 * @see #getType()
	 * @see IScriptElement#getAncestor(int)
	 * 
	 * @throws NullPointerException
	 *             if matched type is <code> null</code>
	 * @return the existing Script model package fragment root (ie. cannot be
	 *         <code>null</code> and will return <code>true</code> to
	 *         <code>exists()</code> message).
	 */
	public IProjectFragment getProjectFragment() {
		return (IProjectFragment) getType().getAncestor(
				IModelElement.PROJECT_FRAGMENT);
	}

	/**
	 * Returns the package name of the stored type.
	 * 
	 * @see #getType()
	 * @see IType#getPackageFragment()
	 * 
	 * @throws NullPointerException
	 *             if matched type is <code> null</code>
	 * @return the package name
	 */
	public String getPackageName() {
		return getType().getScriptFolder().getElementName();
	}

	/**
	 * Returns the name of the stored type.
	 * 
	 * @see #getType()
	 * @see IScriptElement#getElementName()
	 * 
	 * @throws NullPointerException
	 *             if matched type is <code> null</code>
	 * @return the type name
	 */
	public String getSimpleTypeName() {
		return getType().getElementName();
	}

	/**
	 * Returns a Script model type handle. This handle may exist or not, but is
	 * not supposed to be <code>null</code>.
	 * <p>
	 * This is a handle-only method as neither Script Model nor classpath
	 * initializations are done while calling this method.
	 * 
	 * @see IType
	 * @return the non-null handle on matched script model type.
	 */
	public abstract IType getType();

	/**
	 * Name of the type container using '.' character as separator (e.g. package
	 * name + '.' + enclosing type names).
	 * 
	 * @see #getType()
	 * @see IMember#getDeclaringType()
	 * 
	 * @throws NullPointerException
	 *             if matched type is <code> null</code>
	 * @return Name of the type container
	 */
	public String getTypeContainerName() {
		IType outerType = getType().getDeclaringType();
		if (outerType != null) {
			return outerType.getTypeQualifiedName();
		} else {
			return getType().getScriptFolder().getElementName();
		}
	}

	/**
	 * Returns the matched type qualified name using '.' character as separator
	 * (e.g. enclosing type names + '.' + simple name).
	 * 
	 * @see #getType()
	 * @see IType#getTypeQualifiedName(char)
	 * 
	 * @throws NullPointerException
	 *             if matched type is <code> null</code>
	 * @return Fully qualified type name of the type
	 */
	public String getTypeQualifiedName() {
		return getType().getTypeQualifiedName();
	}
}
