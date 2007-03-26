package org.eclipse.dltk.ruby.internal.parser.mixin;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;

public class RubyMixinModel {
	
	private static RubyMixinModel instance;

	public static RubyMixinModel getInstance () {
		if (instance == null)
			instance = new RubyMixinModel ();
		return instance;
	}
	
	public static MixinModel getRawInstance () {
		return getInstance().getRawModel();
	}

	private final MixinModel model;

	private RubyMixinModel() {
		model = new MixinModel(RubyLanguageToolkit.getDefault());
	}

	public MixinModel getRawModel() {
		return model;
	}

	public RubyMixinClass createRubyClass(RubyClassType type) {
		return (RubyMixinClass) createRubyElement(type.getModelKey());
	}

	public IRubyMixinElement createRubyElement(String key) {
		if (key.equals("Object")) {
			return new RubyObjectMixinClass(this);
//			return new RubyMixinClass(this, "Object");
		}
		IMixinElement mixinElement = model.get(key);
		if (mixinElement != null) {
			return createRubyElement(mixinElement);
		}
		return null;
	}

	public IRubyMixinElement createRubyElement(IMixinElement element) {
		Assert.isLegal(element != null);
		if (element.getKey().equals("Object")) {
			return new RubyObjectMixinClass(this);
//			return new RubyMixinClass(this, "Object");
		}
		Object[] objects = element.getAllObjects();
		if (objects != null && objects.length > 0) {
			Object obj = objects[0]; // TODO: add normal info objects
			if (obj instanceof IType) {
				return new RubyMixinClass(this, element.getKey());
			} else if (obj instanceof IMethod) {
				return new RubyMixinMethod(this, element.getKey());
			} else if (obj instanceof IField) {
				return new RubyMixinVariable(this, element.getKey());
			}
		}
		return null;
	}

}
