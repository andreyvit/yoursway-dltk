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

import org.eclipse.dltk.core.IField;
import org.eclipse.dltk.core.mixin.IMixinElement;

public class RubyMixinVariable implements IRubyMixinElement {

	private final String key;
	private final RubyMixinModel model;

	public RubyMixinVariable(RubyMixinModel model, String key) {
		super();
		this.model = model;
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public IField[] getSourceFields () {
		List result = new ArrayList ();
		IMixinElement mixinElement = model.getRawModel().get(key);
		Object[] allObjects = mixinElement.getAllObjects();
		for (int i = 0; i < allObjects.length; i++) {
			RubyMixinElementInfo info = (RubyMixinElementInfo) allObjects[i];
			if (info.getKind() == RubyMixinElementInfo.K_VARIABLE) {
				result.add (info.getObject());							
			}
		}
		return (IField[]) result.toArray(new IField[result.size()]);
	}
	

}
