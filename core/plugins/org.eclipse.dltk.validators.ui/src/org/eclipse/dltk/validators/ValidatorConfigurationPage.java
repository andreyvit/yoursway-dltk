/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators;

import org.eclipse.dltk.validators.core.IValidator;
import org.eclipse.swt.widgets.Composite;

public abstract class ValidatorConfigurationPage {
	protected IValidator validator;
	public void setValidator(IValidator validator) {
		this.validator = validator;
	}
	public IValidator getValidator() {
		return this.validator;
	}
	public abstract void applyChanges();
	public abstract void createControl( Composite parent, int columns );
}
