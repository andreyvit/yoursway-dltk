package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.declarations.Argument;

public class RubyMethodArgument extends Argument {
	

	public final static int SIMPLE = 0;
	
	public final static int VARARG = 1;
	
	public final static int BLOCK = 2;

}
