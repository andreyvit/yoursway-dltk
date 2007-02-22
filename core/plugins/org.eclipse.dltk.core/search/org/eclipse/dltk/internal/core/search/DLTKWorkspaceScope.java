/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.core.search;

import java.util.HashSet;

import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.compiler.env.AccessRuleSet;
import org.eclipse.dltk.internal.core.DLTKProject;
import org.eclipse.dltk.internal.core.ModelManager;


/**
 * A Java-specific scope for searching the entire workspace. The scope can be
 * configured to not search binaries. By default, binaries are included.
 */
public class DLTKWorkspaceScope extends DLTKSearchScope {

	public DLTKWorkspaceScope(IDLTKLanguageToolkit toolkit) {
		super(toolkit);
	}

	protected boolean needsInitialize;

	public boolean encloses(IModelElement element) {
		/*
		 * if (this.needsInitialize) { this.initialize(); } return
		 * super.encloses(element);
		 */
		/*
		 * A workspace scope encloses all script elements (this assumes that the
		 * index selector and thus enclosingProjectAndJars() returns indexes on
		 * the classpath only and that these indexes are consistent.) NOTE:
		 * Returning true gains 20% of a hierarchy build on Object
		 */
		return true;
	}

	public boolean encloses(String resourcePathString) {
		/*
		 * if (this.needsInitialize) { this.initialize(); } return
		 * super.encloses(resourcePathString);
		 */
		/*
		 * A workspace scope encloses all resources (this assumes that the index
		 * selector and thus enclosingProjectAndJars() returns indexes on the
		 * buildpath only and that these indexes are consistent.) NOTE:
		 * Returning true gains 20% of a hierarchy build on Object
		 */
		return true;
	}

	public IPath[] enclosingProjectsAndZips() {
		if (this.needsInitialize) {
			this.initialize(5);
		}
		return super.enclosingProjectsAndZips();
	}

	public boolean equals(Object o) {
		return o instanceof DLTKWorkspaceScope;
	}

	public AccessRuleSet getAccessRuleSet(String relativePath,
			String containerPath) {
		if (this.pathRestrictions == null)
			return null;
		return super.getAccessRuleSet(relativePath, containerPath);
	}

	public int hashCode() {
		return DLTKWorkspaceScope.class.hashCode();
	}

	public void initialize(int size) {
		super.initialize(size);
		try {
			IDLTKProject[] projects = ModelManager.getModelManager().getModel()
					.getScriptProjects();
			for (int i = 0, length = projects.length; i < length; i++) {
				int includeMask = SOURCES | APPLICATION_LIBRARIES
						| SYSTEM_LIBRARIES;
				add((DLTKProject) projects[i], null, includeMask, new HashSet(
						length * 2, 1), null);
			}
		} catch (ModelException ignored) {
			// ignore
		}
		this.needsInitialize = false;
	}

	public void processDelta(IModelElementDelta delta) {
		if (this.needsInitialize)
			return;
		IModelElement element = delta.getElement();
		switch (element.getElementType()) {
		case IModelElement.SCRIPT_MODEL:
			IModelElementDelta[] children = delta.getAffectedChildren();
			for (int i = 0, length = children.length; i < length; i++) {
				IModelElementDelta child = children[i];
				this.processDelta(child);
			}
			break;
		case IModelElement.SCRIPT_PROJECT:
			int kind = delta.getKind();
			switch (kind) {
			case IModelElementDelta.ADDED:
			case IModelElementDelta.REMOVED:
				this.needsInitialize = true;
				break;
			case IModelElementDelta.CHANGED:
				int flags = delta.getFlags();
				if ((flags & IModelElementDelta.F_CLOSED) != 0
						|| (flags & IModelElementDelta.F_OPENED) != 0) {
					this.needsInitialize = true;
				} else {
					children = delta.getAffectedChildren();
					for (int i = 0, length = children.length; i < length; i++) {
						IModelElementDelta child = children[i];
						this.processDelta(child);
					}
				}
				break;
			}
			break;
		case IModelElement.PROJECT_FRAGMENT:
			kind = delta.getKind();
			switch (kind) {
			case IModelElementDelta.ADDED:
			case IModelElementDelta.REMOVED:
				this.needsInitialize = true;
				break;
			case IModelElementDelta.CHANGED:
				int flags = delta.getFlags();
				if ((flags & IModelElementDelta.F_ADDED_TO_BUILDPATH) > 0
						|| (flags & IModelElementDelta.F_REMOVED_FROM_BUILDPATH) > 0) {
					this.needsInitialize = true;
				}
				break;
			}
			break;
		}
	}

	public String toString() {
		return "DLTKWorkspaceScope"; //$NON-NLS-1$
	}
}
