/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.debug.ui;

import java.text.MessageFormat;

import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.ui.IEditorInput;

public class PythonDebugModelPresentation extends ScriptDebugModelPresentation {
	private static final String PYTHON_EDITOR_ID = "org.eclipse.dltk.python.ui.editor.PythonEditor";
	
	// Text
	protected String getThreadText(IScriptThread thread) {
		try {
			return MessageFormat.format("Thread id={0} ({1})", new Object[] {
					thread.getName(),
					thread.isSuspended() ? SUSPENDED_LABEL : RUNNING_LABEL });

		} catch (DebugException e) {
			DLTKDebugUIPlugin.log(e);
		}

		return thread.toString();
	}

	public String getEditorId(IEditorInput input, Object element) {
		return PYTHON_EDITOR_ID;
	}
}
