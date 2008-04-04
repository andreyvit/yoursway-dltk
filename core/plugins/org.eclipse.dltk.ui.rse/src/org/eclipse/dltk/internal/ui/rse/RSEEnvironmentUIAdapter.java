package org.eclipse.dltk.internal.ui.rse;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.dltk.core.internal.rse.RSEEnvironment;
import org.eclipse.dltk.ui.environment.IEnvironmentUI;

public class RSEEnvironmentUIAdapter implements IAdapterFactory {
	private final static Class[] ADAPTABLES = new Class[] { IEnvironmentUI.class };

	public RSEEnvironmentUIAdapter() {
	}

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof RSEEnvironment
				&& adapterType == IEnvironmentUI.class) {
			return new RSEEnvironmentUI((RSEEnvironment) adaptableObject);
		}
		return null;
	}

	public Class[] getAdapterList() {
		return ADAPTABLES;
	}
}
