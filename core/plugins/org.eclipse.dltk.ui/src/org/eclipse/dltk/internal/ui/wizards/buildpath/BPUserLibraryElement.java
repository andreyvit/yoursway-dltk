/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.wizards.buildpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathContainer;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IBuiltinModuleProvider;
import org.eclipse.dltk.core.IDLTKProject;


public class BPUserLibraryElement {
	
	private  class UpdatedBuildpathContainer implements IBuildpathContainer {
			
		
		public IBuildpathEntry[] getBuildpathEntries() {
			BPListElement[] children= getChildren();
			IBuildpathEntry[] entries= new IBuildpathEntry[children.length];
			for (int i= 0; i < entries.length; i++) {
				entries[i]= children[i].getBuildpathEntry();
			}
			return entries;
		}
		
		public String getDescription() {
			return getName();
		}

		public int getKind() {
			return isSystemLibrary() ? IBuildpathContainer.K_SYSTEM : K_APPLICATION;
		}
		
		public IPath getPath() {
			return BPUserLibraryElement.this.getPath();
		}

		public IBuiltinModuleProvider getBuiltinProvider() {
			return null;
		}
	}
	
	
	private String fName;
	private List fChildren;
	private boolean fIsSystemLibrary;

	public BPUserLibraryElement(String name, IBuildpathContainer container, IDLTKProject project) {
		fName= name;
		fChildren= new ArrayList();
		if (container != null) {
			IBuildpathEntry[] entries= container.getBuildpathEntries();
			BPListElement[] res= new BPListElement[entries.length];
			for (int i= 0; i < res.length; i++) {
				IBuildpathEntry curr= entries[i];
				BPListElement elem= BPListElement.createFromExisting(this, curr, project);
				//elem.setAttribute(CPListElement.SOURCEATTACHMENT, curr.getSourceAttachmentPath());
				//elem.setAttribute(CPListElement.JAVADOC, ScriptUI.getLibraryJavadocLocation(curr.getPath()));
				fChildren.add(elem);
			}
			fIsSystemLibrary= container.getKind() == IBuildpathContainer.K_SYSTEM;
		} else {
			fIsSystemLibrary= false;
		}
	}
	
	public BPUserLibraryElement(String name, boolean isSystemLibrary, BPListElement[] children) {
		fName= name;
		fChildren= new ArrayList();
		if (children != null) {
			for (int i= 0; i < children.length; i++) {
				fChildren.add(children[i]);
			}
		}
		fIsSystemLibrary= isSystemLibrary;
	}
	
	public BPListElement[] getChildren() {
		return (BPListElement[]) fChildren.toArray(new BPListElement[fChildren.size()]);
	}

	public String getName() {
		return fName;
	}
	
	public IPath getPath() {
		return new Path(DLTKCore.USER_LIBRARY_CONTAINER_ID).append(fName);
	}

	public boolean isSystemLibrary() {
		return fIsSystemLibrary;
	}
	
	public void add(BPListElement element) {
		if (!fChildren.contains(element)) {
			fChildren.add(element);
		}
	}
		
	private List moveUp(List elements, List move) {
		int nElements= elements.size();
		List res= new ArrayList(nElements);
		Object floating= null;
		for (int i= 0; i < nElements; i++) {
			Object curr= elements.get(i);
			if (move.contains(curr)) {
				res.add(curr);
			} else {
				if (floating != null) {
					res.add(floating);
				}
				floating= curr;
			}
		}
		if (floating != null) {
			res.add(floating);
		}
		return res;
	}
	
	public void moveUp(List toMoveUp) {
		if (toMoveUp.size() > 0) {
			fChildren= moveUp(fChildren, toMoveUp);
		}
	}
	
	public void moveDown(List toMoveDown) {
		if (toMoveDown.size() > 0) {
			Collections.reverse(fChildren);
			fChildren= moveUp(fChildren, toMoveDown);
			Collections.reverse(fChildren);
		}
	}
	
	
	public void remove(BPListElement element) {
		fChildren.remove(element);
	}
	
	public void replace(BPListElement existingElement, BPListElement element) {
		if (fChildren.contains(element)) {
			fChildren.remove(existingElement);
		} else {
			int index= fChildren.indexOf(existingElement);
			if (index != -1) {
				fChildren.set(index, element);
			} else {
				fChildren.add(element);
			}			
			copyAttribute(existingElement, element, BPListElement.ACCESSRULES);
		}
	}
	
	private void copyAttribute(BPListElement source, BPListElement target, String attributeName) {
		Object value= source.getAttribute(attributeName);
		if (value != null) {
			target.setAttribute(attributeName, value);
		}
	}

	public IBuildpathContainer getUpdatedContainer() {
		return new UpdatedBuildpathContainer();
	}
		
	public boolean hasChanges(IBuildpathContainer oldContainer) {
		if (oldContainer == null || (oldContainer.getKind() == IBuildpathContainer.K_SYSTEM) != fIsSystemLibrary) {
			return true;
		}
		IBuildpathEntry[] oldEntries= oldContainer.getBuildpathEntries();
		if (fChildren.size() != oldEntries.length) {
			return true;
		}
		for (int i= 0; i < oldEntries.length; i++) {
			BPListElement child= (BPListElement) fChildren.get(i);
			if (!child.getBuildpathEntry().equals(oldEntries[i])) {
				return true;
			}
		}
		return false;
	}
	
	
}
