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
package org.eclipse.dltk.internal.ui.refactoring.reorg;

import org.eclipse.core.resources.IResource;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.corext.refactoring.reorg.ICreateTargetQuery;
import org.eclipse.dltk.internal.corext.refactoring.reorg.IReorgDestinationValidator;
import org.eclipse.dltk.internal.ui.refactoring.QualifiedNameComponent;
import org.eclipse.dltk.internal.ui.refactoring.RefactoringMessages;
import org.eclipse.dltk.internal.ui.util.SWTUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;




public class ReorgMoveWizard extends RefactoringWizard {

	public ReorgMoveWizard(MoveRefactoring ref) {
		super(ref, DIALOG_BASED_USER_INTERFACE | computeHasPreviewPage(ref));
		if (isTextualMove(ref))
			setDefaultPageTitle(ReorgMessages.ReorgMoveWizard_textual_move); 
		else
			setDefaultPageTitle(ReorgMessages.ReorgMoveWizard_3); 
	}
	
	private static boolean isTextualMove(MoveRefactoring ref) {
		ScriptMoveProcessor moveProcessor= (ScriptMoveProcessor) ref.getAdapter(ScriptMoveProcessor.class);
		return moveProcessor.isTextualMove();
	}

	private static int computeHasPreviewPage(MoveRefactoring refactoring) {
		ScriptMoveProcessor processor= (ScriptMoveProcessor)refactoring.getAdapter(ScriptMoveProcessor.class);
		if (processor.canUpdateReferences() || processor.canEnableQualifiedNameUpdating())
			return NONE;
		return NO_PREVIEW_PAGE;
	}

	protected void addUserInputPages() {
		addPage(new MoveInputPage());
	}
	
	private static class MoveInputPage extends ReorgUserInputPage{

		private static final String PAGE_NAME= "MoveInputPage"; //$NON-NLS-1$
		private Button fReferenceCheckbox;
		private Button fQualifiedNameCheckbox;
		private QualifiedNameComponent fQualifiedNameComponent;
		private ICreateTargetQuery fCreateTargetQuery;
		
		private Object fDestination;
		
		public MoveInputPage() {
			super(PAGE_NAME);
		}

		private ScriptMoveProcessor getScriptMoveProcessor(){
			return (ScriptMoveProcessor)getRefactoring().getAdapter(ScriptMoveProcessor.class);
		}

		protected Object getInitiallySelectedElement() {
			return getScriptMoveProcessor().getCommonParentForInputElements();
		}
		
		protected IModelElement[] getScriptElements() {
			return getScriptMoveProcessor().getScriptElements();
		}

		protected IResource[] getResources() {
			return getScriptMoveProcessor().getResources();
		}

		protected IReorgDestinationValidator getDestinationValidator() {
			return getScriptMoveProcessor();
		}

		protected boolean performFinish() {
			return super.performFinish() || getScriptMoveProcessor().wasCanceled(); //close the dialog if canceled
		}
		
		protected RefactoringStatus verifyDestination(Object selected) throws ModelException{
			ScriptMoveProcessor processor= getScriptMoveProcessor();
			final RefactoringStatus refactoringStatus;
			if (selected instanceof IModelElement)
				refactoringStatus= processor.setDestination((IModelElement)selected);
			else if (selected instanceof IResource)
				refactoringStatus= processor.setDestination((IResource)selected);
			else refactoringStatus= RefactoringStatus.createFatalErrorStatus(ReorgMessages.ReorgMoveWizard_4); 
			
			updateUIStatus();
			fDestination= selected;
			return refactoringStatus;
		}
	
		private void updateUIStatus() {
			getRefactoringWizard().setForcePreviewReview(false);
			ScriptMoveProcessor processor= getScriptMoveProcessor();
			if (fReferenceCheckbox != null){
				fReferenceCheckbox.setEnabled(canUpdateReferences());
				processor.setUpdateReferences(fReferenceCheckbox.getEnabled() && fReferenceCheckbox.getSelection());
			}
			if (fQualifiedNameCheckbox != null){
				boolean enabled= processor.canEnableQualifiedNameUpdating();
				fQualifiedNameCheckbox.setEnabled(enabled);
				if (enabled) {
					fQualifiedNameComponent.setEnabled(processor.getUpdateQualifiedNames());
					if (processor.getUpdateQualifiedNames())
						getRefactoringWizard().setForcePreviewReview(true);
				} else {
					fQualifiedNameComponent.setEnabled(false);
				}
				processor.setUpdateQualifiedNames(fQualifiedNameCheckbox.getEnabled() && fQualifiedNameCheckbox.getSelection());
			}
		}

