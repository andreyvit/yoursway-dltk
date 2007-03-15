/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.javascript.ui.internal.common;

import org.eclipse.jface.viewers.IElementComparer;

public class ContentElementComparerImpl implements IElementComparer {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IElementComparer#equals(java.lang.Object, java.lang.Object)
	 */
	public boolean equals(Object a, Object b) {
		boolean result = false;

		if (a instanceof ContentElement && b instanceof ContentElement) {
			ContentElement aCE = (ContentElement) a;
			ContentElement bCE = (ContentElement) b;

			result = aCE.getName().compareTo(bCE.getName()) == 0 && aCE.getNameOffset() == bCE.getNameOffset();
		}
		else
			result = a == b;

		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IElementComparer#hashCode(java.lang.Object)
	 */
	public int hashCode(Object element) {
		return 0;
	}

}
