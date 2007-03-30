package org.eclipse.dltk.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

/**
 * A very simple context type.
 */
public abstract class ScriptTemplateContextType extends TemplateContextType {

	public ScriptTemplateContextType() {
	}

	public ScriptTemplateContextType(String id) {
		super(id);
	}

	public ScriptTemplateContextType(String id, String name) {
		super(id, name);
	}

	public abstract ScriptTemplateContext createContext(IDocument document,
			int completionPosition, int length, ISourceModule sourceModule);

	protected void validateVariables(TemplateVariable[] variables)
			throws TemplateException {
		// Check for multiple cursor variables
		for (int i = 0; i < variables.length; i++) {
			TemplateVariable var = variables[i];
			if (var.getType().equals(GlobalTemplateVariables.Cursor.NAME)) {
				if (var.getOffsets().length > 1) {					
					throw new TemplateException(
							TemplateMessages.Validation_SeveralCursorPositions);
				}
			}
		}
	}
}
