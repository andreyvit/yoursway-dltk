package org.eclipse.dltk.core;


public interface ISourceModuleInfoCache {
	interface ISourceModuleInfo {
		Object get(Object key);
		void put(Object key, Object value);
		void remove(Object key);
		boolean isEmpty();
	}
	public ISourceModuleInfo get(ISourceModule module);
	public void remove(ISourceModule sourceModule);
}
