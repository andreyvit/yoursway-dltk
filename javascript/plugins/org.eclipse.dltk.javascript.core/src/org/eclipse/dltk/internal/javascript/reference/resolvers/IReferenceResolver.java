package org.eclipse.dltk.internal.javascript.reference.resolvers;

import java.util.Set;

import org.eclipse.dltk.core.ISourceModule;

public interface IReferenceResolver {

	boolean canResolve(ISourceModule module);

	public Set getChilds(IResolvableReference ref);

	public Set resolveGlobals(String id);

	void processCall(String call, String objId);
	
	void init(ReferenceResolverContext owner);
	
	
}
