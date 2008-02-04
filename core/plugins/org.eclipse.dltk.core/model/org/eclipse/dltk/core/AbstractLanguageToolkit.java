/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

import org.eclipse.core.runtime.IPath;

public abstract class AbstractLanguageToolkit implements IDLTKLanguageToolkit {
	public AbstractLanguageToolkit() {
	}
	
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
}
