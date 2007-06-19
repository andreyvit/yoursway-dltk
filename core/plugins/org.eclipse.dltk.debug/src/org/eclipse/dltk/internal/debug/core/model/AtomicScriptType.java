package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.dltk.debug.core.model.IScriptType;

public class AtomicScriptType implements IScriptType {
	private String name;

	public AtomicScriptType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isAtomic() {
		return true;
	}
	
	public boolean validate(String value) {
		return false;
	}
}
