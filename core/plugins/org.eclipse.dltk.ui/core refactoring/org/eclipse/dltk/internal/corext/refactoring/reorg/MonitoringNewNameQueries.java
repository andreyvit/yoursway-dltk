/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.internal.ui.model.DLTKElementResourceMapping;
import org.eclipse.ltk.core.refactoring.participants.ReorgExecutionLog;



public class MonitoringNewNameQueries implements INewNameQueries {
	private INewNameQueries fDelegate;
	private ReorgExecutionLog fExecutionLog;
	public MonitoringNewNameQueries(INewNameQueries delegate, ReorgExecutionLog log) {
		fDelegate= delegate;
		fExecutionLog= log;
	}
	public INewNameQuery createNewSourceModuleNameQuery(final ISourceModule cu, final String initialSuggestedName) {
		return new INewNameQuery() {
			public String getNewName() {
				String result= fDelegate.createNewSourceModuleNameQuery(cu, initialSuggestedName).getNewName();
				String newName= DLTKModelUtil.getRenamedCUName(cu, result);
				fExecutionLog.setNewName(cu, newName);
				ResourceMapping mapping= DLTKElementResourceMapping.create(cu);
				if (mapping != null) {
					fExecutionLog.setNewName(mapping, newName);
				}
				return result;
			}
		};
	}
	public INewNameQuery createNewProjectFragmentNameQuery(final IProjectFragment root, final String initialSuggestedName) {
		return new INewNameQuery() {
			public String getNewName() {
				String result= fDelegate.createNewProjectFragmentNameQuery(root, initialSuggestedName).getNewName();
				fExecutionLog.setNewName(root, result);
				ResourceMapping mapping= DLTKElementResourceMapping.create(root);
				if (mapping != null) {
					fExecutionLog.setNewName(mapping, result);
				}
				return result;
			}
		};
	}
	public INewNameQuery createNewPackageNameQuery(final IScriptFolder pack, final String initialSuggestedName) {
		return new INewNameQuery() {
			public String getNewName() {
				String result= fDelegate.createNewPackageNameQuery(pack, initialSuggestedName).getNewName();
				fExecutionLog.setNewName(pack, result);
				ResourceMapping mapping= DLTKElementResourceMapping.create(pack);
				if (mapping != null) {
					int index= result.lastIndexOf('.');
					String newFolderName= index == -1 ? result : result.substring(index + 1);
					fExecutionLog.setNewName(mapping, newFolderName);
				}
				return result;
			}
		};
	}
	public INewNameQuery createNewResourceNameQuery(final IResource res, final String initialSuggestedName) {
		return new INewNameQuery() {
			public String getNewName() {
				String result= fDelegate.createNewResourceNameQuery(res, initialSuggestedName).getNewName();
				fExecutionLog.setNewName(res, result);
				return result;
			}
		};
	}
	public INewNameQuery createNullQuery() {
		return fDelegate.createNullQuery();
	}
	public INewNameQuery createStaticQuery(String newName) {
		return fDelegate.createStaticQuery(newName);
	}
}
