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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.dltk.core.IBuffer;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IOpenable;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;


public class CallLocation implements IAdaptable {
    public static final int UNKNOWN_LINE_NUMBER= -1;
    private IModelElement fMember;
    private IModelElement fCalledMember;
    private int fStart;
    private int fEnd;

    private String fCallText;
    private int fLineNumber;

    public CallLocation(IModelElement member, IModelElement calledMember, int start, int end, int lineNumber) {
        this.fMember = member;
        this.fCalledMember = calledMember;
        this.fStart = start;
        this.fEnd = end;
        this.fLineNumber= lineNumber;
    }

    /**
     * @return IMethod
     */
    public IModelElement getCalledMember() {
        return fCalledMember;
    }

    /**
     *
     */
    public int getEnd() {
        return fEnd;
    }

    public IModelElement getMember() {
        return fMember;
    }

    /**
     *
     */
    public int getStart() {
        return fStart;
    }

    public int getLineNumber() {
    	initCallTextAndLineNumber();
        return fLineNumber;
    }
    
    public String getCallText() {
    	initCallTextAndLineNumber();
        return fCallText;
    }

    private void initCallTextAndLineNumber() {
    	if (fCallText != null)
    		return;
    	
        IBuffer buffer= getBufferForMember();
        if (buffer == null || buffer.getLength() < fEnd) { //binary, without source attachment || buffer contents out of sync (bug 121900)
        	fCallText= ""; //$NON-NLS-1$
        	fLineNumber= UNKNOWN_LINE_NUMBER;
        	return;
        }

        fCallText= buffer.getText(fStart, (fEnd - fStart));
        
        if (fLineNumber == UNKNOWN_LINE_NUMBER) {
            Document document= new Document(buffer.getContents());
            try {
                fLineNumber= document.getLineOfOffset(fStart) + 1;
            } catch (BadLocationException e) {
                DLTKUIPlugin.log(e);
            }
        }
    }
    
    /**
     * Returns the IBuffer for the IMember represented by this CallLocation.
     * 
     * @return IBuffer for the IMember or null if the member doesn't have a buffer (for
     *          example if it is a binary file without source attachment).
     */
    private IBuffer getBufferForMember() {
        IBuffer buffer = null;
        try {
            IOpenable openable = fMember.getOpenable();
            if (openable != null && fMember.exists()) {
                buffer = openable.getBuffer();
            }
        } catch (ModelException e) {
            DLTKUIPlugin.log(e);
        }
        return buffer;
    }

    public String toString() {
        return getCallText();
    }
    
    public Object getAdapter(Class adapter) {
        if (IModelElement.class.isAssignableFrom(adapter)) {
            return getMember();
        }

        return null;
    }
}
