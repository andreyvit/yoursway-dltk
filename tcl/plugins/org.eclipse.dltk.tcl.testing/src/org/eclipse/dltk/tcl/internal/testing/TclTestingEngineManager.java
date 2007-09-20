package org.eclipse.dltk.tcl.internal.testing;

import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;
import org.eclipse.dltk.core.PriorityDLTKExtensionManager.ElementInfo;
import org.eclipse.dltk.tcl.testing.ITclTestingEngine;

public final class TclTestingEngineManager {
	private static PriorityClassDLTKExtensionManager manager = new PriorityClassDLTKExtensionManager(
			Activator.PLUGIN_ID + ".tclTestEngine", "id");

	public static ITclTestingEngine[] getEngines() {
		ElementInfo[] elementInfos = manager.getElementInfos();
		ITclTestingEngine[] engines = new ITclTestingEngine[elementInfos.length];
		for (int i = 0; i < elementInfos.length; i++) {
			engines[i] = (ITclTestingEngine) manager
					.getInitObject(elementInfos[i]);
		}
		return engines;
	}
}
