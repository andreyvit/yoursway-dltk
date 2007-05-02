/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.tclchecker;

import java.io.IOException;

import org.eclipse.dltk.validators.core.AbstractValidatorType;
import org.eclipse.dltk.validators.core.IValidator;
import org.w3c.dom.Element;

public class TclCheckerType extends AbstractValidatorType {
	private static final String SCRIPT_CHECKER_ID = "Tcl Checker";
	private TclCheckerImpl checker;
	public TclCheckerType() {
		this.checker = new TclCheckerImpl(SCRIPT_CHECKER_ID, this);
		this.checker.setName("Tcl Checker");
		this.validators.put(SCRIPT_CHECKER_ID, checker);
	}
	public IValidator createValidator(String id) {
//		return new ScriptChecker(id, this);
		return null;
	}

	public IValidator createValidatorFrom(String id, Element validatorElement)
			throws IOException {
//		return new ScriptChecker(id, validatorElement, this);
//		checker.loadInfo(validatorElement);
		return checker;
	}

	public String getID() {
		return "org.eclipse.dltk.tclchecker";
	}

	public String getName() {
		return "Tcl Checker Type";
	}

	public String getNature() {
		return "org.eclipse.dltk.tcl.core.nature";
	}

	public boolean isConfigurable() {
		return true;
	}

	public boolean isBuiltin() {
		return true;
	}
}
