package org.eclipse.dltk.debug.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.AbstractRulerActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

public class ScriptBreakpointPropertiesRulerActionDelegate  extends AbstractRulerActionDelegate {
	protected IAction createAction(ITextEditor editor,
			IVerticalRulerInfo rulerInfo) {
		return new ScriptBreakpointPropertiesRulerAction(editor, rulerInfo);		
	}
}
