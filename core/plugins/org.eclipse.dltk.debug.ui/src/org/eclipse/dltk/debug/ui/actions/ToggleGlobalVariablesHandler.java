package org.eclipse.dltk.debug.ui.actions;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;


public class ToggleGlobalVariablesHandler extends AbstractHandler {

	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		IEvaluationContext context = (IEvaluationContext) arg0.getApplicationContext();

			
		Object editor = context.getVariable(ISources.ACTIVE_EDITOR_NAME);
		if (editor instanceof ScriptEditor)
		{
			IEditorInput input =  ((IEditorPart) editor).getEditorInput();
			IScriptProject project = EditorUtility.getScriptProject(input);
			
			
			
		}
		
		// TODO Auto-generated method stub
		return null;
	}

}
