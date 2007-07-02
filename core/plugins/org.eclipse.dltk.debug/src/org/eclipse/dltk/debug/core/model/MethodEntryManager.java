package org.eclipse.dltk.debug.core.model;

import java.util.Iterator;

import org.eclipse.dltk.internal.debug.core.model.ScriptDebugTarget;

public class MethodEntryManager {

	private static boolean suspendOnEntry;
	private static boolean suspendOnExit;

	public static boolean isSuspendOnMethodEntry() {
		return suspendOnEntry;
	}

	public static boolean isSuspendOnMethodExit() {
		return suspendOnExit;
	}

	public static void setSuspendOnMethodEntry(boolean sEntry) {
		Iterator it = ScriptDebugTarget.getAllTargets().iterator();
		while (it.hasNext()) {
			ScriptDebugTarget tr = (ScriptDebugTarget) it.next();
			tr.setSuspendOnMethodEntry(sEntry);
		}
		suspendOnEntry = sEntry;
	}

	public static void setSuspendOnMethodExit(boolean sExit) {
		Iterator it = ScriptDebugTarget.getAllTargets().iterator();
		while (it.hasNext()) {
			ScriptDebugTarget tr = (ScriptDebugTarget) it.next();
			tr.setSuspendOnMethodExit(sExit);
		}
		suspendOnExit = sExit;
	}

}
