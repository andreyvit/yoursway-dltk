package org.eclipse.dltk.debug.core.model;

public class StringScriptType extends AtomicScriptType {
	public StringScriptType(String name) {
		super(name);
	}

	public boolean isString() {
		return true;
	}
}
