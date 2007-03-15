package org.eclipse.dltk.internal.javascript.typeinference;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.javascript.reference.resolvers.IResolvableReference;
import org.eclipse.dltk.internal.javascript.reference.resolvers.ReferenceResolverContext;

public abstract class AbstractCallResultReference implements IReference,
		IDoNotReportChilds , IResolvableReference{

	public Set getChildsNoGlobalOp() {
		return hashSet==null?new HashSet():hashSet;
	}

	private String name;

	private Set hashSet;

	private String id;
	
	private ReferenceResolverContext cs;

	public IReference getChild(String key, boolean resolveLocals) {
		if (!resolveLocals)return null;
		if (hashSet == null)
			getChilds(resolveLocals);
		if (hashSet==null)return null;
		Iterator i = hashSet.iterator();
		while (i.hasNext()) {
			IReference r = (IReference) i.next();
			if (r.getName().equals(key))
				return r;
		}
		return null;
	}

	public IReference getPrototype(boolean resolveLocals){
		return this.getChild("prototype", resolveLocals);
	}

	private static HashSet searchIds = new HashSet();

	public Set getChilds(boolean resolveLocals) {
		if (!resolveLocals)return new HashSet();
		if (searchIds.contains(id)) return new HashSet();
		if (this.hashSet != null)
			return hashSet;
		try {			
			searchIds.add(id);
			internalGetChilds(resolveLocals);		
		} finally {
			searchIds.remove(id);
		}
		return hashSet;
	}

	private void internalGetChilds(boolean resolveLocals) {
		this.hashSet=cs.resolveChilds(this);
	}

	public abstract String getResultId();

	
	public String getName() {
		return name;
	}

	
	public AbstractCallResultReference(String name, String id2,ReferenceResolverContext cs) {
		super();
		this.name = name;
		this.id = id2;
		this.cs=cs;
	}

	public boolean isChildishReference() {
		return false;
	}

	public void recordDelete(String fieldId) {
	}

	public void setChild(String key, IReference ref) {
	}

	public void setPrototype(IReference ref) {
	}
	
	public void addModelElements(Collection toAdd) {
		
	}
	public void setLocationInformation(ModelElement mo, int position, int length) {
		
	}

	public String getId() {
		return id;
	}
}
