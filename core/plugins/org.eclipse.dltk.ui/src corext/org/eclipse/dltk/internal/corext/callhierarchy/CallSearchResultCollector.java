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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;


public class CallSearchResultCollector {
    private Map fCalledMembers;

    public CallSearchResultCollector() {
        this.fCalledMembers = createCalledMethodsData();
    }

    public Map getCallers() {
        return fCalledMembers;
    }

    protected void addMember(IModelElement member, IModelElement calledMember, int start, int end) {
        addMember(member, calledMember, start, end, CallLocation.UNKNOWN_LINE_NUMBER);
    }

    protected void addMember(IModelElement member, IModelElement calledMember, int start, int end, int lineNumber) {
        if ((member != null) && (calledMember != null)) {
            if (!isIgnored(calledMember)) {
                MethodCall methodCall = (MethodCall) fCalledMembers.get(calledMember.getHandleIdentifier());

                if (methodCall == null) {
                    methodCall = new MethodCall(calledMember);
                    fCalledMembers.put(calledMember.getHandleIdentifier(), methodCall); 
                }

                methodCall.addCallLocation(new CallLocation(member, calledMember, start,
                        end, lineNumber));
            }
        }
    }

    protected Map createCalledMethodsData() {
        return new HashMap();
    }

    /**
     * Method isIgnored.
     * @param enclosingElement
     * @return boolean
     */
    private boolean isIgnored(IModelElement enclosingElement) {
    	IType type = getTypeOfElement(enclosingElement);
    	if( type != null ) {
    		String fullyQualifiedName = type.getFullyQualifiedName();

    		return CallHierarchy.getDefault().isIgnored(fullyQualifiedName);
    	}
    	else {
    		return false;
    	}
    }

    private IType getTypeOfElement(IModelElement element) {
        if (element.getElementType() == IModelElement.TYPE) {
            return (IType) element;
        }
        if( element instanceof IMember ) {        		        
        	return ((IMember)element).getDeclaringType();
        }
        return null;
    }
}
