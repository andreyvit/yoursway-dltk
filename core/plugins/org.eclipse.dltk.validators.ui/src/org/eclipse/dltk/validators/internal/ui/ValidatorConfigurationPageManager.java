package org.eclipse.dltk.validators.internal.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.validators.ValidatorConfigurationPage;

public class ValidatorConfigurationPageManager {

	private final static String LANGUAGE_EXTPOINT = ValidatorsUI.PLUGIN_ID
			+ ".validatorConfigPage";

	private final static String ID_ATTR = "id";

	private static Map toolkits;

	private static void initialize() {
		if (toolkits != null) {
			return;
		}

		toolkits = new HashMap(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(LANGUAGE_EXTPOINT);

		for (int i = 0; i < cfg.length; i++) {
			String id = cfg[i].getAttribute(ID_ATTR);
			if (toolkits.get(id) != null)
				System.err.println("TODO log redeclaration");
			toolkits.put(id, cfg[i]);
		}
	}

	public static ValidatorConfigurationPage getConfigurationPage(String Id)
			throws CoreException {
		initialize();

		Object ext = toolkits.get(Id);

		if (ext != null) {
			if (ext instanceof ValidatorConfigurationPage)
				return (ValidatorConfigurationPage) ext;

			IConfigurationElement cfg = (IConfigurationElement) ext;
			ValidatorConfigurationPage toolkit = (ValidatorConfigurationPage) cfg
					.createExecutableExtension("class");
			toolkits.put(Id, toolkit);
			return toolkit;
		}
		return null;
	}
}