		private void addUpdateReferenceComponent(Composite result) {
			final ScriptMoveProcessor processor= getScriptMoveProcessor();
			if (! processor.canUpdateReferences())
				return;
			fReferenceCheckbox= new Button(result, SWT.CHECK);
			fReferenceCheckbox.setText(ReorgMessages.ScriptMoveAction_update_references); 
			fReferenceCheckbox.setSelection(processor.getUpdateReferences());
			fReferenceCheckbox.setEnabled(canUpdateReferences());
			
			fReferenceCheckbox.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					processor.setUpdateReferences(((Button)e.widget).getSelection());
					updateUIStatus();
				}
			});
		}

		private void addUpdateQualifiedNameComponent(Composite parent, int marginWidth) {
			final ScriptMoveProcessor processor= getScriptMoveProcessor();
			if (!processor.canEnableQualifiedNameUpdating() || !processor.canUpdateQualifiedNames())
				return;
			fQualifiedNameCheckbox= new Button(parent, SWT.CHECK);
			int indent= marginWidth + fQualifiedNameCheckbox.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
			fQualifiedNameCheckbox.setText(RefactoringMessages.RenameInputWizardPage_update_qualified_names); 
			fQualifiedNameCheckbox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			fQualifiedNameCheckbox.setSelection(processor.getUpdateQualifiedNames());
		
			fQualifiedNameComponent= new QualifiedNameComponent(parent, SWT.NONE, processor, getRefactoringSettings());
			fQualifiedNameComponent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			GridData gd= (GridData)fQualifiedNameComponent.getLayoutData();
			gd.horizontalAlignment= GridData.FILL;
			gd.horizontalIndent= indent;
			updateQualifiedNameUpdating(processor, processor.getUpdateQualifiedNames());

			fQualifiedNameCheckbox.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					boolean enabled= ((Button)e.widget).getSelection();
					updateQualifiedNameUpdating(processor, enabled);
				}

			});
		}
		
		private void updateQualifiedNameUpdating(final ScriptMoveProcessor processor, boolean enabled) {
			fQualifiedNameComponent.setEnabled(enabled);
			processor.setUpdateQualifiedNames(enabled);
			updateUIStatus();
		}
		
		public void createControl(Composite parent) {
			Composite result;
			
			boolean showDestinationTree= ! getScriptMoveProcessor().hasDestinationSet();
			if (showDestinationTree) {
				fCreateTargetQuery= getScriptMoveProcessor().getCreateTargetQuery();
				super.createControl(parent);
				getTreeViewer().getTree().setFocus();
				result= (Composite)super.getControl();
			} else  {
				initializeDialogUnits(parent);
				result= new Composite(parent, SWT.NONE);
				setControl(result);
				result.setLayout(new GridLayout());
				Dialog.applyDialogFont(result);
			}
			addUpdateReferenceComponent(result);
			addUpdateQualifiedNameComponent(result, ((GridLayout)result.getLayout()).marginWidth);
			setControl(result);
			Dialog.applyDialogFont(result);
		}

		protected Control addLabel(Composite parent) {
			if (fCreateTargetQuery != null) {
				Composite firstLine= new Composite(parent, SWT.NONE);
				GridLayout layout= new GridLayout(2, false);
				layout.marginHeight= layout.marginWidth= 0;
				firstLine.setLayout(layout);
				firstLine.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
				Control label= super.addLabel(firstLine);
				label.addTraverseListener(new TraverseListener() {
					public void keyTraversed(TraverseEvent e) {
						if (e.detail == SWT.TRAVERSE_MNEMONIC && e.doit) {
							e.detail= SWT.TRAVERSE_NONE;
							getTreeViewer().getTree().setFocus();
						}
					}
				});
				
				Button newButton= new Button(firstLine, SWT.PUSH);
				newButton.setText(fCreateTargetQuery.getNewButtonLabel());
				GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
				gd.widthHint = SWTUtil.getButtonWidthHint(newButton);
				newButton.setLayoutData(gd);
				newButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						doNewButtonPressed();
					}
				});
				
				return firstLine;
				
			} else {
				return super.addLabel(parent);
			}
		}
		
		private boolean canUpdateReferences() {
			return getScriptMoveProcessor().canUpdateReferences();
		}

		private void doNewButtonPressed() {
			Object newElement= fCreateTargetQuery.getCreatedTarget(fDestination);
			if (newElement != null) {
				TreeViewer viewer= getTreeViewer();
				ITreeContentProvider contentProvider= (ITreeContentProvider) viewer.getContentProvider();
				viewer.refresh(contentProvider.getParent(newElement));
				viewer.setSelection(new StructuredSelection(newElement), true);
				viewer.getTree().setFocus();
			}
		}
	}
}
