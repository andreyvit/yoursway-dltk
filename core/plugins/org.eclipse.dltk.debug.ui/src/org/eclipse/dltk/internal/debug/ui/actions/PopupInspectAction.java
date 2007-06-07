package org.eclipse.dltk.internal.debug.ui.actions;

import org.eclipse.debug.ui.DebugPopup;
import org.eclipse.debug.ui.InspectPopupDialog;
import org.eclipse.dltk.debug.ui.DLTKDebugUIPlugin;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class PopupInspectAction extends CommonEvaluationAction {

	public static final String ACTION_DEFININITION_ID = "org.eclipse.jdt.debug.ui.commands.Inspect"; //$NON-NLS-1$

	private ScriptInspectExpression expression;

	private void showPopup(StyledText textWidget) {
		DebugPopup displayPopup = new InspectPopupDialog(getShell(),
				getPopupAnchor(textWidget), ACTION_DEFININITION_ID, expression);
		displayPopup.open();
	}

	/**
	 * @see EvaluateAction#displayResult(IEvaluationResult)
	 */
	protected void displayResult(final IScriptEvaluationResult result) {
		IWorkbenchPart part = getPart();
		final StyledText styledText = getStyledText(part);

		if (styledText != null) {
			expression = new ScriptInspectExpression(result.getSnippet(),
					result.getValue());
			DLTKDebugUIPlugin.getStandardDisplay().asyncExec(new Runnable() {
				public void run() {
					showPopup(styledText);
				}
			});
		}

		evaluationCleanup();
	}
}
