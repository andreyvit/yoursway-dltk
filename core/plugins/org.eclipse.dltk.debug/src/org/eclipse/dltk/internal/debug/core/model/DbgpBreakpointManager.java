/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.core.model;

import java.net.URI;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.dltk.dbgp.breakpoints.DbgpBreakpointConfig;
import org.eclipse.dltk.dbgp.commands.IDbgpBreakpointCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;

public class DbgpBreakpointManager implements IBreakpointListener {
	protected static IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}

	private static IDbgpBreakpointCommands getBreakpointCommands(
			IScriptThread thread) {
		return thread.getDbgpSession().getCoreCommands();
	}

	public static boolean supportsBreakpoint(IBreakpoint breakpoint) {
		if (breakpoint instanceof IScriptBreakpoint) {
			return true;
		}
		return false;
	}

	protected static void addBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws CoreException, DbgpException {

		if (breakpoint instanceof IScriptLineBreakpoint) {
			IScriptLineBreakpoint b = (IScriptLineBreakpoint) breakpoint;

			boolean enabled = b.isEnabled()
					&& getBreakpointManager().isEnabled();

			String cExpr = null;
			if (breakpoint.isConditionalExpressionEnabled()) {
				cExpr = breakpoint.getConditionalExpression();
			}

			DbgpBreakpointConfig config = new DbgpBreakpointConfig(enabled, b
					.getHitValue(), b.getHitCondition(), cExpr);

			if (b instanceof IScriptMethodEntryBreakpoint) {
				IScriptMethodEntryBreakpoint me = (IScriptMethodEntryBreakpoint) b;
				if (me.shouldBreakOnEntry()) {
					String id = commands.setLineBreakpoint(b.getResourceURI(),
							b.getLineNumber(), config);
					b.setIdentifier(id);
				}
				if (me.shouldBreakOnExit()) {
					String id = commands.setReturnBreakpoint(
							b.getResourceURI(), me.getMethodName(), config);
					me.setSecondaryId(id);
				}
			} else {
				if (breakpoint instanceof IScriptWatchPoint) {
					IScriptWatchPoint wb = (IScriptWatchPoint) breakpoint;
					String setWatchBreakpoint = commands.setWatchBreakpoint(wb
							.getFieldName()
							+ (wb.isAccess() ? '1' : '0')
							+ (wb.isModification() ? '1' : '0'), b
							.getResourceURI(), b.getLineNumber(), config);
					wb.setIdentifier(setWatchBreakpoint);
					return;
				} else {
					String id = commands.setLineBreakpoint(b.getResourceURI(),
							b.getLineNumber(), config);
					b.setIdentifier(id);
				}
			}

			// TODO: why?
			b.setHitValue(config.getHitValue());
			b.setHitCondition(config.getHitCondition());
		}
	}

	protected void addBreakpoint(IBreakpoint breakpoint) throws CoreException {
		if (!supportsBreakpoint(breakpoint)) {
			return;
		}

		IThread[] threads = threadManager.getThreads();
		for (int i = 0; i < threads.length; ++i) {
			try {
				addBreakpoint(
						getBreakpointCommands((IScriptThread) threads[i]),
						(IScriptBreakpoint) breakpoint);
			} catch (DbgpException e) {
				breakpoint.delete();
			}
		}
	}

	protected static void changeBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws DbgpException, CoreException {
		boolean enabled = breakpoint.isEnabled()
				&& getBreakpointManager().isEnabled();

		IScriptBreakpoint b = breakpoint;

		int hitValue = b.getHitValue();
		int hitCondition = b.getHitCondition();
		String cExpr = null;
		if (breakpoint.isConditionalExpressionEnabled()) {
			cExpr = breakpoint.getConditionalExpression();
		}
		DbgpBreakpointConfig config = new DbgpBreakpointConfig(enabled,
				hitValue, hitCondition, cExpr);

		if (b.getIdentifier() != null) {
			commands.updateBreakpoint(b.getIdentifier(), config);
		}
		if (b instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint ba = (IScriptMethodEntryBreakpoint) b;
			String secondaryId = ba.getSecondaryId();
			if (secondaryId != null) {
				if (!ba.shouldBreakOnExit()) {
					commands.removeBreakpoint(secondaryId);
					ba.setSecondaryId(null);
				} else {
					commands.updateBreakpoint(secondaryId, config);
				}
			} else if (ba.shouldBreakOnExit()) {
				String id = commands.setReturnBreakpoint(ba.getResourceURI(),
						ba.getMethodName(), config);
				ba.setSecondaryId(id);
			}
			if (!ba.shouldBreakOnEntry()) {
				String identifier = ba.getIdentifier();
				if (identifier != null) {
					commands.removeBreakpoint(identifier);
					ba.setIdentifier(null);
				}
			} else {
				String identifier = ba.getIdentifier();
				if (identifier == null) {
					String id = commands.setLineBreakpoint(ba.getResourceURI(),
							ba.getLineNumber(), config);
					ba.setIdentifier(id);
				}
			}
		}
	}

	protected void changeBreakpoint(IBreakpoint breakpoint)
			throws CoreException {
		if (!supportsBreakpoint(breakpoint)) {
			return;
		}

		IThread[] threads = threadManager.getThreads();
		for (int i = 0; i < threads.length; ++i) {
			try {
				changeBreakpoint(
						getBreakpointCommands((IScriptThread) threads[i]),
						(IScriptBreakpoint) breakpoint);
			} catch (DbgpException e) {
				breakpoint.delete();
			}
		}
	}

	protected static void removeBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws DbgpException {
		IScriptBreakpoint b = breakpoint;

		commands.removeBreakpoint(b.getIdentifier());
		if (b instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint mr = (IScriptMethodEntryBreakpoint) b;
			String secondaryId = mr.getSecondaryId();
			if (secondaryId != null) {
				commands.removeBreakpoint(secondaryId);
			}
		}
	}

	protected void removeBreakpoint(IBreakpoint breakpoint)
			throws DbgpException {
		if (!supportsBreakpoint(breakpoint)) {
			return;
		}

		IThread[] threads = threadManager.getThreads();
		for (int i = 0; i < threads.length; ++i) {
			removeBreakpoint(getBreakpointCommands((IScriptThread) threads[i]),
					(IScriptBreakpoint) breakpoint);
		}
	}

	private final ScriptThreadManager threadManager;

	public DbgpBreakpointManager(ScriptThreadManager manager) {
		if (manager == null) {
			throw new IllegalArgumentException();
		}

		this.threadManager = manager;
	}

	public void setupDeferredBreakpoints(final IScriptThread thread)
			throws CoreException {

		Job job = new Job("Setting up deffered breakpoints") {
			protected IStatus run(IProgressMonitor monitor) {
				IBreakpoint[] breakpoints = getBreakpointManager()
						.getBreakpoints(thread.getModelIdentifier());

				for (int i = 0; i < breakpoints.length; i++) {
					IBreakpoint breakpoint = breakpoints[i];

					if (supportsBreakpoint(breakpoint)) {
						try {
							addBreakpoint(getBreakpointCommands(thread),
									(IScriptBreakpoint) breakpoint);
						} catch (Exception e) {

						}
					}
				}

				return Status.OK_STATUS;
			}
		};

		job.setSystem(true);
		job.setUser(false);
		job.schedule();
	}

	public void setupTemporaryBreakpoint(URI uri, int lineNumber)
			throws DbgpException, CoreException {

		DbgpBreakpointConfig config = new DbgpBreakpointConfig(true, -1, -1,
				true, null);

		IThread[] threads = threadManager.getThreads();
		for (int i = 0; i < threads.length; ++i) {
			IDbgpBreakpointCommands commands = getBreakpointCommands((IScriptThread) threads[i]);
			commands.setLineBreakpoint(uri, lineNumber, config);
		}
	}

	// IBreakpointListener
	public void breakpointAdded(IBreakpoint breakpoint) {
		try {
			addBreakpoint(breakpoint);
		} catch (CoreException e) {
			DLTKDebugPlugin.log(e);
		}
	}

	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		try {
			changeBreakpoint(breakpoint);
		} catch (CoreException e) {
			DLTKDebugPlugin.log(e);
		}
	}

	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		try {
			removeBreakpoint(breakpoint);
		} catch (DbgpException e) {
			DLTKDebugPlugin.log(e);
		}
	}
}
