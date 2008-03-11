package org.eclipse.dltk.python.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.dltk.ui.templates.ScriptTemplateVariables;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;

public class PythonTemplateContextType extends ScriptTemplateContextType {

	public static final String CONTEXT_TYPE_ID = "pythonTemplateContextType";

	private void addGlobalResolvers() {
		addResolver(new GlobalTemplateVariables.Cursor());
		addResolver(new GlobalTemplateVariables.WordSelection());
		addResolver(new GlobalTemplateVariables.LineSelection());
		addResolver(new GlobalTemplateVariables.Dollar());
		addResolver(new GlobalTemplateVariables.Date());
		addResolver(new GlobalTemplateVariables.Year());
		addResolver(new GlobalTemplateVariables.Time());
		addResolver(new GlobalTemplateVariables.User());
		addResolver(new ScriptTemplateVariables.File());		
		addResolver(new ScriptTemplateVariables.Language());
	}
	
	public PythonTemplateContextType() {
		addGlobalResolvers();
	}
	
	public PythonTemplateContextType(String id) {
		super(id);
		addGlobalResolvers();
	}

	public PythonTemplateContextType(String id, String name) {
		super(id, name);
		addGlobalResolvers();
	}

	public ScriptTemplateContext createContext(IDocument document,
			int completionPosition, int length, ISourceModule sourceModule) {
		return new PythonTemplateContext(this,document,completionPosition,length,sourceModule);
	}

}
