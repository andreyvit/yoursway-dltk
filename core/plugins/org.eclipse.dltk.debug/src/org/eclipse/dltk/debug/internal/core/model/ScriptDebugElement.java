package org.eclipse.dltk.debug.internal.core.model;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptDebugElement;

public class ScriptDebugElement extends PlatformObject implements
		IScriptDebugElement {

	private IDebugTarget target;

	public ScriptDebugElement() {
		this.target = null;
	}

	public ScriptDebugElement(IDebugTarget target) {
		if (target == null) {
			throw new IllegalArgumentException();
		}

		this.target = target;
	}

	public IDebugTarget getDebugTarget() {
		return target;
	}

	public ILaunch getLaunch() {
		return getDebugTarget().getLaunch();
	}

	public String getModelIdentifier() {
		//TODO: expand this!!!
		return ScriptModelConstants.MODEL_ID;
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IDebugElement.class) {
			return this;
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
