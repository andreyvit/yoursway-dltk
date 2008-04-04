package org.eclipse.dltk.examples.python.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.examples.python.internal.ui.editor.text.IExamplePythonColorConstants;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.EditorsUI;

public class ExamplePythonUIPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = ExamplePythonUI.getDefault()
				.getPreferenceStore();

		EditorsUI.useAnnotationsPreferencePage(store);
		EditorsUI.useQuickDiffPreferencePage(store);

		// Initialize DLTK default values
		PreferenceConstants.initializeDefaultValues(store);

		// Initialize python constants
		PreferenceConverter.setDefault(store,
				IExamplePythonColorConstants.PYTHON_COMMENT, new RGB(63, 127,
						95));
		PreferenceConverter.setDefault(store,
				IExamplePythonColorConstants.PYTHON_KEYWORD,
				new RGB(127, 0, 85));
		PreferenceConverter
				.setDefault(store, IExamplePythonColorConstants.PYTHON_STRING,
						new RGB(42, 0, 255));

		store.setDefault(IExamplePythonColorConstants.PYTHON_COMMENT
				+ PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(IExamplePythonColorConstants.PYTHON_COMMENT
				+ PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);

		store.setDefault(IExamplePythonColorConstants.PYTHON_KEYWORD
				+ PreferenceConstants.EDITOR_BOLD_SUFFIX, true);
		store.setDefault(IExamplePythonColorConstants.PYTHON_KEYWORD
				+ PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);

		store.setDefault(PreferenceConstants.EDITOR_TAB_WIDTH, 8);
		store.setDefault(
				PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);

		// folding
		store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);

		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR,
				CodeFormatterConstants.TAB);
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "8");
		store
				.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE,
						"8");
	}
}
