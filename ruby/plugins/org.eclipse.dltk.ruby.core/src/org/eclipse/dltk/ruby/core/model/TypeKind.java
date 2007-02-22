package org.eclipse.dltk.ruby.core.model;

public interface TypeKind {

	public class Unknown implements TypeKind {
	}

	public class Class implements TypeKind {
	}
	
	public class Builtin implements TypeKind {
	}

	public static final TypeKind UNKNOWN = new Unknown();

	public static final TypeKind CLASS = new Class();
	
	public static final TypeKind BUILTIN = new Builtin();

}
