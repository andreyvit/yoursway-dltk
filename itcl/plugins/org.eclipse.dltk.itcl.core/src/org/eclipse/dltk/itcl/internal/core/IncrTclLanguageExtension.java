package org.eclipse.dltk.itcl.internal.core;

import org.eclipse.dltk.tcl.core.extensions.ICompletionExtension;
import org.eclipse.dltk.tcl.core.extensions.IMatchLocatorExtension;
import org.eclipse.dltk.tcl.core.extensions.IMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ISelectionExtension;
import org.eclipse.dltk.tcl.core.extensions.ISourceElementRequestVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ITclLanguageExtension;

public class IncrTclLanguageExtension implements ITclLanguageExtension {

	private static String[] extensions = new String[] {"itcl", "itk"};

	public IncrTclLanguageExtension() {
	}

	public ICompletionExtension createCompletionExtension() {
		return null;
	}

	public IMatchLocatorExtension createMatchLocatorExtension() {
		return null;
	}

	public IMixinBuildVisitorExtension createMixinBuildVisitorExtension() {
		return null;
	}

	public ISelectionExtension createSelectionExtension() {
		return null;
	}

	public ISourceElementRequestVisitorExtension createSourceElementRequestVisitorExtension() {
		return null;
	}

	public String[] getFileExtensions() {
		return extensions;
	}

	public String getName() {
		return "Itcl";
	}

}
