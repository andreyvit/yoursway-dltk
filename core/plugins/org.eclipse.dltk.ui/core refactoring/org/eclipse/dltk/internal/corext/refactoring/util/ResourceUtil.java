/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IMember;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IOpenable;
import org.eclipse.dltk.core.ISourceModule;



public class ResourceUtil {
	
	private ResourceUtil(){
	}
	
	public static IFile[] getFiles(ISourceModule[] cus) {
		List files= new ArrayList(cus.length);
		for (int i= 0; i < cus.length; i++) {
			IResource resource= ResourceUtil.getResource(cus[i]);
			if (resource != null && resource.getType() == IResource.FILE)
				files.add(resource);
		}
		return (IFile[]) files.toArray(new IFile[files.size()]);
	}

	public static IFile getFile(ISourceModule cu) {
		IResource resource= ResourceUtil.getResource(cu);
		if (resource != null && resource.getType() == IResource.FILE)
			return (IFile)resource;
		else
			return null;
	}

	//----- other ------------------------------
			
	/**
	 * Finds an <code>IResource</code> for a given <code>ISourceModule</code>.
	 * If the parameter is a working copy then the <code>IResource</code> for
	 * the original element is returned.
	 */
	public static IResource getResource(ISourceModule cu) {
		return cu.getResource();
	}


	/**
	 * Returns the <code>IResource</code> that the given <code>IMember</code> is defined in.
	 */
	public static IResource getResource(IMember member) {
		//Assert.isTrue(!member.isBinary());
		return getResource(member.getSourceModule());
	}

	public static IResource getResource(Object o){
		if (o instanceof IResource)
			return (IResource)o;
		if (o instanceof IModelElement)
			return getResource((IModelElement)o);
		return null;	
	}

	private static IResource getResource(IModelElement element){
		if (element.getElementType() == IModelElement.SOURCE_MODULE) 
			return getResource((ISourceModule) element);
		else if (element instanceof IOpenable) 
			return element.getResource();
		else	
			return null;	
	}
}
