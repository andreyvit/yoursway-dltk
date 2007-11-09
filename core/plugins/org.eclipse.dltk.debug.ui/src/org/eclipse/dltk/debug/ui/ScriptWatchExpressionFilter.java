package org.eclipse.dltk.debug.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.actions.IWatchExpressionFactoryAdapterExtension;
import org.eclipse.dltk.debug.core.model.IScriptValue;

public class ScriptWatchExpressionFilter implements
		IWatchExpressionFactoryAdapterExtension {

	public boolean canCreateWatchExpression(IVariable variable) {
		return true;
	}

	public String createWatchExpression(IVariable variable)
			throws CoreException {
		IScriptValue v = (IScriptValue) variable.getValue();
		return v.getEvalName();
	}
}
