package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.debug.core.model.IExpression;
import org.eclipse.debug.ui.DebugPopup;
import org.eclipse.debug.ui.InspectPopupDialog;
import org.eclipse.dltk.debug.core.eval.EvaluatedScriptExpression;
import org.eclipse.dltk.debug.core.eval.IScriptEvaluationResult;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IWorkbenchPart;

public class PopupScriptInspectAction extends ScriptInspectAction {
	private void showPopup(StyledText textWidget, IExpression expression) {
		// TODO: add real command id
		DebugPopup displayPopup = new InspectPopupDialog(getShell(),
				getPopupAnchor(textWidget), null, expression);
		displayPopup.open();
	}

	protected void displayResult(final IScriptEvaluationResult result) {
		IWorkbenchPart part = getPart();
		final StyledText styledText = getStyledText(part);

		if (styledText != null) {
			final IExpression expression = new EvaluatedScriptExpression(result);
			DLTKDebugUIPlugin.getStandardDisplay().asyncExec(new Runnable() {
				public void run() {
					showPopup(styledText, expression);
				}
			});
		}

		evaluationCleanup();
	}
}
