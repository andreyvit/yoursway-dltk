/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.ISourceModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class AbstractValidator implements IValidator {
	private String id;
	private String name;
	private IValidatorType type;
	private boolean active = true;

	protected AbstractValidator(String id, String name, IValidatorType type) {
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
		this.active = (new Boolean(element.getAttribute("active")))
				.booleanValue();
	}

	public void storeTo(Document doc, Element element) {
		element.setAttribute("name", getName()); //$NON-NLS-1$
		element.setAttribute("active", Boolean.toString(isActive()));
	}

	public void clean(IResource resource) {
	}

	public void clean(ISourceModule module) {
	}

	public boolean isValidatorValid() {
		return false;
	}

	public IValidatorType getValidatorType() {
		return this.type;
	}

	protected void setID(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
