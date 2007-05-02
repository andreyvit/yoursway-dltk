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

import java.util.Map;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.search.SearchMatch;
import org.eclipse.dltk.core.search.SearchRequestor;


class MethodReferencesSearchRequestor extends SearchRequestor {
    private CallSearchResultCollector fSearchResults;
    private boolean fRequireExactMatch = true;

    MethodReferencesSearchRequestor() {
        fSearchResults = new CallSearchResultCollector();
    }

    public Map getCallers() {
        return fSearchResults.getCallers();
    }
    
    public void acceptSearchMatch(SearchMatch match) {
        if (fRequireExactMatch && (match.getAccuracy() != SearchMatch.A_ACCURATE)) {
            return;
        }
        
        if (match.isInsideDocComment()) {
            return;
        }

        if (match.getElement() != null && match.getElement() instanceof IModelElement) {
        	IModelElement member= (IModelElement) match.getElement();
            switch (member.getElementType()) {
                case IModelElement.METHOD:
                case IModelElement.TYPE:
                case IModelElement.FIELD:
                case IModelElement.SOURCE_MODULE:
//                case IModelElement.INITIALIZER:
                    fSearchResults.addMember(member, member, match.getOffset(), match.getOffset()+match.getLength());
                    break;
            }
        }
    }
}
