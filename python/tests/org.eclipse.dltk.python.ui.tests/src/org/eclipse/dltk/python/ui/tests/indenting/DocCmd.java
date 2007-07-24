/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/*
 * Created on Feb 16, 2006
 */
package org.eclipse.dltk.python.ui.tests.indenting;

import org.eclipse.jface.text.DocumentCommand;

public class DocCmd extends DocumentCommand{
    public DocCmd(int offset, int length, String text){
        this.offset = offset;
        this.length = length;
        this.text   = text;
        this.doit = true;
        this.caretOffset = -1;
        this.shiftsCaret = true;
    }

}