package org.eclipse.dltk.validators.core;

import java.io.OutputStream;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.dltk.core.ISourceModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Contains validator properties.
 * @author Haiodo
 *
 */
public interface IValidator {
	String getID();
	String getName();
	void setName(String name);
	IValidatorType getValidatorType();
	boolean isValidatorValid();
	
	//Per-resouce operations
	// If console is non null then output to console are possible.
	IStatus validate(ISourceModule module, OutputStream console );
	IStatus validate(IResource resource, OutputStream console );
	
	// Used to store information into
	void storeTo(Document doc, Element element);
	
	boolean isActive();
	void setActive(boolean active);
}
