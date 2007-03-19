package org.eclipse.dltk.ruby.ui;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.internal.ui.ScriptTemplateContextType;
import org.eclipse.dltk.ruby.internal.ui.ScriptTemplateContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateException;

public class RubyTemplateContextType extends ScriptTemplateContextType {
	public static final String CONTEXT_TYPE_ID = "org.eclipse.dltk.ruby.ui.RubyTemplateContextType";

	private void addGlobalResolvers() {
		addResolver(new GlobalTemplateVariables.Cursor());
		addResolver(new GlobalTemplateVariables.WordSelection());
		addResolver(new GlobalTemplateVariables.LineSelection());
		addResolver(new GlobalTemplateVariables.Dollar());
		addResolver(new GlobalTemplateVariables.Date());
		addResolver(new GlobalTemplateVariables.Year());
		addResolver(new GlobalTemplateVariables.Time());
		addResolver(new GlobalTemplateVariables.User());
	}

	public RubyTemplateContextType() {
		super(CONTEXT_TYPE_ID);

		addGlobalResolvers();
	}
	
	public ScriptTemplateContext createContext(IDocument document, int offset,
			int length, ISourceModule sourceModule) {
		return new RubyTemplateContext(this, document, offset, length, sourceModule);
	}		
}
