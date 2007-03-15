package org.eclipse.dltk.internal.javascript.typeinference;

import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;


public class NewReference extends AbstractCallResultReference {
	
	
	public NewReference(String name, String globalName,ReferenceResolverContext cs) {
		super(name,globalName,cs);		
	}		

	public String getResultId() {
		return "this";
	}	
}
