package org.eclipse.dltk.core.internal.rse;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.dltk.core.environment.IExecutionEnvironment;

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
