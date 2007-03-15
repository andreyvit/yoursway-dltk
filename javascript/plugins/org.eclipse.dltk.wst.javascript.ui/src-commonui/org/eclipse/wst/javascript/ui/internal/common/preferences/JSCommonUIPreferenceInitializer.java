package org.eclipse.wst.javascript.ui.internal.common.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.javascript.ui.internal.common.style.IStyleConstantsJavaScript;
import org.eclipse.wst.javascript.ui.internal.editor.JSEditorPlugin;
import org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper;

/**
 * Sets default values for JS Common UI preferences
 */
public class JSCommonUIPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JSEditorPlugin.getDefault().getPreferenceStore();

		// editor prefs
		store.setDefault(JSCommonUIPreferenceNames.AUTO_PROPOSE, true);
		store.setDefault(JSCommonUIPreferenceNames.AUTO_PROPOSE_CODE, ".");//$NON-NLS-1$

		// JavaScript Style Preferences
		String NOBACKGROUNDBOLD = " | null | false"; //$NON-NLS-1$
		String styleValue = "null" + NOBACKGROUNDBOLD; //$NON-NLS-1$
		store.setDefault(IStyleConstantsJavaScript.DEFAULT, styleValue);

		// keywords not "bold" at request of Usability
		styleValue = ColorHelper.getColorString(127, 0, 85) + NOBACKGROUNDBOLD;
		store.setDefault(IStyleConstantsJavaScript.KEYWORD, styleValue);

		styleValue = ColorHelper.getColorString(142, 0, 255) + NOBACKGROUNDBOLD;
		store.setDefault(IStyleConstantsJavaScript.LITERAL, styleValue);

		styleValue = ColorHelper.getColorString(63, 95, 191) + NOBACKGROUNDBOLD;
		store.setDefault(IStyleConstantsJavaScript.COMMENT, styleValue);

		styleValue = ColorHelper.getColorString(63, 95, 191) + " | null | true"; //$NON-NLS-1$
		store.setDefault(IStyleConstantsJavaScript.UNFINISHED_COMMENT, styleValue);

		store.setDefault(JSCommonUIPreferenceNames.INDENTATION_CHAR, JSCommonUIPreferenceNames.TAB);
		store.setDefault(JSCommonUIPreferenceNames.INDENTATION_SIZE, 1);
	}

}
