package org.eclipse.dltk.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;

public class ScriptTemplateContext extends DocumentTemplateContext {
	private ISourceModule sourceModule;

	protected ScriptTemplateContext(TemplateContextType type,
			IDocument document, int completionOffset, int completionLength,
			ISourceModule sourceModule) {
		super(type, document, completionOffset, completionLength);

		if (sourceModule == null) {
			throw new IllegalArgumentException();
		}

		this.sourceModule = sourceModule;
	}

	public final ISourceModule getSourceModule() {
		return sourceModule;
	}
}
