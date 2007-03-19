package org.eclipse.dltk.ruby.internal.ui;

import java.io.IOException;

import org.eclipse.dltk.ruby.internal.ui.templates.RubyUniversalTemplateContextType;
import org.eclipse.dltk.ruby.internal.ui.text.RubyTextTools;
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

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

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
}
