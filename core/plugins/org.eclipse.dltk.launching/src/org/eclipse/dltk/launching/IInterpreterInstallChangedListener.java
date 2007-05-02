/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.launching;

import org.eclipse.dltk.internal.launching.DLTKLaunchingPlugin;

 

/**
 * A Interpreter install changed listener is notified when
 * the workspace default Interpreter install changes, or when an attribute of
 * a specific Interpreter install changes.
 * Listeners register with <code>ScriptRuntime</code>.
 * <p>
 * Clients may implement this interface.
 * </p>
	 *
 */
public interface IInterpreterInstallChangedListener {
	
	/**
	 * Property constant indicating the library locations associated
	 * with a Interpreter install have changed.
	 */
	public static final String PROPERTY_LIBRARY_LOCATIONS = DLTKLaunchingPlugin.getUniqueIdentifier() + ".PROPERTY_LIBRARY_LOCATIONS"; //$NON-NLS-1$

	/**
	 * Property constant indicating the name associated
	 * with a Interpreter install has changed.
	 */
	public static final String PROPERTY_NAME = DLTKLaunchingPlugin.getUniqueIdentifier() + ".PROPERTY_NAME"; //$NON-NLS-1$
	
	/**
	 * Property constant indicating the install location of
	 * a Interpreter install has changed.
	 */
	public static final String PROPERTY_INSTALL_LOCATION = DLTKLaunchingPlugin.getUniqueIdentifier() + ".PROPERTY_INSTALL_LOCATION";	 //$NON-NLS-1$
			
	/**
	 * Property constant indicating the Interpreter arguments associated
	 * with a Interpreter install has changed.
     * 
	 *
	 */
	public static final String PROPERTY_Interpreter_ARGUMENTS = DLTKLaunchingPlugin.getUniqueIdentifier() + ".PROPERTY_Interpreter_ARGUMENTS"; //$NON-NLS-1$

	/**
	 * Notification that the default interpreter install
	 * has changed.
	 * 
	 * @param previous the Interpreter install that was previously assigned
	 * 	to the workspace, possibly <code>null</code>
	 * @param current the Interpreter install that is currently assigned to the
	 * 	workspace, possibly <code>null</code>
	 */
	public void defaultInterpreterInstallChanged(IInterpreterInstall previous, IInterpreterInstall current);
	
	/**
	 * Notification that a property of a Interpreter install has changed.
	 * 
	 * @param event event describing the change. The Interpreter that has changed
	 * 	is the source object associated with the event.
	 */
	public void interpreterChanged(PropertyChangeEvent event);	
	
	/**
	 * Notification that a Interpreter has been created.
	 * 
	 * @param Interpreter the Interpreter that has been created
	 */
	public void interpreterAdded(IInterpreterInstall Interpreter);		
	
	/**
	 * Notification that a Interpreter has been disposed.
	 * 
	 * @param Interpreter the Interpreter that has been disposed
	 */
	public void interpreterRemoved(IInterpreterInstall Interpreter);			
	
}
