package org.eclipse.dltk.debug.core;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.debug.core.model.IScriptTypeFactory;

public class ScriptDebugManager {
	private static final String SCRIPT_DEBUG_MODEL_EXT_POINT = DLTKDebugPlugin.PLUGIN_ID
			+ ".scriptDebugModel"; //$NON-NLS-1$
	private static final String NATURE_ID = "natureId"; //$NON-NLS-1$
	private static final String DEBUG_MODEL_ID = "debugModelId"; //$NON-NLS-1$
	private static final String TYPE_FACTORY = "typeFactory"; //$NON-NLS-1$

	private static ScriptDebugManager instance;

	public static ScriptDebugManager getInstance() {
		if (instance == null) {
			instance = new ScriptDebugManager();
		}

		return instance;
	}

	private final HashMap natureToInfoMap;
	private final HashMap modelToNatureMap;

	private static class Info {
		public final String debugModelId;
		public final IScriptTypeFactory typeFactory;

		public Info(String debugModelId, IScriptTypeFactory typeFactory) {
			this.debugModelId = debugModelId;
			this.typeFactory = typeFactory;
		}
	}

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

				IScriptTypeFactory typeFactory = null;

				try {
					typeFactory = (IScriptTypeFactory) element
							.createExecutableExtension(TYPE_FACTORY);
				} catch (CoreException e) {
					// TODO: log exception
				}

				if (natureId != null && debugModelId != null) {
					natureToInfoMap.put(natureId, new Info(debugModelId,
							typeFactory));
					modelToNatureMap.put(debugModelId, natureId);
				}
			}
		}
	}

	protected Info getInfo(String natureId) {
		return (Info) natureToInfoMap.get(natureId);
	}

	protected ScriptDebugManager() {
		natureToInfoMap = new HashMap();
		modelToNatureMap = new HashMap();

		loadExtenstionPoints();
	}

	public String getNatureByDebugModel(String debugModelId) {
		return (String) modelToNatureMap.get(debugModelId);
	}

	public String getDebugModelByNature(String natureId) {
		return getInfo(natureId).debugModelId;
	}

	public IScriptTypeFactory getTypeFactoryByNature(String natureId) {
		return getInfo(natureId).typeFactory;
	}

	public IScriptTypeFactory getTypeFactoryByDebugModel(String debugModelId) {
		return getTypeFactoryByNature(getNatureByDebugModel(debugModelId));
	}
}
