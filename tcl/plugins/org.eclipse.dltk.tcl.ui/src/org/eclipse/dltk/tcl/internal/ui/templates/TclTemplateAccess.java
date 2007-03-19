package org.eclipse.dltk.tcl.internal.ui.templates;

import java.io.IOException;

import org.eclipse.dltk.tcl.internal.ui.TclUI;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;

public class TclTemplateAccess {
	// Template
	private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.tcl.Templates";
	
	private static TclTemplateAccess instance;
	
	public static TclTemplateAccess getInstance() {
		if (instance == null){
			instance = new TclTemplateAccess();
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
			fRegistry.addContextType(TclUniversalTemplateContextType.CONTEXT_TYPE_ID);
		}

		return fRegistry;
	}
	
	protected IPreferenceStore getPreferenceStore() { 
		return TclUI.getDefault().getPreferenceStore();
	}
}
