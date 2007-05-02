/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.corext.refactoring;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.rename.RenameResourceProcessor;
import org.eclipse.dltk.internal.corext.refactoring.rename.ScriptRenameRefactoring;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ReorgPolicyFactory;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ScriptDeleteProcessor;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgPolicy.IMovePolicy;
import org.eclipse.dltk.internal.corext.refactoring.structure.ScriptMoveRefactoring;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringExecutionHelper;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.refactoring.actions.RefactoringStarter;
import org.eclipse.dltk.internal.ui.refactoring.reorg.CreateTargetQueries;
import org.eclipse.dltk.internal.ui.refactoring.reorg.DeleteUserInterfaceManager;
import org.eclipse.dltk.internal.ui.refactoring.reorg.RenameUserInterfaceManager;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgMoveWizard;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ReorgQueries;
import org.eclipse.dltk.internal.ui.refactoring.reorg.ScriptMoveProcessor;
import org.eclipse.dltk.ui.refactoring.RenameSupport;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ltk.core.refactoring.RefactoringCore;
import org.eclipse.ltk.core.refactoring.participants.DeleteRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;
import org.eclipse.swt.widgets.Shell;



/**
 * Helper class to run refactorings from action code.
 * <p>
 * This class has been introduced to decouple actions from the refactoring code, in order not to eagerly load refactoring classes during action initialization.
 * </p>
 * 
	 *
 */
public final class RefactoringExecutionStarter {
	public static void startDeleteRefactoring(final Object[] elements, final Shell shell) throws CoreException {
		final DeleteRefactoring refactoring= new DeleteRefactoring(new ScriptDeleteProcessor(elements));
		DeleteUserInterfaceManager.getDefault().getStarter(refactoring).activate(refactoring, shell, false);
	}
	public static void startCutRefactoring(final Object[] elements, final Shell shell) throws CoreException, InterruptedException, InvocationTargetException {
		final ScriptDeleteProcessor processor= new ScriptDeleteProcessor(elements);		
		processor.setQueries(new ReorgQueries(shell));
		new RefactoringExecutionHelper(new DeleteRefactoring(processor), RefactoringCore.getConditionCheckingFailedSeverity(), false, shell, new ProgressMonitorDialog(shell)).perform(false);
	}
	public static void startRenameRefactoring(final IModelElement element, final Shell shell) throws CoreException {
		final RenameSupport support= createRenameSupport(element, null, RenameSupport.UPDATE_REFERENCES);
		if (support != null && support.preCheck().isOK())
			support.openDialog(shell);
	}
	private static RenameSupport createRenameSupport(IModelElement element, String newName, int flags) throws CoreException {
		switch (element.getElementType()) {
			case IModelElement.SCRIPT_PROJECT:
				return RenameSupport.create((IDLTKProject) element, newName, flags);
			case IModelElement.PROJECT_FRAGMENT:
				return RenameSupport.create((IProjectFragment) element, newName);
			case IModelElement.SCRIPT_FOLDER:
				return RenameSupport.create((IScriptFolder) element, newName, flags);
			case IModelElement.SOURCE_MODULE:
				return RenameSupport.create((ISourceModule) element, newName, flags);
//			case IModelElement.TYPE:
//				return RenameSupport.create((IType) element, newName, flags);
//			case IModelElement.METHOD:
//				final IMethod method= (IMethod) element;
//				if (method.isConstructor())
//					return createRenameSupport(method.getDeclaringType(), newName, flags);
//				else
//					return RenameSupport.create((IMethod) element, newName, flags);
//			case IModelElement.FIELD:
//				return RenameSupport.create((IField) element, newName, flags);
//			case IModelElement.TYPE_PARAMETER:
//				return RenameSupport.create((ITypeParameter) element, newName, flags);
//			case IModelElement.LOCAL_VARIABLE:
//				return RenameSupport.create((ILocalVariable) element, newName, flags);
		}
		return null;
	}
	public static void startRenameResourceRefactoring(final IResource resource, final Shell shell) throws CoreException {
		final ScriptRenameRefactoring refactoring= new ScriptRenameRefactoring(new RenameResourceProcessor(resource));
		RenameUserInterfaceManager.getDefault().getStarter(refactoring).activate(refactoring, shell, true);
	}
	public static void startRefactoring(final IResource[] resources, final IModelElement[] elements, final Shell shell) throws ModelException {
		IMovePolicy policy= ReorgPolicyFactory.createMovePolicy(resources, elements);
		if (policy.canEnable()) {
			final ScriptMoveProcessor processor= new ScriptMoveProcessor(policy);
			final ScriptMoveRefactoring refactoring= new ScriptMoveRefactoring(processor);
			final RefactoringWizard wizard= new ReorgMoveWizard(refactoring);
			processor.setCreateTargetQueries(new CreateTargetQueries(wizard));
			processor.setReorgQueries(new ReorgQueries(wizard));
			new RefactoringStarter().activate(refactoring, wizard, shell, RefactoringMessages.OpenRefactoringWizardAction_refactoring, true);
		}
	}
}
