/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.debug.ui.interpreters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.internal.launching.InterpreterDefinitionsContainer;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Processes add/removed/changed Interpreters.
 */
public class InterpretersUpdater {

	// The interpreters defined when this updated is instantiated
	private InterpreterDefinitionsContainer fOriginalInterpreters;

	/**
	 * Constructs a new Interpreter updater to update Interpreter install
	 * settings.
	 */
	public InterpretersUpdater() {
		saveCurrentAsOriginal();
	}

	private void saveCurrentAsOriginal() {
		fOriginalInterpreters = new InterpreterDefinitionsContainer();

		final String[] natureIds = ScriptRuntime.getInterpreterNatures();
		for (int i = 0; i < natureIds.length; i++) {
			final String natureId = natureIds[i];

			IInterpreterInstall def = ScriptRuntime
					.getDefaultInterpreterInstall(natureId);

			if (def != null) {
				fOriginalInterpreters.setDefaultInterpreterInstallCompositeID(
						natureId, ScriptRuntime
								.getCompositeIdFromInterpreter(def));
			}
		}

		final IInterpreterInstallType[] types = ScriptRuntime
				.getInterpreterInstallTypes();
		for (int i = 0; i < types.length; i++) {
			IInterpreterInstall[] installs = types[i].getInterpreterInstalls();
			if (installs != null) {
				for (int j = 0; j < installs.length; j++) {
					fOriginalInterpreters.addInterpreter(installs[j]);
				}
			}
		}
	}

	/**
	 * Updates Interpreter settings and returns whether the update was
	 * successful.
	 * 
	 * @param interpreters
	 *            new installed InterpreterEnvironments
	 * @param defaultInterpreter
	 *            new default Interpreter
	 * @return whether the update was successful
	 */
	public boolean updateInterpreterSettings(String langNatureId,
			IInterpreterInstall[] interpreters,
			IInterpreterInstall defaultInterpreter) {
		// Create a Interpreter definition container
		InterpreterDefinitionsContainer container = new InterpreterDefinitionsContainer();

		// Default interpreter id for natureId
		if (defaultInterpreter != null) {
			final String defaultId = ScriptRuntime
					.getCompositeIdFromInterpreter(defaultInterpreter);
			container.setDefaultInterpreterInstallCompositeID(langNatureId,
					defaultId);
		} else {
			container.setDefaultInterpreterInstallCompositeID(langNatureId,
					null);
		}

		// Interpreters for natureId
		for (int i = 0; i < interpreters.length; i++) {
			container.addInterpreter(interpreters[i]);
		}

		// Default interpreters for other languages
		final String[] natureIds = fOriginalInterpreters
				.getInterpreterNatures();
		for (int i = 0; i < natureIds.length; i++) {
			final String natureId = natureIds[i];
			if (!langNatureId.equals(natureId)) {
				final String defaultId = fOriginalInterpreters
						.getDefaultInterpreterInstallCompositeID(natureId);
				container.setDefaultInterpreterInstallCompositeID(natureId,
						defaultId);
			}
		}

		// Save interpreters from other languages to the container
		final Iterator it = fOriginalInterpreters.getInterpreterList()
				.iterator();
		while (it.hasNext()) {
			final IInterpreterInstall install = (IInterpreterInstall) it.next();
			if (!langNatureId.equals(install.getInterpreterInstallType()
					.getNatureId())) {
				container.addInterpreter(install);
			}
		}

		// Generate XML for the interpreter definitions and save it as the new
		// value of the Interpreter preference
		saveInterpreterDefinitions(container);

		saveCurrentAsOriginal();

		return true;
	}

	private void saveInterpreterDefinitions(
			final InterpreterDefinitionsContainer container) {
		IRunnableWithProgress runnable = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					monitor.beginTask(
							InterpretersMessages.InterpretersUpdater_0, 100);
					final String xml = container.getAsXML();
					monitor.worked(40);
					ScriptRuntime.getPreferences().setValue(
							ScriptRuntime.PREF_INTERPRETER_XML, xml);
					monitor.worked(30);
					ScriptRuntime.savePreferences();
					monitor.worked(30);
				} catch (IOException ioe) {
					DLTKDebugUIPlugin.log(ioe);
				} catch (ParserConfigurationException e) {
					DLTKDebugUIPlugin.log(e);
				} catch (TransformerException e) {
					DLTKDebugUIPlugin.log(e);
				} finally {
					monitor.done();
				}

			}
		};
		try {
			DLTKDebugUIPlugin.getDefault().getWorkbench().getProgressService()
					.busyCursorWhile(runnable);
		} catch (InvocationTargetException e) {
			DLTKDebugUIPlugin.log(e);
		} catch (InterruptedException e) {
			DLTKDebugUIPlugin.log(e);
		}
	}
}
