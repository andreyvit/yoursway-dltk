/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.console.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.dltk.console.IScriptInterpreter;
import org.eclipse.dltk.console.ScriptConsoleServer;
import org.eclipse.dltk.console.ScriptInterpreterManager;
import org.eclipse.dltk.launching.IDLTKLaunchConfigurationConstants;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;


public class ScriptConsoleManager implements ILaunchListener {
	private static ScriptConsoleManager instance;

	public static ScriptConsoleManager getInstance() {
		if (instance == null) {
			instance = new ScriptConsoleManager();
		}

		return instance;
	}

	private IConsoleManager manager;

	protected ScriptConsoleManager() {
		this.manager = ConsolePlugin.getDefault().getConsoleManager();
	}

	public ScriptConsole[] getScriptConsoles(String consoleType) {
		List consoles = new ArrayList();
		IConsole[] consoles2 = manager.getConsoles();
		for( int i  = 0; i < consoles2.length; ++i ) {
			if( consoles2[i] instanceof ScriptConsole && consoles2[i].getType().equals(consoleType)) {
				consoles.add(consoles2[i]);
			}
		}
		return (ScriptConsole[])consoles.toArray(new ScriptConsole[consoles.size()]);
	}

	public ScriptConsole getActiveScriptConsole(String consoleType) {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				IViewPart part = page
						.findView(IConsoleConstants.ID_CONSOLE_VIEW);

				if (part != null && part instanceof IConsoleView) {
					IConsoleView view = (IConsoleView) part;
					IConsole console = view.getConsole();

					if (console instanceof ScriptConsole
							&& console.getType().equals(consoleType)) {
						return (ScriptConsole) console;
					}
				}
			}
		}

		return null;
	}

	public void close(ScriptConsole console) {
		console.terminate();
		remove(console);
	}

	public void closeAll() {
		IConsole[] consoles = manager.getConsoles();
		for (int i = 0; i < consoles.length; ++i) {
			IConsole console = consoles[i];
			if (console instanceof ScriptConsole) {
				close((ScriptConsole) console);
			}
		}
	}

	public void showConsole(ScriptConsole console) {
		manager.showConsoleView(console);
	}

	public void add(ScriptConsole console) {
		manager.addConsoles(new IConsole[] { console });
	}

	public void remove(ScriptConsole console) {
		manager.removeConsoles(new IConsole[] { console });
	}

	protected IScriptConsoleFactory findScriptConsoleFactory(String natureId)
			throws CoreException {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint ep = reg
				.getExtensionPoint(ScriptConsoleUIConstants.SCRIPT_CONSOLE_EP);
		IExtension[] extensions = ep.getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension ext = extensions[i];
			IConfigurationElement[] ce = ext.getConfigurationElements();
			for (int j = 0; j < ce.length; j++) {
				if (natureId
						.equals(ce[j]
								.getAttribute(ScriptConsoleUIConstants.SCRIPT_CONSOLE_NATURE_ID))) {
					Object obj = ce[j]
							.createExecutableExtension(ScriptConsoleUIConstants.SCRIPT_CONSOLE_CLASS);
					if (obj instanceof IScriptConsoleFactory) {
						return (IScriptConsoleFactory) obj;
					} else {
						return null;
					}
				}
			}
		}

		return null;
	}

	// ILaunchListener
	public void launchAdded(ILaunch launch) {
		try {
			final ILaunchConfiguration configuration = launch
					.getLaunchConfiguration();
			
			final String natureId = configuration.getAttribute(IDLTKLaunchConfigurationConstants.ATTR_NATURE, (String)null);
			
			if (natureId == null){
				return;
			}
			
			boolean useDltk = configuration.getAttribute(IDLTKLaunchConfigurationConstants.ATTR_USE_DLTK_OUTPUT, false);

			if (!useDltk) {
				return;
			}

			final String consoleId = configuration.getAttribute(IDLTKLaunchConfigurationConstants.ATTR_DLTK_CONSOLE_ID,
					(String) null);

			final IScriptConsoleFactory factory = findScriptConsoleFactory(natureId);

			if (factory == null) {
				return;
			}

			ISafeRunnable runnable = new ISafeRunnable() {
				public void handleException(Throwable exception) {
				}

				public void run() throws Exception {
					ScriptConsoleServer server = ScriptConsoleServer
							.getInstance();

					IScriptInterpreter interpreter = ScriptInterpreterManager
							.getInstance().createInterpreter(natureId);

					server.register(consoleId, interpreter);

					factory.openConsole(interpreter, configuration.getName());
				}
			};

			SafeRunner.run(runnable);

		} catch (CoreException e) {
			//TODO: log exception
			e.printStackTrace();
		}
	}

	public void launchChanged(ILaunch launch) {
	}

	public void launchRemoved(ILaunch launch) {
	}
}
