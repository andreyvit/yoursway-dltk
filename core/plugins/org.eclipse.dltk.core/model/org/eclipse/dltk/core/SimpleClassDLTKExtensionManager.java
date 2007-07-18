package org.eclipse.dltk.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class SimpleClassDLTKExtensionManager extends SimpleDLTKExtensionManager {
	private static final String CLASS_ATTR = "class";
	
	public SimpleClassDLTKExtensionManager(String extension) {
		super(extension);
	}
	
	public Object[] getObjects() {
		ElementInfo[] infos = this.getElementInfos();
		List objs = new ArrayList();
		for (int i = 0; i < infos.length; i++) {
			Object o = getInitObject(infos[i]);
			if( o != null ) {
				objs.add(o);
			}
		}
		return objs.toArray(new Object[objs.size()]);
	}
	public Object getInitObject(ElementInfo ext) {
		try {
			if (ext != null) {
				if (ext.object != null) {
					return ext.object;
				}

				IConfigurationElement cfg = (IConfigurationElement) ext.config;
				Object object = createObject(cfg);
				ext.object = object;
				return object;
			}
		} catch (CoreException e) {
			if( DLTKCore.DEBUG ) {
				e.printStackTrace();
			}
		}
		return null;
	}

	protected Object createObject(IConfigurationElement cfg)
			throws CoreException {
		return cfg.createExecutableExtension(CLASS_ATTR);
	}
}
