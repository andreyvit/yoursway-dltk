/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.ArrayList;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.IRegion;

/**
 * @see IRegion
 */

public class Region implements IRegion {

	/**
	 * A collection of the top level elements that have been added to the region
	 */
	protected ArrayList fRootElements;

	/**
	 * Creates an empty region.
	 * 
	 * @see IRegion
	 */
	public Region() {
		fRootElements = new ArrayList(1);
	}

	/**
	 * @see IRegion#add(IJavaElement)
	 */
	public void add(IModelElement element) {
		if (!contains(element)) {
			// "new" element added to region
			removeAllChildren(element);
			fRootElements.add(element);
			fRootElements.trimToSize();
		}
	}

	/**
	 * @see IRegion
	 */
	public boolean contains(IModelElement element) {

		int size = fRootElements.size();
		ArrayList parents = getAncestors(element);

		for (int i = 0; i < size; i++) {
			IModelElement aTop = (IModelElement) fRootElements.get(i);
			if (aTop.equals(element)) {
				return true;
			}
			for (int j = 0, pSize = parents.size(); j < pSize; j++) {
				if (aTop.equals(parents.get(j))) {
					// an ancestor is already included
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a collection of all the parents of this element in bottom-up
	 * order.
	 * 
	 */
	private ArrayList getAncestors(IModelElement element) {
		ArrayList parents = new ArrayList();
		IModelElement parent = element.getParent();
		while (parent != null) {
			parents.add(parent);
			parent = parent.getParent();
		}
		parents.trimToSize();
		return parents;
	}

	/**
	 * @see IRegion
	 */
	public IModelElement[] getElements() {
		int size = fRootElements.size();
		IModelElement[] roots = new IModelElement[size];
		for (int i = 0; i < size; i++) {
			roots[i] = (IModelElement) fRootElements.get(i);
		}

		return roots;
	}

	/**
	 * @see IRegion#close(IJavaElement)
	 */
	public boolean remove(IModelElement element) {

		removeAllChildren(element);
		return fRootElements.remove(element);
	}

	/**
	 * Removes any children of this element that are contained within this
	 * region as this parent is about to be added to the region.
	 * 
	 * <p>
	 * Children are all children, not just direct children.
	 */
	protected void removeAllChildren(IModelElement element) {
		if (element instanceof IParent) {
			ArrayList newRootElements = new ArrayList();
			for (int i = 0, size = fRootElements.size(); i < size; i++) {
				IModelElement currentRoot = (IModelElement) fRootElements
						.get(i);
				// walk the current root hierarchy
				IModelElement parent = currentRoot.getParent();
				boolean isChild = false;
				while (parent != null) {
					if (parent.equals(element)) {
						isChild = true;
						break;
					}
					parent = parent.getParent();
				}
				if (!isChild) {
					newRootElements.add(currentRoot);
				}
			}
			fRootElements = newRootElements;
		}
	}

	/**
	 * Returns a printable representation of this region.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		IModelElement[] roots = getElements();
		buffer.append('[');
		for (int i = 0; i < roots.length; i++) {
			buffer.append(roots[i].getElementName());
			if (i < (roots.length - 1)) {
				buffer.append(", "); //$NON-NLS-1$
			}
		}
		buffer.append(']');
		return buffer.toString();
	}
}
