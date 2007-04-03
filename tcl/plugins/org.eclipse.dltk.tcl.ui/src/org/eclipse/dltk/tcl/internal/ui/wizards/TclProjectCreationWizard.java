package org.eclipse.dltk.tcl.internal.ui.wizards;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.debug.ui.interpreters.TclInterpreterPreferencePage;
import org.eclipse.dltk.tcl.internal.ui.TclImages;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.preferences.TclBuildPathsBlock;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.NewElementWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
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

				protected void showInterpreterPreferencePage() {
					IPreferencePage page = new TclInterpreterPreferencePage();
					DLTKDebugUIPlugin
							.showPreferencePage(
									"org.eclipse.dltk.tcl.debug.ui.interpreters.TclInterpreterPreferencePage",
									page);
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
				return fInterpreterGroup.getSelectedJInterpreter();
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
		fSecondPage = new ProjectWizardSecondPage(fFirstPage) {
			protected BuildpathsBlock createBuildpathBlock(
					IStatusChangeListener listener) {
				return new TclBuildPathsBlock(
						new BusyIndicatorRunnableContext(), listener, 0,
						useNewSourcePage(), null);
			}

			protected String getScriptNature() {
				return TclNature.NATURE_ID;
			}

			protected IPreferenceStore getPreferenceStore() {
				return TclUI.getDefault().getPreferenceStore();
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
			selectAndReveal(fSecondPage.getDLTKProject().getProject());
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
