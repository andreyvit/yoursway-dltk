/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;
import org.eclipse.dltk.ui.templates.ScriptTemplateVariables;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;

public class RubyUniversalTemplateContextType extends ScriptTemplateContextType {
	public static final String CONTEXT_TYPE_ID = "rubyUniversalTemplateContextType";
	
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

	public RubyUniversalTemplateContextType() {		
		addGlobalResolvers();
	}
	
	public RubyUniversalTemplateContextType(String id) {
		super(id);
		addGlobalResolvers();
	}
	
	public RubyUniversalTemplateContextType(String id, String name) {
		super(id, name);
		addGlobalResolvers();
	}
	
	public ScriptTemplateContext createContext(IDocument document, int offset,
			int length, ISourceModule sourceModule) {
		return new RubyTemplateContext(this, document, offset, length, sourceModule);
	}		
}
