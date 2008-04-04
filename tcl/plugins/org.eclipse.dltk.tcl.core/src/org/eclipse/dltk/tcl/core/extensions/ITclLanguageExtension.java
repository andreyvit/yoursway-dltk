package org.eclipse.dltk.tcl.core.extensions;


/**
 * Interface used to provide tcl langauge extensions, such itcl, xotcl, etc.
 * @author haiodo
 */
public interface ITclLanguageExtension {
	String getName();
		
	ISourceElementRequestVisitorExtension createSourceElementRequestVisitorExtension();
	
	IMixinBuildVisitorExtension createMixinBuildVisitorExtension();
	
	IMatchLocatorExtension createMatchLocatorExtension();
	
	ICompletionExtension createCompletionExtension();
	ISelectionExtension createSelectionExtension();
}
