/**
 * 
 */
package org.eclipse.dltk.tcl.internal.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.wizards.BuildpathDetector;
import org.eclipse.dltk.tcl.core.TclNature;
import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.dltk.tcl.internal.ui.preferences.TclBuildPathsBlock;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;
import org.eclipse.jface.preference.IPreferenceStore;

final class TclProjectWizardSecondPage extends ProjectWizardSecondPage {
	TclProjectWizardSecondPage(ProjectWizardFirstPage mainPage) {
		super(mainPage);
	}

	protected BuildpathsBlock createBuildpathBlock(
			IStatusChangeListener listener) {
		return new TclBuildPathsBlock(new BusyIndicatorRunnableContext(),
				listener, 0, useNewSourcePage(), null);
	}

	protected String getScriptNature() {
		return TclNature.NATURE_ID;
	}

	protected IPreferenceStore getPreferenceStore() {
		return TclUI.getDefault().getPreferenceStore();
	}

	protected BuildpathDetector createBuildpathDetector(
			IProgressMonitor monitor, IDLTKLanguageToolkit toolkit)
			throws CoreException {
		TclBuildpathDetector detector = new TclBuildpathDetector(
				getCurrProject(), toolkit);
		detector.detectBuildpath(new SubProgressMonitor(monitor, 20));
		return detector;
	}

}