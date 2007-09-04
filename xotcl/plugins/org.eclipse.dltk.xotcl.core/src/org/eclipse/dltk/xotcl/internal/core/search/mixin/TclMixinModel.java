package org.eclipse.dltk.xotcl.internal.core.search.mixin;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.mixin.MixinModel.IMixinObjectInitializeListener;
import org.eclipse.dltk.xotcl.internal.core.XOTclLanguageToolkit;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.model.ITclMixinElement;

public class TclMixinModel {

	private static TclMixinModel instance;

	public static TclMixinModel getInstance() {
		if (instance == null)
			instance = new TclMixinModel();
		return instance;
	}

	public static MixinModel getRawInstance() {
		return getInstance().getRawModel();
	}

	public MixinModel getRawModel() {
		return model;
	}

	private MixinModel model;
	private TclMixinModel() {
		model = new MixinModel(XOTclLanguageToolkit.getDefault());
		model.addObjectInitializeListener(new IMixinObjectInitializeListener() {
			public void initialize(IMixinElement element, Object object,
					ISourceModule module) {
				if(object != null && object instanceof ITclMixinElement ) {
					((ITclMixinElement)object).initialize(element, module, TclMixinModel.this);
				}
			}
		});
	}
	
	public IMixinElement createElement(String key) {
		return model.get(key);
	}
	public IMixinElement[] find( String pattern ) {
		return model.find(pattern);
	}
	
}
