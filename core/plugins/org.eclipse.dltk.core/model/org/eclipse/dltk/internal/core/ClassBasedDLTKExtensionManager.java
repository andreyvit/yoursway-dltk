package org.eclipse.dltk.internal.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.dltk.core.IModelElement;

public class ClassBasedDLTKExtensionManager extends
		BasicDLTKExtensionManager {

	private static final String CLASS_ATTR = "class";

	public ClassBasedDLTKExtensionManager(String extensionPoint) {
		super(extensionPoint);
	}

	public Object getObject(String natureId) throws CoreException {
		ElementInfo ext = this.getElementInfo(natureId);

		return getInitObject(ext);
	}

	public Object getInitObject(ElementInfo ext) throws CoreException {
		if (ext != null) {
			if (ext.object != null) {
				return ext.object;
			}

			IConfigurationElement cfg = (IConfigurationElement) ext.config;
			Object object = createObject(cfg);
			ext.object = object;
			return object;
		}
		return null;
	}

	protected Object createObject(IConfigurationElement cfg) throws CoreException {
		return cfg.createExecutableExtension(CLASS_ATTR);
	}

	public Object getObject(
			IModelElement element) throws CoreException {
		if (element.getElementType() == IModelElement.SCRIPT_MODEL) {
			return null;
		}
		IProject project = element.getScriptProject().getProject();
		String natureId = findScriptNature(project);
		if (natureId != null) {
			Object toolkit = getObject(natureId);
			if (toolkit != null) {
				return toolkit;
			}
		}
		return null;
	}
}
