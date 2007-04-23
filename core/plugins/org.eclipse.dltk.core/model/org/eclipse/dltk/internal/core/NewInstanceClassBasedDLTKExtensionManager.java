package org.eclipse.dltk.internal.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class NewInstanceClassBasedDLTKExtensionManager extends
		ClassBasedDLTKExtensionManager {

	public NewInstanceClassBasedDLTKExtensionManager(String extensionPoint) {
		super(extensionPoint);
	}
	public Object getInitObject(ElementInfo ext) throws CoreException {
		if (ext != null) {
			IConfigurationElement cfg = (IConfigurationElement) ext.config;
			Object object = createObject(cfg);
			return object;
		}
		return null;
	}
}
