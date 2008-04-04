package org.eclipse.dltk.debug.ui.handlers;

import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.core.model.IScriptDebugTarget;

/**
 * Abstract handler implementation that can be used to toggle the display of
 * 'local' debugging variables.
 */
public abstract class AbstractToggleLocalVariableHandler extends
		AbstractToggleVariableHandler {

	/*
	 * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getVariableDisplayPreferenceKey()
	 */
	protected String getVariableDisplayPreferenceKey() {
		return DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL;
	}

	/*
	 * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#toggleVariableDisplay(org.eclipse.dltk.debug.core.model.IScriptDebugTarget,
	 *      boolean)
	 */
	protected final void toggleVariableDisplay(IScriptDebugTarget target,
			boolean enabled) {
		target.toggleLocalVariables(enabled);
	}

}
