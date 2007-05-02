/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

public class DebuggingEngineNotConnectedStatusHandler implements IStatusHandler {
	public Object handleStatus(IStatus status, Object source)
			throws CoreException {

		DLTKDebugUIPlugin.getStandardDisplay().syncExec(new Runnable() {
			public void run() {
				String title = "Debugging engine not avaialble";
				String message = "Debugging engine cannot connect to dbgp service during period of time";

				MessageDialog.openError(DLTKDebugUIPlugin
						.getActiveWorkbenchShell(), title, message);
			}
		});

		return null;
	}
}
