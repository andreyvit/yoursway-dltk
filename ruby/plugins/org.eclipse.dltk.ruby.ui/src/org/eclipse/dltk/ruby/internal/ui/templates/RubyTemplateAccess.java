/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.ruby.internal.ui.templates;

import java.io.IOException;

import org.eclipse.dltk.ruby.internal.ui.RubyUI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;

public class RubyTemplateAccess {
	// Template
	private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.ruby.Templates";
	
	private static RubyTemplateAccess instance;
	
	public static RubyTemplateAccess getInstance() {
		if (instance == null){
			instance = new RubyTemplateAccess();
		}
		
		return instance;
	}
	

	private TemplateStore fStore;

	private ContributionContextTypeRegistry fRegistry;
	
	public TemplateStore getTemplateStore() {
		if (fStore == null) {
			fStore = new ContributionTemplateStore(getContextTypeRegistry(),
					getPreferenceStore(), CUSTOM_TEMPLATES_KEY);
			try {
				fStore.load();
			} catch (IOException e) {
				// TODO: handle
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return fStore;
	}

	public ContextTypeRegistry getContextTypeRegistry() {
		if (fRegistry == null) {
			fRegistry = new ContributionContextTypeRegistry();
			fRegistry.addContextType(RubyUniversalTemplateContextType.CONTEXT_TYPE_ID);
		}

		return fRegistry;
	}
	
	protected IPreferenceStore getPreferenceStore() { 
		return RubyUI.getDefault().getPreferenceStore();
	}
}
