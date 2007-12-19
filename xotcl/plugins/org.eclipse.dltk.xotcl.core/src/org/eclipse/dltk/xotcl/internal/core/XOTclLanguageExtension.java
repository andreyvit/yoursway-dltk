package org.eclipse.dltk.xotcl.internal.core;

import org.eclipse.dltk.tcl.core.extensions.ICompletionExtension;
import org.eclipse.dltk.tcl.core.extensions.IMatchLocatorExtension;
import org.eclipse.dltk.tcl.core.extensions.IMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ISelectionExtension;
import org.eclipse.dltk.tcl.core.extensions.ISourceElementRequestVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ITclLanguageExtension;
import org.eclipse.dltk.xotcl.internal.core.parser.XOTclSourceElementRequestVisitorExtension;
import org.eclipse.dltk.xotcl.internal.core.search.XOTclMatchLocatorExtension;
import org.eclipse.dltk.xotcl.internal.core.search.mixin.XOTclMixinBuildVisitorExtension;

public class XOTclLanguageExtension implements ITclLanguageExtension {

	private static final String[] XOTCL_EXTENSIONS = new String[]{ "xotcl" };

	public XOTclLanguageExtension() {
	}

	public String getName() {
		return "XOTcl";
	}

	public ISourceElementRequestVisitorExtension createSourceElementRequestVisitorExtension() {
		return new XOTclSourceElementRequestVisitorExtension();
	}

	public String[] getFileExtensions() {
		return XOTCL_EXTENSIONS;
	}

	public IMixinBuildVisitorExtension createMixinBuildVisitorExtension() {
		return new XOTclMixinBuildVisitorExtension();
	}

	public IMatchLocatorExtension createMatchLocatorExtension() {
		return new XOTclMatchLocatorExtension();
	}

	public ICompletionExtension createCompletionExtension() {
		return new XOTclCompletionExtension();
	}

	public ISelectionExtension createSelectionExtension() {
		return new XOTclSelectionExtension();
	}
}
