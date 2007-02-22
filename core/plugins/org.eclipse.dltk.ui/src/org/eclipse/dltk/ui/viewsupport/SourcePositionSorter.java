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
package org.eclipse.dltk.ui.viewsupport;

import org.eclipse.dltk.core.Flags;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;


/**
 *  Viewer sorter which sorts the Java elements like
 *  they appear in the source.
 * 
 * @since 3.2
 */
public class SourcePositionSorter extends ViewerSorter {

	/*
	 * @see org.eclipse.jface.viewers.ViewerSorter#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (!(e1 instanceof ISourceReference))
			return 0;
		if (!(e2 instanceof ISourceReference))
			return 0;
		
		IModelElement parent1= ((IModelElement)e1).getParent();
		if (parent1 == null || !parent1.equals(((IModelElement)e2).getParent())) {
				IType t1= getOutermostDeclaringType(e1);
				if (t1 == null)
					return 0;
				
				IType t2= getOutermostDeclaringType(e2);
				try {
					if (!t1.equals(t2)) {
						if (t2 == null)
							return 0;

						if (Flags.isPublic(t1.getFlags()) && Flags.isPublic(t2.getFlags()))
							return 0;

						

						ISourceModule cu1= (ISourceModule)((IModelElement)e1).getAncestor(IModelElement.SOURCE_MODULE);
						if (cu1 != null) {
							if (!cu1.equals(((IModelElement)e2).getAncestor(IModelElement.SOURCE_MODULE)))
								return 0;
						} 
					}
				} catch (ModelException e3) {
					return 0;
				}
		}
		
		try {
			ISourceRange sr1= ((ISourceReference)e1).getSourceRange();
			ISourceRange sr2= ((ISourceReference)e2).getSourceRange();
			if (sr1 == null || sr2 == null)
				return 0;
			
			return sr1.getOffset() - sr2.getOffset();
			
		} catch (ModelException e) {
			return 0;
		}
	}

	private IType getOutermostDeclaringType(Object element) {
		if (!(element instanceof IMember))
			return null;
		
		IType declaringType;
		if (element instanceof IType)
			declaringType= (IType)element;
		else {
			declaringType= ((IMember)element).getDeclaringType();
			if (declaringType == null)
				return null;
		}
		
		IType declaringTypeDeclaringType= declaringType.getDeclaringType();
		while (declaringTypeDeclaringType != null) {
			declaringType= declaringTypeDeclaringType;
			declaringTypeDeclaringType= declaringType.getDeclaringType();
		}
		return declaringType;
	}
}
