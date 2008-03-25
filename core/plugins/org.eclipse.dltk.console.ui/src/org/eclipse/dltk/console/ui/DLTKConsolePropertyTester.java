package org.eclipse.dltk.console.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.ui.console.TextConsole;

public class DLTKConsolePropertyTester extends PropertyTester {

	private static final String IS_DLTK_CONSOLE_PROPERTY = "isDLTKConsole";

	public DLTKConsolePropertyTester() {
	}

	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if (IS_DLTK_CONSOLE_PROPERTY.equals(property)) {
			if (receiver instanceof ScriptConsole) {
				return true;
			} else if (receiver instanceof TextConsole) {
				TextConsole textConsole = (TextConsole) receiver;
				Object obj = textConsole
						.getAttribute(IDebugUIConstants.ATTR_CONSOLE_PROCESS);
				if (obj != null && obj instanceof IProcess) {
					IProcess process = (IProcess) obj;
					ILaunch launch = process.getLaunch();
					ILaunchConfiguration configuration = launch
							.getLaunchConfiguration();
					try {
						String nature = configuration
								.getAttribute(
										ScriptLaunchConfigurationConstants.ATTR_SCRIPT_NATURE,
										(String) null);
						if (nature != null) {
							if (DLTKLanguageManager.getLanguageToolkit(nature) != null) {
								return true;
							}
						}
					} catch (CoreException e) {
						if (DLTKCore.DEBUG) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return false;
	}
}
