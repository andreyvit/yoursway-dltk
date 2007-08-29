package org.eclipse.dltk.tcl.internal.ui;

import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.ui.AbstractScriptProjectDecorator;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Tcl project decorator
 */
public class TclProjectDecorator extends AbstractScriptProjectDecorator {

	/*
	 * @see org.eclipse.dltk.ui.AbstractScriptProjectDecorator#getNatureId()
	 */
	protected String getNatureId() {
		return TclNature.NATURE_ID;
	}

	/*
	 * @see org.eclipse.dltk.ui.AbstractScriptProjectDecorator#getProjectDecorator()
	 */
	protected ImageDescriptor getProjectDecorator() {
		return TclImages.PROJECT_DECARATOR;
	}
}