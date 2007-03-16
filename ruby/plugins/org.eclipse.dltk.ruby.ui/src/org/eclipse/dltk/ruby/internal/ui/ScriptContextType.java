package org.eclipse.dltk.ruby.internal.ui;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;

/**
 * A very simple context type.
 */
public abstract class ScriptContextType extends TemplateContextType {
	public ScriptContextType(String id) {
		super(id);
	}

	public abstract ScriptTemplateContext createContext(IDocument document,
			int completionPosition, int length, ISourceModule sourceModule);

	protected void validateVariables(TemplateVariable[] variables)
			throws TemplateException {
		// check for multiple cursor variables
		for (int i = 0; i < variables.length; i++) {
			TemplateVariable var = variables[i];
			if (var.getType().equals(GlobalTemplateVariables.Cursor.NAME)) {
				if (var.getOffsets().length > 1) {
					// TODO: localize
					throw new TemplateException("Invalid variable name");
				}
			}
		}
	}
}
