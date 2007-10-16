/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IModelStatusConstants;
import org.eclipse.dltk.core.IProblemRequestor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.WorkingCopyOwner;
import org.eclipse.dltk.internal.core.mixin.MixinBuilder;
import org.eclipse.dltk.internal.core.util.Messages;

public class ReconcileWorkingCopyOperation extends ModelOperation {
	boolean forceProblemDetection;

	WorkingCopyOwner workingCopyOwner;

	public HashMap problems;

	public ModelElementDeltaBuilder deltaBuilder;

	public ReconcileWorkingCopyOperation(ISourceModule module,
			boolean forceProblemDetection, WorkingCopyOwner workingCopyOwner) {
		super(new IModelElement[] { module });
		this.forceProblemDetection = forceProblemDetection;
		this.workingCopyOwner = workingCopyOwner;
	}

	protected void executeOperation() throws ModelException {
		if (this.progressMonitor != null) {
			if (this.progressMonitor.isCanceled())
				throw new OperationCanceledException();
			this.progressMonitor.beginTask(Messages.element_reconciling, 2);
		}

		SourceModule workingCopy = getWorkingCopy();
		// boolean wasConsistent = workingCopy.isConsistent();
		IProblemRequestor problemRequestor = workingCopy
				.getPerWorkingCopyInfo();

		// create the delta builder (this remembers the current content of the
		// cu)
		this.deltaBuilder = new ModelElementDeltaBuilder(workingCopy);

		// make working copy consistent if needed and compute AST if needed
		makeConsistent(workingCopy, problemRequestor);
		// report delta
		try {
			ModelElementDelta delta = this.deltaBuilder.delta;
			if (delta != null) {
				addReconcileDelta(workingCopy, delta);
			}
		} finally {
			if (this.progressMonitor != null)
				this.progressMonitor.done();
		}
	}

	/*
	 * Makes the given working copy consistent, computes the delta and computes
	 * an AST if needed. Returns the AST.
	 */
	public void makeConsistent(SourceModule workingCopy,
			IProblemRequestor problemRequestor) throws ModelException {
		if (!workingCopy.isConsistent()) {
			// make working copy consistent
			if (this.problems == null)
				this.problems = new HashMap();
			workingCopy.makeConsistent(this.progressMonitor);
			this.deltaBuilder.buildDeltas();
			// Mixin source index operation required.
			int sum = this.deltaBuilder.delta.getAffectedChildren().length;
			if (sum > 0) {
				List elements = new ArrayList();
				IScriptProject project = workingCopy.getScriptProject();
				elements.add(workingCopy);
				MixinBuilder.getDefault().buildModelElements(project, elements,
						new NullProgressMonitor(), false);
			}
			return;
		}
	}

	/**
	 * Returns the working copy this operation is working on.
	 */
	protected SourceModule getWorkingCopy() {
		return (SourceModule) getElementToProcess();
	}

	/**
	 * @see ModelOperation#isReadOnly
	 */
	public boolean isReadOnly() {
		return true;
	}

	protected IModelStatus verify() {
		IModelStatus status = super.verify();
		if (!status.isOK()) {
			return status;
		}
		SourceModule workingCopy = getWorkingCopy();
		if (!workingCopy.isWorkingCopy()) {
			return new ModelStatus(
					IModelStatusConstants.ELEMENT_DOES_NOT_EXIST, workingCopy); // was
			// destroyed
		}
		return status;
	}
}
