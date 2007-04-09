package org.eclipse.dltk.validators.core;

import java.io.OutputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.ISourceModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public abstract class AbstractValidator implements IValidator {
	private String id;
	private String name;
	private IValidatorType type;
	private boolean active = true;
	
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
	
	protected void loadFrom(Element element) {
		this.name = element.getAttribute("name"); //$NON-NLS-1$
		this.active = (new Boolean(element
				.getAttribute("active"))).booleanValue();
	}
	
	public void storeTo(Document doc, Element element) {
		element.setAttribute("name", getName()); //$NON-NLS-1$
		element.setAttribute("active", Boolean.toString(isActive()));
	}

	public void clean(IResource resource) {
		// TODO Auto-generated method stub
		
	}
	public void clean(ISourceModule module) {
		// TODO Auto-generated method stub
		
	}
	public boolean isValidatorValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public IStatus validate(IResource resource, OutputStream console) {
		// TODO Auto-generated method stub
		return null;
	}
	public IStatus validate(ISourceModule module, OutputStream console) {
		// TODO Auto-generated method stub
		return null;
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
