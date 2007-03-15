package org.ecipse.dltk.javascript.internal.representation;

import java.util.HashMap;

import org.eclipse.dltk.internal.javascript.typeinference.IReference;

public class ScriptEnvironment implements IEnvironment {

	private HashMap map = new HashMap();

	static IEnvironment env;

	private static void init() {
	}
	public static synchronized IEnvironment getInstance() {
		if (env == null)
			init();
		return env;
	}

	public IReference query(String name) {
		return null;
	}
}
