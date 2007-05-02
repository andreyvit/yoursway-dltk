/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.util.MementoTokenizer;


public class BuiltinScriptFolder extends ScriptFolder {
	public BuiltinScriptFolder(ProjectFragment parent, IPath path) {
		super(parent, path);
	}

	void computeForeignResources(BuiltinScriptFolderInfo info) {
		info.setForeignResources(ModelElementInfo.NO_NON_SCRIPT_RESOURCES);
	}
	public ISourceModule getSourceModule(String name) {
		return new BuiltinSourceModule(this, name, DefaultWorkingCopyOwner.PRIMARY );
	}
	
	protected boolean computeChildren(OpenableElementInfo info, String[] entryNames) {
		if (entryNames != null && entryNames.length > 0) {
			ArrayList vChildren = new ArrayList();
			for (int iter = 0; iter < entryNames.length; iter++) {
				String child = entryNames[iter];
				ISourceModule classFile = getSourceModule(child);
				vChildren.add(classFile);
			}
			IModelElement[] children = new IModelElement[vChildren.size()];
			vChildren.toArray(children);
			info.setChildren(children);
		} else {
			info.setChildren(NO_ELEMENTS);
		}
		return true;
	}

	public ISourceModule[] getSourceModules() throws ModelException {
		ArrayList list = getChildrenOfType(SOURCE_MODULE);
		ISourceModule[] array = new ISourceModule[list.size()];
		list.toArray(array);
		return array;
	}

	public boolean isReadOnly() {
		return true;
	}

	// Open my archive: this creates all the pkg infos
	protected void generateInfos(Object info, HashMap newElements, IProgressMonitor pm) throws ModelException {
		// Open my archive: this creates all the pkg infos
		Openable openableParent = (Openable) this.parent;
		if (!openableParent.isOpen()) {
			openableParent.generateInfos(openableParent.createElementInfo(), newElements, pm);
		}
	}
	public IModelElement getHandleFromMemento(String token, MementoTokenizer memento, WorkingCopyOwner owner) {
		switch (token.charAt(0)) {
			case JEM_SOURCEMODULE:
				if (!memento.hasMoreTokens()) return this;
				String classFileName = memento.nextToken();
				ModelElement classFile = (ModelElement)getSourceModule(classFileName);
				return classFile.getHandleFromMemento(memento, owner);			
		}
		return null;
	}
	
	protected Object createElementInfo() {
		return null; // not used for ExternalScriptFolders: info is created when directory are opened.
	}
	protected boolean resourceExists() {
		return true;
	}
	public Object[] getForeignResources() throws ModelException {		
		return ((BuiltinScriptFolderInfo) getElementInfo()).getForeignResources();		
	}
}
