package org.eclipse.dltk.ruby.internal.ui.properties;

import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public class RubyBuildPathPropertyPage extends BuildPathsPropertyPage implements IWorkbenchPropertyPage {
	public RubyBuildPathPropertyPage() {
	}

	protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer) {
		return new RubyBuildPathsBlock(new BusyIndicatorRunnableContext(), 
				this, getSettings().getInt(INDEX), false, pageContainer);
	}
}
