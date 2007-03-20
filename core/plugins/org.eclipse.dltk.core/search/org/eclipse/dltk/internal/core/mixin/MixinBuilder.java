package org.eclipse.dltk.internal.core.mixin;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.builder.IScriptBuilder;

public class MixinBuilder implements IScriptBuilder {

	public IStatus buildResources(IResource res) {
		return null;
	}

	public List getDependencies(List resources) {
		return null;
	}

	public IStatus buildModelElement(IModelElement element) {
		return null;
	}
}
