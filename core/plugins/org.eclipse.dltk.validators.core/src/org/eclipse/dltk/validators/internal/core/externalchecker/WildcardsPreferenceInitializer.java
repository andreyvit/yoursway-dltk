package org.eclipse.dltk.validators.internal.core.externalchecker;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.validators.internal.core.ValidatorsCore;

public class WildcardsPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public WildcardsPreferenceInitializer() {
	}

	public void initializeDefaultPreferences() {
		String xmlString = ExternalCheckerWildcardManager.getDefaultWildcards();
		ValidatorsCore.getDefault().getPluginPreferences().setDefault(ExternalCheckerWildcardManager.WILDCARDS, xmlString);
		ValidatorsCore.getDefault().savePluginPreferences();
	}
}
