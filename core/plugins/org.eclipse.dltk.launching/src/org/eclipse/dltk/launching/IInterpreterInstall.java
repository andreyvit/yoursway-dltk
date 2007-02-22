package org.eclipse.dltk.launching;

import java.io.File;

public interface IInterpreterInstall {
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

	public void setInterpreterArguments(String[] InterpreterArgs);

	public String getInterpreterArgs();

	public void setInterpreterArgs(String InterpreterArgs);
}
