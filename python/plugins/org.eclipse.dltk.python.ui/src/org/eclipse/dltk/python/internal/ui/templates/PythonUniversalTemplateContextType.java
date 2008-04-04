package org.eclipse.dltk.python.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.jface.text.IDocument;

public class PythonUniversalTemplateContextType extends
		ScriptTemplateContextType {

	public static final String CONTEXT_TYPE_ID = "pythonUniversalTemplateContextType";
	
	public PythonUniversalTemplateContextType() {
		// empty constructor
	}
	
	public PythonUniversalTemplateContextType(String id, String name) {
		super(id, name);
	}

	public PythonUniversalTemplateContextType(String id) {
		super(id);
	}

	public ScriptTemplateContext createContext(IDocument document,
			int completionPosition, int length, ISourceModule sourceModule) {
		return new PythonTemplateContext(this, document, completionPosition,
				length, sourceModule);
	}

}
