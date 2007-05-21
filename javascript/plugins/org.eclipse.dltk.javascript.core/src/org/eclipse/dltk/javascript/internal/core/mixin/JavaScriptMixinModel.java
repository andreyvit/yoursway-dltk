/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.javascript.internal.core.mixin;

import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.javascript.core.JavaScriptLanguageToolkit;

public class JavaScriptMixinModel {

	private static JavaScriptMixinModel instance;

	private final MixinModel model;

	public static MixinModel getRawInstance() {
		return getInstance().getRawModel();
	}

	public MixinModel getRawModel() {
		return model;
	}

	private JavaScriptMixinModel() {
		this.model = new MixinModel(JavaScriptLanguageToolkit.getDefault());
	}

	public static synchronized JavaScriptMixinModel getInstance() {
		if (instance == null)
			instance = new JavaScriptMixinModel();
		return instance;
	}

	public String[] findElements(String elements) {
		String[] findKeys = model.findKeys(elements + "*");
		System.out.println(findKeys.length);		
		return findKeys;
	}
}
