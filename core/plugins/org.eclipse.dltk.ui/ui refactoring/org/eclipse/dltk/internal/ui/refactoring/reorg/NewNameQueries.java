/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.dltk.core.DLTKContentTypeManager;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.IScriptFolder;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ScriptModelUtil;
import org.eclipse.dltk.internal.corext.refactoring.Checks;
import org.eclipse.dltk.internal.corext.refactoring.reorg.INewNameQueries;
import org.eclipse.dltk.internal.corext.refactoring.reorg.INewNameQuery;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


public class NewNameQueries implements INewNameQueries {

	private static final String INVALID_NAME_NO_MESSAGE= "";//$NON-NLS-1$
	private final Wizard fWizard;
	private final Shell fShell;

	public NewNameQueries() {
		fShell= null;
		fWizard= null;
	}
	
	public NewNameQueries(Wizard wizard) {
		fWizard= wizard;
		fShell= null;
	}
	
	public NewNameQueries(Shell shell) {
		fShell = shell;
		fWizard= null;
	}

	private Shell getShell() {
		Assert.isTrue(fWizard == null || fShell == null);
		if (fWizard != null)
			return fWizard.getContainer().getShell();
			
		if (fShell != null)
			return fShell;
		return DLTKUIPlugin.getActiveWorkbenchShell();
	}

	public INewNameQuery createNewSourceModuleNameQuery(ISourceModule cu, String initialSuggestedName) {
		if (DLTKCore.DEBUG) {
			System.err.println("TODO:add removeScriptLikeExtensions code here..."); //$NON-NLS-1$
		}
		String[] keys= {/*DLTKCore.removeScriptLikeExtension(*/cu.getElementName()/*)*/};
		String message= Messages.format(ReorgMessages.ReorgQueries_enterNewNameQuestion, keys); 
		return createStaticQuery(createSourceModuleNameValidator(cu), message, initialSuggestedName, getShell());
	}


	public INewNameQuery createNewResourceNameQuery(IResource res, String initialSuggestedName) {
		String[] keys= {res.getName()};
		String message= Messages.format(ReorgMessages.ReorgQueries_enterNewNameQuestion, keys); 
		return createStaticQuery(createResourceNameValidator(res), message, initialSuggestedName, getShell());
	}


	public INewNameQuery createNewPackageNameQuery(IScriptFolder pack, String initialSuggestedName) {
		String[] keys= {pack.getElementName()};
		String message= Messages.format(ReorgMessages.ReorgQueries_enterNewNameQuestion, keys); 
		return createStaticQuery(createPackageNameValidator(pack), message, initialSuggestedName, getShell());
	}

	public INewNameQuery createNewProjectFragmentNameQuery(IProjectFragment root, String initialSuggestedName) {
		String[] keys= {root.getElementName()};
		String message= Messages.format(ReorgMessages.ReorgQueries_enterNewNameQuestion, keys); 
		return createStaticQuery(createProjectFragmentNameValidator(root), message, initialSuggestedName, getShell());
	}


	public INewNameQuery createNullQuery(){
		return createStaticQuery(null);
	}


	public INewNameQuery createStaticQuery(final String newName){
		return new INewNameQuery(){
			public String getNewName() {
				return newName;
			}
		};
	}

	private static INewNameQuery createStaticQuery(final IInputValidator validator, final String message, final String initial, final Shell shell){
		return new INewNameQuery(){
			public String getNewName() {
				InputDialog dialog= new InputDialog(shell, ReorgMessages.ReorgQueries_nameConflictMessage, message, initial, validator) {
					/* (non-Javadoc)
					 * @see org.eclipse.jface.dialogs.InputDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
					 */
					protected Control createDialogArea(Composite parent) {
						Control area= super.createDialogArea(parent);
						TextFieldNavigationHandler.install(getText());
						return area;
					}
				};
				if (dialog.open() == Window.CANCEL)
					throw new OperationCanceledException();
				return dialog.getValue();
			}
		};
	}

