/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.BuildpathContainerInitializer;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.util.Messages;


public class ModelStatus extends Status implements IModelStatus, IModelStatusConstants {

	/**
	 * The elements related to the failure, or <code>null</code> if no
	 * elements are involved.
	 */
	protected IModelElement[] elements = new IModelElement[0];

	/**
	 * The path related to the failure, or <code>null</code> if no path is
	 * involved.
	 */
	protected IPath path;

	/**
	 * The <code>String</code> related to the failure, or <code>null</code>
	 * if no <code>String</code> is involved.
	 */
	protected String string;
	
	/**
	 * Empty children
	 */
	protected final static IStatus[] NO_CHILDREN = new IStatus[] {};
	protected IStatus[] children= NO_CHILDREN;

	
	/**
	 * Constructs an script model status with no corresponding elements.
	 */
	public ModelStatus() {
		// no code for an multi-status
		super(ERROR, DLTKCore.PLUGIN_ID, 0, "ModelStatus", null); //$NON-NLS-1$
	}
	
	/**
	 * Constructs a model status with no corresponding elements.
	 */
	public ModelStatus(int code) {
		super(ERROR, DLTKCore.PLUGIN_ID, code, "ModelStatus", null); //$NON-NLS-1$
		this.elements = ModelElement.NO_ELEMENTS;
	}

	/**
	 * Constructs an script model status with no corresponding elements.
	 */
	public ModelStatus(CoreException coreException) {
		super(ERROR, DLTKCore.PLUGIN_ID, CORE_EXCEPTION, "ModelStatus", coreException); //$NON-NLS-1$
		elements = ModelElement.NO_ELEMENTS;
	}

	/**
	 * Constructs a model status with no corresponding elements.
	 */
	public ModelStatus(int code, Throwable throwable) {
		super(ERROR, DLTKCore.PLUGIN_ID, code, "ModelStatus", throwable); //$NON-NLS-1$
		this.elements = ModelElement.NO_ELEMENTS;
	}

	/**
	 * Constructs a model status with the given corresponding element.
	 */
	public ModelStatus(int code, IModelElement element) {
		this(code, new IModelElement[] { element });
	}

	/**
	 * Constructs a model status with no corresponding elements.
	 */
	public ModelStatus(int severity, int code, String string) {
		super(severity, DLTKCore.PLUGIN_ID, code, "ModelStatus", null); //$NON-NLS-1$
		this.elements = ModelElement.NO_ELEMENTS;
		this.path = null;
		this.string = string;
	}

	/**
	 * Constructs a model status with the given corresponding elements.
	 */
	public ModelStatus(int code, IModelElement[] elements) {
		super(ERROR, DLTKCore.PLUGIN_ID, code, "ModelStatus", null); //$NON-NLS-1$
		this.elements = elements;
		this.path = null;
	}

	/**
	 * Constructs a model status with no corresponding elements.
	 */
	public ModelStatus(int code, String string) {
		this(ERROR, code, string);
	}

	/**
	 * Constructs an script model status with the given corresponding element and
	 * path
	 */
	public ModelStatus(int code, IModelElement element, IPath path) {
		this(code, new IModelElement[] { element });
		this.path = path;
	}
	
	/**
	 * Constructs an script model status with the given corresponding
	 * element and string
	 */
	public ModelStatus(int code, IModelElement element, String string) {
		this(code, new IModelElement[]{element});
		this.string = string;
	}

	/**
	 * @see IModelStatus#isDoesNotExist()
	 */
	public boolean isDoesNotExist() {
		int code = getCode();
		return code == ELEMENT_DOES_NOT_EXIST || code == ELEMENT_NOT_ON_BUILDPATH;
	}

	/**
	 * @see IModelStatus#getPath()
	 */
	public IPath getPath() {
		return path;
	}

