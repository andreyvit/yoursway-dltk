package org.eclipse.dltk.debug.core.model;


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
}
