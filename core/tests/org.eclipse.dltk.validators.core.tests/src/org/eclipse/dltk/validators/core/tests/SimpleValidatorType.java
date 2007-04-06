package org.eclipse.dltk.validators.core.tests;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.validators.core.AbstractValidatorType;
import org.eclipse.dltk.validators.core.IValidator;
import org.w3c.dom.Element;

public class SimpleValidatorType extends AbstractValidatorType {
	public final String ID = "org.eclipse.dltk.validators.core.tests.simpleValidator";
	
	public IValidator createValidator(String id) {
		SimpleValidator simpleValidator = new SimpleValidator(id, this);
		validators.put(id, simpleValidator);
		return simpleValidator;
	}

	public IValidator createValidatorFrom(String id, Element validatorElement) throws IOException {
		SimpleValidator simpleValidator = new SimpleValidator(id, validatorElement, this);
		validators.put(id, simpleValidator);
		return simpleValidator;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return "Simple Test Validator";
	}

	public String getNature() {
		return "#";
	}

	public boolean isConfigurable() {
		return true;
	}

	public boolean isBuiltin() {
		return false;
	}
}