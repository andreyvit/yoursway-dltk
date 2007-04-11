package org.eclipse.dltk.ruby.internal.parser.mixin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementVisitor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.MixinModel;
import org.eclipse.dltk.internal.core.BuiltinProjectFragment;
import org.eclipse.dltk.ruby.core.RubyLanguageToolkit;
import org.eclipse.dltk.ruby.typeinference.RubyClassType;

public class RubyMixinModel {
	
	private static RubyMixinModel instance;

	public static RubyMixinModel getInstance () {
		if (instance == null)
			instance = new RubyMixinModel ();
		return instance;
	}
	
	public static MixinModel getRawInstance () {
		return getInstance().getRawModel();
	}

	private final MixinModel model;

	private RubyMixinModel() {
		model = new MixinModel(RubyLanguageToolkit.getDefault());
	}

	public MixinModel getRawModel() {
		return model;
	}

	public RubyMixinClass createRubyClass(RubyClassType type) {
		return (RubyMixinClass) createRubyElement(type.getModelKey());
	}

	public IRubyMixinElement createRubyElement(String key) {
		if (key.equals("Object")) {
			return new RubyObjectMixinClass(this, true);
		} else if (key.equals("Object%")) {
			return new RubyObjectMixinClass(this, false);
		}
		IMixinElement mixinElement = model.get(key);
		System.out.println();
		if (mixinElement != null) {
			return createRubyElement(mixinElement);
		}
		return null;
	}

	public IRubyMixinElement createRubyElement(IMixinElement element) {
		Assert.isLegal(element != null);
		if (element.getKey().equals("Object")) {
			return new RubyObjectMixinClass(this, true);
		} else if (element.getKey().equals("Object%")) {
			return new RubyObjectMixinClass(this, false);
		}		
		Object[] objects = element.getAllObjects();
		if (objects == null)
			return null;
		for (int i = 0; i < objects.length; i++) {
			RubyMixinElementInfo obj = (RubyMixinElementInfo) objects[i];
			if (obj == null)
				continue;
			switch (obj.getKind()) {
				case RubyMixinElementInfo.K_CLASS:
					return new RubyMixinClass(this, element.getKey(), false);
				case RubyMixinElementInfo.K_MODULE:
					return new RubyMixinClass(this, element.getKey(), true);
				case RubyMixinElementInfo.K_METHOD:
					return new RubyMixinMethod(this, element.getKey());
				case RubyMixinElementInfo.K_VARIABLE:
					return new RubyMixinVariable(this, element.getKey());
			}						
		}
		return null;
	}
	// Testing purpose only.
	private static void preBuildMixinModelForBuiltint(IDLTKProject project, IProgressMonitor monitor ) {
		try {
			final MixinModel model = getRawInstance();
			final List modules = new ArrayList();
			project.accept( new IModelElementVisitor() {
				public boolean visit(IModelElement element) {
					if( element.getElementType() == IModelElement.PROJECT_FRAGMENT ) {
						//Ignore not builtin project fragments.
//						if( element instanceof BuiltinProjectFragment) {
//							return true;
//						}
						return true;
					}
					if( element.getElementType() == IModelElement.SOURCE_MODULE) {
//						if( ((ISourceModule)element).isBuiltin() ) {
							modules.add(element);
//						}
						return false;
					}
					return true;
				}
			});
			monitor.beginTask("Ruby builtin precaching...", modules.size());
			model.setRemovesToZero();
			for (int i = 0; i < modules.size(); i++) {
				monitor.worked(1);
				model.reportModule((ISourceModule)modules.get(i));
				if( monitor.isCanceled() ) {
					return;
				}
			}
			model.makeAllElementsFinalIfNoCacheRemoves();
			monitor.done();
		} catch (ModelException e) {
			e.printStackTrace();
		}
	}
}
