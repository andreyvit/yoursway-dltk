package org.eclipse.dltk.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;


public interface IDLTKLanguageToolkit {

	/*
	 * Validation of language toolkit resources
	 */

	IStatus validateSourceModule(IResource resource);

	IStatus validateSourceModule(IPath path);

	IStatus validateSourceModuleName(String name);

	boolean validateSourcePackage(IPath path);

	/*
	 * Different stuff
	 */
	
	boolean languageSupportZIPBuildpath();

	String getNatureID();

	String getDelimeterReplacerString();
	
	String[] getLanguageFileExtensions();
	
	String getLanguageName();
}