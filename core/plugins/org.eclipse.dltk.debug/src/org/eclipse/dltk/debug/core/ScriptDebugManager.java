package org.eclipse.dltk.debug.core;

import java.util.HashMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class ScriptDebugManager {
	private static final String SCRIPT_DEBUG_MODEL_EXT_POINT = DLTKDebugPlugin.PLUGIN_ID
			+ ".scriptDebugModel";
	private static final String NATURE_ID = "natureId";
	private static final String DEBUG_MODEL_ID = "debugModelId";

	private static ScriptDebugManager instance;

	public static ScriptDebugManager getInstance() {
		if (instance == null) {
			instance = new ScriptDebugManager();
		}

		return instance;
	}

	private HashMap natureToModelMap;

	private void loadExtenstionPoints() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtension[] extensions = registry.getExtensionPoint(
				SCRIPT_DEBUG_MODEL_EXT_POINT).getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension
					.getConfigurationElements();

			if (elements.length > 0) {
				IConfigurationElement element = elements[0];
				final String natureId = element.getAttribute(NATURE_ID);
				final String debugModelId = element
						.getAttribute(DEBUG_MODEL_ID);

				if (natureId != null && debugModelId != null) {
					natureToModelMap.put(natureId, debugModelId);
				}
			}
		}
	}

	protected ScriptDebugManager() {
		natureToModelMap = new HashMap();

		loadExtenstionPoints();
	}

	public String getDebugModelByNature(String natureId) {
		return (String) natureToModelMap.get(natureId);
	}
}
