package org.eclipse.dltk.core.internal.rse;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;
import org.eclipse.rse.core.model.IHost;
import org.eclipse.rse.core.subsystems.ISubSystem;
import org.eclipse.rse.subsystems.shells.core.subsystems.servicesubsystem.IShellServiceSubSystem;

public class RSEExecEnvironmentAdapter implements IAdapterFactory {
	public static final Class[] ADAPTER_LIST = { IExecutionEnvironment.class };
	
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IExecutionEnvironment.class && 
				adaptableObject instanceof RSEEnvironment) {
			RSEEnvironment env = (RSEEnvironment) adaptableObject;
			return new RSEExecEnvironment(env);
		}
		return null;
	}
	public Class[] getAdapterList() {
		return ADAPTER_LIST; 
	}

}
