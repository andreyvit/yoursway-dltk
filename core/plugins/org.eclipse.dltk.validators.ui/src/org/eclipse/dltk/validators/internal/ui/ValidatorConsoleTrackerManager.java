package org.eclipse.dltk.validators.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.console.IPatternMatchListener;

public class ValidatorConsoleTrackerManager {

	private final static String EXTPOINT = ValidatorsUI.PLUGIN_ID
			+ ".validatorConsoleTracker";

	private static List listeners;

	private static void initialize() throws CoreException {
		if (listeners != null) {
			return;
		}

		listeners = new ArrayList(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTPOINT);

		for (int i = 0; i < cfg.length; i++) {
			IPatternMatchListener listener = (IPatternMatchListener) cfg[i]
					.createExecutableExtension("class");
			listeners.add(listener);
		}
	}
	public static IPatternMatchListener[] getListeners() {
		try {
			initialize();
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		return (IPatternMatchListener[])listeners.toArray(new IPatternMatchListener[listeners.size()]);
	}
}
