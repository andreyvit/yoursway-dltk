package org.eclipse.dltk.ruby.internal.debug.ui.actions;

import org.eclipse.dltk.debug.ui.actions.AddExceptionAction;
import org.eclipse.dltk.ruby.debug.RubyDebugConstants;

public class AddRubyExceptionAction extends AddExceptionAction {
	protected String getDebugModelId() {
		return RubyDebugConstants.DEBUG_MODEL_ID;
	}
}
