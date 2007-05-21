/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.ui.IEditorInput;

public class TclDebugModelPresentation extends ScriptDebugModelPresentation {
	private static final String TCL_EDITOR_ID = "org.eclipse.dltk.tcl.ui.editor.TclEditor";

	public String getEditorId(IEditorInput input, Object element) {
		return TCL_EDITOR_ID;
	}
}
