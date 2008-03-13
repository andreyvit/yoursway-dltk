/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.editor;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IPath;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.core.BuiltinSourceModule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.ILocationProvider;

public class ExternalStorageEditorInput implements IEditorInput,
		IStorageEditorInput, ILocationProvider {
	private IStorage fStorage;

	public ExternalStorageEditorInput(IStorage storage) {
		this.fStorage = storage;
	}

	public boolean exists() {
		return fStorage != null;
	}

	public ImageDescriptor getImageDescriptor() {
		return PlatformUI.getWorkbench().getEditorRegistry()
				.getImageDescriptor(this.fStorage.getName());
	}

	public String getName() {
		return fStorage.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		IPath path = fStorage.getFullPath();
		if (path == null) {
			return ""; //$NON-NLS-1$
		}

		return path.toOSString();
	}

	public Object getAdapter(Class adapter) {

		if (!(fStorage instanceof BuiltinSourceModule)
				&& (adapter == this.getClass() || adapter == ILocationProvider.class)) {
			return this;
		}
		if (adapter == IModelElement.class && fStorage instanceof IModelElement) {
			return fStorage;
		}
		return null;
	}

	public IStorage getStorage() {
		return this.fStorage;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ExternalStorageEditorInput)) {
			return false;
		}
		ExternalStorageEditorInput other = (ExternalStorageEditorInput) obj;
		return fStorage.equals(other.fStorage);
	}

	/*
	 * (non-Javadoc) Method declared on Object.
	 */
	public int hashCode() {
		return fStorage.hashCode();
	}

	public IPath getPath(Object element) {
		return fStorage.getFullPath();
	}
}
