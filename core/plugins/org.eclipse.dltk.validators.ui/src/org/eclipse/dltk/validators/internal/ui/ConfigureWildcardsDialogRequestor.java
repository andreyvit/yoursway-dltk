package org.eclipse.dltk.validators.internal.ui;

import org.eclipse.dltk.validators.core.IValidator;

public interface ConfigureWildcardsDialogRequestor {
	public boolean isDuplicateName(String name);
	public void validatorAdded(IValidator validator);

}