	private static IInputValidator createResourceNameValidator(final IResource res){
		IInputValidator validator= new IInputValidator(){
			public String isValid(String newText) {
				if (newText == null || "".equals(newText) || res.getParent() == null) //$NON-NLS-1$
					return INVALID_NAME_NO_MESSAGE;
				if (res.getParent().findMember(newText) != null)
					return ReorgMessages.ReorgQueries_resourceWithThisNameAlreadyExists; 
				if (! res.getParent().getFullPath().isValidSegment(newText))
					return ReorgMessages.ReorgQueries_invalidNameMessage; 
				IStatus status= res.getParent().getWorkspace().validateName(newText, res.getType());
				if (status.getSeverity() == IStatus.ERROR)
					return status.getMessage();
					
				if (res.getName().equalsIgnoreCase(newText))
					return ReorgMessages.ReorgQueries_resourceExistsWithDifferentCaseMassage; 
					
				return null;
			}
		};
		return validator;
	}

	private static IInputValidator createSourceModuleNameValidator(final ISourceModule cu) {
		IInputValidator validator= new IInputValidator(){
			public String isValid(String newText) {
				if (newText == null || "".equals(newText)) //$NON-NLS-1$
					return INVALID_NAME_NO_MESSAGE;
				String newCuName= ScriptModelUtil.getRenamedCUName(cu, newText);
				IDLTKLanguageToolkit toolkit = null;
				toolkit = DLTKLanguageManager.getLanguageToolkit(cu);
				IStatus status= new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, 0, ReorgMessages.NewNameQueries_mustBeAScriptProject,null);
				if( toolkit != null ) {
					if( DLTKContentTypeManager.isValidFileNameForContentType(toolkit, newCuName)) {
						status = IModelStatus.VERIFIED_OK;
					}
					else {
						status = new Status(IStatus.ERROR, DLTKUIPlugin.PLUGIN_ID, ReorgMessages.NewNameQueries_invalidFileName);
					}
				}
				if (status.getSeverity() == IStatus.ERROR)
					return status.getMessage();
				RefactoringStatus refStatus;
				refStatus= Checks.checkSourceModuleNewName(cu, newText);
				if (refStatus.hasFatalError())
					return refStatus.getMessageMatchingSeverity(RefactoringStatus.FATAL);

				if (cu.getElementName().equalsIgnoreCase(newCuName))
					return ReorgMessages.ReorgQueries_resourceExistsWithDifferentCaseMassage; 
				
				return null;	
			}
		};
		return validator;
	}


	private static IInputValidator createProjectFragmentNameValidator(final IProjectFragment root) {
		return new IInputValidator() {
			IInputValidator resourceNameValidator= createResourceNameValidator(root.getResource());
			public String isValid(String newText) {
				return resourceNameValidator.isValid(newText);
			}
		};
	}
	
	private static IInputValidator createPackageNameValidator(final IScriptFolder pack) {
		IInputValidator validator= new IInputValidator(){
			public String isValid(String newText) {
				if (newText == null || "".equals(newText)) //$NON-NLS-1$
					return INVALID_NAME_NO_MESSAGE;
//				IStatus status= ScriptConventions.validatePackageName(newText);
//				if (status.getSeverity() == IStatus.ERROR)
//					return status.getMessage();
				
				IModelElement parent= pack.getParent();
//				try {
					if (parent instanceof IProjectFragment){
						if (DLTKCore.DEBUG) {
							System.err.println("TODO:NewNamequeries add isPackageNameOkInRoot check..."); //$NON-NLS-1$
						}
//						if (! RenamePackageProcessor.isPackageNameOkInRoot(newText, (IProjectFragment)parent))
//							return ReorgMessages.ReorgQueries_packagewithThatNameexistsMassage;	 
					}	
//				} catch (CoreException e) {
//					return INVALID_NAME_NO_MESSAGE;
//				}
				if (pack.getElementName().equalsIgnoreCase(newText))
					return ReorgMessages.ReorgQueries_resourceExistsWithDifferentCaseMassage; 
					
				return null;
			}
		};	
		return validator;
	}			
}
