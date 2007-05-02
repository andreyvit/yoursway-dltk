/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;



public class ParentChecker {
	private IResource[] fResources;
	private IModelElement[] fScriptElements;

	public ParentChecker(IResource[] resources, IModelElement[] modelElements) {
		Assert.isNotNull(resources);
		Assert.isNotNull(modelElements);
		fResources= resources;
		fScriptElements= modelElements;
	}
	
	public boolean haveCommonParent() {
		return getCommonParent() != null;
	}
	
	public Object getCommonParent(){
		if (fScriptElements.length == 0 && fResources.length == 0)
			return null;
		if (! resourcesHaveCommonParent() || ! modelElementsHaveCommonParent())
			return null;
		if (fScriptElements.length == 0){
			IResource commonResourceParent= getCommonResourceParent();
			Assert.isNotNull(commonResourceParent);
			IModelElement convertedToScript= DLTKCore.create(commonResourceParent);
			if (convertedToScript != null && convertedToScript.exists())
				return convertedToScript;
			else
				return commonResourceParent;
		}
		if (fResources.length == 0)
			return getCommonScriptElementParent();
			
		IResource commonResourceParent= getCommonResourceParent();
		IModelElement commonScriptElementParent= getCommonScriptElementParent();
		Assert.isNotNull(commonScriptElementParent);
		Assert.isNotNull(commonResourceParent);
		IModelElement convertedToScript= DLTKCore.create(commonResourceParent);
		if (convertedToScript == null || 
			! convertedToScript.exists() || 
			! commonScriptElementParent.equals(convertedToScript))
			return null;
		return commonScriptElementParent;	
	}

	private IModelElement getCommonScriptElementParent() {
		Assert.isNotNull(fScriptElements);
		Assert.isTrue(fScriptElements.length > 0);//safe - checked before
		return fScriptElements[0].getParent();
	}

	private IResource getCommonResourceParent() {
		Assert.isNotNull(fResources);
		Assert.isTrue(fResources.length > 0);//safe - checked before
		return fResources[0].getParent();
	}

	private boolean modelElementsHaveCommonParent() {
		if (fScriptElements.length == 0)
			return true;
		IModelElement firstParent= fScriptElements[0].getParent();
		Assert.isNotNull(firstParent); //this should never happen			
		for (int i= 1; i < fScriptElements.length; i++) {
			if (! firstParent.equals(fScriptElements[i].getParent()))
				return false;
		}
		return true;
	}

	private boolean resourcesHaveCommonParent() {
		if (fResources.length == 0)
			return true;
		IResource firstParent= fResources[0].getParent();
		Assert.isNotNull(firstParent);
		for (int i= 1; i < fResources.length; i++) {
			if (! firstParent.equals(fResources[i].getParent()))
				return false;
		}
		return true;
	}
	
	public IResource[] getResources(){
		return fResources;
	}		
		
	public IModelElement[] getScriptElements(){
		return fScriptElements;
	}

	public void removeElementsWithAncestorsOnList(boolean removeOnlyScriptElements) {
		if (! removeOnlyScriptElements){
			removeResourcesDescendantsOfResources();
			removeResourcesDescendantsOfScriptElements();
		}
		removeScriptElementsDescendantsOfScriptElements();
//		removeScriptElementsChildrenOfResources(); //this case is covered by removeUnconfirmedArchives
	}
				
	private void removeResourcesDescendantsOfScriptElements() {
		List subResources= new ArrayList(3);
		for (int i= 0; i < fResources.length; i++) {
			IResource subResource= fResources[i];
			for (int j= 0; j < fScriptElements.length; j++) {
				IModelElement superElements= fScriptElements[j];
				if (isDescendantOf(subResource, superElements))
					subResources.add(subResource);
			}
		}
		removeFromSetToDelete((IResource[]) subResources.toArray(new IResource[subResources.size()]));
	}

	private void removeScriptElementsDescendantsOfScriptElements() {
		List subElements= new ArrayList(3);
		for (int i= 0; i < fScriptElements.length; i++) {
			IModelElement subElement= fScriptElements[i];
			for (int j= 0; j < fScriptElements.length; j++) {
				IModelElement superElement= fScriptElements[j];
				if (isDescendantOf(subElement, superElement))
					subElements.add(subElement);
			}
		}
		removeFromSetToDelete((IModelElement[]) subElements.toArray(new IModelElement[subElements.size()]));
	}

	private void removeResourcesDescendantsOfResources() {
		List subResources= new ArrayList(3);
		for (int i= 0; i < fResources.length; i++) {
			IResource subResource= fResources[i];
			for (int j= 0; j < fResources.length; j++) {
				IResource superResource= fResources[j];
				if (isDescendantOf(subResource, superResource))
					subResources.add(subResource);
			}
		}
		removeFromSetToDelete((IResource[]) subResources.toArray(new IResource[subResources.size()]));
	}

	public static boolean isDescendantOf(IResource subResource, IModelElement superElement) {
		IResource parent= subResource.getParent();
		while(parent != null){
			IModelElement el= DLTKCore.create(parent);
			if (el != null && el.exists() && el.equals(superElement))
				return true;
			parent= parent.getParent();
		}
		return false;
	}

	public static boolean isDescendantOf(IModelElement subElement, IModelElement superElement) {
		if (subElement.equals(superElement))
			return false;
		IModelElement parent= subElement.getParent();
		while(parent != null){
			if (parent.equals(superElement))
				return true;
			parent= parent.getParent();
		}
		return false;
	}

	public static boolean isDescendantOf(IResource subResource, IResource superResource) {
		return ! subResource.equals(superResource) && superResource.getFullPath().isPrefixOf(subResource.getFullPath());
	}

	private void removeFromSetToDelete(IResource[] resourcesToNotDelete) {
		fResources= ReorgUtils.setMinus(fResources, resourcesToNotDelete);
	}
	
	private void removeFromSetToDelete(IModelElement[] elementsToNotDelete) {
		fScriptElements= ReorgUtils.setMinus(fScriptElements, elementsToNotDelete);
	}
}
