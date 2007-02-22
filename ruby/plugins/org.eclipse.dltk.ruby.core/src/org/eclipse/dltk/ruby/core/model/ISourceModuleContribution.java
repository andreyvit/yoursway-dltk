package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.core.ISourceModule;

public interface ISourceModuleContribution {
	
	ISourceModule getSourceModule();	
	
	IMethod[] getConributedMethods ();
	
	IVariable[] getConributedVariables ();
	
	ITypeFragment[] getContributedFragments ();
	
}
