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
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.dltk.dbgp.breakpoints.DbgpBreakpointConfig;
import org.eclipse.dltk.dbgp.commands.IDbgpBreakpointCommands;
import org.eclipse.dltk.dbgp.exceptions.DbgpException;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.model.IScriptBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptLineBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptMethodEntryBreakpoint;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.core.model.IScriptWatchPoint;

public class ScriptBreakpointManager implements IBreakpointListener {
	// Utility methods
	protected static IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}

	protected static DbgpBreakpointConfig createBreakpointConfig(
			IScriptBreakpoint breakpoint) throws CoreException {
		// Enabled
		boolean enabled = breakpoint.isEnabled()
				&& getBreakpointManager().isEnabled();

		DbgpBreakpointConfig config = new DbgpBreakpointConfig(enabled);

		// Hit value
		config.setHitValue(breakpoint.getHitValue());

		// Hit condition
		config.setHitCondition(breakpoint.getHitCondition());

		// Expression
		if (breakpoint.getExpressionState()) {
			config.setExpression(breakpoint.getExpression());
		}

		return config;
	}

	protected static String makeWatchpointExpression(
			IScriptWatchPoint watchpoint) throws CoreException {
		return watchpoint.getFieldName() + (watchpoint.isAccess() ? '1' : '0')
				+ (watchpoint.isModification() ? '1' : '0');
	}

	// Adding, removing, updating
	protected static void addBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws CoreException, DbgpException {

		DbgpBreakpointConfig config = createBreakpointConfig(breakpoint);

		String id = null;
		// Type specific
		if (breakpoint instanceof IScriptWatchPoint) {
			IScriptWatchPoint watchpoint = (IScriptWatchPoint) breakpoint;

			config.setExpression(makeWatchpointExpression(watchpoint));

			id = commands.setWatchBreakpoint(watchpoint.getResourceURI(),
					watchpoint.getLineNumber(), config);
		} else if (breakpoint instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint entryBreakpoint = (IScriptMethodEntryBreakpoint) breakpoint;

			if (entryBreakpoint.breakOnExit()) {
				final String exitId = commands.setReturnBreakpoint(
						entryBreakpoint.getResourceURI(), entryBreakpoint
								.getMethodName(), config);

				entryBreakpoint.setExitBreakpointId(exitId);
			}

			if (entryBreakpoint.breakOnEntry()) {
				final String entryId = commands.setLineBreakpoint(
						entryBreakpoint.getResourceURI(), entryBreakpoint
								.getLineNumber(), config);

				entryBreakpoint.setEntryBreakpointId(entryId);
			}
		} else if (breakpoint instanceof IScriptLineBreakpoint) {
			IScriptLineBreakpoint lineBreakpoint = (IScriptLineBreakpoint) breakpoint;

			id = commands.setLineBreakpoint(lineBreakpoint.getResourceURI(),
					lineBreakpoint.getLineNumber(), config);
		}

		// Identifier
		breakpoint.setIdentifier(id);
	}

	protected static void changeBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws DbgpException, CoreException {

		if (breakpoint instanceof IScriptMethodEntryBreakpoint) {
			DbgpBreakpointConfig config = createBreakpointConfig(breakpoint);
			IScriptMethodEntryBreakpoint entryBreakpoint = (IScriptMethodEntryBreakpoint) breakpoint;

			String entryId = null;
			if (entryBreakpoint.breakOnEntry()) {
				if (entryId == null) {
					// Create entry breakpoint
					entryId = commands.setLineBreakpoint(entryBreakpoint
							.getResourceURI(), entryBreakpoint.getLineNumber(),
							config);
					entryBreakpoint.setEntryBreakpointId(entryId);
				} else {
					// Update entry breakpoint
					commands.updateBreakpoint(entryId, config);
				}
			} else {
				if (entryId != null) {
					// Remove existing entry breakpoint
					commands.removeBreakpoint(entryId);
					entryBreakpoint.setEntryBreakpointId(null);
				}
			}

			String exitId = null;
			if (entryBreakpoint.breakOnExit()) {
				if (exitId == null) {
					// Create exit breakpoint
					exitId = commands.setReturnBreakpoint(entryBreakpoint
							.getResourceURI(), entryBreakpoint.getMethodName(),
							config);
					entryBreakpoint.setExitBreakpointId(exitId);
				} else {
					// Update exit breakpoint
					commands.updateBreakpoint(exitId, config);
				}
			} else {
				if (exitId != null) {
					// Remove exit breakpoint
					commands.removeBreakpoint(exitId);
					entryBreakpoint.setExitBreakpointId(null);
				}
			}
		} else {
			// All other breakpoints
			final String id = breakpoint.getIdentifier();
			final DbgpBreakpointConfig config = createBreakpointConfig(breakpoint);

			if (breakpoint instanceof IScriptWatchPoint) {
				config
						.setExpression(makeWatchpointExpression((IScriptWatchPoint) breakpoint));
			}

			commands.updateBreakpoint(id, config);
		}
	}

	protected static void removeBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws DbgpException, CoreException {

		commands.removeBreakpoint(breakpoint.getIdentifier());

		if (breakpoint instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint entryBreakpoint = (IScriptMethodEntryBreakpoint) breakpoint;

			final String entryId = entryBreakpoint.getEntryBreakpointId();
			if (entryId != null) {
				commands.removeBreakpoint(entryId);
			}

			final String exitId = entryBreakpoint.getExitBreakpointId();
			if (exitId != null) {
				commands.removeBreakpoint(exitId);
			}
		}
	}

	// DebugTarget
	private final IScriptDebugTarget target;

	// Add, remove, update to debug target
	protected void addBreakpoint(IBreakpoint breakpoint) throws CoreException,
			DbgpException {
		IScriptThread[] threads = (IScriptThread[]) target.getThreads();

		if (threads.length > 0) {
			if (supportsBreakpoint(breakpoint)) {
				addBreakpoint(threads[0].getDbgpSession().getCoreCommands(),
						(IScriptBreakpoint) breakpoint);
			}
		}
	}

	protected void changeBreakpoint(IBreakpoint breakpoint)
			throws CoreException, DbgpException {
		IScriptThread[] threads = (IScriptThread[]) target.getThreads();
		if (threads.length > 0) {
			if (supportsBreakpoint(breakpoint)) {
				changeBreakpoint(threads[0].getDbgpSession().getCoreCommands(),
						(IScriptBreakpoint) breakpoint);
			}
		}
	}

	protected void removeBreakpoint(IBreakpoint breakpoint)
			throws CoreException, DbgpException {
		IScriptThread[] threads = (IScriptThread[]) target.getThreads();
		if (threads.length > 0) {
			if (supportsBreakpoint(breakpoint)) {
				removeBreakpoint(threads[0].getDbgpSession().getCoreCommands(),
						(IScriptBreakpoint) breakpoint);
			}
		}
	}

	public ScriptBreakpointManager(IScriptDebugTarget target) {
		this.target = target;
	}

	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		if (breakpoint instanceof IScriptBreakpoint) {
			final String modelId = target.getModelIdentifier();
			final String breakpointModelId = breakpoint.getModelIdentifier();

			return breakpointModelId.equals(modelId);
		}

		return false;
	}

	public void setupDeferredBreakpoints() {
		IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(
				target.getModelIdentifier());

		for (int i = 0; i < breakpoints.length; i++) {
			try {
				addBreakpoint(breakpoints[i]);
			} catch (Exception e) {
				// TODO: log
				e.printStackTrace();
			}
		}
	}

	// Simple breakpoint management
	public String addBreakpoint(URI uri, int line) {
		try {
			IScriptThread[] threads = (IScriptThread[]) target.getThreads();
			if (threads.length > 0) {
				IScriptThread thread = threads[0];

				DbgpBreakpointConfig config = new DbgpBreakpointConfig(true);

				return thread.getDbgpSession().getCoreCommands()
						.setLineBreakpoint(uri, line, config);
			}

		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbgpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void removeBreakpoint(String id) {
		try {
			IScriptThread[] threads = (IScriptThread[]) target.getThreads();
			if (threads.length > 0) {
				IScriptThread thread = threads[0];
				thread.getDbgpSession().getCoreCommands().removeBreakpoint(id);
			}
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbgpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBreakpointUntilFirstSuspend(URI uri, int line) {
		final String tempId = addBreakpoint(uri, line);

		DebugPlugin.getDefault().addDebugEventListener(
				new IDebugEventSetListener() {
					public void handleDebugEvents(DebugEvent[] events) {
						for (int i = 0; i < events.length; ++i) {
							DebugEvent event = events[i];
							if (event.getKind() == DebugEvent.SUSPEND) {
								removeBreakpoint(tempId);
								DebugPlugin.getDefault()
										.removeDebugEventListener(this);
							}
						}
					}
				});
	}

	// IBreakpointListener
	public void breakpointAdded(IBreakpoint breakpoint) {
		try {
			addBreakpoint(breakpoint);
		} catch (Exception e) {
			DLTKDebugPlugin.log(e);
		}
	}

	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		try {
			changeBreakpoint(breakpoint);
		} catch (Exception e) {
			DLTKDebugPlugin.log(e);
		}
	}

	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		try {
			removeBreakpoint(breakpoint);
		} catch (Exception e) {
			DLTKDebugPlugin.log(e);
		}
	}
}
