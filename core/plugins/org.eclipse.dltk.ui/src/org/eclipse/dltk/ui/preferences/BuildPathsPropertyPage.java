/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ui.preferences;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.internal.ui.actions.WorkbenchRunnableAdapter;
import org.eclipse.dltk.internal.ui.dialogs.StatusUtil;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.ExceptionHandler;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


/**
 * Property page for configuring the DLTK build path
 */
public abstract class BuildPathsPropertyPage extends PropertyPage implements IStatusChangeListener {
	
	public static final String PROP_ID= "org.eclipse.dltk.ui.propertyPages.BuildPathsPropertyPage"; //$NON-NLS-1$
		
	private static final String PAGE_SETTINGS= "BuildPathsPropertyPage"; //$NON-NLS-1$
	protected static final String INDEX= "pageIndex"; //$NON-NLS-1$

	public static final Object DATA_ADD_ENTRY= "add_buildpath_entry"; //$NON-NLS-1$
	
	public static final Object DATA_REVEAL_ENTRY= "select_buildpath_entry"; //$NON-NLS-1$
	public static final Object DATA_REVEAL_ATTRIBUTE_KEY= "select_buildpath_attribute_key"; //$NON-NLS-1$
	
	public static final Object DATA_BLOCK= "block_until_buildpath_applied"; //$NON-NLS-1$
		
	private BuildpathsBlock fBuildPathsBlock;
	private boolean fBlockOnApply= false;
	
	/*
	 * @see PreferencePage#createControl(Composite)
	 */
	protected Control createContents(Composite parent) {
		// ensure the page has no special buttons
		noDefaultAndApplyButton();		
		
		IProject project= getProject();
		Control result;
		if (project == null || !isDLTKProject(project)) {
			result= createWithoutScript(parent);
		} else if (!project.isOpen()) {
			result= createForClosedProject(parent);
		} else {
			result= createWith(parent, project);
		}
		Dialog.applyDialogFont(result);
		return result;
	}
	
	/*
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		//PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IHelpContextIds.BUILD_PATH_PROPERTY_PAGE);
	}	
	
	protected IDialogSettings getSettings() {
		IDialogSettings javaSettings= DLTKUIPlugin.getDefault().getDialogSettings();
		IDialogSettings pageSettings= javaSettings.getSection(PAGE_SETTINGS);
		if (pageSettings == null) {
			pageSettings= javaSettings.addNewSection(PAGE_SETTINGS);
			pageSettings.put(INDEX, 3);
		}
		return pageSettings;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		if (fBuildPathsBlock != null) {
			if (!visible) {
				if (fBuildPathsBlock.hasChangesInDialog()) {
					String title= PreferencesMessages.BuildPathsPropertyPage_unsavedchanges_title; 
					String message= PreferencesMessages.BuildPathsPropertyPage_unsavedchanges_message; 
					String[] buttonLabels= new String[] {
							PreferencesMessages.BuildPathsPropertyPage_unsavedchanges_button_save, 
							PreferencesMessages.BuildPathsPropertyPage_unsavedchanges_button_discard, 
							PreferencesMessages.BuildPathsPropertyPage_unsavedchanges_button_ignore
					};
					MessageDialog dialog= new MessageDialog(getShell(), title, null, message, MessageDialog.QUESTION, buttonLabels, 0);
					int res= dialog.open();
					if (res == 0) {
						performOk();
					} else if (res == 1) {
						fBuildPathsBlock.init(DLTKCore.create(getProject()), null);
					} else {
						// keep unsaved
					}
				}
			} else {
				if (!fBuildPathsBlock.hasChangesInDialog() && fBuildPathsBlock.hasChangesInBuildpathFile()) {
					fBuildPathsBlock.init(DLTKCore.create(getProject()), null);
				}
			}
		}
		super.setVisible(visible);
	}
	
	
	/*
	 * Content for valid projects.
	 */
	private Control createWith(Composite parent, IProject project) {
		IWorkbenchPreferenceContainer pageContainer= null;	
		IPreferencePageContainer container= getContainer();
		if (container instanceof IWorkbenchPreferenceContainer) {
			pageContainer= (IWorkbenchPreferenceContainer) container;
		}
		
		fBuildPathsBlock = createBuildPathBlock(pageContainer);
		fBuildPathsBlock.init(DLTKCore.create(project), null);
		return fBuildPathsBlock.createControl(parent);
	}

