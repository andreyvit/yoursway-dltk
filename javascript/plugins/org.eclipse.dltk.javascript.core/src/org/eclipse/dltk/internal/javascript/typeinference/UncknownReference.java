package org.eclipse.dltk.internal.javascript.typeinference;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.dltk.internal.core.ModelElement;

public class UncknownReference implements IReference {

	private String name;
	private final boolean childIsh;
	
	protected ModelElement parent;
	private int offset;
	private int length;
	
	public UncknownReference(String paramOrVarName, boolean childIsh) {
		this.name = paramOrVarName;
		this.childIsh = childIsh;
		
	}
	
	public void setLocationInformation(ModelElement parent,int offset,int length){
		this.parent=parent;
		this.length=length;
		this.offset=offset;
	}

	public Set getChilds(boolean resolveLocals) {
		if (childs == null)
			return new HashSet(1);
		return new HashSet(childs.values());
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public String getParentName() {
		return name;
	}

	HashMap childs;

	public IReference getChild(String key, boolean resolveLocals) {
		if (childs == null)
			return null;
		IReference r = (IReference) childs.get(key);
		return r;
	}

	public void setChild(String key, IReference ref) {
		if (childs == null)
			childs = new HashMap();
		childs.put(key, ref);
	}

	public boolean isChildishReference() {
		return childIsh;
	}

	public void recordDelete(String fieldId) {
		if (childs == null)
			return;
		childs.remove(fieldId);
	}

	public IReference getPrototype(boolean resolveLocals) {
		return getChild("prototype", true);
	}

	public void setPrototype(IReference ref) {
		this.setChild("prototype", ref);
	}

	public void addModelElements(Collection toAdd) {
		if (parent!=null)
		toAdd.add(new FakeField( parent,name,offset,length));
	}
	

}
