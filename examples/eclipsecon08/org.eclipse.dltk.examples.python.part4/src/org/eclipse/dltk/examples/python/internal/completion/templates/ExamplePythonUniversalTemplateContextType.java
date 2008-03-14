package org.eclipse.dltk.examples.python.internal.completion.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;

public class ExamplePythonUniversalTemplateContextType extends
		ScriptTemplateContextType {

	public static final String CONTEXT_TYPE_ID = "examplePythonUniversalTemplateContextType";
	
	public ExamplePythonUniversalTemplateContextType() {
	}
	
	public ExamplePythonUniversalTemplateContextType(String id, String name) {
		super(id, name);
	}

	public ExamplePythonUniversalTemplateContextType(String id) {
		super(id);
	}

	public ScriptTemplateContext createContext(IDocument document,
			int completionPosition, int length, ISourceModule sourceModule) {
		return new ExamplePythonTemplateContext(this, document, completionPosition,
				length, sourceModule);
	}
}
