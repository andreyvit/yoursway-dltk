package org.eclipse.dltk.examples.internal.python.interpreter.ui;

import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.ScriptInterpreterPreferencePage;

public class ExamplePythonInterpreterPreferencePage extends
		ScriptInterpreterPreferencePage {
	public static final String PAGE_ID = "org.eclipse.dltk.python.examples.preferences.interpreters";

	public InterpretersBlock createInterpretersBlock() {
		return new ExamplePythonInterpretersBlock();
	}
}
