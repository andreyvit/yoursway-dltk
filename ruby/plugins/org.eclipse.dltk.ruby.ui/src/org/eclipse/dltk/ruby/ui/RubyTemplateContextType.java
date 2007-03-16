package org.eclipse.dltk.ruby.ui;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ruby.internal.ui.ScriptContextType;
import org.eclipse.dltk.ruby.internal.ui.ScriptTemplateContext;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;

public class RubyTemplateContextType extends ScriptContextType {
	public static final String TEMPLATE_ID = "org.eclipse.dltk.ruby.ui.RubyTemplateContextType";

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
		super(TEMPLATE_ID);

		addGlobalResolvers();
	}

	public RubyTemplateContextType(String id) {
		super(id);

		addGlobalResolvers();
	}

	public ScriptTemplateContext createContext(IDocument document, int offset,
			int length, ISourceModule sourceModule) {

		return new RubyContext(this, document, offset, length, sourceModule);
	}
}
