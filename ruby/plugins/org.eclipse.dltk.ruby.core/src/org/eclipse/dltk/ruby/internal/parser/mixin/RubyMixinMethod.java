/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;

public class RubyMixinMethod implements IRubyMixinElement {

	private final String key;
	private final RubyMixinModel model;
	private IMethod[] sourceMethods;

	public RubyMixinMethod(RubyMixinModel model, String key) {
		super();
		this.model = model;
		this.key = key;
	}

	public String getName() {
		return key.substring(key.lastIndexOf(MixinModel.SEPARATOR) + 1);
	}

	public RubyMixinMethod(RubyMixinModel model, String key,
			IMethod[] sourceMethods) {
		super();
		this.model = model;
		this.key = key;
		this.sourceMethods = sourceMethods;
	}

	public String getKey() {
		return key;
	}

	public RubyMixinClass getSelfType() {
		IMixinElement mixinElement = model.getRawModel().get(key);
		IMixinElement parent = mixinElement.getParent();
		if (parent == null)
			return new RubyObjectMixinClass(model, true);
		IRubyMixinElement rubyParent = model.createRubyElement(parent);
		if (rubyParent instanceof RubyMixinClass) {
			return (RubyMixinClass) rubyParent;
		}
		return null;
	}

	/**
	 * Allows to set precalculated source methods and not use mixin model to
	 * find them.
	 */
	public void setSourceMethods(IMethod[] sourceMethods) {
		this.sourceMethods = sourceMethods;
	}

	/**
	 * Returns model elements for this method. If they were previously saved
	 * using setSourceMethods() or constructor, then exactly they are returned.
	 * Else mixin model are used.
	 */
	public IMethod[] getSourceMethods() {
		if (this.sourceMethods != null)
			return sourceMethods;		
		List result = new ArrayList();
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		for (int i = 0; i < allObjects.length; i++) {
			RubyMixinElementInfo info = (RubyMixinElementInfo) allObjects[i];
			if (info.getKind() == RubyMixinElementInfo.K_METHOD) {
				result.add(info.getObject());
			}
		}
		return (IMethod[]) result.toArray(new IMethod[result.size()]);
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
