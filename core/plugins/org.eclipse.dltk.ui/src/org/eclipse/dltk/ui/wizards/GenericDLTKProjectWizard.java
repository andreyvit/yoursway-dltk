package org.eclipse.dltk.ui.wizards;

import java.util.Map;
import java.util.Observable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.ui.DLTKUILanguageManager;
import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.IDLTKUILanguageToolkit;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class GenericDLTKProjectWizard extends NewElementWizard implements
		INewWizard, IExecutableExtension {
	private ProjectWizardFirstPage fFirstPage;
	private ProjectWizardSecondPage fSecondPage;
	private IConfigurationElement fConfigElement;
	private String nature;
	private String preferencePageId;
	
	public GenericDLTKProjectWizard() {
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
		setWindowTitle(Messages.GenericDLTKProjectWizard_newDltkProject);
	}
	
	public String getNature() {
		return nature;
	}
	
	protected IPreferenceStore getPreferenceStoreFromNature() {
		IDLTKUILanguageToolkit languageToolkit = DLTKUILanguageManager.getLanguageToolkit(nature);
		if( languageToolkit != null ) {
			return languageToolkit.getPreferenceStore();
		}
		return null;
	}
	
	private class GenericDLTKBuildpathBlock extends BuildpathsBlock {

		public GenericDLTKBuildpathBlock(IRunnableContext runnableContext,
				IStatusChangeListener context, int pageToShow,
				boolean useNewPage, IWorkbenchPreferenceContainer pageContainer) {
			super(runnableContext, context, pageToShow, useNewPage, pageContainer);
		}

		protected IPreferenceStore getPreferenceStore() {
			return getPreferenceStoreFromNature();
		}

		protected boolean supportZips() {
			IDLTKLanguageToolkit languageToolkit = null;
			languageToolkit = DLTKLanguageManager.getLanguageToolkit(nature);
			if( languageToolkit != null ) {
				return languageToolkit.languageSupportZIPBuildpath();
			}
			return false;
		}		
	}
	public void addPages() {
		super.addPages();
		final String curNature = this.nature;
		final String curPreferencePageId = this.preferencePageId;
		fFirstPage = new ProjectWizardFirstPage() {

			GenericDLTKInterpreterGroup fInterpreterGroup;
			
			class GenericDLTKInterpreterGroup extends AbstractInterpreterGroup {
				public GenericDLTKInterpreterGroup(Composite composite) {
					super(composite);
				}

				protected String getCurrentLanguageNature() {
					return curNature;
				}

				protected String getIntereprtersPreferencePageId() {
					return curPreferencePageId;
				}
			};

			protected void createInterpreterGroup(Composite parent) {
				fInterpreterGroup = new GenericDLTKInterpreterGroup(parent);
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
				return false;
			}
		};

		// First page
		fFirstPage.setTitle(Messages.GenericDLTKProjectWizard_newDltkProject);
		fFirstPage
				.setDescription(Messages.GenericDLTKProjectWizard_createNewDltkProject);
		addPage(fFirstPage);

		// Second page
		fSecondPage = new ProjectWizardSecondPage(fFirstPage) {
			protected BuildpathsBlock createBuildpathBlock(
					IStatusChangeListener listener) {
				return new GenericDLTKBuildpathBlock(
						new BusyIndicatorRunnableContext(), listener, 0,
						useNewSourcePage(), null);
			}

			protected String getScriptNature() {
				return curNature;
			}

			protected IPreferenceStore getPreferenceStore() {
				return getPreferenceStoreFromNature();
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
		if( data instanceof String ) {
			this.nature = (String)data;
		}
		else if( data instanceof Map ) {
			this.nature = (String) ((Map)data).get("nature"); //$NON-NLS-1$
		}
		if( this.nature == null || this.nature.length() == 0 ) {
			throw new RuntimeException(Messages.GenericDLTKProjectWizard_natureMustBeSpecified);
		}
	}

	public boolean performCancel() {
		fSecondPage.performCancel();
		return super.performCancel();
	}

	public IModelElement getCreatedElement() {
		return DLTKCore.create(fFirstPage.getProjectHandle());
	}
}
