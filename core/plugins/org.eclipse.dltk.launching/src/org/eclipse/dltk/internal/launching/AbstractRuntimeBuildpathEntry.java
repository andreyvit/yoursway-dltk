/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.launching;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.launching.ScriptLaunchConfigurationConstants;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry;
import org.eclipse.dltk.launching.IRuntimeBuildpathEntry2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Common function for runtime buildpath entries.
 * <p>
 * Clients implementing runtime buildpath entries must subclass this
 * class.
 * </p>
	 *
 */
public abstract class AbstractRuntimeBuildpathEntry extends PlatformObject implements IRuntimeBuildpathEntry2 {
	
	private int buildpathProperty = IRuntimeBuildpathEntry.USER_ENTRY;
	/**
	 * Associated Script project, or <code>null</code>
	 */
	private IDLTKProject fProject;
	
	/* (non-Javadoc)
	 * 
	 * Default implementation returns <code>null</code>.
	 * Subclasses should override if required.
	 * 
	 */
	public String getContainerName() {
		return null;
	}
	
	/* (non-Javadoc)
	 * 
	 * Default implementation returns <code>false</code>.
	 * Subclasses should override if required.
	 * 
	 * @see org.eclipse.dltk.internal.launching.IRuntimeBuildpathEntry2#isComposite()
	 */
	public boolean isComposite() {
		return false;
	}
	
	/* (non-Javadoc)
	 * 
	 * Default implementation returns an empty collection.
	 * Subclasses should override if required.
	 * 
	 * @see org.eclipse.dltk.internal.launching.IRuntimeBuildpathEntry2#getRuntimeBuildpathEntries()
	 */
	public IRuntimeBuildpathEntry[] getRuntimeBuildpathEntries() throws CoreException {
		return new IRuntimeBuildpathEntry[0];
	}
	
	/**
	 * Throws an exception with the given message and underlying exception.
	 * 
	 * @param message error message
	 * @param exception underlying exception or <code>null</code> if none
	 * @throws CoreException
	 */
	protected void abort(String message, Throwable exception) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, DLTKLaunchingPlugin.getUniqueIdentifier(), ScriptLaunchConfigurationConstants.ERR_INTERNAL_ERROR, message, exception);
		throw new CoreException(status);
	}

	/* (non-Javadoc)
	 * 
	 * Default implementation generates a string containing an XML
	 * document. Subclasses should override <code>buildMemento</code>
	 * to specify the contents of the required <code>memento</code>
	 * node.
	 * 
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getMemento()
	 */
	public String getMemento() throws CoreException {
		Document doc= DebugPlugin.newDocument();
		Element root = doc.createElement("runtimeBuildpathEntry"); //$NON-NLS-1$
		doc.appendChild(root);
		root.setAttribute("id", getTypeId()); //$NON-NLS-1$
		Element memento = doc.createElement("memento"); //$NON-NLS-1$
		root.appendChild(memento);
		buildMemento(doc, memento);
		return DebugPlugin.serializeDocument(doc);
	}
	
	/**
	 * Constructs a memento for this buildpath entry in the given 
	 * document and element. The memento element has already been
	 * appended to the document.
	 * 
	 * @param document XML document
	 * @param memento element node for client specific attributes
	 * @throws CoreException if unable to create a memento 
	 */
	protected abstract void buildMemento(Document document, Element memento) throws CoreException;
	
	/* (non-Javadoc)
	 * 
	 * Default implementation returns <code>null</code>.
	 * Subclasses should override if required.
	 * 
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getPath()
	 */
	public IPath getPath() {
		return null;
	}
	
	/* (non-Javadoc)
	 * 
	 * Default implementation returns <code>null</code>.
	 * Subclasses should override if required.
	 * 
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getResource()
	 */
	public IResource getResource() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getBuildpathProperty()
	 */
	public int getBuildpathProperty() {
		return buildpathProperty;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#setBuildpathProperty(int)
	 */
	public void setBuildpathProperty(int property) {
		buildpathProperty = property;
	}
	/* (non-Javadoc)
	 * 
	 * Default implementation returns <code>null</code>.
	 * Subclasses should override if required.
	 * 
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getLocation()
	 */
	public String getLocation() {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * 
	 * Default implementation returns <code>null</code>.
	 * Subclasses should override if required.
	 * 
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getBuildpathEntry()
	 */
	public IBuildpathEntry getBuildpathEntry() {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.dltk.launching.IRuntimeBuildpathEntry#getScriptProject()
	 */
	public IDLTKProject getDLTKProject() {
		return fProject;
	}
	
	/**
	 * Sets the Script project associated with this entry.
	 * 
	 * @param scriptProject
	 */
	protected void setDLTKProject(IDLTKProject sProject) {
		fProject = sProject;
	}
}
