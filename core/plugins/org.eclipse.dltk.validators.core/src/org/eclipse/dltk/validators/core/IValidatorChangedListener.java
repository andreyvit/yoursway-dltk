package org.eclipse.dltk.validators.core;

public interface IValidatorChangedListener {
	public void validatorChanged(IValidator validator);	
	public void validatorAdded(IValidator validator);		
	public void validatorRemoved(IValidator validator);				
}
