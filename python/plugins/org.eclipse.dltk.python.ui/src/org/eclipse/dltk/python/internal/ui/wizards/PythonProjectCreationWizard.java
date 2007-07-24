/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.python.internal.ui.wizards;

import java.util.Observable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.python.core.PythonNature;
import org.eclipse.dltk.python.internal.ui.PythonUI;
import org.eclipse.dltk.python.internal.ui.preferences.PythonBuildPathsBlock;
import org.eclipse.dltk.ui.DLTKPluginImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.NewElementWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class PythonProjectCreationWizard extends NewElementWizard implements
		INewWizard, IExecutableExtension {
	private ProjectWizardFirstPage fFirstPage;
	private ProjectWizardSecondPage fSecondPage;
	private IConfigurationElement fConfigElement;

	public PythonProjectCreationWizard() {
		setDefaultPageImageDescriptor(DLTKPluginImages.DESC_WIZBAN_NEWPRJ);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(PythonWizardMessages.ProjectCreationWizard_title);
	}

	public void addPages() {
		super.addPages();
		fFirstPage = new ProjectWizardFirstPage() {

			PythonInterpreterGroup fInterpreterGroup;
        	
        	final class PythonInterpreterGroup extends AbstractInterpreterGroup {
        		
        		public PythonInterpreterGroup(Composite composite) {
        			super (composite);
        		}

				protected String getCurrentLanguageNature() {
					return PythonNature.NATURE_ID;
				}
				
				protected String getIntereprtersPreferencePageId() {
					return "org.eclipse.dltk.python.preferences.interpreters";
				}
            };
        	
			protected void createInterpreterGroup(Composite parent) {
				fInterpreterGroup = new PythonInterpreterGroup(parent);
			}

			protected Observable getInterpreterGroupObservable() {
				return fInterpreterGroup;
			}

			protected boolean supportInterpreter() {
				return true;
			}

			protected IInterpreterInstall getInterpreter() {
				return fInterpreterGroup.getSelectedInterpreter();
			}

			protected void handlePossibleInterpreterChange() {
				fInterpreterGroup.handlePossibleInterpreterChange();
			}

			protected boolean interpeterRequired() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		fFirstPage.setTitle(PythonWizardMessages.ProjectCreationWizardFirstPage_title);
		fFirstPage.setDescription(PythonWizardMessages.ProjectCreationWizardFirstPage_description);
		addPage(fFirstPage);
		fSecondPage = new ProjectWizardSecondPage(fFirstPage) {
			protected BuildpathsBlock createBuildpathBlock(IStatusChangeListener listener) {
				return new PythonBuildPathsBlock(new BusyIndicatorRunnableContext(), listener, 0, useNewSourcePage(), null);
			}

			protected String getScriptNature() {
				return PythonNature.NATURE_ID;
			}

			protected IPreferenceStore getPreferenceStore() {
				return PythonUI.getDefault().getPreferenceStore();
			}
		};
		addPage(fSecondPage);
	}

	protected void finishPage(IProgressMonitor monitor)
			throws InterruptedException, CoreException {
		fSecondPage.performFinish(monitor); // use the full progress monitor
	}

	public boolean performFinish() {
		boolean res = super.performFinish();
		if (res) {
			BasicNewProjectResourceWizard.updatePerspective(fConfigElement);
			selectAndReveal(fSecondPage.getScriptProject().getProject());
		}
		return res;
	}

	/*
	 * Stores the configuration element for the wizard. The config element will
	 * be used in <code>performFinish</code> to set the result perspective.
	 */
	public void setInitializationData(IConfigurationElement cfig,
			String propertyName, Object data) {
		fConfigElement = cfig;
	}

	public boolean performCancel() {
		fSecondPage.performCancel();
		return super.performCancel();
	}

	public IModelElement getCreatedElement() {
		return DLTKCore.create(fFirstPage.getProjectHandle());
	}
}
