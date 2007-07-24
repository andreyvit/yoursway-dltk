/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/

package org.eclipse.dltk.internal.debug.ui.log;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.dltk.dbgp.IDbgpRawListener;
import org.eclipse.dltk.debug.core.DLTKDebugPlugin;
import org.eclipse.dltk.debug.core.DebugPreferenceConstants;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.dltk.debug.core.model.IScriptThread;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class ScriptDebugLogManager implements ILaunchListener,
		IDebugEventSetListener, IDbgpRawListener {

	private static ScriptDebugLogManager instance;

	public static ScriptDebugLogManager getInstance() {
		if (instance == null) {
			instance = new ScriptDebugLogManager();
		}

		return instance;
	}

	private ScriptDebugLogView view;

	private ScriptDebugLogManager() {

	}

	// ILaunchListener
	public void launchAdded(ILaunch launch) {

	}

	public void launchChanged(ILaunch launch) {
		Preferences prefs = DLTKDebugPlugin.getDefault().getPluginPreferences();
		boolean enableLogging = prefs
				.getBoolean(DebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);

		if (!enableLogging) {
			return;
		}

		IDebugTarget target = launch.getDebugTarget();
		if (target instanceof IScriptDebugTarget) {
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
	}

	public void launchRemoved(ILaunch launch) {

	}

	private static String getDebugEventKind(int kind) {
		String eventName = "UNKNOWN";
		switch (kind) {
		case DebugEvent.CREATE:
			eventName = "CREATE";
			break;
		case DebugEvent.TERMINATE:
			eventName = "TERMINATE";
			break;
		case DebugEvent.CHANGE:
			eventName = "CHANGE";
			break;
		case DebugEvent.SUSPEND:
			eventName = "SUSPEND";
			break;
		case DebugEvent.RESUME:
			eventName = "RESUME";
			break;
		case DebugEvent.MODEL_SPECIFIC:
			eventName = "MODEL_SPECIFIC";
			break;
		}
		return eventName;
	}

	// IDebugEventSetListener
	public void handleDebugEvents(DebugEvent[] events) {
		if (view == null) {
			return;
		}

		for (int i = 0; i < events.length; ++i) {
			DebugEvent event = events[i];

			append("Event: " + getDebugEventKind(event.getKind()) + " from "
					+ event.getSource().getClass().getName());

			if (event.getKind() == DebugEvent.CREATE) {

				if (event.getSource() instanceof IScriptThread) {
					((IScriptThread) event.getSource()).getDbgpSession()
							.addRawListener(this);
					// append("Thread connected!");
				}
			}
		}
	}

	protected synchronized void append(final String text) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				view.append(text + "\n");
			}
		});
	}

	// IDbgpRawListener
	public void dbgpPacketReceived(String content) {
		append("<< " + content);

	}

	public void dbgpPacketSent(String content) {
		append(">> " + content);
	}
}
