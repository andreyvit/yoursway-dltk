package org.eclipse.dltk.tcl.internal.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;

public class TclProjectDecorator extends LabelProvider implements
		ILightweightLabelDecorator {

	public void decorate(Object element, IDecoration decoration) {
		IProject project = null;
		if (element instanceof IDLTKProject) {
			project = ((IDLTKProject) element).getProject();
		} else if (element instanceof IProject) {
			project = (IProject) element;
		}

		if (project != null) {
			try {
				if (project.hasNature(TclNature.NATURE_ID)) {
					decoration.addOverlay(TclImages.PROJECT_DECARATOR);
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}