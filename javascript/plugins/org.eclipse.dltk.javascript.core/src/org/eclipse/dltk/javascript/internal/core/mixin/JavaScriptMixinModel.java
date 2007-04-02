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

	public static JavaScriptMixinModel getInstance() {
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
