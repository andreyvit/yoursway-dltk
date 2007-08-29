package org.eclipse.dltk.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * Abstract base class for script project decorators.
 */
public abstract class AbstractScriptProjectDecorator extends LabelProvider
		implements ILightweightLabelDecorator {

	/*
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 *      org.eclipse.jface.viewers.IDecoration)
	 */
	public void decorate(Object element, IDecoration decoration) {
		IProject project = null;
		if (element instanceof IScriptProject) {
			project = ((IScriptProject) element).getProject();
		} else if (element instanceof IProject) {
			project = (IProject) element;
		}

		if (project != null && project.isOpen()) {
			try {
				if (project.hasNature(getNatureId())) {
					decoration.addOverlay(getProjectDecorator());
				}
			} catch (CoreException e) {
				DLTKUIPlugin.log(e);
			}
		}
	}

	/**
	 * Returns the project's nature id
	 */
	protected abstract String getNatureId();

	/**
	 * Returns the image descriptor that should be used to decorate the project
	 */
	protected abstract ImageDescriptor getProjectDecorator();

}
