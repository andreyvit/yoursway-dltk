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
package org.eclipse.dltk.ui.browsing;

import java.util.Comparator;

import org.eclipse.dltk.core.IModelElement;

public class ScriptElementTypeComparator implements Comparator {


	/**
	 * Compares two Java element types. A type is considered to be
	 * greater if it may contain the other.
	 *
	 * @return		an int less than 0 if object1 is less than object2,
	 *				0 if they are equal, and > 0 if object1 is greater
	 *
	 * @see Comparator#compare(Object, Object)
	 */
	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof IModelElement) || !(o2 instanceof IModelElement))
			throw new ClassCastException();
		return getIdForJavaElement((IModelElement)o1) - getIdForJavaElement((IModelElement)o2);
	}

	/**
	 * Compares two Java element types. A type is considered to be
	 * greater if it may contain the other.
	 *
	 * @return		an int < 0 if object1 is less than object2,
	 *				0 if they are equal, and > 0 if object1 is greater
	 *
	 * @see Comparator#compare(Object, Object)
	 */
	public int compare(Object o1, int elementType) {
		if (!(o1 instanceof IModelElement))
			throw new ClassCastException();
		return getIdForJavaElement((IModelElement)o1) - getIdForJavaElementType(elementType);
	}

	int getIdForJavaElement(IModelElement element) {
		return getIdForJavaElementType(element.getElementType());
	}

	int getIdForJavaElementType(int elementType) {
		switch (elementType) {
			case IModelElement.SCRIPT_MODEL:
				return 130;
			case IModelElement.SCRIPT_PROJECT:
				return 120;
			case IModelElement.PROJECT_FRAGMENT:
				return 110;
			case IModelElement.SCRIPT_FOLDER:
				return 100;
			case IModelElement.SOURCE_MODULE:
				return 90;
			case IModelElement.TYPE:
				return 70;
			case IModelElement.FIELD:
				return 60;
			case IModelElement.METHOD:
				return 50;
			case IModelElement.PACKAGE_DECLARATION:
				return 30;
			default :
				return 1;
		}
	}
}
