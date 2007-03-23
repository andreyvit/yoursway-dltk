package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.mixin.IMixinElement;

public class RubyMixinMethod implements IRubyMixinElement {

	private final String key;
	private final RubyMixinModel model;

	public RubyMixinMethod(RubyMixinModel model, String key) {
		super();
		this.model = model;
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public RubyMixinClass getSelfType () {
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement parent = mixinElement.getParent();
		if (parent == null)
			return null;
		IRubyMixinElement rubyParent = model.createRubyElement(parent);
		if (rubyParent instanceof RubyMixinClass) {
			return (RubyMixinClass) rubyParent;
		}		
		return null;
	}
	
	public IMethod[] getSourceMethods () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		for (int i = 0; i < allObjects.length; i++) {
			if (allObjects[i] instanceof IMethod)
				result.add (allObjects[i]);			
		}
		return (IMethod[]) result.toArray(new IMethod[result.size()]);
	}

}
