package org.eclipse.dltk.debug.core.model;


public class ComplexScriptType implements IScriptType {
	private String name;

	public ComplexScriptType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public boolean isAtomic() {
		return false;
	}

	public boolean isCollection() {
		return false;
	}
}
