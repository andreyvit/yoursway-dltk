package org.mozilla.javascript.debug;

import org.mozilla.javascript.ScriptableObject;

public interface IDeguggerWithWatchPoints extends Debugger {

	public void access(String property, ScriptableObject object);

	public void modification(String property, ScriptableObject object);
}
