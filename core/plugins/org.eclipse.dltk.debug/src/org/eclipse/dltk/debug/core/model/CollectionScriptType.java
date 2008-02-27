package org.eclipse.dltk.debug.core.model;


public class CollectionScriptType implements IScriptType {
	private String name;

	public CollectionScriptType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public boolean isAtomic() {
		return false;
	}

	public boolean isCollection() {
		return true;
	}

	public boolean isString() {
		return false;
	}
}
