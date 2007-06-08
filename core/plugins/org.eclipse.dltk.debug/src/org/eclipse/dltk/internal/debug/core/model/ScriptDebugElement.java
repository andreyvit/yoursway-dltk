/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ITerminate;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptDebugElement;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;

public abstract class ScriptDebugElement extends PlatformObject implements
		IScriptDebugElement {

	protected IScriptDebugTarget getScriptDebugTarget() {
		return (IScriptDebugTarget) getDebugTarget();
	}

	public ILaunch getLaunch() {
		return getDebugTarget().getLaunch();
	}

	public String getModelIdentifier() {
		return getDebugTarget().getModelIdentifier();
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IDebugElement.class) {
			return this;
		}

		/* Not implemented currently
		 * 
		 * if (adapter == IStepFilters.class) { return getDebugTarget(); }
		 */

		if (adapter == IDebugTarget.class) {
			return getDebugTarget();
		}

		if (adapter == ITerminate.class) {
			return getDebugTarget();
		}

		if (adapter == IScriptDebugTarget.class) {
			return getScriptDebugTarget();
		}

		if (adapter == ILaunch.class) {
			return getLaunch();
		}

		return super.getAdapter(adapter);
	}

	protected void abort(String message, Throwable e) throws DebugException {
		throw new DebugException(new Status(IStatus.ERROR,
				DLTKDebugPlugin.PLUGIN_ID, DebugPlugin.INTERNAL_ERROR, message,
				e));
	}

	protected DebugException makeNotSupported(String message, Throwable e)
			throws DebugException {
		return new DebugException(new Status(IStatus.ERROR,
				DLTKDebugPlugin.PLUGIN_ID, DebugException.NOT_SUPPORTED,
				message, e));
	}

	protected DebugException wrapDbgpException(String message, DbgpException e) {
		return new DebugException(new Status(IStatus.ERROR, DebugPlugin
				.getUniqueIdentifier(), DebugException.INTERNAL_ERROR, message,
				e));
	}

	protected DebugException wrapIOException(String message, IOException e) {
		return new DebugException(new Status(IStatus.ERROR, DebugPlugin
				.getUniqueIdentifier(), DebugException.INTERNAL_ERROR, message,
				e));
	}
}
