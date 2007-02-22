/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.ui.DLTKUIPlugin;


/**
 * Convenience class for error exceptions thrown inside DLTKUI plugin.
 */
public class DLTKUIStatus extends Status {

	private DLTKUIStatus(int severity, int code, String message, Throwable throwable) {
		super(severity, DLTKUIPlugin.PLUGIN_ID, code, message, throwable);
	}
	
	public static IStatus createError(int code, Throwable throwable) {
		String message= throwable.getMessage();
		if (message == null) {
			message= throwable.getClass().getName();
		}
		return new DLTKUIStatus(IStatus.ERROR, code, message, throwable);
	}

	public static IStatus createError(int code, String message, Throwable throwable) {
		return new DLTKUIStatus(IStatus.ERROR, code, message, throwable);
	}
	
	public static IStatus createWarning(int code, String message, Throwable throwable) {
		return new DLTKUIStatus(IStatus.WARNING, code, message, throwable);
	}

	public static IStatus createInfo(int code, String message, Throwable throwable) {
		return new DLTKUIStatus(IStatus.INFO, code, message, throwable);
	}
}

