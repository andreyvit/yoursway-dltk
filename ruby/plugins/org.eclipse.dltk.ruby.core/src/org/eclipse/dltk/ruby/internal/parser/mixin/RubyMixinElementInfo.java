/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parser.mixin;

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;

public class RubyMixinElementInfo {

	public static final int K_CLASS = 0;

	public static final int K_MODULE = 1;

	public static final int K_METHOD = 2;

	public static final int K_VARIABLE = 3;

	public static final int K_INCLUDE = 4;
	
	public static final int K_VIRTUAL = 5;

	private int kind = 0;
	private Object object = null;

	public RubyMixinElementInfo(int kind, Object object) {
		super();
		this.kind = kind;
		this.object = object;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public  static RubyMixinElementInfo createClass (IType type) {
		return new RubyMixinElementInfo(K_CLASS, type);
	}
	
	public  static RubyMixinElementInfo createModule (IType type) {
		return new RubyMixinElementInfo(K_MODULE, type);
	}
	
	public  static RubyMixinElementInfo createMethod (IMethod m) {
		return new RubyMixinElementInfo(K_METHOD, m);
	}
	
	public  static RubyMixinElementInfo createInclude (String  key) {
		return new RubyMixinElementInfo(K_INCLUDE, key);
	}
	
	public  static RubyMixinElementInfo createVariable (IField type) {
		return new RubyMixinElementInfo(K_VARIABLE, type);
	}
	
	public  static RubyMixinElementInfo createVirtualClass () {
		return new RubyMixinElementInfo(K_VIRTUAL, null);
	}

}
