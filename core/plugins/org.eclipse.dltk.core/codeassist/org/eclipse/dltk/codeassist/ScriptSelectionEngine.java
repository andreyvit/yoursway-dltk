package org.eclipse.dltk.codeassist;

import java.util.Map;

import org.eclipse.dltk.internal.codeassist.impl.Engine;

public abstract class ScriptSelectionEngine extends Engine implements
		ISelectionEngine {

	public ScriptSelectionEngine(Map settings) {
		super(settings);
	}

}
