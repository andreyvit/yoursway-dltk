/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.wizards;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.DLTKModelUtil;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IDLTKProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IModelStatus;
import org.eclipse.dltk.core.IProjectFragment;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.BuildpathEntry;
import org.eclipse.dltk.internal.corext.util.Messages;
import org.eclipse.dltk.internal.ui.dialogs.StatusInfo;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.util.CoreUtility;
import org.eclipse.dltk.internal.ui.wizards.NewWizardMessages;
import org.eclipse.dltk.internal.ui.wizards.TypedElementSelectionValidator;
import org.eclipse.dltk.internal.ui.wizards.TypedViewerFilter;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.dltk.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.ModelElementLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

public class NewSourceFolderWizardPage extends NewElementWizardPage {
		
	private static final String PAGE_NAME= "NewSourceFolderWizardPage"; //$NON-NLS-1$

	private StringButtonDialogField fProjectField;
	private StatusInfo fProjectStatus;
	
	private StringButtonDialogField fRootDialogField;
	private StatusInfo fRootStatus;
	
	private SelectionButtonDialogField fExcludeInOthersFields;
	
	private IWorkspaceRoot fWorkspaceRoot;
	
	private IDLTKProject fCurrJProject;
	private IBuildpathEntry[] fEntries;
	
	private IBuildpathEntry[] fNewEntries;
	
	private boolean fIsProjectAsSourceFolder;
	
	private IProjectFragment fCreatedRoot;
	
	public NewSourceFolderWizardPage() {
		super(PAGE_NAME);
		
		setTitle(NewWizardMessages.NewSourceFolderWizardPage_title); 
		setDescription(NewWizardMessages.NewSourceFolderWizardPage_description);		 
		
		fWorkspaceRoot= ResourcesPlugin.getWorkspace().getRoot();
		
		RootFieldAdapter adapter= new RootFieldAdapter();
		
		fProjectField= new StringButtonDialogField(adapter);
		fProjectField.setDialogFieldListener(adapter);
		fProjectField.setLabelText(NewWizardMessages.NewSourceFolderWizardPage_project_label); 
		fProjectField.setButtonLabel(NewWizardMessages.NewSourceFolderWizardPage_project_button);	 
		
		fRootDialogField= new StringButtonDialogField(adapter);
		fRootDialogField.setDialogFieldListener(adapter);
		fRootDialogField.setLabelText(NewWizardMessages.NewSourceFolderWizardPage_root_label); 
		fRootDialogField.setButtonLabel(NewWizardMessages.NewSourceFolderWizardPage_root_button); 
		
		fExcludeInOthersFields= new SelectionButtonDialogField(SWT.CHECK);
		fExcludeInOthersFields.setDialogFieldListener(adapter);
		fExcludeInOthersFields.setLabelText(NewWizardMessages.NewSourceFolderWizardPage_exclude_label); 
		
		fExcludeInOthersFields.setEnabled(DLTKCore.ENABLED.equals(DLTKCore.getOption(DLTKCore.CORE_ENABLE_BUILDPATH_EXCLUSION_PATTERNS)));
		
		fRootStatus= new StatusInfo();
		fProjectStatus= new StatusInfo();
	}
			
	// -------- Initialization ---------
		
	public void init(IStructuredSelection selection) {
		if (selection == null || selection.isEmpty()) {
			setDefaultAttributes();
			return;
		}
		
		Object selectedElement= selection.getFirstElement();
		if (selectedElement == null) {
			selectedElement= EditorUtility.getActiveEditorModelInput();
		}				
		
		String projPath= null;
		
		if (selectedElement instanceof IResource) {
			IProject proj= ((IResource)selectedElement).getProject();
			if (proj != null) {
				projPath= proj.getFullPath().makeRelative().toString();
			}	
		} else if (selectedElement instanceof IModelElement) {
			IDLTKProject jproject= ((IModelElement)selectedElement).getScriptProject();
			if (jproject != null) {
				projPath= jproject.getProject().getFullPath().makeRelative().toString();
			}
		}	
		
		if (projPath != null) {
			fProjectField.setText(projPath);
			fRootDialogField.setText(""); //$NON-NLS-1$
		} else {
			setDefaultAttributes();
		}
	}
	
