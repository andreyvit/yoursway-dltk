package org.eclipse.dltk.debug.core;

import org.eclipse.dltk.debug.core.model.IScriptThread;

public interface ISmartStepEvaluator {
	boolean skipSuspend(String[] filters, IScriptThread thread);
}
