package org.eclipse.dltk.javascript.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class JavaScriptPlugin extends Plugin {

	public static final String PLUGIN_ID = "org.eclipse.dltk.javascript.core";

	//The shared instance.
	private static JavaScriptPlugin plugin;
	
	/**
	 * The constructor.
	 */
	public JavaScriptPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static JavaScriptPlugin getDefault() {
		return plugin;
	}

}
