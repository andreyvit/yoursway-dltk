package org.eclipse.dltk.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

public class DLTKLanguageManager {

	private final static String LANGUAGE_EXTPOINT = DLTKCore.PLUGIN_ID
			+ ".language";

	private final static String NATURE_ATTR = "nature";

	private static Map toolkits;

	private static void initialize() {
		if (toolkits != null) {
			return;
		}

		toolkits = new HashMap(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(LANGUAGE_EXTPOINT);

		for (int i = 0; i < cfg.length; i++) {
			String nature = cfg[i].getAttribute(NATURE_ATTR);
			if (toolkits.get(nature) != null)
				System.err.println("TODO log redeclaration");
			toolkits.put(nature, cfg[i]);
		}
	}

	private static String findScriptNature(IProject project)
			throws CoreException {
		initialize();

		try {
			String[] natureIds = project.getDescription().getNatureIds();
			for (int i = 0; i < natureIds.length; i++) {
				String natureId = natureIds[i];

				if (toolkits.containsKey(natureId)) {
					return natureId;
				}
			}
		} catch (CoreException e) {
			return null;
		}
		

		return null;
	}

	public static IDLTKLanguageToolkit getLanguageToolkit(String natureId)
			throws CoreException {
		initialize();

		Object ext = toolkits.get(natureId);

		if (ext != null) {
			if (ext instanceof IDLTKLanguageToolkit)
				return (IDLTKLanguageToolkit) ext;

			IConfigurationElement cfg = (IConfigurationElement) ext;
			IDLTKLanguageToolkit toolkit = (IDLTKLanguageToolkit) cfg
					.createExecutableExtension("class");
			toolkits.put(natureId, toolkit);
			return toolkit;
		}
		return null;
	}

	private static IDLTKLanguageToolkit findAppropriateToolkitByObject(Object object) {
		initialize();

		Iterator i = toolkits.keySet().iterator();
		while (i.hasNext()) {
			try {
				String natureId = (String) i.next();
				IDLTKLanguageToolkit toolkit = getLanguageToolkit(natureId);
				if (object instanceof IResource) {
					if (toolkit.validateSourceModule((IResource) object)
							.getSeverity() == Status.OK) {
						return toolkit;
					}
				} else if (object instanceof IPath) {
					if (toolkit.validateSourceModule((IPath) object)
							.getSeverity() == Status.OK) {
						return toolkit;
					}
				} else {
					return null;
				}
			} catch (CoreException ex) {
				if (DLTKCore.DEBUG) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	public static boolean hasScriptNature(IProject project) {
		try {
			return findScriptNature(project) != null;
		} catch (CoreException e) {
			// not existent or closed
			return false;
		}
	}

	public static IDLTKLanguageToolkit getLangaugeToolkit(IModelElement element)
			throws CoreException {
		IProject project = element.getScriptProject().getProject();
		String natureId = findScriptNature(project);
		if (natureId != null) {
			IDLTKLanguageToolkit toolkit = getLanguageToolkit(natureId);
			if (toolkit != null) {
				return toolkit;
			}
		}
		
		IStatus status = new Status(IStatus.ERROR, DLTKCore.PLUGIN_ID, 0,
				"Project has no associated script nature", null);
		throw new CoreException(status);
	}

	public static IDLTKLanguageToolkit findToolkit(IResource resource) {
		return findAppropriateToolkitByObject(resource);
	}

	public static IDLTKLanguageToolkit findToolkit(IPath path) {
		return findAppropriateToolkitByObject(path);
	}
}
