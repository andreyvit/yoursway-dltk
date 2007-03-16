package org.eclipse.dltk.ruby.ui;

import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateContextType;

public class RubyTemplateContext extends TemplateContextType {	
	public static final String TEMPLATE_ID = "org.eclipse.dltk.ruby.ui.RubyTemplateContext";

	public RubyTemplateContext() {
		addGlobalResolvers();
	}

	public RubyTemplateContext(String id) {
		super(id);
		addGlobalResolvers();
	}

	public RubyTemplateContext(String id, String name) {
		super(id, name);
		addGlobalResolvers();
	}
	
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

}
