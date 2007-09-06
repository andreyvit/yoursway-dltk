package org.eclipse.dltk.xotcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.TclMixinModel;

public abstract class TclMixinElement implements ITclMixinElement {
	public abstract int getType();

	private IMixinElement mixinElement = null;
	private ISourceModule sourceModule = null;
	private IModelElement modelElement = null;
	private TclMixinModel model = null;

	/**
	 * Search for module model element from specified module.
	 */
	protected IModelElement findElement(ISourceModule module, String key) {
		String[] split = key.split("\\" +IMixinRequestor.MIXIN_NAME_SEPARATOR);
		IParent parent = module;
		int q = 0;
		if( split[0].equals("")) {
			q = 1;
		}
		for (int i = q; i < split.length; i++) {
			try {
				IModelElement[] children = parent.getChildren();
				for (int j = 0; j < children.length; j++) {
					if (children[j].getElementName().equals(split[i])) {
						IModelElement e = children[j];
						if (i == split.length - 1) {
							return e;
						}
						if (e instanceof IParent) {
							parent = (IParent) e;
							break;
						}
					}
				}
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
				return null;
			}
		}
		return null;
	}

	public void initialize(IMixinElement element, ISourceModule module, TclMixinModel model) {
		this.mixinElement = element;
		this.sourceModule = module;
		this.model = model;
	}

	public IModelElement getModelElement() {
		if (this.modelElement == null && this.mixinElement != null
				&& this.sourceModule != null) {
			this.modelElement = findElement(this.sourceModule,
					this.mixinElement.getKey());
		}
		return this.modelElement;
	}

	public String getKey() {
		if( this.mixinElement != null) {
			return this.mixinElement.getKey();
		}
		return null;
	}

	public String getName() {
		IModelElement element = this.getModelElement();
		if( element != null ) {
			return element.getElementName();
		}
		return null;
	}
	protected TclMixinModel getModel() {
		return this.model;
	}
	protected ISourceModule getSourceModule() {
		return this.sourceModule;
	}
	protected IMixinElement getMixinElement() {
		return this.mixinElement;
	}
}
