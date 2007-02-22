package org.eclipse.dltk.ruby.core.model;


public interface RubyTypeKind extends TypeKind {

	public class Mixin implements TypeKind {
	}

	public static final TypeKind MIXIN = new Mixin(); 

}