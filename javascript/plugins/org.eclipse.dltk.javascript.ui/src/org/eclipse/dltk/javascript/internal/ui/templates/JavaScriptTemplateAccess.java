package org.eclipse.dltk.javascript.internal.ui.templates;

import java.io.IOException;

import org.eclipse.dltk.javascript.internal.ui.JavaScriptUI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;

public class JavaScriptTemplateAccess {
	// Template
	private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.ruby.Templates";
	
	private static JavaScriptTemplateAccess instance;
	
	public static JavaScriptTemplateAccess getInstance() {
		if (instance == null){
			instance = new JavaScriptTemplateAccess();
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
			fRegistry.addContextType(JavaScriptUniversalTemplateContextType.CONTEXT_TYPE_ID);
		}

		return fRegistry;
	}
	
	protected IPreferenceStore getPreferenceStore() { 
		return JavaScriptUI.getDefault().getPreferenceStore();
	}
}
