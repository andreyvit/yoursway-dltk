package org.eclipse.dltk.internal.debug.core.model;

import org.eclipse.dltk.debug.core.model.IScriptType;

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
	
	public boolean validate(String value) {
		return false;
	}
}
