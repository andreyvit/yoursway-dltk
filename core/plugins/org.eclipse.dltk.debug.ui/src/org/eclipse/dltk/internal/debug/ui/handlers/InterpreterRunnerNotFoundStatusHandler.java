/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.handlers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * 
 * @author kds
 * 
 */
public class InterpreterRunnerNotFoundStatusHandler implements IStatusHandler {
	public Object handleStatus(final IStatus status, Object source)
			throws CoreException {
		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				MessageDialog.openError(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(),
						HandlerMessages.InterpreterRunnerNotFound, status
								.getMessage());
			}
		});

		return null;
	}
}