	/**
	 * Returns a printable representation of this exception for debugging
	 * purposes.
	 */
	public String toString() {
		if (this == VERIFIED_OK) {
			return "ModelStatus[OK]"; //$NON-NLS-1$
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("Model Status ["); //$NON-NLS-1$
		buffer.append(getMessage());
		buffer.append("]"); //$NON-NLS-1$
		return buffer.toString();
	}

	/**
	 * Returns the message that is relevant to the code of this status.
	 */
	public String getMessage() {
		Throwable exception = getException();
		if (exception == null) {
			switch (getCode()) {
			case CORE_EXCEPTION:
				return Messages.status_coreException;

			case DEVICE_PATH:
				return Messages.bind(Messages.status_cannotUseDeviceOnPath, getPath().toString());

			case ELEMENT_DOES_NOT_EXIST:
				return Messages.bind(Messages.element_doesNotExist, ((ModelElement) elements[0]).toStringWithAncestors());

			case ELEMENT_NOT_ON_BUILDPATH:
				return Messages.bind(Messages.element_notOnClasspath, ((ModelElement) elements[0]).toStringWithAncestors());

			case INDEX_OUT_OF_BOUNDS:
				return Messages.status_indexOutOfBounds;

			case INVALID_CONTENTS:
				return Messages.status_invalidContents;

			case INVALID_DESTINATION:
				return Messages.bind(Messages.status_invalidDestination, ((ModelElement) elements[0]).toStringWithAncestors());

			case INVALID_ELEMENT_TYPES:
				StringBuffer buff = new StringBuffer(Messages.operation_notSupported);
				for (int i = 0; i < elements.length; i++) {
					if (i > 0) {
						buff.append(", "); //$NON-NLS-1$
					}
					buff.append(((ModelElement) elements[i]).toStringWithAncestors());
				}
				return buff.toString();

			case INVALID_NAME:
				return Messages.bind(Messages.status_invalidName, string);

			case INVALID_SCRIPT_FOLDER:
				return Messages.bind(Messages.status_invalidPackage, string);

			case INVALID_PATH:
				if (string != null) {
					return string;
				} else {
					return Messages.bind(Messages.status_invalidPath, new String[] { getPath() == null ? "null" : getPath().toString() } //$NON-NLS-1$
							);
				}

			case INVALID_PROJECT:
				return Messages.bind(Messages.status_invalidProject, string);

			case INVALID_RESOURCE:
				return Messages.bind(Messages.status_invalidResource, string);

			case INVALID_RESOURCE_TYPE:
				return Messages.bind(Messages.status_invalidResourceType, string);

			case INVALID_SIBLING:
				if (string != null) {
					return Messages.bind(Messages.status_invalidSibling, string);
				} else {
					return Messages.bind(Messages.status_invalidSibling, ((ModelElement) elements[0]).toStringWithAncestors());
				}

			case IO_EXCEPTION:
				return Messages.status_IOException;

			case NAME_COLLISION:
				// if (elements != null && elements.length > 0) {
				// IModelElement element = elements[0];
				// if (element instanceof ScriptFolder && ((ScriptFolder)
				// element).is()) {
				// return Messages.operation_cannotRenameDefaultPackage;
				// }
				// }
				if (string != null) {
					return string;
				} else {
					return Messages.bind(Messages.status_nameCollision, ""); //$NON-NLS-1$
				}
			case NO_ELEMENTS_TO_PROCESS:
				return Messages.operation_needElements;

			case NULL_NAME:
				return Messages.operation_needName;

			case NULL_PATH:
				return Messages.operation_needPath;

			case NULL_STRING:
				return Messages.operation_needString;

			case PATH_OUTSIDE_PROJECT:
				return Messages.bind(Messages.operation_pathOutsideProject, new String[] { string, ((ModelElement) elements[0]).toStringWithAncestors() });

			case READ_ONLY:
				IModelElement element = elements[0];
				String name = element.getElementName();

				return Messages.bind(Messages.status_readOnly, name);

			case RELATIVE_PATH:
				return Messages.bind(Messages.operation_needAbsolutePath, getPath().toString());

			case TARGET_EXCEPTION:
				return Messages.status_targetException;

			case UPDATE_CONFLICT:
				return Messages.status_updateConflict;

			case NO_LOCAL_CONTENTS:
				return Messages.bind(Messages.status_noLocalContents, getPath().toString());

			case BP_CONTAINER_PATH_UNBOUND:
				ScriptProject scriptProject = (ScriptProject) elements[0];
				BuildpathContainerInitializer initializer = DLTKCore.getBuildpathContainerInitializer(this.path.segment(0));
				String description = null;
				if (initializer != null)
					description = initializer.getDescription(this.path, scriptProject);
				if (description == null)
					description = path.makeRelative().toString();
				return Messages.bind(Messages.buildpath_unboundContainerPath, new String[] { description, scriptProject.getElementName() });

			case INVALID_BP_CONTAINER_ENTRY:
				scriptProject = (ScriptProject) elements[0];
				IBuildpathContainer container = null;
				description = null;
				try {
					container = DLTKCore.getBuildpathContainer(path, scriptProject);
				} catch (ModelException e) {
					// project doesn't exist: ignore
				}
				if (container == null) {
					initializer = DLTKCore.getBuildpathContainerInitializer(path.segment(0));
					if (initializer != null)
						description = initializer.getDescription(path, scriptProject);
				} else {
					description = container.getDescription();
				}
				if (description == null)
					description = path.makeRelative().toString();
				return Messages.bind(Messages.buildpath_invalidContainer, new String[] { description, scriptProject.getElementName() });

			case BUILDPATH_CYCLE:
				scriptProject = (ScriptProject) elements[0];
				return Messages.bind(Messages.buildpath_cycle, scriptProject.getElementName());

			}
			if (string != null) {
				return string;
			} else {
				return ""; //$NON-NLS-1$
			}
		} else {
			String message = exception.getMessage();
			if (message != null) {
				return message;
			} else {
				return exception.toString();
			}
		}
	}
	/**
	 * Creates and returns a new <code>IModelStatus</code> that is a
	 * a multi-status status.
	 *
	 * @see IStatus#isMultiStatus()
	 */
	public static IModelStatus newMultiStatus(IModelStatus[] children) {
		ModelStatus jms = new ModelStatus();
		jms.children = children;
		return jms;
	}
}
