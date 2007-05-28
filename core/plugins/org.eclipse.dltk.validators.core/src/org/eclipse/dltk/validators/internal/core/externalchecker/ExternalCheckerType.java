package org.eclipse.dltk.validators.internal.core.externalchecker;

import java.io.IOException;
import java.text.MessageFormat;

import org.eclipse.dltk.validators.core.AbstractValidatorType;
import org.eclipse.dltk.validators.core.IValidator;
import org.w3c.dom.Element;

public class ExternalCheckerType extends AbstractValidatorType {

	public final String ID = "org.eclipse.dltk.validators.core.externalChecker";
//	private ExternalChecker checker;

	public ExternalCheckerType() {
//		this.checker = new ExternalChecker(ID, this);
//		this.checker.setName("External Checker");
//		this.validators.put(ID, checker);
	}

	public IValidator createValidator(String id) {
		if (findValidator(id) != null) {
			String format = "Duplicate Validator";
			throw new IllegalArgumentException(MessageFormat.format(format,
					new String[] { id }));
		}
		ExternalChecker externalChecker = new ExternalChecker(id, "", this);
		validators.put(id, externalChecker);
		return externalChecker;
	}

	public IValidator createValidatorFrom(String id, Element validatorElement)
			throws IOException {
		ExternalChecker externalChecker = new ExternalChecker(id,
				validatorElement, this);
		validators.put(id, externalChecker);
		return externalChecker;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return "External Checker";
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
