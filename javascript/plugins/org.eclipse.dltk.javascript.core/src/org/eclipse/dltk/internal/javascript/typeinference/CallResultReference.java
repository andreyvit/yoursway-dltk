package org.eclipse.dltk.internal.javascript.typeinference;

import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;


public class CallResultReference extends AbstractCallResultReference {

	protected IReference root;
	
	public CallResultReference(HostCollection collection, String key, String id,ReferenceResolverContext cs) 
	{
		super(key,id, cs);
		int pm=id.lastIndexOf('.');
		if (pm!=-1){
			String root=id.substring(0,pm);
			this.root=collection.getReference(root);
			
		}
	}
	
	public IReference getRoot(){
		return root;
	}


	public String getResultId() {
		return "!!!returnValue";
	}
}
