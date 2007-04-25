package org.eclipse.dltk.ti.goals;

public abstract class ItemReference {
	
	private final String name;
	private final String parentModelKey;
	private final PossiblePosition position;
	
	public ItemReference(String name, String parentModelKey, PossiblePosition pos) {
		super();
		this.name = name;
		this.parentModelKey = parentModelKey;
		position = pos;
	}
	
	public String getName() {
		return name;
	}
	
	public String getParentModelKey() {
		return parentModelKey;
	}
	
	public PossiblePosition getPosition() {
		return position;
	}
	
	
	
}
