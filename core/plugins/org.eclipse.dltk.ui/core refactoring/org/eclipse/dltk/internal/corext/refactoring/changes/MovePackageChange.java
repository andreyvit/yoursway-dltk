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
package org.eclipse.dltk.internal.corext.refactoring.changes;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.RefactoringCoreMessages;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.ltk.core.refactoring.Change;


public class MovePackageChange extends ScriptFolderReorgChange {
	
	public MovePackageChange(IScriptFolder pack, IProjectFragment dest){
		super(pack, dest, null);
	}
	
	protected Change doPerformReorg(IProgressMonitor pm) throws ModelException{
		getPackage().move(getDestination(), null, getNewName(), true, pm);
		return null;
	}
	
	public String getName() {
		return Messages.format(RefactoringCoreMessages.MovePackageChange_move, 
			new String[]{getPackage().getElementName(), getDestination().getElementName()});
	}
}
