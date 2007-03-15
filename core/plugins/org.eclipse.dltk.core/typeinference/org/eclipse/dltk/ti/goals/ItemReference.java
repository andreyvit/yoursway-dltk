package org.eclipse.dltk.ti.goals;

public abstract class ItemReference {
	
	private final String name;
	private final Object parentModelKey;
	
	public ItemReference(String name, Object parentModelKey) {
		super();
		this.name = name;
		this.parentModelKey = parentModelKey;
	}
	public String getName() {
		return name;
	}
	public Object getParentModelKey() {
		return parentModelKey;
	}
	
	
	
}
