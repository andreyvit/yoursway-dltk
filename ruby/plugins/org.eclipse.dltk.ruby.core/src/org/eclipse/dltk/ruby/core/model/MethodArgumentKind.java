package org.eclipse.dltk.ruby.core.model;


public interface MethodArgumentKind {
	
	public class BlockArgument implements MethodArgumentKind {

	}

	public class VariableArgument implements MethodArgumentKind {

	}

	public class Simple implements MethodArgumentKind {
	}

	public static final MethodArgumentKind SIMPLE = new Simple();
	
	public static final MethodArgumentKind VARARG = new VariableArgument();
	
	public static final MethodArgumentKind BLOCK = new BlockArgument();

}
