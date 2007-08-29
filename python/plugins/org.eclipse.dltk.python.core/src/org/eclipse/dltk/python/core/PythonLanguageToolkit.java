/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.core;

import java.io.File;
import java.io.FilenameFilter;
import java.text.MessageFormat;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.ICallProcessor;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.internal.core.util.Messages;

public class PythonLanguageToolkit implements IDLTKLanguageToolkit {
	private static PythonLanguageToolkit sInstance = new PythonLanguageToolkit();
	public PythonLanguageToolkit() {
	}

	public IStatus validateSourceModule(String name) {
		if (name == null) {
			return new Status(IStatus.ERROR, PythonPlugin.PLUGIN_ID, -1,
					Messages.convention_unit_nullName, null);
		}
		if (!isScriptLikeFileName(name)) {
			return new Status(IStatus.ERROR, PythonPlugin.PLUGIN_ID, -1,
					MessageFormat.format(
							Messages.convention_unit_notScriptName,
							new String[] { getPythonExtension(), "Python" }),
					null);
		}
		return IModelStatus.VERIFIED_OK;
	}

	private String getPythonExtension() {
		return "py";
	}

	public boolean isScriptLikeFileName(String name) {
		// TODO: Add more correct checking here.
		if (name.endsWith("." + getPythonExtension())) {
			return true;
		}
		return false;
	}

	public boolean languageSupportZIPBuildpath() {
		return true;
	}

	public boolean validateSourcePackage(IPath path) {
		File file = new File(path.toOSString());
		if (file != null) {
			String members[] = file.list(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					if (name.toLowerCase().equals("__init__.py")) {
						return true;
					}
					return false;
				}
			});
			if (members.length > 0) {
				return true;
			}
		}
		return false;
	}

	public String getNatureId() {
		return PythonNature.NATURE_ID;
	}

	public IStatus validateSourceModule(IResource resource) {
		return validateSourceModule(resource.getName());
	}

	public IStatus validateSourceModule(IPath resource) {
		return validateSourceModule(resource.lastSegment());
	}

	public IStatus validateSourceModule(IModelElement parent, String name) {
		return validateSourceModule(name);
	}
	
	public IStatus validateSourceModuleName(String str) {
		return validateSourceModule(str);
	}

	public static IDLTKLanguageToolkit getDefault() {
		return sInstance;
	}

	public String getDelimeterReplacerString() {
		return ".";
	}

	public ICallProcessor createCallProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getLanguageFileExtensions() {		
		return new String[] {"py"};
	}

	public String getLanguageName()
	{
		return "Python";
	}	
}