	//fBuildPathsBlock= new BuildPathsBlock(new BusyIndicatorRunnableContext(),this, getSettings().getInt(INDEX), false, pageContainer);
	protected abstract BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer);

	/*
	 * Content for non-script projects.
	 */	
	private Control createWithoutScript(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(PreferencesMessages.BuildPathsPropertyPage_no_script_project_message); 
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}

	/*
	 * Content for closed projects.
	 */		
	private Control createForClosedProject(Composite parent) {
		Label label= new Label(parent, SWT.LEFT);
		label.setText(PreferencesMessages.BuildPathsPropertyPage_closed_project_message); 
		
		fBuildPathsBlock= null;
		setValid(true);
		return label;
	}
	
	private IProject getProject() {
		IAdaptable adaptable= getElement();
		if (adaptable != null) {
			IModelElement elem= (IModelElement) adaptable.getAdapter(IModelElement.class);
			if (elem instanceof IScriptProject) {
				return ((IScriptProject) elem).getProject();
			}
		}
		return null;
	}
	
	private boolean isDLTKProject(IProject proj) {		
		return DLTKLanguageManager.hasScriptNature(proj);		
	}
	
	/*
	 * @see IPreferencePage#performOk
	 */
	public boolean performOk() {
		if (fBuildPathsBlock != null) {
			getSettings().put(INDEX, fBuildPathsBlock.getPageIndex());
			if (fBuildPathsBlock.hasChangesInDialog()) {
				IWorkspaceRunnable runnable= new IWorkspaceRunnable() {
					public void run(IProgressMonitor monitor)	throws CoreException, OperationCanceledException {
						fBuildPathsBlock.configureDLTKProject(monitor);
					}
				};
				WorkbenchRunnableAdapter op= new WorkbenchRunnableAdapter(runnable);
				if (fBlockOnApply) {
					try {
						new ProgressMonitorDialog(getShell()).run(true, true, op);
					} catch (InvocationTargetException e) {
						ExceptionHandler.handle(e, getShell(), PreferencesMessages.BuildPathsPropertyPage_error_title, PreferencesMessages.BuildPathsPropertyPage_error_message);
						return false;
					} catch (InterruptedException e) {
						return false;
					}
				} else {
					op.runAsUserJob(PreferencesMessages.BuildPathsPropertyPage_job_title, null);
				}
			}
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see IStatusChangeListener#statusChanged
	 */
	public void statusChanged(IStatus status) {
		setValid(!status.matches(IStatus.ERROR));
		StatusUtil.applyToStatusLine(this, status);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#applyData(java.lang.Object)
	 */
	public void applyData(Object data) {
		if (data instanceof Map) {
			Map map= (Map) data;
			Object selectedLibrary= map.get(DATA_REVEAL_ENTRY);
			if (selectedLibrary instanceof IBuildpathEntry) {
				IBuildpathEntry entry= (IBuildpathEntry) selectedLibrary;
				Object attr= map.get(DATA_REVEAL_ATTRIBUTE_KEY);
				String attributeKey= attr instanceof String ? (String) attr : null;
				if (fBuildPathsBlock != null) {
					fBuildPathsBlock.setElementToReveal(entry, attributeKey);
				}
			}
			Object entryToAdd= map.get(DATA_ADD_ENTRY);
			if (entryToAdd instanceof IBuildpathEntry) {
				if (fBuildPathsBlock != null) {
					fBuildPathsBlock.addElement((IBuildpathEntry) entryToAdd);
				}
			}
			fBlockOnApply= Boolean.TRUE.equals(map.get(DATA_BLOCK));
		}
	}

}
