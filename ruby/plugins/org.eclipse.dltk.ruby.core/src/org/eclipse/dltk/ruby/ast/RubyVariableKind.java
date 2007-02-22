package org.eclipse.dltk.ruby.ast;

import org.eclipse.dltk.ast.references.VariableKind;

public interface RubyVariableKind extends VariableKind {

	public class Local extends VariableKind.Local implements RubyVariableKind {
	}

	public class Global extends VariableKind.Global implements RubyVariableKind {
	}

	public class Instance extends VariableKind.Instance implements RubyVariableKind {
	}
	
	public class Class extends VariableKind.Global implements RubyVariableKind {
	}

	public static final RubyVariableKind LOCAL = new Local();

	public static final RubyVariableKind GLOBAL = new Global();

	public static final RubyVariableKind INSTANCE = new Instance();

	public static final RubyVariableKind CLASS = new Class();
	

}
