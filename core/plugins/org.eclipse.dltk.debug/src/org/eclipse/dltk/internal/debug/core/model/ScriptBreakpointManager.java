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

public class ScriptBreakpointManager implements IBreakpointListener {
	// Utility methods
	protected static IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}

	// Adding, removing, updating
	protected static void addBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws CoreException, DbgpException {

		if (breakpoint instanceof IScriptLineBreakpoint) {
			IScriptLineBreakpoint lineBreakpoint = (IScriptLineBreakpoint) breakpoint;

			// Enabled
			boolean enabled = lineBreakpoint.isEnabled()
					&& getBreakpointManager().isEnabled();

			DbgpBreakpointConfig config = new DbgpBreakpointConfig(enabled);

			// Hit value
			config.setHitValue(lineBreakpoint.getHitValue());

			// Hit condition
			config.setHitCondition(lineBreakpoint.getHitCondition());

			// Expression
			if (breakpoint.getExpressionState()) {
				config.setExpression(breakpoint.getExpression());
			}

			// IScriptMethodEntryBreakpoint

			/*
			 * if (lineBreakpoint instanceof IScriptMethodEntryBreakpoint) {
			 * IScriptMethodEntryBreakpoint entryBreakpoint =
			 * (IScriptMethodEntryBreakpoint) lineBreakpoint;
			 * 
			 * if (entryBreakpoint.breakOnEntry()) { String id =
			 * commands.setLineBreakpoint(lineBreakpoint .getResourceURI(),
			 * lineBreakpoint.getLineNumber(), config);
			 * lineBreakpoint.setIdentifier(id); }
			 * 
			 * if (entryBreakpoint.breakOnExit()) { String id =
			 * commands.setReturnBreakpoint(lineBreakpoint .getResourceURI(),
			 * entryBreakpoint.getMethodName(), config);
			 * entryBreakpoint.setSecondaryId(id); } }
			 */

			/*
			 * if (breakpoint instanceof IScriptWatchPoint) { IScriptWatchPoint
			 * watchPoint = (IScriptWatchPoint) breakpoint;
			 * 
			 * String exp = watchPoint.getFieldName() + (watchPoint.isAccess() ?
			 * '1' : '0') + (watchPoint.isModification() ? '1' : '0');
			 * 
			 * config.setExpression(exp);
			 * 
			 * String id = commands.setWatchBreakpoint(lineBreakpoint
			 * .getResourceURI(), lineBreakpoint.getLineNumber(), config);
			 * watchPoint.setIdentifier(id); return;
			 *  } else {
			 *  }
			 */

			// Identifier
			final String id = commands.setLineBreakpoint(lineBreakpoint
					.getResourceURI(), lineBreakpoint.getLineNumber(), config);
			lineBreakpoint.setIdentifier(id);
		}
	}

	protected static void changeBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws DbgpException, CoreException {
		
		final String id = breakpoint.getIdentifier();

		final boolean enabled = breakpoint.isEnabled()
				&& getBreakpointManager().isEnabled();

		final int hitValue = breakpoint.getHitValue();
		final int hitCondition = breakpoint.getHitCondition();
		
		String expression = null;
		if (breakpoint.getExpressionState()) {
			expression = breakpoint.getExpression();
		}
		DbgpBreakpointConfig config = new DbgpBreakpointConfig(enabled,
				hitValue, hitCondition, expression);
		
		// Update
		commands.updateBreakpoint(id, config);
		

		/*
		if (b instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint ba = (IScriptMethodEntryBreakpoint) b;
			String secondaryId = ba.getSecondaryId();
			if (secondaryId != null) {
				if (!ba.breakOnExit()) {
					commands.removeBreakpoint(secondaryId);
					ba.setSecondaryId(null);
				} else {
					commands.updateBreakpoint(secondaryId, config);
				}
			} else if (ba.breakOnExit()) {
				String id = commands.setReturnBreakpoint(ba.getResourceURI(),
						ba.getMethodName(), config);
				ba.setSecondaryId(id);
			}
			if (!ba.breakOnEntry()) {
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
		*/

	}

	protected static void removeBreakpoint(IDbgpBreakpointCommands commands,
			IScriptBreakpoint breakpoint) throws DbgpException, CoreException {

		commands.removeBreakpoint(breakpoint.getIdentifier());

		if (breakpoint instanceof IScriptMethodEntryBreakpoint) {
			IScriptMethodEntryBreakpoint mr = (IScriptMethodEntryBreakpoint) breakpoint;
			String secondaryId = mr.getSecondaryId();
			if (secondaryId != null) {
				commands.removeBreakpoint(secondaryId);
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

				DbgpBreakpointConfig config = new DbgpBreakpointConfig(true);

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
