package org.eclipse.dltk.tcl.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.dltk.ui.templates.ScriptTemplateVariables;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;

public class TclUniversalTemplateContextType extends ScriptTemplateContextType {
	public static final String CONTEXT_TYPE_ID = "tclUniversalTemplateContextType";
		
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

	public TclUniversalTemplateContextType() {
		addGlobalResolvers();
	}
	
	public TclUniversalTemplateContextType(String id) {
		super(id);
		addGlobalResolvers();
	}
	
	public TclUniversalTemplateContextType(String id, String name) {
		super(id, name);
		addGlobalResolvers();
	}
	
	
		
	public ScriptTemplateContext createContext(IDocument document, int offset,
			int length, ISourceModule sourceModule) {
		return new TclTemplateContext(this, document, offset, length, sourceModule);
	}		
}
