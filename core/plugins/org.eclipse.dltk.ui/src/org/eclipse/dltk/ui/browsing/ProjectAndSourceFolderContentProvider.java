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
package org.eclipse.dltk.ui.browsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.jface.viewers.IStructuredSelection;

class ProjectAndSourceFolderContentProvider extends
		ScriptBrowsingContentProvider {

	ProjectAndSourceFolderContentProvider(ScriptBrowsingPart browsingPart,
			IDLTKLanguageToolkit languageToolkit) {
		super(false, browsingPart, languageToolkit);
	}

	/*
	 * (non-Javadoc) Method declared on ITreeContentProvider.
	 */
	public Object[] getChildren(Object element) {
		if (!exists(element))
			return NO_CHILDREN;

		try {
			startReadInDisplayThread();
			if (element instanceof IStructuredSelection) {
				Assert.isLegal(false);
				Object[] result = new Object[0];
				Class clazz = null;
				Iterator iter = ((IStructuredSelection) element).iterator();
				while (iter.hasNext()) {
					Object item = iter.next();
					if (clazz == null)
						clazz = item.getClass();
					if (clazz == item.getClass())
						result = concatenate(result, getChildren(item));
					else
						return NO_CHILDREN;
				}
				return result;
			}
			if (element instanceof IStructuredSelection) {
				Assert.isLegal(false);
				Object[] result = new Object[0];
				Iterator iter = ((IStructuredSelection) element).iterator();
				while (iter.hasNext())
					result = concatenate(result, getChildren(iter.next()));
				return result;
			}
			if (element instanceof IScriptProject)
				return getProjectFragments((IScriptProject) element);
			if (element instanceof IProjectFragment)
				return NO_CHILDREN;

			Object[] children = super.getChildren(element);
			
			// We need to filter all elements with different nature
			List newObjs = new ArrayList();
			for (int i = 0; i < children.length; i++) {
				if (children[i] instanceof IModelElement) {
					IDLTKLanguageToolkit languageToolkit = DLTKLanguageManager
							.getLanguageToolkit((IModelElement) children[i]);
					if (getToolkit().equals(languageToolkit)) {
						newObjs.add(children[i]);
					}
				}
				else {
					newObjs.add(children[i]);
				}
			}
			return newObjs.toArray();
		} catch (ModelException e) {
			return NO_CHILDREN;
		} catch (CoreException e) {
			if (DLTKCore.DEBUG) {
				e.printStackTrace();
			}
			return NO_CHILDREN;
		} finally {
			finishedReadInDisplayThread();
		}
	}

	protected Object[] getProjectFragments(IScriptProject project)
			throws ModelException {
		if (!project.getProject().isOpen())
			return NO_CHILDREN;

		IProjectFragment[] roots = project.getProjectFragments();
		List list = new ArrayList(roots.length);
		// filter out package fragments that correspond to projects and
		// replace them with the package fragments directly
		for (int i = 0; i < roots.length; i++) {
			IProjectFragment root = roots[i];
			if (!isProjectProjectFragment(root))
				list.add(root);
		}
		return list.toArray();
	}

	/*
	 * 
	 * @see ITreeContentProvider
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IScriptProject && super.hasChildren(element);
	}
}
