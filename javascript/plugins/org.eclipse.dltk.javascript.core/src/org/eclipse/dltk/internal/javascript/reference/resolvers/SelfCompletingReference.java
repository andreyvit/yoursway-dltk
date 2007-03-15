package org.eclipse.dltk.internal.javascript.reference.resolvers;

import org.eclipse.dltk.internal.javascript.typeinference.IReference;

public interface SelfCompletingReference extends IReference{

	

	int getKind();
	
	char[] getSignature();
	
	char[] getDeclarationSignature();

	char[][] getParameterNames();

	
}
