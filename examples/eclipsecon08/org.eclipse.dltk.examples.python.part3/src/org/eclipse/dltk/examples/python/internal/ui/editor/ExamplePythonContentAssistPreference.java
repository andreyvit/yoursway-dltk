package org.eclipse.dltk.examples.python.internal.ui.editor;

import org.eclipse.dltk.examples.python.internal.ExamplePythonUI;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

public class ExamplePythonContentAssistPreference extends
		ContentAssistPreference {

	private static ExamplePythonContentAssistPreference instance;

	public static ContentAssistPreference getDefault() {
		if (instance == null) {
			instance = new ExamplePythonContentAssistPreference();
		}
		return instance;
	}

	protected ScriptTextTools getTextTools() {
		return ExamplePythonUI.getDefault().getTextTools();
	}
}
