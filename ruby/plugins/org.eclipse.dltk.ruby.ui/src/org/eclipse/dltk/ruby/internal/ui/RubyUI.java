package org.eclipse.dltk.ruby.internal.ui;

import java.io.IOException;

import org.eclipse.dltk.ruby.internal.ui.text.RubyTextTools;
import org.eclipse.dltk.ruby.ui.RubyTemplateContextType;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RubyUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.dltk.ruby.ui";

	// The shared instance
	private static RubyUI plugin;

	private RubyTextTools fRubyTextTools;

	/**
	 * The constructor
	 */
	public RubyUI() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static RubyUI getDefault() {
		return plugin;
	}

	public RubyTextTools getTextTools() {
		if (fRubyTextTools == null) {
			fRubyTextTools = new RubyTextTools(true);
		}

		return fRubyTextTools;
	}

	// Template
	private static final String CUSTOM_TEMPLATES_KEY = "org.eclipse.ruby.Templates";

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
			fRegistry.addContextType(RubyTemplateContextType.CONTEXT_TYPE_ID);
		}

		return fRegistry;
	}
}
