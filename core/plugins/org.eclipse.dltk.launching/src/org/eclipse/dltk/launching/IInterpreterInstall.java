/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.dltk.core.IBuiltinModuleProvider;

public interface IInterpreterInstall extends IBuiltinModuleProvider {
	IInterpreterRunner getInterpreterRunner(String mode);

	String getId();

	String getName();

	void setName(String name);

	File getInstallLocation();	

	void setInstallLocation(File installLocation);

	IInterpreterInstallType getInterpreterInstallType();

	LibraryLocation[] getLibraryLocations();

	void setLibraryLocations(LibraryLocation[] locations);

	public String[] getInterpreterArguments();

	void setInterpreterArguments(String[] InterpreterArgs);

	String getInterpreterArgs();

	void setInterpreterArgs(String InterpreterArgs);
}
