/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

/**
 * An implementation of IInterpreterInstall that is used for manipulating
 * interpreters without necessarily committing changes.
 * <p>
 * Instances of this class act like wrappers. All other instances of
 * IInterpreterInstall represent 'real live' interpreters that may be used for
 * building or launching. Instances of this class behave like 'temporary'
 * interpreters that are not visible and not available for building or
 * launching.
 * </p>
 * <p>
 * Instances of this class may be constructed as a preliminary step to creating
 * a 'live' interpreter or as a preliminary step to making changes to a 'real'
 * interpreter.
 * </p>
 * When <code>convertToRealInterpreter</code> is called, a corresponding
 * 'real' interpreter is created if one did not previously exist, or the
 * corresponding 'real' interpreter is updated.
 * </p>
 * <p>
 * Clients may instantiate this class; it is not intended to be subclassed.
 * </p>
 * 
 * 
 */
public class InterpreterStandin extends AbstractInterpreterInstall {
	public InterpreterStandin(IInterpreterInstallType type, String id) {
		super(type, id);
		setNotify(false);
	}

	/**
	 * Constructs a copy of the specified Interpreter with the given identifier.
	 * 
	 * @param sourceInterpreter
	 * @param id
	 * 
	 */
	public InterpreterStandin(IInterpreterInstall sourceInterpreter, String id) {
		super(sourceInterpreter.getInterpreterInstallType(), id);
		setNotify(false);
		init(sourceInterpreter);
	}

	/**
	 * Construct a <code>InterpreterStandin</code> instance based on the
	 * specified <code>IInterpreterInstall</code>. Changes to this standin
	 * will not be reflected in the 'real' Interpreter until
	 * <code>convertToRealInterpreter</code> is called.
	 * 
	 * @param realInterpreter
	 *            the 'real' Interpreter from which to construct this standin
	 *            Interpreter
	 */
	public InterpreterStandin(IInterpreterInstall realInterpreter) {
		this(realInterpreter.getInterpreterInstallType(), realInterpreter
				.getId());
		init(realInterpreter);
	}

	/**
	 * Initializes the settings of this standin based on the settings in the
	 * given Interpreter install.
	 * 
	 * @param realInterpreter
	 *            Interpreter to copy settings from
	 */
	private void init(IInterpreterInstall realInterpreter) {
		setName(realInterpreter.getName());
		setInstallLocation(realInterpreter.getInstallLocation());
		setLibraryLocations(realInterpreter.getLibraryLocations());
		setInterpreterArgs(realInterpreter.getInterpreterArgs());
	}

	/**
	 * If no corresponding 'real' Interpreter exists, create one and populate it
	 * from this standin instance. If a corresponding Interpreter exists, update
	 * its attributes from this standin instance.
	 * 
	 * @return IInterpreterInstall the 'real' corresponding to this standin
	 *         Interpreter
	 */
	public IInterpreterInstall convertToRealInterpreter() {
		IInterpreterInstallType installType = getInterpreterInstallType();
		IInterpreterInstall realInterpreter = installType
				.findInterpreterInstall(getId());

		boolean notify = true;

		if (realInterpreter == null) {
			realInterpreter = installType.createInterpreterInstall(getId());
			notify = false;
		}

		// do not notify of property changes on new Interpreters
		if (realInterpreter instanceof AbstractInterpreterInstall) {
			((AbstractInterpreterInstall) realInterpreter).setNotify(notify);
		}

		realInterpreter.setName(getName());
		realInterpreter.setInstallLocation(getInstallLocation());
		realInterpreter.setLibraryLocations(getLibraryLocations());
		realInterpreter.setInterpreterArgs(getInterpreterArgs());

		if (realInterpreter instanceof AbstractInterpreterInstall) {
			((AbstractInterpreterInstall) realInterpreter).setNotify(true);
		}

		if (!notify) {
			ScriptRuntime.fireInterpreterAdded(realInterpreter);
		}

		return realInterpreter;
	}

	public IInterpreterRunner getInterpreterRunner(String mode) {
		return null;
	}

	public String getNatureId() {
		return null;
	}
}
