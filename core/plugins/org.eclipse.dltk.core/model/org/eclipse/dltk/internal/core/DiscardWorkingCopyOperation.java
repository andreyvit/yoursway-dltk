/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelElementDelta;
import org.eclipse.dltk.core.ModelException;

/**
 * Discards a working copy (decrement its use count and remove its working copy info if the use count is 0)
 * and signal its removal through a delta.
 */
public class DiscardWorkingCopyOperation extends ModelOperation {
	
	public DiscardWorkingCopyOperation(IModelElement workingCopy) {
		super(new IModelElement[] {workingCopy});
	}
	protected void executeOperation() throws ModelException {
		SourceModule workingCopy = getWorkingCopy();
		ModelManager manager = ModelManager.getModelManager();
		int useCount = manager.discardPerWorkingCopyInfo(workingCopy);
		if (useCount == 0) {
			IDLTKProject scriptProject = workingCopy.getScriptProject();
			if (ExternalDLTKProject.EXTERNAL_PROJECT_NAME.equals(scriptProject.getElementName())) {
				manager.removePerProjectInfo((DLTKProject) scriptProject);
				manager.containerRemove(scriptProject);
			}
			if (!workingCopy.isPrimary()) {
				// report removedscriptdelta for a non-primary working copy
				ModelElementDelta delta = new ModelElementDelta(this.getModel());
				delta.removed(workingCopy);
				addDelta(delta);
				removeReconcileDelta(workingCopy);
			} else {
				if (workingCopy.getResource().isAccessible()) {
					// report a F_PRIMARY_WORKING_COPY change delta for a primary working copy
					ModelElementDelta delta = new ModelElementDelta(this.getModel());
					delta.changed(workingCopy, IModelElementDelta.F_PRIMARY_WORKING_COPY);
					addDelta(delta);
				} else {
					// report a REMOVED delta
					ModelElementDelta delta = new ModelElementDelta(this.getModel());
					delta.removed(workingCopy, IModelElementDelta.F_PRIMARY_WORKING_COPY);
					addDelta(delta);
				}
			}
		}
	}
	/**
	 * Returns the working copy this operation is working on.
	 */
	protected SourceModule getWorkingCopy() {
		return (SourceModule)getElementToProcess();
	}
	/**
	 * @see ModelOperation#isReadOnly
	 */
	public boolean isReadOnly() {
		return true;
	}
}
