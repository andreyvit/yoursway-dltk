package org.eclipse.dltk.validators.internal.ui;

import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.ui.actions.IActionFilterTester;
import org.eclipse.dltk.validators.core.IValidatorType;
import org.eclipse.dltk.validators.core.ValidatorRuntime;

public class ValidatorsVisibilityTester implements IActionFilterTester {

	public ValidatorsVisibilityTester() {
	}

	public boolean test(Object target, String name, String value) {
		if( target instanceof IModelElement ) {
			IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager.getLanguageToolkit((IModelElement)target);
			if( languageToolkit != null ) {
				IValidatorType[] validatorTypes = ValidatorRuntime.getValidatorTypes(languageToolkit.getNatureId());
				if( validatorTypes != null && validatorTypes.length > 0 ) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
