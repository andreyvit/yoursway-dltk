package org.eclipse.dltk.launching.debug;


public class PriorityBasedDebuggingEngineSelector implements
		IDebuggingEngineSelector {

	public IDebuggingEngine select(IDebuggingEngine[] engines) {
		IDebuggingEngine selected = null;
		int maxPriority = Integer.MIN_VALUE;

		for (int i = 0; i < engines.length; ++i) {
			IDebuggingEngine engine = engines[i];
			if (engine.getPriority() > maxPriority) {
				selected = engine;
				maxPriority = engine.getPriority();
			}
		}

		return selected;
	}
}
