package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.dltk.debug.core.model.IScriptDebugTypeFactory;
import org.eclipse.dltk.debug.core.model.IScriptType;

public class ScriptDebugTypeManager {
	private static ScriptDebugTypeManager instance;

	public static ScriptDebugTypeManager getInstance() {
		if (instance == null) {
			instance = new ScriptDebugTypeManager();
		}

		return instance;
	}

	public IScriptDebugTypeFactory getScriptDebugTypeFactory(String debugModelId) {
		return new IScriptDebugTypeFactory() {
			public IScriptType buildType(String type) {
				if (type.equals("Fixnum") || type.equals("NilClass")
						|| type.equals("String")) {
					return new AtomicScriptType(type);
				} else {
					return new ComplexScriptType(type);
				}
			}
		};
	}
}
