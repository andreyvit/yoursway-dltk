package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.ruby.typeinference.RubyModelUtils;

public class RubyObjectMixinClass extends RubyMixinClass {

	public RubyObjectMixinClass(RubyMixinModel model) {
		super(model, "Object");
	}

	public RubyMixinMethod[] findMethods(String pattern,
			boolean ignoreObjectMethods) {
		HashMap result = new HashMap ();
		IMethod[] topLevelMethods = RubyModelUtils.findTopLevelMethods(null, pattern);
		for (int i = 0; i < topLevelMethods.length; i++) {
			String name = topLevelMethods[i].getElementName();
			List l = (List) result.get(name);
			if (l == null) {
				l = new ArrayList ();
				result.put(name, l);				
			}
			l.add(topLevelMethods[i]);
		}
		
		List mixinResult = new ArrayList ();
		for (Iterator iterator = result.keySet().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			List l = (List) result.get(name);
			IMethod[] m = (IMethod[]) l.toArray(new IMethod[l.size()]);
			mixinResult.add(new RubyMixinMethod(model, getKey() + MixinModel.SEPARATOR + name, m));
		}
			
		return (RubyMixinMethod[]) mixinResult.toArray(new RubyMixinMethod[mixinResult.size()]);
	}

	public RubyMixinVariable[] getFields() {
		return new RubyMixinVariable[0];
	}


}
