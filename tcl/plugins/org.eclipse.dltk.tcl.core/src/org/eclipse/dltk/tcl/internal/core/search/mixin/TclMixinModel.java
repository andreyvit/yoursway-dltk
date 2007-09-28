package org.eclipse.dltk.tcl.internal.core.search.mixin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.core.mixin.MixinModel.IMixinObjectInitializeListener;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.core.search.mixin.model.ITclMixinElement;

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
		try {
			model = new MixinModel(DLTKLanguageManager
					.getLanguageToolkit(TclNature.NATURE_ID));
			model
					.addObjectInitializeListener(new IMixinObjectInitializeListener() {
						public void initialize(IMixinElement element,
								Object object, ISourceModule module) {
							if (object != null
									&& object instanceof ITclMixinElement) {
								((ITclMixinElement) object).initialize(element,
										module, TclMixinModel.this);
							}
						}
					});
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	public IMixinElement createElement(String key) {
		return model.get(key);
	}

	public IMixinElement[] find(String pattern) {
		return model.find(pattern);
	}

}
