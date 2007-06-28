package org.eclipse.dltk.launching.debug;


public abstract class IdBasedDebuggingEngineSelector implements
		IDebuggingEngineSelector {

	public IDebuggingEngine select(IDebuggingEngine[] engines) {
		String id = getDebuggingEngineId();

		if (id != null) {
			for (int i = 0; i < engines.length; ++i) {
				IDebuggingEngine engine = engines[i];
				if (engine.getId().equals(id)) {
					return engine;
				}
			}
		}

		return null;
	}

	protected abstract String getDebuggingEngineId();
}
