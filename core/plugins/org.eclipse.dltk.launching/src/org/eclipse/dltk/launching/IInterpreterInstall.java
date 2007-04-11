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
