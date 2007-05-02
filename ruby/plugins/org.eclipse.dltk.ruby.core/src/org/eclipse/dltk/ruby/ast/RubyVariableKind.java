/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
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
	
	public class Constant extends VariableKind.Global implements RubyVariableKind {
	}

	public static final RubyVariableKind LOCAL = new Local();

	public static final RubyVariableKind GLOBAL = new Global();

	public static final RubyVariableKind INSTANCE = new Instance();

	public static final RubyVariableKind CLASS = new Class();
	
	public static final RubyVariableKind CONSTANT = new Constant();

}
