package org.eclipse.dltk.validators.core;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractValidatorType implements IValidatorType {
	protected Map validators = new HashMap();
	
	public IValidator[] getValidators() {
		return (IValidator[])validators.values().toArray(new IValidator[validators.size()]);
	}

	public IValidator findValidator(String id) {
		return (IValidator)this.validators.get(id);
	}

	public void disposeValidator(String id) {
		validators.remove(id);
	}
}
