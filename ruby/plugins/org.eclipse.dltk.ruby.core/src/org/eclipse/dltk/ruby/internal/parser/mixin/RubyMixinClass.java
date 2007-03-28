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
	
	public RubyMixinClass getMetaclass () {
		if (isMeta())
			return this;
		String metakey = key.substring(0, key.indexOf(RubyMixin.INSTANCE_SUFFIX));
		IRubyMixinElement r = model.createRubyElement(metakey);
		if (r instanceof RubyMixinClass)
			return (RubyMixinClass) r;
		return null;
	}
	
	public boolean isMeta () {
		return (!key.endsWith(RubyMixin.INSTANCE_SUFFIX))&&(!key.endsWith(RubyMixin.VIRTUAL_SUFFIX));
	}
	
	public String getName () {
		String name = key.substring(key.lastIndexOf(MixinModel.SEPARATOR));
		int pos;
		if ((pos = name.indexOf(RubyMixin.INSTANCE_SUFFIX)) != -1)
			name = name.substring(0, pos);
		return name;
	}
	
	public IType[] getSourceTypes () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		for (int i = 0; i < allObjects.length; i++) {
			if (allObjects[i] instanceof IType) {
				result.add(allObjects[i]);
			}
		}
		return (IType[]) result.toArray(new IType[result.size()]);
	}
	
	public RubyMixinClass getSuperclass () {
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();		
		IType type = (IType) allObjects[0];
		if (type != null) { 
			String key = RubyModelUtils.evaluateSuperClass (type);
			if (key != null) {
				RubyMixinClass s = (RubyMixinClass) model.createRubyElement(key);				
				return s;
			}
		}
		return null;
	}
	
	public RubyMixinMethod[] findMethods (boolean ignoreObjectMethods) {
		final List result = new ArrayList ();
			
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinMethod)
				result.add(element);
		}
		if (!this.key.equals("Object")) {
			RubyMixinClass superclass = getSuperclass();
			if (superclass != null) {
				if (!(superclass.key.equals("Object") && ignoreObjectMethods)) {
					if (!this.isMeta())
						superclass = superclass.getInstanceClass();
					RubyMixinMethod[] methods = superclass.findMethods(ignoreObjectMethods);
					result.addAll(Arrays.asList(methods));
				}
			}		
		}
		return (RubyMixinMethod[]) result.toArray(new RubyMixinMethod[result.size()]);
	}
	
	public RubyMixinMethod[] findMethods (String pattern, boolean ignoreObjectMethods) {
		final List result = new ArrayList ();
		
		String[] keys = model.getRawModel().findKeys(key + MixinModel.SEPARATOR + pattern);
		for (int i = 0; i < keys.length; i++) {
			IRubyMixinElement element = model.createRubyElement(keys[i]);
			if (element instanceof RubyMixinMethod)
				result.add(element);
		}					
		if (!this.key.equals("Object")) {
			RubyMixinClass superclass = getSuperclass();
			if (superclass != null) {
				if (!(superclass.key.equals("Object") && ignoreObjectMethods)) {
					if (!this.isMeta())
						superclass = superclass.getInstanceClass();
					RubyMixinMethod[] methods = superclass.findMethods(pattern, ignoreObjectMethods);
					result.addAll(Arrays.asList(methods));
				}
			}	
		}
		return (RubyMixinMethod[]) result.toArray(new RubyMixinMethod[result.size()]);
	}
	
	public RubyMixinMethod getMethod (String name) {
		String possibleKey = key + MixinModel.SEPARATOR + name;
		IMixinElement mixinElement = model.getRawModel().get(possibleKey);
		if (mixinElement != null) {
			return (RubyMixinMethod) model.createRubyElement(mixinElement);
		}
		return null;
	}
	
	public RubyMixinClass[] getClasses () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinClass)
				result.add(element);
		}
		return (RubyMixinClass[]) result.toArray(new RubyMixinClass[result.size()]);
	}
	
	public RubyMixinVariable[] getFields () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement[] children = mixinElement.getChildren();
		for (int i = 0; i < children.length; i++) {
			IRubyMixinElement element = model.createRubyElement(children[i]);
			if (element instanceof RubyMixinVariable)
				result.add(element);
		}
		return (RubyMixinVariable[]) result.toArray(new RubyMixinVariable[result.size()]);
	}
	
	public RubyMixinVariable[] findFields (String pattern) {
		List result = new ArrayList ();
		String[] keys = model.getRawModel().findKeys(this.key + MixinModel.SEPARATOR + pattern);
		for (int i = 0; i < keys.length; i++) {
			IRubyMixinElement element = model.createRubyElement(keys[i]);
			if (element instanceof RubyMixinVariable)
				result.add(element);
		}
		return (RubyMixinVariable[]) result.toArray(new RubyMixinVariable[result.size()]);
	}

	
	
}
