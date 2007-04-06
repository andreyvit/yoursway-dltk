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
