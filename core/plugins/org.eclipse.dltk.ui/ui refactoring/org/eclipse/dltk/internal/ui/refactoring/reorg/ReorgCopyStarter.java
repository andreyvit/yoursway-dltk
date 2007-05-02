/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringExecutionHelper;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.core.refactoring.participants.CopyRefactoring;
import org.eclipse.swt.widgets.Shell;


public class ReorgCopyStarter {
	
	private final ScriptCopyProcessor fCopyProcessor;

	private ReorgCopyStarter(ScriptCopyProcessor copyProcessor) {
		Assert.isNotNull(copyProcessor);
		fCopyProcessor= copyProcessor;
	}
	
	public static ReorgCopyStarter create(IModelElement[] modelElements, IResource[] resources, IModelElement destination) throws ModelException {
		Assert.isNotNull(modelElements);
		Assert.isNotNull(resources);
		Assert.isNotNull(destination);
		ScriptCopyProcessor copyProcessor= ScriptCopyProcessor.create(resources, modelElements);
		if (copyProcessor == null)
			return null;
		if (! copyProcessor.setDestination(destination).isOK())
			return null;
		return new ReorgCopyStarter(copyProcessor);
	}

	public static ReorgCopyStarter create(IModelElement[] modelElements, IResource[] resources, IResource destination) throws ModelException {
		Assert.isNotNull(modelElements);
		Assert.isNotNull(resources);
		Assert.isNotNull(destination);
		ScriptCopyProcessor copyProcessor= ScriptCopyProcessor.create(resources, modelElements);
		if (copyProcessor == null)
			return null;
		if (! copyProcessor.setDestination(destination).isOK())
			return null;
		return new ReorgCopyStarter(copyProcessor);
	}
	
	public void run(Shell parent) throws InterruptedException, InvocationTargetException {
		IRunnableContext context= new ProgressMonitorDialog(parent);
		fCopyProcessor.setNewNameQueries(new NewNameQueries(parent));
		fCopyProcessor.setReorgQueries(new ReorgQueries(parent));
		new RefactoringExecutionHelper(new CopyRefactoring(fCopyProcessor), RefactoringCore.getConditionCheckingFailedSeverity(), false, parent, context).perform(false);
	}
}
