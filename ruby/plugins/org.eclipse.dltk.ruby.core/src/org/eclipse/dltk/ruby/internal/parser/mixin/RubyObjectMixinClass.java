package org.eclipse.dltk.ruby.internal.parser.mixin;

import org.eclipse.dltk.core.IType;

public class RubyObjectMixinClass extends RubyMixinClass {

	public RubyObjectMixinClass(RubyMixinModel model) {
		super(model, "Object");
	}

	public RubyMixinClass[] getClasses() {
		return new RubyMixinClass[0];
	}

	public RubyMixinVariable[] getFields() {
		return new RubyMixinVariable[0];
	}

	public String getKey() {
		return "Object";
	}

	public RubyMixinClass getMetaclass() {
		return this;
	}

	public RubyMixinMethod getMethod(String name) {
		return null;
	}

	public RubyMixinMethod[] getMethods(boolean ignoreObjectMethods) {
		return new RubyMixinMethod[0];
	}

	public String getName() {
		return "Object";
	}

	public IType[] getSourceTypes() {
		return new IType[0];
	}

	public RubyMixinClass getSuperclass() {
		return null;
	}

	public boolean isMeta() {
		return true;
	}
	
	

}
