/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.internal.core.util.Messages;

public abstract class AbstractLanguageToolkit implements IDLTKLanguageToolkit {
	public AbstractLanguageToolkit() {
	}

	protected abstract String getCorePluginID();


	public boolean languageSupportZIPBuildpath() {
		return false;
	}

	public boolean validateSourcePackage(IPath path) {
		return true;
	}

	public String getDelimeterReplacerString() {
		return ".";
	}

	public IType[] getParentTypes(IType type) {
		return null;
	}


	protected Status createNotScriptFileStatus() {
		return new Status(IStatus.ERROR, getCorePluginID(), -1, MessageFormat
				.format(Messages.convention_unit_notScriptName, new String[] {
						getLanguageFileExtensions()[0].toString(), "Tcl" }),
				null);
	}
}
