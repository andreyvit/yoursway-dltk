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

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IType;


public interface IImplementorFinder {
	
    /**
     * Find implementors of the specified IType instance.
     */
    public abstract Collection findImplementingTypes(IType type,
        IProgressMonitor progressMonitor);

//    /**
//     * Find interfaces which are implemented by the specified IType instance.
//     */
//    public abstract Collection findInterfaces(IType type, IProgressMonitor progressMonitor);
}
