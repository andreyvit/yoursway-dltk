/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *          (report 36180: Callers/Callees view)
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.callhierarchy;

public abstract class CallHierarchyVisitor {
    public void preVisit(MethodWrapper methodWrapper) {
    }
    
    public void postVisit(MethodWrapper methodWrapper) {
    }
    
    public boolean visit(MethodWrapper methodWrapper) {
        return true;
    }
}
