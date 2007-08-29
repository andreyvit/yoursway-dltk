package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.ruby.core.RubyNature;
import org.eclipse.dltk.ui.AbstractScriptProjectDecorator;
import org.eclipse.jface.resource.ImageDescriptor;

public class RubyProjectDecorator extends AbstractScriptProjectDecorator {

	public String getNatureId() {
		return RubyNature.NATURE_ID;
	}
	
	public ImageDescriptor getProjectDecorator() {
		return RubyImages.PROJECT_DECARATOR;
	}

}