/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 * 			(report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.callhierarchy;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;


/**
 * The main plugin class to be used in the desktop.
 */
public class Implementors {
    private static Implementors fgInstance;

    /**
     * Returns the shared instance.
     */
    public static Implementors getInstance() {
        if (fgInstance == null) {
            fgInstance = new Implementors();
        }

        return fgInstance;
    }

    /**
     * Searches for implementors of the specified Script elements. Currently, only IMethod
     * instances are searched for. Also, only the first element of the elements
     * parameter is taken into consideration.
     *
     * @param elements
     *
     * @return An array of found implementing Script elements (currently only IMethod
     *         instances)
     */
    public IModelElement[] searchForImplementors(IModelElement[] elements,
        IProgressMonitor progressMonitor) {
        if ((elements != null) && (elements.length > 0)) {
            IModelElement element = elements[0];

//            try {
                if (element instanceof IMember) {
                    //IMember member = (IMember) element;
//                    IType type = member.getDeclaringType();

//                    if (type.isInterface()) {
//                        IType[] implementingTypes = findImplementingTypes(type,
//                                progressMonitor);
//
//                        if (member.getElementType() == IModelElement.METHOD) {
//                            return findMethods((IMethod)member, implementingTypes, progressMonitor);
//                        } else {
//                            return implementingTypes;
//                        }
//                    }
                }
//            } catch (ModelException e) {
//                DLTKUIPlugin.log(e);
//            }
        }

        return null;
    }
}
