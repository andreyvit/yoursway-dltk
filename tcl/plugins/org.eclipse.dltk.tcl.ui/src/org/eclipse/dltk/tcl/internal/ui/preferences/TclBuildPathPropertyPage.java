package org.eclipse.dltk.tcl.internal.ui.preferences;

import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class TclBuildPathPropertyPage extends BuildPathsPropertyPage implements
		IWorkbenchPropertyPage {
	public TclBuildPathPropertyPage() {
	}

	protected BuildpathsBlock createBuildPathBlock(
			IWorkbenchPreferenceContainer pageContainer) {
		return new TclBuildPathsBlock(new BusyIndicatorRunnableContext(), this,
				getSettings().getInt(INDEX), false, pageContainer);
	}
}
