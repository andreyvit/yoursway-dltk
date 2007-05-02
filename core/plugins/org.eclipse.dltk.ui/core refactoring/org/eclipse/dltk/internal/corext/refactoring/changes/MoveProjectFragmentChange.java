/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.changes;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IProjectFragmentManipulationQuery;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.Change;


public class MoveProjectFragmentChange extends ProjectFragmentReorgChange {

	public MoveProjectFragmentChange(IProjectFragment root, IProject destination, IProjectFragmentManipulationQuery updateClasspathQuery) {
		super(root, destination, null, updateClasspathQuery);
	}

	protected Change doPerformReorg(IPath destinationPath, IProgressMonitor pm) throws ModelException {
		getRoot().move(destinationPath, getResourceUpdateFlags(), getUpdateModelFlags(false), null, pm);
		return null;
	}
	
	public String getName() {
		String[] keys= {getRoot().getElementName(), getDestinationProject().getName()};
		return Messages.format(RefactoringCoreMessages.MoveProjectFragmentChange_move, keys); 
	}
}