	private void setDefaultAttributes() {
		String projPath= ""; //$NON-NLS-1$
		
		// find the first script project
		IProject[] projects= fWorkspaceRoot.getProjects();
		for (int i= 0; i < projects.length; i++) {
			IProject proj= projects[i];
			if (DLTKLanguageManager.hasScriptNature(proj)) {
				projPath= proj.getFullPath().makeRelative().toString();
				break;
			}
		}
		fProjectField.setText(projPath);
		fRootDialogField.setText("");		 //$NON-NLS-1$
	}
	

	// -------- UI Creation ---------

	/*
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		
		Composite composite= new Composite(parent, SWT.NONE);
			
		GridLayout layout= new GridLayout();
		layout.numColumns= 3;
		composite.setLayout(layout);
				
		fProjectField.doFillIntoGrid(composite, 3);	
		fRootDialogField.doFillIntoGrid(composite, 3);
		fExcludeInOthersFields.doFillIntoGrid(composite, 3);
		
		int maxFieldWidth= convertWidthInCharsToPixels(40);
		LayoutUtil.setWidthHint(fProjectField.getTextControl(null), maxFieldWidth);
		LayoutUtil.setHorizontalGrabbing(fProjectField.getTextControl(null));	
		LayoutUtil.setWidthHint(fRootDialogField.getTextControl(null), maxFieldWidth);	
			
		setControl(composite);
		Dialog.applyDialogFont(composite);
		if (DLTKCore.DEBUG) {
			System.err.println("Add help support here...");
		}
		
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, IScriptHelpContextIds.NEW_PACKAGEROOT_WIZARD_PAGE);		
	}
	
	/*
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			fRootDialogField.setFocus();
		}
	}	
		
	// -------- ContainerFieldAdapter --------

	private class RootFieldAdapter implements IStringButtonAdapter, IDialogFieldListener {

		// -------- IStringButtonAdapter
		public void changeControlPressed(DialogField field) {
			packRootChangeControlPressed(field);
		}
		
		// -------- IDialogFieldListener
		public void dialogFieldChanged(DialogField field) {
			packRootDialogFieldChanged(field);
		}
	}
	protected void packRootChangeControlPressed(DialogField field) {
		if (field == fRootDialogField) {
			IPath initialPath= new Path(fRootDialogField.getText());
			String title= NewWizardMessages.NewSourceFolderWizardPage_ChooseExistingRootDialog_title; 
			String message= NewWizardMessages.NewSourceFolderWizardPage_ChooseExistingRootDialog_description; 
			IFolder folder= chooseFolder(title, message, initialPath);
			if (folder != null) {
				IPath path= folder.getFullPath().removeFirstSegments(1);
				fRootDialogField.setText(path.toString());
			}
		} else if (field == fProjectField) {
			IDLTKProject jproject= chooseProject();
			if (jproject != null) {
				IPath path= jproject.getProject().getFullPath().makeRelative();
				fProjectField.setText(path.toString());
			}
		} 
	}	
	
	protected void packRootDialogFieldChanged(DialogField field) {
		if (field == fRootDialogField) {
			updateRootStatus();
		} else if (field == fProjectField) {
			updateProjectStatus();
			updateRootStatus();
		} else if (field == fExcludeInOthersFields) {
			updateRootStatus();
		}
		updateStatus(new IStatus[] { fProjectStatus, fRootStatus });
	}
	
	
	private void updateProjectStatus() {
		fCurrJProject= null;
		fIsProjectAsSourceFolder= false;
		
		String str= fProjectField.getText();
		if (str.length() == 0) {
			fProjectStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_EnterProjectName); 
			return;
		}
		IPath path= new Path(str);
		if (path.segmentCount() != 1) {
			fProjectStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_InvalidProjectPath); 
			return;
		}
		IProject project= fWorkspaceRoot.getProject(path.toString());
		if (!project.exists()) {
			fProjectStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_ProjectNotExists); 
			return;
		}
		try {
			if (DLTKLanguageManager.hasScriptNature(project)) {
				fCurrJProject= DLTKCore.create(project);
				fEntries= fCurrJProject.getRawBuildpath();				
				fProjectStatus.setOK();
				return;
			}
		} catch (CoreException e) {
			DLTKUIPlugin.log(e);
			fCurrJProject= null;
		}	
		fProjectStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_NotAScriptProject); 
	}

	private void updateRootStatus() {
		fRootDialogField.enableButton(fCurrJProject != null);
		fIsProjectAsSourceFolder= false;
		if (fCurrJProject == null) {
			return;
		}
		fRootStatus.setOK();
		
		IPath projPath= fCurrJProject.getProject().getFullPath();
		String str= fRootDialogField.getText();
		if (str.length() == 0) {
			fRootStatus.setError(Messages.format(NewWizardMessages.NewSourceFolderWizardPage_error_EnterRootName, fCurrJProject.getProject().getFullPath().toString())); 
		} else {
			IPath path= projPath.append(str);
			IStatus validate= fWorkspaceRoot.getWorkspace().validatePath(path.toString(), IResource.FOLDER);
			if (validate.matches(IStatus.ERROR)) {
				fRootStatus.setError(Messages.format(NewWizardMessages.NewSourceFolderWizardPage_error_InvalidRootName, validate.getMessage())); 
			} else {
				IResource res= fWorkspaceRoot.findMember(path);
				if (res != null) {
					if (res.getType() != IResource.FOLDER) {
						fRootStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_NotAFolder); 
						return;
					}
				} else {
					URI projLocation= fCurrJProject.getProject().getLocationURI();
					if (projLocation != null) {
						try {
							IFileStore store= EFS.getStore(projLocation).getChild(str);
							if (store.fetchInfo().exists()) {
								fRootStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_AlreadyExistingDifferentCase); 
								return;
							}
						} catch (CoreException e) {
							// we couldn't create the file store. Ignore the exception
							// since we can't check if the file exist. Pretend that it
							// doesn't.
						}
					}
				}
				ArrayList newEntries= new ArrayList(fEntries.length + 1);
				int projectEntryIndex= -1;
				
				for (int i= 0; i < fEntries.length; i++) {
					IBuildpathEntry curr= fEntries[i];
					if (curr.getEntryKind() == IBuildpathEntry.BPE_SOURCE) {
						if (path.equals(curr.getPath())) {
							fRootStatus.setError(NewWizardMessages.NewSourceFolderWizardPage_error_AlreadyExisting); 
							return;
						}
						if (projPath.equals(curr.getPath())) {
							projectEntryIndex= i;
						}	
					}
					newEntries.add(curr);
				}
				
				IBuildpathEntry newEntry= DLTKCore.newSourceEntry(path);
				
				Set modified= new HashSet();				
				if (fExcludeInOthersFields.isSelected()) {
					addExclusionPatterns(newEntry, newEntries, modified);
					IBuildpathEntry entry= DLTKCore.newSourceEntry(path);
					insertAtEndOfCategory(entry, newEntries);
				} else {
					if (projectEntryIndex != -1) {
						fIsProjectAsSourceFolder= true;
						newEntries.set(projectEntryIndex, newEntry);
					} else {
						IBuildpathEntry entry= DLTKCore.newSourceEntry(path);
						insertAtEndOfCategory(entry, newEntries);
					}
				}
					
				fNewEntries= (IBuildpathEntry[]) newEntries.toArray(new IBuildpathEntry[newEntries.size()]);

				IModelStatus status= BuildpathEntry.validateBuildpath(fCurrJProject, fNewEntries);
				if (!status.isOK()) {
					fRootStatus.setError(status.getMessage());
					return;
				} else if (fIsProjectAsSourceFolder) {
					fRootStatus.setInfo(NewWizardMessages.NewSourceFolderWizardPage_warning_ReplaceSF); 
					return;
				}
				if (!modified.isEmpty()) {
					fRootStatus.setInfo(Messages.format(NewWizardMessages.NewSourceFolderWizardPage_warning_AddedExclusions, String.valueOf(modified.size()))); 
					return;
				}
			}
		}
	}
	
	private void insertAtEndOfCategory(IBuildpathEntry entry, List entries) {
		int length= entries.size();
		IBuildpathEntry[] elements= (IBuildpathEntry[])entries.toArray(new IBuildpathEntry[length]);
		int i= 0;
		while (i < length && elements[i].getEntryKind() != entry.getEntryKind()) {
			i++;
		}
		if (i < length) {
			i++;
			while (i < length && elements[i].getEntryKind() == entry.getEntryKind()) {
				i++;
			}
			entries.add(i, entry);
			return;
		}
		
		switch (entry.getEntryKind()) {
		case IBuildpathEntry.BPE_SOURCE:
			entries.add(0, entry);
			break;
		case IBuildpathEntry.BPE_CONTAINER:
		case IBuildpathEntry.BPE_LIBRARY:
		case IBuildpathEntry.BPE_PROJECT:		
		default:
			entries.add(entry);
			break;
		}
	}
	
	private void addExclusionPatterns(IBuildpathEntry newEntry, List existing, Set modifiedEntries) {
		IPath entryPath= newEntry.getPath();
		for (int i= 0; i < existing.size(); i++) {
			IBuildpathEntry curr= (IBuildpathEntry) existing.get(i);
			IPath currPath= curr.getPath();
			if (curr.getEntryKind() == IBuildpathEntry.BPE_SOURCE && currPath.isPrefixOf(entryPath)) {
				IPath[] exclusionFilters= curr.getExclusionPatterns();
				if (!DLTKModelUtil.isExcludedPath(entryPath, exclusionFilters)) {
					IPath pathToExclude= entryPath.removeFirstSegments(currPath.segmentCount()).addTrailingSeparator();
					IPath[] newExclusionFilters= new IPath[exclusionFilters.length + 1];
					System.arraycopy(exclusionFilters, 0, newExclusionFilters, 0, exclusionFilters.length);
					newExclusionFilters[exclusionFilters.length]= pathToExclude;
					
					IBuildpathEntry updated= DLTKCore.newSourceEntry(currPath, newExclusionFilters);
					existing.set(i, updated);
					modifiedEntries.add(updated);
				}
			}
		}
	}	
	
	// ---- creation ----------------
	
	public IProjectFragment getNewProjectFragment() {
		return fCreatedRoot;
	}
	
	public IResource getCorrespondingResource() {
		return fCurrJProject.getProject().getFolder(fRootDialogField.getText());
	}
	
	public void createProjectFragment(IProgressMonitor monitor) throws CoreException, InterruptedException {
		if (monitor == null) {
			monitor= new NullProgressMonitor();
		}
		monitor.beginTask(NewWizardMessages.NewSourceFolderWizardPage_operation, 3); 
		try {
//			IPath projPath= fCurrJProject.getProject().getFullPath();			
			
			String relPath= fRootDialogField.getText();
				
			IFolder folder= fCurrJProject.getProject().getFolder(relPath);
			if (!folder.exists()) {
				CoreUtility.createFolder(folder, true, true, new SubProgressMonitor(monitor, 1));			
			}
			if (monitor.isCanceled()) {
				throw new InterruptedException();
			}
			
			fCurrJProject.setRawBuildpath(fNewEntries, new SubProgressMonitor(monitor, 2));
	
			fCreatedRoot= fCurrJProject.getProjectFragment(folder);
		} finally {
			monitor.done();
		}
	}
		
	// ------------- choose dialogs
	
	private IFolder chooseFolder(String title, String message, IPath initialPath) {	
		Class[] acceptedClasses= new Class[] { IFolder.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, false);
		ViewerFilter filter= new TypedViewerFilter(acceptedClasses, null);	
		
		ILabelProvider lp= new WorkbenchLabelProvider();
		ITreeContentProvider cp= new WorkbenchContentProvider();

		IProject currProject= fCurrJProject.getProject();

		ElementTreeSelectionDialog dialog= new ElementTreeSelectionDialog(getShell(), lp, cp);
		dialog.setValidator(validator);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.addFilter(filter);
		dialog.setInput(currProject);
		dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));
		IResource res= currProject.findMember(initialPath);
		if (res != null) {
			dialog.setInitialSelection(res);
		}

		if (dialog.open() == Window.OK) {
			return (IFolder) dialog.getFirstResult();
		}			
		return null;		
	}
	
	private IDLTKProject chooseProject() {
		IDLTKProject[] projects;
		try {
			projects= DLTKCore.create(fWorkspaceRoot).getScriptProjects();
		} catch (ModelException e) {
			DLTKUIPlugin.log(e);
			projects= new IDLTKProject[0];
		}
		
		ILabelProvider labelProvider= new ModelElementLabelProvider(ModelElementLabelProvider.SHOW_DEFAULT);
		ElementListSelectionDialog dialog= new ElementListSelectionDialog(getShell(), labelProvider);
		dialog.setTitle(NewWizardMessages.NewSourceFolderWizardPage_ChooseProjectDialog_title); 
		dialog.setMessage(NewWizardMessages.NewSourceFolderWizardPage_ChooseProjectDialog_description); 
		dialog.setElements(projects);
		dialog.setInitialSelections(new Object[] { fCurrJProject });
		dialog.setHelpAvailable(false);
		if (dialog.open() == Window.OK) {			
			return (IDLTKProject) dialog.getFirstResult();
		}			
		return null;		
	}
				
}
