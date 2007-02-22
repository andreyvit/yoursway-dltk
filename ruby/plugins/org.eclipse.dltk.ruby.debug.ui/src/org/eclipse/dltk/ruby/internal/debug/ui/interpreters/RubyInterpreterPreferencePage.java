package org.eclipse.dltk.ruby.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.InterpreterPreferencePage;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;

public class RubyInterpreterPreferencePage extends InterpreterPreferencePage {

	public InterpretersBlock createInterpretersBlock() {
		return new RubyInterpretersBlock();
	}
}
