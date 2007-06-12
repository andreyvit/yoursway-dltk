package org.eclipse.dltk.ruby.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;
import org.eclipse.dltk.internal.debug.ui.ScriptDebugHover;
import org.eclipse.jface.preference.IPreferenceStore;

public class RubyDebugHover extends ScriptDebugHover {

	protected ScriptDebugModelPresentation getModelPresentation() {
		return new RubyDebugModelPresentation();
	}

	public void setPreferenceStore(IPreferenceStore store) {

	}
}
