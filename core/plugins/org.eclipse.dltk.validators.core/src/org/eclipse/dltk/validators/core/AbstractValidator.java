package org.eclipse.dltk.validators.core;


public abstract class AbstractValidator implements IValidator {
	private String id;
	private String name;
	private IValidatorType type;
	boolean active = true;
	
	protected AbstractValidator(String id, String name, IValidatorType type ) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public IValidatorType getValidatorType() {
		return this.type;
	}
	protected void setID(String id ) {
		this.id = id;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}	
}
