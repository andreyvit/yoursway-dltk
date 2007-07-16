/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;
import org.eclipse.dltk.launching.debug.DebuggingEngineManager;
import org.eclipse.dltk.launching.debug.IDebuggingEngine;

/**
 * Abstract implementation of a interpreter install.
 * <p>
 * Clients implementing interpreter installs must subclass this class.
 * </p>
 */
public abstract class AbstractInterpreterInstall implements IInterpreterInstall {
	private IInterpreterInstallType fType;
	private String fId;
	private String fName;
	private File fInstallLocation;
	private LibraryLocation[] fSystemLibraryDescriptions;
	private String fInterpreterArgs;

	// whether change events should be fired
	private boolean fNotify = true;

	private void firePropertyChangeEvent(PropertyChangeEvent event) {
		if (fNotify) {
			ScriptRuntime.fireInterpreterChanged(event);
		}
	}

	/**
	 * Constructs a new interpreter install.
	 * 
	 * @param type
	 *            The type of this interpreter install. Must not be
	 *            <code>null</code>
	 * @param id
	 *            The unique identifier of this interpreter instance Must not be
	 *            <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if any of the required parameters are <code>null</code>.
	 */
	public AbstractInterpreterInstall(IInterpreterInstallType type, String id) {
		if (type == null || id == null) {
			throw new IllegalArgumentException();
		}

		fType = type;
		fId = id;
	}

	public String getId() {
		return fId;
	}

	public String getName() {
		return fName;
	}

	public void setName(String name) {
		if (!name.equals(fName)) {
			PropertyChangeEvent event = new PropertyChangeEvent(this,
					IInterpreterInstallChangedListener.PROPERTY_NAME, fName,
					name);
			fName = name;

			firePropertyChangeEvent(event);
		}
	}

	public File getInstallLocation() {
		return fInstallLocation;
	}

	public void setInstallLocation(File installLocation) {
		if (!installLocation.equals(fInstallLocation)) {
			PropertyChangeEvent event = new PropertyChangeEvent(
					this,
					IInterpreterInstallChangedListener.PROPERTY_INSTALL_LOCATION,
					fInstallLocation, installLocation);
			fInstallLocation = installLocation;
			firePropertyChangeEvent(event);
		}
	}

	public IInterpreterInstallType getInterpreterInstallType() {
		return fType;
	}

	public LibraryLocation[] getLibraryLocations() {
		return fSystemLibraryDescriptions;
	}

	public void setLibraryLocations(LibraryLocation[] locations) {
		if (locations == fSystemLibraryDescriptions) {
			return;
		}
		LibraryLocation[] newLocations = locations;
		if (newLocations == null) {
			newLocations = getInterpreterInstallType()
					.getDefaultLibraryLocations(getInstallLocation());
		}
		LibraryLocation[] prevLocations = fSystemLibraryDescriptions;
		if (prevLocations == null) {
			prevLocations = getInterpreterInstallType()
					.getDefaultLibraryLocations(getInstallLocation());
		}

		if (newLocations.length == prevLocations.length) {
			int i = 0;
			boolean equal = true;
			while (i < newLocations.length && equal) {
				equal = newLocations[i].equals(prevLocations[i]);
				i++;
			}
			if (equal) {
				return;
			}
		}

		PropertyChangeEvent event = new PropertyChangeEvent(this,
				IInterpreterInstallChangedListener.PROPERTY_LIBRARY_LOCATIONS,
				prevLocations, newLocations);
		fSystemLibraryDescriptions = locations;

		firePropertyChangeEvent(event);
	}

	/**
	 * Whether this Interpreter should fire property change notifications.
	 * 
	 * @param notify
	 */
	protected void setNotify(boolean notify) {
		fNotify = notify;
	}

	public boolean equals(Object object) {
		if (object instanceof IInterpreterInstall) {
			IInterpreterInstall Interpreter = (IInterpreterInstall) object;
			return getInterpreterInstallType().equals(
					Interpreter.getInterpreterInstallType())
					&& getId().equals(Interpreter.getId());
		}
		return false;
	}

	public int hashCode() {
		return getInterpreterInstallType().hashCode() + getId().hashCode();
	}

	public String[] getInterpreterArguments() {
		String args = getInterpreterArgs();
		if (args == null) {
			return null;
		}
		ExecutionArguments ex = new ExecutionArguments(args, ""); //$NON-NLS-1$
		return ex.getInterpreterArgumentsArray();
	}

	public void setInterpreterArguments(String[] InterpreterArgs) {
		if (InterpreterArgs == null) {
			setInterpreterArgs(null);
		} else {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < InterpreterArgs.length; i++) {
				String string = InterpreterArgs[i];
				buf.append(string);
				buf.append(' ');
			}
			setInterpreterArgs(buf.toString().trim());
		}
	}

	public String getInterpreterArgs() {
		return fInterpreterArgs;
	}

	public void setInterpreterArgs(String InterpreterArgs) {
		if (fInterpreterArgs == null) {
			if (InterpreterArgs == null) {
				return;
			}
		} else if (fInterpreterArgs.equals(InterpreterArgs)) {
			return;
		}
		PropertyChangeEvent event = new PropertyChangeEvent(
				this,
				IInterpreterInstallChangedListener.PROPERTY_Interpreter_ARGUMENTS,
				fInterpreterArgs, InterpreterArgs);
		fInterpreterArgs = InterpreterArgs;

		firePropertyChangeEvent(event);
	}

	/**
	 * Throws a core exception with an error status object built from the given
	 * message, lower level exception, and error code.
	 * 
	 * @param message
	 *            the status message
	 * @param exception
	 *            lower level exception associated with the error, or
	 *            <code>null</code> if none
	 * @param code
	 *            error code
	 * @throws CoreException
	 *             the "abort" core exception
	 * 
	 */
	protected void abort(String message, Throwable exception, int code)
			throws CoreException {
		throw new CoreException(new Status(IStatus.ERROR, DLTKLaunchingPlugin
				.getUniqueIdentifier(), code, message, exception));
	}

	// IBuiltinModuleProvider
	public String[] getBuiltinModules() {
		return null;
	}

	public String getBuiltinModuleContent(String name) {
		return null;
	}

	protected IInterpreterRunner getDebugInterpreterRunner() {
		DebuggingEngineManager manager = DebuggingEngineManager.getInstance();
		IDebuggingEngine engine = manager.getSelectedDebuggineEngine(getNatureId());

		if (engine != null) {
			return engine.getRunner(this);
		}

		return null;
	}

	public IInterpreterRunner getInterpreterRunner(String mode) {
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			return getDebugInterpreterRunner();
		}

		return null;
	}
}
