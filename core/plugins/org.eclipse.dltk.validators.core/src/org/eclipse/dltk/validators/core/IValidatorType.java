/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.validators.core;

import java.io.IOException;

import org.w3c.dom.Element;

/**
 * Validator class
 * 
 * @author Haiodo
 * 
 */
public interface IValidatorType {
	/**
	 * Return validator identifier, must be equal to extension point id. Used to
	 * determine validator UI configuration preferences.
	 * 
	 * @return
	 */
	String getID();

	String getNature();
	
	String getName();

	/**
	 * If return true, then this validatorh has UI, and some instances of this
	 * validator could be used. For example external tool validator should
	 * return true here, to support specify external progman and arguments.
	 * 
	 * If return false, then this is always runningm builtin validator. Static checkers could be here.
	 * 
	 * @return
	 */
	boolean isConfigurable();
	
	/**
	 * If true then validators of this type could not be added, edited or removed, only activated or diactivated.
	 * @return
	 */
	boolean isBuiltin();

	IValidator createValidatorFrom(String id, Element validatorElement) throws IOException;
	IValidator createValidator(String id);
	
	IValidator[] getValidators();

	IValidator findValidator(String id);

	void disposeValidator(String id);
}
