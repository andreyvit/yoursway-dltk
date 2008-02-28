package org.eclipse.dltk.itcl.internal.core;

import org.eclipse.dltk.itcl.internal.core.parser.IncrTclSourceElementRequestVisitorExtension;
import org.eclipse.dltk.itcl.internal.core.search.IncrTclMatchLocatorExtension;
import org.eclipse.dltk.itcl.internal.core.search.IncrTclSelectionExtension;
import org.eclipse.dltk.itcl.internal.core.search.mixin.IncrTclMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ICompletionExtension;
import org.eclipse.dltk.tcl.core.extensions.IMatchLocatorExtension;
import org.eclipse.dltk.tcl.core.extensions.IMixinBuildVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ISelectionExtension;
import org.eclipse.dltk.tcl.core.extensions.ISourceElementRequestVisitorExtension;
import org.eclipse.dltk.tcl.core.extensions.ITclLanguageExtension;

public class IncrTclLanguageExtension implements ITclLanguageExtension {

	public IncrTclLanguageExtension() {
	}

	public ICompletionExtension createCompletionExtension() {
		return new IncrTclCompletionExtension();
	}

	public IMatchLocatorExtension createMatchLocatorExtension() {
		return new IncrTclMatchLocatorExtension();
	}

	public IMixinBuildVisitorExtension createMixinBuildVisitorExtension() {
		return new IncrTclMixinBuildVisitorExtension();
	}

	public ISelectionExtension createSelectionExtension() {
		return new IncrTclSelectionExtension();
	}

	public ISourceElementRequestVisitorExtension createSourceElementRequestVisitorExtension() {
		return new IncrTclSourceElementRequestVisitorExtension();
	}

	public String getName() {
		return "Itcl";
	}

}
