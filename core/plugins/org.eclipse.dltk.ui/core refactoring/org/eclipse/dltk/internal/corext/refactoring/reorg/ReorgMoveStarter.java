/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring.reorg;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgPolicy.IMovePolicy;
import org.eclipse.dltk.internal.corext.refactoring.structure.ScriptMoveRefactoring;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringExecutionHelper;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.refactoring.actions.RefactoringStarter;
import org.eclipse.dltk.internal.ui.refactoring.reorg.CreateTargetQueries;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgMoveWizard;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgQueries;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ScriptMoveProcessor;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;
import org.eclipse.swt.widgets.Shell;



public class ReorgMoveStarter {
	private final ScriptMoveProcessor fMoveProcessor;

	private ReorgMoveStarter(ScriptMoveProcessor moveProcessor) {
		Assert.isNotNull(moveProcessor);
		fMoveProcessor= moveProcessor;
	}
	
	public static ReorgMoveStarter create(IModelElement[] modelElements, IResource[] resources, IModelElement destination) throws ModelException {
		Assert.isNotNull(modelElements);
		Assert.isNotNull(resources);
		Assert.isNotNull(destination);
		IMovePolicy policy= ReorgPolicyFactory.createMovePolicy(resources, modelElements);
		if (!policy.canEnable())
			return null;
		ScriptMoveProcessor processor= new ScriptMoveProcessor(policy);
		if (! processor.setDestination(destination).isOK())
			return null;
		return new ReorgMoveStarter(processor);
	}

	public static ReorgMoveStarter create(IModelElement[] modelElements, IResource[] resources, IResource destination) throws ModelException {
		Assert.isNotNull(modelElements);
		Assert.isNotNull(resources);
		Assert.isNotNull(destination);
		IMovePolicy policy= ReorgPolicyFactory.createMovePolicy(resources, modelElements);
		if (!policy.canEnable())
			return null;
		ScriptMoveProcessor processor= new ScriptMoveProcessor(policy);
		if (! processor.setDestination(destination).isOK())
			return null;
		return new ReorgMoveStarter(processor);
	}
	
	public void run(Shell parent) throws InterruptedException, InvocationTargetException {
		try {
			ScriptMoveRefactoring ref= new ScriptMoveRefactoring(fMoveProcessor);
			if (fMoveProcessor.hasAllInputSet()) {
				IRunnableContext context= new ProgressMonitorDialog(parent);
				fMoveProcessor.setCreateTargetQueries(new CreateTargetQueries(parent));
				fMoveProcessor.setReorgQueries(new ReorgQueries(parent));
				new RefactoringExecutionHelper(ref, RefactoringCore.getConditionCheckingFailedSeverity(), true, parent, context).perform(false);
			} else  {
				RefactoringWizard wizard= new ReorgMoveWizard(ref);
				/*
				 * We want to get the shell from the refactoring dialog but it's not known at this point, 
				 * so we pass the wizard and then, once the dialog is open, we will have access to its shell.
				 */
				fMoveProcessor.setCreateTargetQueries(new CreateTargetQueries(wizard));
				fMoveProcessor.setReorgQueries(new ReorgQueries(wizard));
				new RefactoringStarter().activate(ref, wizard, parent, RefactoringMessages.OpenRefactoringWizardAction_refactoring, true); 
			}
		} catch (ModelException e) {
			ExceptionHandler.handle(e, RefactoringMessages.OpenRefactoringWizardAction_refactoring, RefactoringMessages.OpenRefactoringWizardAction_exception); 
		}
	}
}
