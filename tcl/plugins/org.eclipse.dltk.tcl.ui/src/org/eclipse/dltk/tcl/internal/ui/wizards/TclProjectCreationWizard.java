/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.tcl.internal.ui.wizards;

import java.util.Observable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclImages;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewElementWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class TclProjectCreationWizard extends NewElementWizard implements
		INewWizard, IExecutableExtension {

	public static final String ID_WIZARD = "org.eclipse.dltk.tcl.internal.ui.wizards.TclProjectWizard"; //$NON-NLS-1$

	private ProjectWizardFirstPage fFirstPage;
	private ProjectWizardSecondPage fSecondPage;

	private IConfigurationElement fConfigElement;

	public TclProjectCreationWizard() {
		setDefaultPageImageDescriptor(TclImages.DESC_WIZBAN_PROJECT_CREATION);
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(TclWizardMessages.ProjectCreationWizard_title);
	}

	public void addPages() {
		super.addPages();
		fFirstPage = new ProjectWizardFirstPage() {
			TclInterpreterGroup fInterpreterGroup;

			final class TclInterpreterGroup extends AbstractInterpreterGroup {

				public TclInterpreterGroup(Composite composite) {
					super(composite);
				}

				protected String getCurrentLanguageNature() {
					return TclNature.NATURE_ID;
				}

				protected String getIntereprtersPreferencePageId() {
					return "org.eclipse.dltk.tcl.preferences.interpreters";
				}
			};

			protected void createInterpreterGroup(Composite parent) {
				fInterpreterGroup = new TclInterpreterGroup(parent);
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
		fFirstPage
				.setTitle(TclWizardMessages.ProjectCreationWizardFirstPage_title);
		fFirstPage
				.setDescription(TclWizardMessages.ProjectCreationWizardFirstPage_description);
		addPage(fFirstPage);
		fSecondPage = new TclProjectWizardSecondPage(fFirstPage);
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
