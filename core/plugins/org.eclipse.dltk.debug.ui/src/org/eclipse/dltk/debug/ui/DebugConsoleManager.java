package org.eclipse.dltk.debug.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;

public class DebugConsoleManager implements ILaunchListener {

	private static DebugConsoleManager instance;

	public static DebugConsoleManager getInstance() {
		if (instance == null) {
			instance = new DebugConsoleManager();
		}

		return instance;
	}

	private Map launchToConsoleMap;

	protected boolean acceptLaunch(ILaunch launch) {
		if (launch == null){
			return false;
		}
		
		if (!launch.getLaunchMode().equals(ILaunchManager.DEBUG_MODE)){
			return false;
		}
		
		return true;
		
//		try {
//			ILaunchConfiguration configuration = launch
//					.getLaunchConfiguration();
//
//			final String natureId = configuration.getAttribute(
//					IDLTKLaunchConfigurationConstants.ATTR_NATURE,
//					(String) null);
//
//			if (natureId == null) {
//				return false;
//			}
//
//			String capture = configuration.getAttribute(
//					DebugPlugin.ATTR_CAPTURE_OUTPUT, (String) null);
//
//			if (capture == null) {
//				return false;
//			}
//			
//		} catch (CoreException e) {
//			return false;
//		}
//
//		return true;
	}

	protected IOConsole createConsole(String name) {
		IOConsole console = new IOConsole(name, null);

		IConsoleManager manager = ConsolePlugin.getDefault()
				.getConsoleManager();
		manager.addConsoles(new IConsole[] { console });
		manager.showConsoleView(console);
		return console;
	}

	protected void destroyConsole(IOConsole console) {
		IConsoleManager manager = ConsolePlugin.getDefault()
				.getConsoleManager();
		manager.removeConsoles(new IConsole[] { console });
	}

	protected DebugConsoleManager() {
		launchToConsoleMap = new HashMap();
	}

	public void launchAdded(ILaunch launch) {
		if (!acceptLaunch(launch)) {
			return;
		}

		launchToConsoleMap.put(launch, createConsole("Debug console"));
	}

	public void launchChanged(ILaunch launch) {
		if (!acceptLaunch(launch)) {
			return;
		}

		IScriptDebugTarget target = (IScriptDebugTarget) launch
				.getDebugTarget();

		if (target != null) {
			IOConsole console = (IOConsole) launchToConsoleMap.get(launch);
			if (console != null) {
				if (target.getStreamManager() == null) {
					ConsoleScriptDebugTargetStreamManager manager = new ConsoleScriptDebugTargetStreamManager(
							console);
					target.setStreamManager(manager);
				}
			}
		}
	}

	public void launchRemoved(ILaunch launch) {
		if (!acceptLaunch(launch)) {
			return;
		}

		IOConsole console = (IOConsole) launchToConsoleMap.get(launch);
		destroyConsole(console);
		launchToConsoleMap.remove(launch);
	}
}
