package org.eclipse.dltk.tcl.internal.core.search.mixin.model;

import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IParent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.mixin.IMixinElement;
import org.eclipse.dltk.core.mixin.IMixinRequestor;
import org.eclipse.dltk.tcl.internal.core.search.mixin.TclMixinModel;

public abstract class TclMixinElement implements ITclMixinElement {
	public abstract int getType();

	private IMixinElement mixinElement = null;
	private ISourceModule sourceModule = null;
	private IModelElement modelElement = null;
	private TclMixinModel model = null;

	protected abstract boolean isValidModelElement(IModelElement element);

	/**
	 * Search for module model element from specified module.
	 */
	protected IModelElement findElement(ISourceModule module, String key) {
		String[] split = key.split("\\" + IMixinRequestor.MIXIN_NAME_SEPARATOR);
		IParent parent = module;
		int q = 0;
		if (split[0].equals("")) {
			q = 1;
		}
		for (int i = q; i < split.length; i++) {
			try {
				IModelElement[] children = parent.getChildren();
				IModelElement e = findElementInChildren(split, parent, i,
						children);
				if (e != null) {
					return e;
				}
			} catch (ModelException e) {
				if (DLTKCore.DEBUG) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private IModelElement findElementInChildren(String[] split, IParent parent,
			int i, IModelElement[] children) {
		if (i >= split.length) {
			return null;
		}
		for (int j = 0; j < children.length; j++) {
			if (children[j].getElementName().equals(split[i])) {
				IModelElement e = children[j];
				if (i == split.length - 1
						&& isValidModelElement(e)) {
					return e;
				}
				if (e instanceof IParent) {
					IParent pa = (IParent) e;
					IModelElement e2 = null;
					try {
						e2 = findElementInChildren(split, pa, i + 1, pa
								.getChildren());
					} catch (ModelException e1) {
						if (DLTKCore.DEBUG) {
							e1.printStackTrace();
						}
					}
					if (e2 != null) {
						return e2;
					}
				}
			}
		}
		return null;
	}

	public void initialize(IMixinElement element, ISourceModule module,
			TclMixinModel model) {
		this.mixinElement = element;
		this.sourceModule = module;
		this.model = model;
	}

	public IModelElement getModelElement() {
		if (this.model != null && this.modelElement == null
				&& this.mixinElement != null && this.sourceModule != null) {
			this.modelElement = findElement(this.sourceModule,
					this.mixinElement.getKey());
		}
		return this.modelElement;
	}

	public String getKey() {
		if (this.mixinElement != null) {
			return this.mixinElement.getKey();
		}
		return null;
	}

	public String getName() {
		IModelElement element = this.getModelElement();
		if (element != null) {
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mixinElement == null) ? 0 : mixinElement.hashCode());
		result = prime * result
				+ ((sourceModule == null) ? 0 : sourceModule.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TclMixinElement other = (TclMixinElement) obj;
		if (mixinElement == null) {
			if (other.mixinElement != null)
				return false;
		} else if (!mixinElement.equals(other.mixinElement))
			return false;
		if (sourceModule == null) {
			if (other.sourceModule != null)
				return false;
		} else if (!sourceModule.equals(other.sourceModule))
			return false;
		return true;
	}
}
