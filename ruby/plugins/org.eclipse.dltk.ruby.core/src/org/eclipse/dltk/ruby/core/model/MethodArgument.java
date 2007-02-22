package org.eclipse.dltk.ruby.core.model;


public class MethodArgument implements IMethodArgument {
	
	private String name;
	
	private MethodArgumentKind kind = MethodArgumentKind.SIMPLE;

	public MethodArgument(String name, MethodArgumentKind kind) {
		super();
		this.name = name;
		this.kind = kind;
	}

	public MethodArgumentKind getKind() {
		return kind;
	}

	public String getName() {
		return name;
	}			

}
