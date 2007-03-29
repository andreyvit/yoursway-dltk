package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.typeinference.RubyModelUtils;

public class RubyMixinClass implements IRubyMixinElement {

	private final String key;
	protected final RubyMixinModel model;

	public RubyMixinClass(RubyMixinModel model, String key) {
		super();
		this.model = model;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public RubyMixinClass getInstanceClass() {
		if (!isMeta())
			return this;
		String newkey = key + RubyMixin.INSTANCE_SUFFIX;
		IRubyMixinElement r = model.createRubyElement(newkey);
		if (r instanceof RubyMixinClass)
			return (RubyMixinClass) r;
		return null;
	}

	public RubyMixinClass getMetaclass() {
		if (isMeta())
			return this;
		String metakey = key.substring(0, key
				.indexOf(RubyMixin.INSTANCE_SUFFIX));
		IRubyMixinElement r = model.createRubyElement(metakey);
		if (r instanceof RubyMixinClass)
			return (RubyMixinClass) r;
		return null;
	}

	public boolean isMeta() {
		return (!key.endsWith(RubyMixin.INSTANCE_SUFFIX))
				&& (!key.endsWith(RubyMixin.VIRTUAL_SUFFIX));
	}

	public String getName() {
		String name = key.substring(key.lastIndexOf(MixinModel.SEPARATOR));
		int pos;
		if ((pos = name.indexOf(RubyMixin.INSTANCE_SUFFIX)) != -1)
			name = name.substring(0, pos);
		return name;
	}

	public IType[] getSourceTypes() {
		List result = new ArrayList();
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		for (int i = 0; i < allObjects.length; i++) {
			if (allObjects[i] instanceof IType) {
				result.add(allObjects[i]);
			}
		}
		return (IType[]) result.toArray(new IType[result.size()]);
	}

	public RubyMixinClass getSuperclass() {
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		IType type = (IType) allObjects[0];
		if (type != null) {
			String key = RubyModelUtils.evaluateSuperClass(type);
			if (key == null) {
				if (this.isMeta())
					key = "Class%";
				else
					key = "Object";
			} 
			if (!this.isMeta())
				key = key + RubyMixin.INSTANCE_SUFFIX;
			RubyMixinClass s = (RubyMixinClass) model.createRubyElement(key);
			return s;

		}
		return null;
	}

	public RubyMixinMethod[] findMethods(String prefix, boolean includeTopLevel) {
		final List result = new ArrayList();

		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinMethod) {
				RubyMixinMethod rubyMixinMethod = (RubyMixinMethod) element;
				if (rubyMixinMethod.getName().startsWith(prefix))
					result.add(element);
			}
		}

		RubyMixinClass superclass = getSuperclass();
		if (superclass != null) {
			
			if (!superclass.getKey().equals(key)) {
				RubyMixinMethod[] methods = superclass.findMethods(prefix,
						includeTopLevel);
				result.addAll(Arrays.asList(methods));
			}
		}

		return (RubyMixinMethod[]) result.toArray(new RubyMixinMethod[result
				.size()]);
	}

	public RubyMixinMethod getMethod(String name) {
		String possibleKey = key + MixinModel.SEPARATOR + name;
		IMixinElement mixinElement = model.getRawModel().get(possibleKey);
		if (mixinElement != null) {
			return (RubyMixinMethod) model.createRubyElement(mixinElement);
		}
		// search superclass
		// if (!this.key.equals("Object") && !this.key.equals("Object%")) {
		RubyMixinClass superclass = getSuperclass();
		if (superclass != null) {
			if (superclass.getKey().equals(key))
				return null;
			return superclass.getMethod(name);
		}
		// }
		return null;
	}

	public RubyMixinClass[] getClasses() {
		List result = new ArrayList();
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinClass)
				result.add(element);
		}
		return (RubyMixinClass[]) result.toArray(new RubyMixinClass[result
				.size()]);
	}

	public RubyMixinVariable[] getFields() {
		List result = new ArrayList();
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinVariable)
				result.add(element);
		}
		return (RubyMixinVariable[]) result
				.toArray(new RubyMixinVariable[result.size()]);
	}

}
