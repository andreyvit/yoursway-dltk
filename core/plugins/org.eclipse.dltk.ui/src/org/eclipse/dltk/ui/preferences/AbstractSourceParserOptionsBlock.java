package org.eclipse.dltk.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.dltk.ast.parser.SourceParserManager;
import org.eclipse.dltk.core.DLTKContributionExtensionManager;
import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

public abstract class AbstractSourceParserOptionsBlock extends
		DLTKContributedExtensionOptionsBlock {

	public AbstractSourceParserOptionsBlock(IStatusChangeListener context,
			IProject project, PreferenceKey[] allKeys,
			IWorkbenchPreferenceContainer container) {
		super(context, project, allKeys, container);
	}

	 protected DLTKContributionExtensionManager getExtensionManager()
     {
         return SourceParserManager.getInstance();
     }

    protected String getSelectorGroupLabel()
    {
        return PreferencesMessages.SourceParsers_groupLabel;
    }

    protected String getSelectorNameLabel()
    {
      return PreferencesMessages.SourceParsers_nameLabel;
    }
}
