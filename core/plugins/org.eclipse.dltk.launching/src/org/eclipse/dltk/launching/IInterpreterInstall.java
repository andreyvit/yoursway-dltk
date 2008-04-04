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
	// Runner
	IInterpreterRunner getInterpreterRunner(String mode);

	String getNatureId();

	// Id
	String getId();

	// Name
	String getName();

	void setName(String name);

	// Also search for Platform location relative locations.
	File getInstallLocation();
	//
	File getRawInstallLocation();

	void setInstallLocation(File installLocation);

	// Type
	IInterpreterInstallType getInterpreterInstallType();

	// Library locations
	LibraryLocation[] getLibraryLocations();

	EnvironmentVariable[] getEnvironmentVariables();

	void setEnvironmentVariables(EnvironmentVariable[] variables);

	void setLibraryLocations(LibraryLocation[] locations);

	// Arguments
	public String[] getInterpreterArguments();

	void setInterpreterArguments(String[] args);

	// Arguments
	String getInterpreterArgs();

	void setInterpreterArgs(String args);
}
