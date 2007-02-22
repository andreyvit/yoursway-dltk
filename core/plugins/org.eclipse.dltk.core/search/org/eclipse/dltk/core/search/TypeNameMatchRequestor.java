/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core.search;

/**
 * A <code>TypeNameMatchRequestor</code> collects matches from a <code>searchAllTypeNames</code>
 * query to a <code>SearchEngine</code>. Clients must subclass this abstract class and pass an instance to the
 * {@link SearchEngine#searchAllTypeNames(
 *		char[] packageName, 
 *		int packageMatchRule, 
 *		char[] typeName,
 *		int typeMatchRule, 
 *		int searchFor, 
 *		IDLTKSearchScope scope, 
 *		TypeNameMatchRequestor nameMatchRequestor,
 *		int waitingPolicy,
 * 	org.eclipse.core.runtime.IProgressMonitor monitor)} method.
 * Only top-level and member types are reported. Local types are not reported.
 * <p>
 * Although {@link TypeNameRequestor} only reports type names information (e.g. package, enclosing types, simple name, modifiers, etc.),
 * this one reports {@link TypeNameMatch} objects instead which stored these information.
 * <p>
 * This class may be subclassed by clients.
 * </p>
 * @see TypeNameMatch
 * @see TypeNameRequestor
 * 
 */
public abstract class TypeNameMatchRequestor {
	/**
	 * Accepts a type name match ({@link TypeNameMatch}) which contains top-level or a member type
	 * information as package name, enclosing types names, simple type name, modifiers, etc.
	 * <p>
	 * The default implementation of this method does nothing.
	 * Subclasses should override.
	 * </p>
	 *
	 * @param match the match which contains all type information.
	 */
	public abstract void acceptTypeNameMatch(TypeNameMatch match);
}
