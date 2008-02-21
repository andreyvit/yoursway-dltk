/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.log;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.dltk.dbgp.IDbgpRawListener;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.internal.launching.LaunchConfigurationUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class ScriptDebugLogManager implements ILaunchListener,
		IDebugEventSetListener, IDbgpRawListener {

	private static ScriptDebugLogManager instance;

	private ScriptDebugLogView view;

	private ScriptDebugLogManager() {
		// empty constructor
	}

	public static ScriptDebugLogManager getInstance() {
		if (instance == null) {
			instance = new ScriptDebugLogManager();
		}

		return instance;
	}

	/*
	 * @see org.eclipse.dltk.dbgp.IDbgpRawListener#dbgpPacketReceived(java.lang.String)
	 */
	public void dbgpPacketReceived(String content) {
		append("<< " + content);
	}

	/*
	 * @see org.eclipse.dltk.dbgp.IDbgpRawListener#dbgpPacketSent(java.lang.String)
	 */
	public void dbgpPacketSent(String content) {
		append(">> " + content);
	}

	/*
	 * @see org.eclipse.debug.core.IDebugEventSetListener#handleDebugEvents(org.eclipse.debug.core.DebugEvent[])
	 */
	public void handleDebugEvents(DebugEvent[] events) {
		if (view == null) {
			return;
		}

		for (int i = 0; i < events.length; ++i) {
			DebugEvent event = events[i];

			append("Event: " + getDebugEventKind(event.getKind()) + " from "
					+ event.getSource().getClass().getName());

			if (event.getKind() == DebugEvent.CREATE) {
				handleCreateEvent(event);
			} else if (event.getKind() == DebugEvent.TERMINATE) {
				handleTerminateEvent(event);
			}
		}
	}

	/*
	 * @see org.eclipse.debug.core.ILaunchListener#launchAdded(org.eclipse.debug.core.ILaunch)
	 */
	public void launchAdded(ILaunch launch) {
		// empty implemenation
	}

	/*
	 * @see org.eclipse.debug.core.ILaunchListener#launchChanged(org.eclipse.debug.core.ILaunch)
	 */
	public void launchChanged(ILaunch launch) {
		IDebugTarget target = launch.getDebugTarget();
		boolean loggingEnabled = LaunchConfigurationUtils
				.isDbgpLoggingEnabled(launch.getLaunchConfiguration());

		// bail if we're not a ScriptDebugTarget or logging isn't enabled
		if (!((target instanceof IScriptDebugTarget) && loggingEnabled)) {
			return;
		}

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = DLTKDebugUIPlugin.getActivePage();

				if (page != null) {
					try {
						view = (ScriptDebugLogView) page
								.showView(ScriptDebugLogView.VIEW_ID);

						DebugPlugin.getDefault().addDebugEventListener(
								ScriptDebugLogManager.this);
					} catch (PartInitException e) {
						DLTKDebugUIPlugin.log(e);
					}
				}
			}
		});
	}

	/*
	 * @see org.eclipse.debug.core.ILaunchListener#launchRemoved(org.eclipse.debug.core.ILaunch)
	 */
	public void launchRemoved(ILaunch launch) {
		// empty implementation
	}

	protected synchronized void append(final String text) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				view.append(text + "\n");
			}
		});
	}

	private static String getDebugEventKind(int kind) {
		String eventName = "UNKNOWN";

		switch (kind) {
		case DebugEvent.CREATE: {
			eventName = "CREATE";
			break;
		}
		case DebugEvent.TERMINATE: {
			eventName = "TERMINATE";
			break;
		}
		case DebugEvent.CHANGE: {
			eventName = "CHANGE";
			break;
		}
		case DebugEvent.SUSPEND: {
			eventName = "SUSPEND";
			break;
		}
		case DebugEvent.RESUME: {
			eventName = "RESUME";
			break;
		}
		case DebugEvent.MODEL_SPECIFIC: {
			eventName = "MODEL_SPECIFIC";
			break;
		}
		}

		return eventName;
	}

	private void handleCreateEvent(DebugEvent event) {
		if (event.getSource() instanceof IScriptThread) {
			((IScriptThread) event.getSource()).getDbgpSession()
					.addRawListener(this);
		}
	}

	private void handleTerminateEvent(DebugEvent event) {
		if (event.getSource() instanceof IScriptThread) {
			((IScriptThread) event.getSource()).getDbgpSession()
					.removeRawListenr(this);
			DebugPlugin.getDefault().removeDebugEventListener(this);
		}
	}

}
