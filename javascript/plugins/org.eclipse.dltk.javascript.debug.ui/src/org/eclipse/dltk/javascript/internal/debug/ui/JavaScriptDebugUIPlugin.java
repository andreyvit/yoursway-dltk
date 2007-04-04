package org.eclipse.dltk.javascript.internal.debug.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class JavaScriptDebugUIPlugin extends AbstractUIPlugin {

	public JavaScriptDebugUIPlugin() {
		plugin = this;
	}

	// The shared instance.
	private static JavaScriptDebugUIPlugin plugin;

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
	public static JavaScriptDebugUIPlugin getDefault() {
		return plugin;
	}
}
